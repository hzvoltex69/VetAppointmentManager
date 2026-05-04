/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vetappointmentmanager.service;

/**
 *
 * @author hzvol
 */

import vetappointmentmanager.model.*;
import vetappointmentmanager.exception.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AppointmentService {
    private List<Appointment> appointments;
    private int appointmentIdCounter;
    
    public AppointmentService(List<Appointment> appointments) {
        this.appointments = appointments;
        this.appointmentIdCounter = 1;
    }
    
    public void addAppointment(Client client, Pet pet, LocalDate date,
                AppointmentType type, String observation) throws InvalidAppointmentException {
        if (date.isBefore(LocalDate.now())) {
            throw new InvalidAppointmentException("Fecha no puede ser pasada !");
        }
        if (client == null) {
            throw new InvalidAppointmentException("Cliente no existe");
        }
        if (pet == null) {
            throw new InvalidAppointmentException("Mascota no existe");
        }
        
        Appointment appointment = new Appointment(appointmentIdCounter++, client,
                                  pet, date, type, observation);
        appointments.add(appointment);
        System.out.println("Cita agregada");
    }
    
    public void listAppointments() {
        if (appointments.isEmpty()) {
            System.out.println("No existen citas agendadas");
            return;
        }
        for (Appointment app : appointments) {
            System.out.println(app);
            System.out.println("- - - - -");
        }
    }
    
    public Appointment searchAppointment(int id) {
        for (Appointment app : appointments) {
            if (app.getId() == id) {
                return app;
            }
        }
        System.out.println("Cita no encontrada");
        return null;
    }
    
    public void editAppointment(int id, LocalDate date, AppointmentType type,
                                String observation) throws InvalidAppointmentException {
        Appointment appointment = searchAppointment(id);
        if (appointment == null) {
            throw new InvalidAppointmentException("Cita " + id + " no encontrada");
        }
        appointment.setDate(date);
        appointment.setType(type);
        appointment.setObservation(observation);
        System.out.println("Cita actualizada");
    }
    
    public void deleteAppointment(int id) throws InvalidAppointmentException {
        Appointment toRemove = searchAppointment(id);
        if (toRemove == null) {
            throw new InvalidAppointmentException("Cita: " + id + " no encontrada");
        }
        appointments.remove(toRemove);
        System.out.println("Cita eliminada");
    }
    
    public void filterByStatus(AppointmentStatus status) {
        boolean found = false;
        for (Appointment app : appointments) {
            if (app.getStatus() == status) {
                System.out.println(app);
                System.out.println("- - - - -");
                found = true;
            }
        }
        if (!found) {
            System.out.println("No existen citas con estado: " + status);
        }
    }
    
    public List<Appointment> getAppointments() {
        return appointments;
    }
}
