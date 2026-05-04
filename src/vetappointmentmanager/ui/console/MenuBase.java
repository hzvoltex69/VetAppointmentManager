/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vetappointmentmanager.ui.console;

/**
 *
 * @author hzvol
 */

import java.util.Scanner;

public abstract class MenuBase {
    protected Scanner scanner;
    
    public MenuBase() {
        this.scanner = new Scanner(System.in);
    }
    
    public abstract void start();
    
    protected abstract void startOptions();
    
    protected int readOptions() {
        int op = -1;
        try {
            op = Integer.valueOf(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Opcion ingresada no valida. Intente nuevamente");
        }
        return op;
    }
}
