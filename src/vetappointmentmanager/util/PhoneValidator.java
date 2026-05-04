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

import vetappointmentmanager.exception.InvalidPhoneException;

public class PhoneValidator {
    public static void validate(String rut) throws InvalidPhoneException {
        if (!rut.matches("9\\d{8}")) {
            throw new InvalidPhoneException("ERROR !! Formato valido: 998247586");
        }
    }
}