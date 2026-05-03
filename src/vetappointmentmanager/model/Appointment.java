/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vetappointmentmanager.model;

/**
 *
 * @author hzvol
 */
public class Appointment {
    private int id;
    private Client client;
    private Pet pet;
    private String date;
    private AppointmentType type;
    private AppointmentStatus status;
    private String observation;
    
    public Appointment(int id, Client client, Pet pet, String date, 
            AppointmentType type, AppointmentStatus status, String observation) {
        this.id = id;
        this.client = client;
        this.pet = pet;
        this.date = date;
        this.type = type;
        this.status = status;
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
    
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
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
        return "Cita: " + getId() + "Cliente: " + client.getName()+ "Mascota: " +
                pet.getName() + " Fecha: " + getDate() + "Servicio: " + getType() + 
                "Estado: " + getStatus() + "Notas: " + getObservation();
    }
}
