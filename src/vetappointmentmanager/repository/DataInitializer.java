/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vetappointmentmanager.repository;
import vetappointmentmanager.model.*;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author hzvol
 */


public class DataInitializer {
    public static Map<String, Client> loadInitialClients() {
        Map<String, Client> clients = new HashMap();
        
        Client c1 = new Client ("2239482-9", "Kiko Perez", "942323232");
        c1.addPet(new Pet(-1, "Chavo", "Perro", 3));
        
        Client c2 = new Client ("21203973-4", "Don Ramon", "936328563");
        c2.addPet(new Pet(-2, "Michi", "Gato", 2));
        
        Client c3 = new Client("20312538-3", "Juan Cabezon", "983793691");
        c3.addPet(new Pet(-3, "Firulais", "Perro", 6));
        
        clients.put(c1.getRut(), c1);
        clients.put(c2.getRut(), c2);
        clients.put(c3.getRut(), c3);
        
        return clients;
    }
    
    public static List<Appointment> loadInitialAppointments() {
        List<Appointment> appointments = new ArrayList<>();
        
        Map<String, Client> clients = loadInitialClients();
        Client c1 = clients.get("2239482-9");
        Client c2 = clients.get("21203973-4");
        Client c3 = clients.get("20312538-3");
        
        appointments.add(new Appointment(-1, c1, c1.getPets().get(0),
                    LocalDate.of(2026, 5, 25), AppointmentType.CONSULTATION,
                    "Dolor en patita derecha"));
        appointments.add(new Appointment(-2, c2, c2.getPets().get(0), 
                    LocalDate.of(2026, 6, 26), AppointmentType.GROOMING,
                    "Requiere shampoo pelo liso"));
        appointments.add(new Appointment(-3, c3, c3.getPets().get(0), 
                    LocalDate.of(2026, 7, 27), AppointmentType.CONSULTATION,
                    "Segunda dosis vacuna parasitaria"));
        
        return appointments;
    }
}
