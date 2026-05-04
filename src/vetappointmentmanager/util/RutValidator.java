/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vetappointmentmanager.util;

/**
 *
 * @author hzvol
 */
import vetappointmentmanager.exception.InvalidRutException;

public class RutValidator {
    public static void validate(String rut) throws InvalidRutException {
        if (!rut.matches("\\d{7,8}-\\d")) {
            throw new InvalidRutException("ERROR !! Formato valido: 12345678-9");
        }
    }
}
