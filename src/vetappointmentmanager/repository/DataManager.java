/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vetappointmentmanager.repository;

import vetappointmentmanager.model.*;
import java.io.*;
import java.time.LocalDate;
import java.util.*;

/**
 *
 * @author hzvol
 */
public class DataManager {
    
    //Save Data
    
    public static void saveClients(Map<String, Client> clients) {
        String clientsFile = "data/clients.csv";
        
        //File folder = new File("data");
        PrintWriter pw = null;
        try {
            pw = new PrintWriter(new FileWriter(clientsFile));
            for (Client c : clients.values()) {
                pw.println(c.getRut() + "," + c.getName() + "," + c.getPhone());
                for (Pet pet : c.getPets()) {
                    pw.println("PET," + pet.getId() + "," + pet.getName() + 
                                "," + pet.getSpecies() + "," + pet.getAge());
                }
            }
            System.out.println("Clientes guardados!");
        } catch (IOException e) {
            System.out.println("Error " + e.getMessage());
        } finally {
            if (pw != null) {
                pw.close();
            }
        }
    }

    public static void saveAppointments(List<Appointment> appointments) {
        String appointmentsFile = "data/appointments.csv";
        PrintWriter pw = null;
        try {
            pw = new PrintWriter(new FileWriter(appointmentsFile));
            for (Appointment app : appointments) {
                pw.println(app.getId() + "," + app.getClient().getRut() +
                        "," + app.getPet().getId() + "," + app.getDate() +
                        "," + app.getType().name() + "," + app.getStatus().name() +
                        "," + app.getObservation());
            }
            System.out.println("Citas guardadas!");
        } catch (IOException e) {
            System.out.println("Error al guardar citas: " + e.getMessage());
        } finally {
            if (pw != null) {
                pw.close();
            }
        }
    }
    
    //Load Data
    
    public static Map<String, Client> loadClients() {
        String clientsFile = "data/clients.csv";
        Map <String, Client> clients = new HashMap<>();
        File file = new File(clientsFile);
        if (!file.exists()) {
            return clients;
        }
        
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(file));
            String line;
            Client current = null;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts[0].equals("PET")) {
                    if (current != null) {
                        Pet pet = new Pet(Integer.valueOf(parts[1]), parts[2], 
                                parts[3], Integer.valueOf(parts[4]));
                        current.addPet(pet);
                    } 
                } else {
                        current = new Client(parts[0], parts[1], parts[2]);
                        clients.put(current.getRut(), current);
                    }
            } 
            System.out.println("Clientes cargados!");
        } catch (IOException e) {
            System.out.println("Error al cargar clientes: " + e.getMessage());
        } finally {
            if (br != null) {
                closeReader(br);
            }
        }
        return clients;
    }
    
    public static List<Appointment> loadAppointments(Map<String, Client> clients) {
        String appointmentsFile = "data/appointments.csv";
        List<Appointment> appointments = new ArrayList<>();
        File file = new File(appointmentsFile);
        if (!file.exists()) {
            return appointments;
        }
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(file));
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                int id = Integer.valueOf(parts[0]);
                Client client = clients.get(parts[1]);
                int petId = Integer.valueOf(parts[2]);
                
                Pet pet = null;
                for (Pet p : client.getPets()) {
                    if (p.getId() == petId) {
                        pet = p;
                        break;
                    }
                }
                
                LocalDate date = LocalDate.parse(parts[3]);
                AppointmentType type = AppointmentType.valueOf(parts[4]);
                AppointmentStatus status = AppointmentStatus.valueOf(parts[5]);
                String observation = parts[6];
                Appointment app = new Appointment(id, client, pet, date, type, observation);
                app.setStatus(status);
                appointments.add(app);
            }
            System.out.println("Citas cargadas");
        } catch (IOException e) {
            System.out.println("Error al cargar citas: " + e.getMessage());
        } finally {
            closeReader(br);
        }
        return appointments;
    }
    
    public static void closeReader(BufferedReader br) {
        if (br != null) {
            try {
                br.close();
            } catch (IOException e) {
                System.out.println("Error: " + e.getMessage()); 
                   
            }
        }
    }
}