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
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class MenuAppointments extends MenuBase {
    private AppointmentService appointmentService;
    private ClientService clientService;

    public MenuAppointments(AppointmentService appointmentService, ClientService clientService) {
        this.appointmentService = appointmentService;
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
                    addAppointment();
                    break;
                case 2:
                    appointmentService.listAppointments();
                    break;
                case 3:
                    editAppointment();
                    break;
                case 4:
                    deleteAppointment();
                    break;
                case 5:
                    searchAppointment();
                    break;
                case 6:
                    filterByStatus();
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
        System.out.println("\n===== Gestionar Citas =====");
        System.out.println("1. Agregar Cita");
        System.out.println("2. Listar Citas");
        System.out.println("3. Editar Cita");
        System.out.println("4. Eliminar Cita");
        System.out.println("5. Buscar Cita");
        System.out.println("6. Filtrar por Estado");
        System.out.println("0. Volver");
        System.out.print("Elija una opcion: ");
    }

    private void addAppointment() {
        try {
            System.out.print("Ingrese RUT del cliente: ");
            String rut = scanner.nextLine();
            Client client = clientService.searchClient(rut);
            if (client == null) {
                return;
            }

            clientService.listPets(rut);
            System.out.print("Ingrese ID de la mascota:");
            int petId = Integer.valueOf(scanner.nextLine());
            Pet pet = clientService.searchPet(rut, petId);

            System.out.print("Ingrese fecha (YYYY-MM-DD): ");
            LocalDate date = LocalDate.parse(scanner.nextLine());

            System.out.println("Tipo de servicio:");
            System.out.println("1. Consulta Veterinaria");
            System.out.println("2. Peluqueria");
            System.out.print("Elija una opcion ");
            int typeOption = Integer.valueOf(scanner.nextLine());
            AppointmentType type;
            if (typeOption == 1) {
                type = AppointmentType.CONSULTATION;
            } else {
                type = AppointmentType.GROOMING;
            }

            System.out.print("Ingrese observacion: ");
            String observation = scanner.nextLine();

            appointmentService.addAppointment(client, pet, date, type, observation);

        } catch (InvalidAppointmentException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (PetNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Error: ingrese un valor valido.");
        } catch (DateTimeParseException e) {
            System.out.println("Error: formato de fecha no valido. Use YYYY-MM-DD.");
        }
    }

    private void editAppointment() {
        try {
            System.out.print("Ingrese ID de la cita a editar: ");
            int id = Integer.valueOf(scanner.nextLine());

            System.out.print("Ingrese nueva fecha (YYYY-MM-DD): ");
            LocalDate date = LocalDate.parse(scanner.nextLine());

            System.out.println("Tipo de servicio:");
            System.out.println("1. Consulta Veterinaria");
            System.out.println("2. Peluqueria");
            System.out.print("Elija una opcion");
            int typeOption = Integer.valueOf(scanner.nextLine());
            AppointmentType type;
            if (typeOption == 1) {
                type = AppointmentType.CONSULTATION;
            } else {
                type = AppointmentType.GROOMING;
            }

            System.out.print("Ingrese una nueva observacion: ");
            String observation = scanner.nextLine();

            appointmentService.editAppointment(id, date, type, observation);

        } catch (InvalidAppointmentException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Error: ingresa un valor valido.");
        } catch (DateTimeParseException e) {
            System.out.println("Error: formato de fecha no valido. Use YYYY-MM-DD.");
        }
    }

    private void deleteAppointment() {
        try {
            System.out.print("Ingrese ID de la cita a eliminar: ");
            int id = Integer.valueOf(scanner.nextLine());
            appointmentService.deleteAppointment(id);
        } catch (InvalidAppointmentException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Error: ingrese un valor valido");
        }
    }

    private void searchAppointment() {
        try {
            System.out.print("Ingrese ID de la cita a buscar: ");
            int id = Integer.valueOf(scanner.nextLine());
            Appointment appointment = appointmentService.searchAppointment(id);
            if (appointment != null) {
                System.out.println(appointment.toString());
            }
        } catch (NumberFormatException e) {
            System.out.println("Error: ingrese un valor valido");
        }
    }

    private void filterByStatus() {
        System.out.println("Filtrar por estado:");
        System.out.println("1. Pendiente");
        System.out.println("2. Completado");
        System.out.println("3. Cancelado");
        System.out.print("Elija una opcion ");
        try {
            int option = Integer.valueOf(scanner.nextLine());
            AppointmentStatus status;
            switch (option) {
                case 1:
                    status = AppointmentStatus.PENDING;
                    break;
                case 2:
                    status = AppointmentStatus.COMPLETED;
                    break;
                case 3:
                    status = AppointmentStatus.CANCELLED;
                    break;
                default:
                    System.out.println("Opcion no valida.");
                    return;
            }
            appointmentService.filterByStatus(status);
        } catch (NumberFormatException e) {
            System.out.println("Error: ingrese una opcion valida");
        }
    }
}
