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

import vetappointmentmanager.exception.*;
import vetappointmentmanager.model.*;
import vetappointmentmanager.service.*;

public class MenuClients extends MenuBase {
    private ClientService clientService;

    public MenuClients(ClientService clientService) {
        this.clientService = clientService;
    }
    
    @Override
    public void start() {
        int op = -1;
        while (op != 0) {
            startOptions();
            op = readOptions();
            switch (op) {
                case 1:
                    addClient();
                    break;
                case 2:
                    clientService.listClients();
                    break;
                case 3:
                    editClient();
                    break;
                case 4:
                    deleteClient();
                    break;
                case 5:
                    searchClient();
                    break;
                case 6:
                    managePets();
                    break;
                case 0:
                    System.out.println("Volviendo al menu principal...");
                    break;
                default:
                    System.out.println("Opcion no valida.");
            }
        }
    }   
    
    @Override
    protected void startOptions() {
        System.out.println("\n===== Gestionar Clientes =====");
        System.out.println("1. Agregar Cliente");
        System.out.println("2. Listar Clientes");
        System.out.println("3. Editar Cliente");
        System.out.println("4. Eliminar Cliente");
        System.out.println("5. Buscar Cliente");
        System.out.println("6. Gestionar Mascotas");
        System.out.println("0. Volver");
        System.out.print("Elija una opcion: ");
    }    

    private void addClient() {
        try {
            System.out.print("Ingrese RUT: ");
            String rut = scanner.nextLine();
            System.out.print("Ingrese nombre: ");
            String name = scanner.nextLine();
            System.out.print("Ingrese numero de celular: ");
            String phone = scanner.nextLine();
            clientService.addClient(rut, name, phone);
        } catch (InvalidRutException | InvalidPhoneException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void editClient() {
        try {
            Client client = getClient();
            if (client == null) return;
            System.out.print("Ingrese nuevo nombre: ");
            String name = scanner.nextLine();
            System.out.print("Ingrese nuevo numero de celular: ");
            String phone = scanner.nextLine();
            clientService.editClient(client.getRut(), name, phone);
        } catch (InvalidPhoneException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void deleteClient() {
        Client client = getClient();
        if (client == null) return;
        clientService.deleteClient(client.getRut());
    }

    private void searchClient() {
        Client client = getClient();
        if (client != null) {
            System.out.println(client);
        }
    }

    private void managePets() {
        Client client = getClient();
        if (client != null) {
            new MenuPets(clientService, client).start();
        }
    }
    
    public Client getClient() {
        System.out.println("Ingrese Rut del Cliente");
        String rut = scanner.nextLine();
        Client client = clientService.searchClient(rut);
        if (rut == null || client == null) {
            System.out.println("CLiente no existe!");
            return null;
        }
        return client;
    }
}
