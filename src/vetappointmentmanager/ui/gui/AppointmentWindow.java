/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vetappointmentmanager.ui.gui;

/**
 *
 * @author hzvol
 */

import vetappointmentmanager.exception.*;
import vetappointmentmanager.model.*;
import vetappointmentmanager.service.*;
import javax.swing.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class AppointmentWindow extends WindowBase {
    private AppointmentService appointmentService;
    private ClientService clientService;

    public AppointmentWindow(AppointmentService appointmentService, ClientService clientService) {
        super("Gestionar Citas");
        this.appointmentService = appointmentService;
        this.clientService = clientService;
    }

    @Override
    protected void initComponents() {
        JButton btnAdd = new JButton("Agregar Cita");
        JButton btnList = new JButton("Listar Citas");
        JButton btnEdit = new JButton("Editar Cita");
        JButton btnDelete = new JButton("Eliminar Cita");
        JButton btnSearch = new JButton("Buscar Cita");
        JButton btnFilter = new JButton("Filtrar por Estado");
        JButton btnBack = new JButton("Volver");

        btnAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addAppointment();
            }
        });

        btnList.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                listAppointments();
            }
        });

        btnEdit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                editAppointment();
            }
        });

        btnDelete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deleteAppointment();
            }
        });

        btnSearch.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                searchAppointment();
            }
        });

        btnFilter.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                filterByStatus();
            }
        });

        btnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        add(btnAdd);
        add(btnList);
        add(btnEdit);
        add(btnDelete);
        add(btnSearch);
        add(btnFilter);
        add(btnBack);
    }

    private void addAppointment() {
        try {
            String rut = askInput("Ingrese RUT del cliente:");
            if (rut == null) return;
            Client client = clientService.searchClient(rut);
            if (client == null) return;

            String petList = "";
            for (Pet p : client.getPets()) {
                petList += p.toString() + "\n---------------------------\n";
            }
            showMessage(petList);

            String petIdStr = askInput("Ingrese ID de la mascota:");
            if (petIdStr == null) return;
            int petId = Integer.parseInt(petIdStr);
            Pet pet = clientService.searchPet(rut, petId);

            String dateStr = askInput("Ingrese fecha (YYYY-MM-DD):");
            if (dateStr == null) return;
            LocalDate date = LocalDate.parse(dateStr);

            String[] types = {"Consulta Veterinaria", "Peluqueria"};
            int typeOption = JOptionPane.showOptionDialog(null, "Tipo de servicio:",
                    "Servicio", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
                    null, types, types[0]);
            AppointmentType type;
            if (typeOption == 0) {
                type = AppointmentType.CONSULTATION;
            } else {
                type = AppointmentType.GROOMING;
            }

            String observation = askInput("Ingrese observacion:");
            if (observation == null) return;

            appointmentService.addAppointment(client, pet, date, type, observation);
            showMessage("Cita agregada correctamente !");

        } catch (InvalidAppointmentException e) {
            showMessage("Error: " + e.getMessage());
        } catch (PetNotFoundException e) {
            showMessage("Error: " + e.getMessage());
        } catch (NumberFormatException e) {
            showMessage("Error: ingrese un numero valido.");
        } catch (DateTimeParseException e) {
            showMessage("Error: formato de fecha invalido. Use YYYY-MM-DD.");
        }
    }

    private void listAppointments() {
        if (appointmentService.getAppointments().isEmpty()) {
            showMessage("No hay citas registradas.");
            return;
        }
        String result = "";
        for (Appointment a : appointmentService.getAppointments()) {
            result += a.toString() + "\n---------------------------\n";
        }
        JTextArea textArea = new JTextArea(result);
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new java.awt.Dimension(400, 300));
        JOptionPane.showMessageDialog(null, scrollPane, "Lista de Citas", JOptionPane.PLAIN_MESSAGE);
    }

    private void editAppointment() {
        try {
            String idStr = askInput("Ingrese ID de la cita a editar:");
            if (idStr == null) return;
            int id = Integer.parseInt(idStr);

            String dateStr = askInput("Ingresa nueva fecha (YYYY-MM-DD):");
            if (dateStr == null) return;
            LocalDate date = LocalDate.parse(dateStr);

            String[] types = {"Consulta Veterinaria", "Peluqueria"};
            int typeOption = JOptionPane.showOptionDialog(null, "Tipo de servicio:",
                    "Servicio", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
                    null, types, types[0]);
            AppointmentType type;
            if (typeOption == 0) {
                type = AppointmentType.CONSULTATION;
            } else {
                type = AppointmentType.GROOMING;
            }

            String observation = askInput("Ingrese nueva observación:");
            if (observation == null) return;

            appointmentService.editAppointment(id, date, type, observation);
            showMessage("Cita actualizada correctamente!");

        } catch (InvalidAppointmentException e) {
            showMessage("Error: " + e.getMessage());
        } catch (NumberFormatException e) {
            showMessage("Error: ingrese un numero valido.");
        } catch (DateTimeParseException e) {
            showMessage("Error: formato de fecha invalido. Use YYYY-MM-DD.");
        }
    }

    private void deleteAppointment() {
        try {
            if (!askConfirm("Eliminar cita?")) return;
            String idStr = askInput("Ingrese ID de la cita a eliminar:");
            if (idStr == null) return;
            int id = Integer.parseInt(idStr);
            appointmentService.deleteAppointment(id);
            showMessage("Cita eliminada correctamente!");
        } catch (InvalidAppointmentException e) {
            showMessage("Error: " + e.getMessage());
        } catch (NumberFormatException e) {
            showMessage("Error: ingrese un numero valido.");
        }
    }

    private void searchAppointment() {
        try {
            String idStr = askInput("Ingrese ID de la cita a buscar:");
            if (idStr == null) return;
            int id = Integer.parseInt(idStr);
            Appointment appointment = appointmentService.searchAppointment(id);
            if (appointment != null) {
                showMessage(appointment.toString());
            }
        } catch (NumberFormatException e) {
            showMessage("Error: ingrese un numero valido.");
        }
    }

    private void filterByStatus() {
        String[] options = {"Pendiente", "Completado", "Cancelado"};
        int option = JOptionPane.showOptionDialog(null, "Filtrar por estado:",
                "Estado", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
                null, options, options[0]);
        
        AppointmentStatus status;
        if (option == 0) {
            status = AppointmentStatus.PENDING;
        } else if (option == 1) {
            status = AppointmentStatus.COMPLETED;
        } else if (option == 2) {
            status = AppointmentStatus.CANCELLED;
        } else {
            return;
        }
        
        String result = "";
        for (Appointment a : appointmentService.getAppointments()) {
            if (a.getStatus() == status) {
                result += a.toString() + "\n---------------------------\n";
            }
        }
        if (result.isEmpty()) {
            showMessage("No hay citas con ese estado.");
        } else {
            JTextArea textArea = new JTextArea(result);
            textArea.setEditable(false);
            JScrollPane scrollPane = new JScrollPane(textArea);
            scrollPane.setPreferredSize(new java.awt.Dimension(400, 300));
            JOptionPane.showMessageDialog(null, scrollPane, "Citas filtradas", JOptionPane.PLAIN_MESSAGE);
        }
    }
}
