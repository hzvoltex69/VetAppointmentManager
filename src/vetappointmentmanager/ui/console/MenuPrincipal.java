/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vetappointmentmanager.ui.console;

/**
 *
 * @author hzvol
 */

import vetappointmentmanager.model.*;
import vetappointmentmanager.repository.*;
import vetappointmentmanager.service.*;
import java.util.List;
import java.util.Map;

public class MenuPrincipal extends MenuBase {
    private Map<String, Client> clients;
    private List<Appointment> appointments;
    private ClientService clientService;
    private AppointmentService appointmentService;
    
    public MenuPrincipal(Map<String, Client> clients, List<Appointment> appointments) {
        this.clients = clients;
        this.appointments = appointments;
        this.clientService = new ClientService(clients);
        this.appointmentService = new AppointmentService(appointments);
    }
    
    @Override
    public void start() {
        int op = -1;
        while (op != 4 && op != 0) {
            startOptions();
            op = readOptions();
            switch (op) {
                case 1:
                    new MenuClients(clientService).start();
                    break;
                case 2:
                    new MenuAppointments(appointmentService, clientService).start();
                    break;
                case 3:
                    clients = DataManager.loadClients();
                    appointments = DataManager.loadAppointments(clients);
                    System.out.println("Datos cargados !");
                    break;
                case 4:
                    DataManager.saveClients(clients);
                    DataManager.saveAppointments(appointments);
                    System.out.println("Bye bye !");
                    break;
                case 0:
                    System.out.println("Saliendo sin guardar cambios.");
                    break;
                default:
                    System.out.println("Opcion Invalida !.");
            }
        }
    }
    
    @Override
    protected void startOptions() {
        System.out.println("\n===== Menu Principal =====");
        System.out.println("1. Gestionar Clientes");
        System.out.println("2. Gestionar Citas");
        System.out.println("3. Cargar datos desde /data/");
        System.out.println("4. Guardar y Salir");
        System.out.println("0. Salir sin guardar");
        System.out.print("Elige una opcion: ");
    }
}
