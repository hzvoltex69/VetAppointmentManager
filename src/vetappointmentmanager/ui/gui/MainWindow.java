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

import vetappointmentmanager.model.*;
import vetappointmentmanager.repository.*;
import vetappointmentmanager.service.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.List;
import java.util.Map;

public class MainWindow extends WindowBase {
    private Map<String, Client> clients;
    private List<Appointment> appointments;
    private ClientService clientService;
    private AppointmentService appointmentService;
    private ClientWindow clientWindow;
    private AppointmentWindow appointmentWindow;

    public MainWindow(Map<String, Client> clients, List<Appointment> appointments) {
        super("VetAppointment Manager");
        this.clients = clients;
        this.appointments = appointments;
        this.clientService = new ClientService(clients);
        this.appointmentService = new AppointmentService(appointments);
    }

    @Override
    protected void initComponents() {
        JButton btnClients = new JButton("Gestionar Clientes");
        JButton btnAppointments = new JButton("Gestionar Citas");
        JButton btnLoad = new JButton("Cargar datos desde CSV file");
        JButton btnSaveExit = new JButton("Guardar y Salir");
        JButton btnExit = new JButton("Salir sin guardar");

        btnClients.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (clientWindow == null) {
                    clientWindow = new ClientWindow(clientService);
                }
            clientWindow.setVisible(true);
            clientWindow.toFront();
            }
        });

        btnAppointments.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (appointmentWindow == null) {
                    appointmentWindow = new AppointmentWindow(appointmentService, clientService);
                }
            appointmentWindow.setVisible(true);
            appointmentWindow.toFront();
            }
        });

        btnLoad.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                clients = DataManager.loadClients();
                appointments = DataManager.loadAppointments(clients);
                showMessage("Datos cargados correctamente !");
            }
        });

        btnSaveExit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                DataManager.saveClients(clients);
                DataManager.saveAppointments(appointments);
                showMessage("Datos guardados, bye bye !");
                System.exit(0);
            }
        });

        btnExit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (askConfirm("Salir sin guardar?")) {
                    System.exit(0);
                }
            }
        });

        add(btnClients);
        add(btnAppointments);
        add(btnLoad);
        add(btnSaveExit);
        add(btnExit);
    }
}
