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
public enum AppointmentType {
    CONSULTATION,
    GROOMING;
    
    public String toString() {
        switch(this) {
            case CONSULTATION:
                return "Consulta Veterinaria";
            case GROOMING:
                return "Peluqueria";
            default:
                return "VIP Service";
        }
    }
}
