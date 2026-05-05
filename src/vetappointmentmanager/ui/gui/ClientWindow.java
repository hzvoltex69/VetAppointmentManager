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

public class ClientWindow extends WindowBase{
    private ClientService clientService;

    public ClientWindow(ClientService clientService) {
        super("Gestionar Clientes");
        this.clientService = clientService;
    }

    @Override
    protected void initComponents() {
        JButton btnAdd = new JButton("Agregar Cliente");
        JButton btnList = new JButton("Listar Clientes");
        JButton btnEdit = new JButton("Editar Cliente");
        JButton btnDelete = new JButton("Eliminar Cliente");
        JButton btnSearch = new JButton("Buscar Cliente");
        JButton btnPets = new JButton("Gestionar Mascotas");
        JButton btnBack = new JButton("Volver");

        btnAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addClient();
            }
        });

        btnList.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                listClients();
            }
        });

        btnEdit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                editClient();
            }
        });

        btnDelete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deleteClient();
            }
        });

        btnSearch.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                searchClient();
            }
        });

        btnPets.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                managePets();
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
        add(btnPets);
        add(btnBack);
    }

    private void addClient() {
        try {
            String rut = askInput("Ingrese RUT:");
            if (rut == null) return;
            String name = askInput("Ingrese nombre:");
            if (name == null) return;
            String phone = askInput("Ingrese numero de celular:");
            if (phone == null) return;
            clientService.addClient(rut, name, phone);
            showMessage("Cliente agregado correctamente !");
        } catch (InvalidRutException e) {
            showMessage("Error: " + e.getMessage());
        } catch (InvalidPhoneException e) {
            showMessage("Error: " + e.getMessage());
        }
    }

    private void listClients() {
        if (clientService.getClients().isEmpty()) {
            showMessage("No hay clientes registrados.");
            return;
        }
        String result = "";
        for (Client c : clientService.getClients().values()) {
            result += c + "\n----------------------------------------"
                    + "--------------------------------------------------------------------\n";
        }
        JTextArea textArea = new JTextArea(result);
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new java.awt.Dimension(400, 300));
        JOptionPane.showMessageDialog(null, scrollPane, "Lista de Clientes", JOptionPane.PLAIN_MESSAGE);
    }

    private void editClient() {
        try {
            String rut = askInput("Ingrese RUT del cliente a editar:");
            Client client = getClient(rut);
            if (client == null) return;
            String name = askInput("Ingrese nuevo nombre:");
            if (name == null) return;
            String phone = askInput("Ingrese nuevo telefono:");
            if (phone == null) return;
            clientService.editClient(rut, name, phone);
            showMessage("Cliente actualizado correctamente !");
        } catch (InvalidPhoneException e) {
            showMessage("Error: " + e.getMessage());
        }
    }

    private void deleteClient() {
        if (!askConfirm("Eliminar cliente?")) return;
        String rut = askInput("Ingrese RUT del cliente a eliminar:");
        Client client = getClient(rut);
        if (client == null) return;
        clientService.deleteClient(rut);
        showMessage("Cliente eliminado correctamente!");
    }

    private void searchClient() {
        String rut = askInput("Ingrese RUT del cliente a buscar:");
        Client client = getClient(rut);
        if (client != null) {
            showMessage(client.toString());
        }
    }

    private void managePets() {
        String rut = askInput("Ingrese RUT del cliente:");
        Client client = getClient(rut);
        if (client != null) {
            new PetWindow(clientService, client).setVisible(true);
        }
    }
    
    public Client getClient(String rut) {
        Client client = clientService.searchClient(rut);
        if (rut == null || client == null) {
            showMessage("CLiente no existe!");
            return null;
        }
        return client;
    }
}
