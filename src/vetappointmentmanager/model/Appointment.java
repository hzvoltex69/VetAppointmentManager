/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vetappointmentmanager.model;
import java.time.LocalDate;

/**
 *
 * @author hzvol
 */
public class Appointment {
    private int id;
    private Client client;
    private Pet pet;
    private LocalDate date;
    private AppointmentType type;
    private AppointmentStatus status;
    private String observation;
    
    public Appointment(int id, Client client, Pet pet, LocalDate date, 
            AppointmentType type, String observation) {
        this.id = id;
        this.client = client;
        this.pet = pet;
        this.date = date;
        this.type = type;
        this.status = AppointmentStatus.PENDING;
        this.observation = observation;
    }
    
    //getters & setters
    public int getId() {
        return this.id;
    }
    public void setId(int id) {
        this.id = id;
    }
    
    public Client getClient() {
        return this.client;
    }
    public void setClient(Client client) {
        this.client = client;
    }
    
    public Pet getPet() {
        return this.pet;
    }
    public void setPet(Pet pet) {
        this.pet = pet;
    }
    
    public LocalDate getDate() {
        return date;
    }
    public void setDate(LocalDate date) {
        this.date = date;
    }
    
    public AppointmentType getType() {
        return type;
    }
    public void setType(AppointmentType type) {
        this.type = type;
    }
    
    public AppointmentStatus getStatus() {
        return status;
    }
    public void setStatus(AppointmentStatus status) {
        this.status = status;
    }
    
    public String getObservation() {
        return this.observation;
    }
    public void setObservation(String observation) {
        this.observation = observation;
    }
    
    @Override
    public String toString() {
        return "Cita id: " + getId() + " Cliente: " + client.getName()+ " Mascota: " +
                pet.getName() + " Fecha: " + getDate() + " Servicio: " + getType() + 
                " Estado: " + getStatus() + " Notas: " + getObservation();
    }
}
