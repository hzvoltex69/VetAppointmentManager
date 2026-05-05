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

public class PetWindow extends WindowBase {
    private ClientService clientService;
    private Client client;

    public PetWindow(ClientService clientService, Client client) {
        super("Mascotas de: " + client.getName());
        this.clientService = clientService;
        this.client = client;
    }

    @Override
    protected void initComponents() {
        JButton btnAdd = new JButton("Agregar Mascota");
        JButton btnList = new JButton("Listar Mascotas");
        JButton btnEdit = new JButton("Editar Mascota");
        JButton btnDelete = new JButton("Eliminar Mascota");
        JButton btnSearch = new JButton("Buscar Mascota");
        JButton btnBack = new JButton("Volver");

        btnAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addPet();
            }
        });

        btnList.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                listPets();
            }
        });

        btnEdit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                editPet();
            }
        });

        btnDelete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deletePet();
            }
        });

        btnSearch.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                searchPet();
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
        add(btnBack);
    }

    private void addPet() {
        try {
            String name = askInput("Ingrese nombre:");
            if (name == null) return;
            String species = askInput("Ingrese especie:");
            if (species == null) return;
            String ageStr = askInput("Ingrese edad:");
            if (ageStr == null) return;
            int age = Integer.parseInt(ageStr);
            clientService.addPet(client.getRut(), name, species, age);
            showMessage("Mascota agregada correctamente!");
        } catch (PetNotFoundException e) {
            showMessage("Error: " + e.getMessage());
        } catch (NumberFormatException e) {
            showMessage("Error: " + e.getMessage());
        }
    }

    private void listPets() {
        if (client.getPets().isEmpty()) {
            showMessage("Cliente no tiene mascotas registradas.");
            return;
        }
        String result = "";
        for (Pet p : client.getPets()) {
            result += p.toString() + "\n----------------------------------------"
                    + "--------------------------------------------------------------------\n";;
        }
        JTextArea textArea = new JTextArea(result);
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new java.awt.Dimension(400, 300));
        JOptionPane.showMessageDialog(null, scrollPane, "Lista de Mascotas", JOptionPane.PLAIN_MESSAGE);
    }

    private void editPet() {
        try {
            String idStr = askInput("Ingrese ID de la mascota a editar:");
            if (idStr == null) return;
            int id = Integer.parseInt(idStr);
            String name = askInput("Ingrese nuevo nombre:");
            if (name == null) return;
            String species = askInput("Ingrese nueva especie:");
            if (species == null) return;
            String ageStr = askInput("Ingrese nueva edad:");
            if (ageStr == null) return;
            int age = Integer.parseInt(ageStr);
            clientService.editPet(client.getRut(), id, name, species, age);
            showMessage("Mascota actualizada correctamente!");
        } catch (PetNotFoundException e) {
            showMessage("Error: " + e.getMessage());
        } catch (NumberFormatException e) {
            showMessage("Error: " + e.getMessage());
        }
    }

    private void deletePet() {
        try {
            if (!askConfirm("Eliminar mascota?")) return;
            String idStr = askInput("Ingrese ID de la mascota a eliminar:");
            if (idStr == null) return;
            int id = Integer.parseInt(idStr);
            clientService.deletePet(client.getRut(), id);
            showMessage("Mascota eliminada correctamente!");
        } catch (PetNotFoundException e) {
            showMessage("Error: " + e.getMessage());
        } catch (NumberFormatException e) {
            showMessage("Error: " + e.getMessage());
        }
    }

    private void searchPet() {
        try {
            String idStr = askInput("Ingrese ID de la mascota a buscar:");
            if (idStr == null) return;
            int id = Integer.parseInt(idStr);
            Pet pet = clientService.searchPet(client.getRut(), id);
            showMessage(pet.toString());
        } catch (PetNotFoundException e) {
            showMessage("Error: " + e.getMessage());
        } catch (NumberFormatException e) {
            showMessage("Error: " + e.getMessage());
        }
    }
}
