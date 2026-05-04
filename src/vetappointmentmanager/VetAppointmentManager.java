/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vetappointmentmanager;

/**
 *
 * @author hzvol
 */

import vetappointmentmanager.model.*;
import vetappointmentmanager.repository.*;
import vetappointmentmanager.service.*;
import vetappointmentmanager.ui.console.*;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class VetAppointmentManager {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Map<String, Client> clients = DataInitializer.loadInitialClients();
        List<Appointment> appointments = DataInitializer.loadInitialAppointments();
        
        Scanner scanner = new Scanner(System.in);
        System.out.println("===== VetAppointment Manager =====");
        System.out.println("Ingrese version a utilizar");
        System.out.println("1. Consola");
        System.out.println("2. Ventana");
        System.out.println("Elija una opcion: ");
        
        int op = -1;
        try {
            op = Integer.valueOf(scanner.nextLine());
        } catch (NumberFormatException  e) {
            System.out.println("Opcion no valida. Se iniciara consola por defecto.");
            op = 1;
        }
        
        if (op == 1) {
            new MenuPrincipal(clients, appointments).start();
        } else if (op == 2) {
            System.out.println("try later");
        } else {
            System.out.println("Iniciando consola por defecto.");
            new MenuPrincipal(clients, appointments).start();
        }
        
    }
    
}
