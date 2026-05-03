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
public enum AppointmentStatus {
    PENDING,
    COMPLETED,
    CANCELLED;
    
    @Override
    public String toString() {
        switch(this) {
            case PENDING:
                return "Pendiente";
            case COMPLETED:
                return "Completado";
            case CANCELLED:
                return "Cancelado";
            default:
                return "Por Confirmar";
        }
    }
    
}
