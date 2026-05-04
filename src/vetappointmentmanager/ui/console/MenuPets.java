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

public class MenuPets extends MenuBase {
    private ClientService clientService;
    private Client client;

    public MenuPets(ClientService clientService, Client client) {
        this.clientService = clientService;
        this.client = client;
    }
    
    @Override
    public void start() {
        int op = -1;
        while (op != 0) {
            startOptions();
            op = readOptions();
            switch (op) {
                case 1:
                    addPet();
                    break;
                case 2:
                    listPets();
                    break;
                case 3:
                    editPet();
                    break;
                case 4:
                    deletePet();
                    break;
                case 5:
                    searchPet();
                    break;
                case 0:
                    System.out.println("Volviendo al menu de clientes...");
                    break;
                default:
                    System.out.println("Opcion no valida.");
            }
        }
    }

    @Override
    protected void startOptions() {
        System.out.println("\n===== Mascotas de: " + client.getName() + " =====");
        System.out.println("1. Agregar Mascota");
        System.out.println("2. Listar Mascotas");
        System.out.println("3. Editar Mascota");
        System.out.println("4. Eliminar Mascota");
        System.out.println("5. Buscar Mascota");
        System.out.println("0. Volver");
        System.out.print("Elija una opcion ");
    }

    private void addPet() {
        try {
            System.out.print("Ingrese nombre: ");
            String name = scanner.nextLine();
            System.out.print("Ingrese especie: ");
            String species = scanner.nextLine();
            System.out.print("Ingrese edad: ");
            int age = Integer.parseInt(scanner.nextLine());
            clientService.addPet(client.getRut(), name, species, age);
        } catch (PetNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void listPets() {
        if (client.getPets().isEmpty()) {
            System.out.println("Este cliente no tiene mascotas registradas:b");
            return;
        }
        for (Pet pet : client.getPets()) {
            System.out.println(pet);
        }
    }

    private void editPet() {
        try {
            System.out.print("Ingrese ID de la mascota a editar: ");
            int id = Integer.valueOf(scanner.nextLine());
            System.out.print("Ingrese nuevo nombre: ");
            String name = scanner.nextLine();
            System.out.print("Ingrese nueva especie: ");
            String species = scanner.nextLine();
            System.out.print("Ingrese nueva edad: ");
            int age = Integer.parseInt(scanner.nextLine());
            clientService.editPet(client.getRut(), id, name, species, age);
        } catch (PetNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void deletePet() {
        try {
            System.out.print("Ingrese ID de la mascota a eliminar: ");
            int id = Integer.valueOf(scanner.nextLine());
            clientService.deletePet(client.getRut(), id);
        } catch (PetNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void searchPet() {
        try {
            System.out.print("Ingrese ID de la mascota a buscar: ");
            int id = Integer.valueOf(scanner.nextLine());
            Pet pet = clientService.searchPet(client.getRut(), id);
            System.out.println(pet.toString());
        } catch (PetNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
