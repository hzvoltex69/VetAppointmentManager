# Vet Appointment Manager ver 1.0

A simple Java-based system to manage veterinary appointments, clients, and pets.

## Built With

- Apache NetBeans IDE 12
- Java JDK 11

## Overview

Vet Appointment Manager is designed to help veterinary clinics organize their daily operations, including scheduling appointments, managing pet records, and handling client information.

This project is ideal for learning object-oriented programming and basic system design.

## Features

- Appointment scheduling
- Pet management (name, type, etc.)
- Client management
- Basic data handling and storage
- Modular Java structure (multiple classes)

## Technologies Used

- Java
- Object-Oriented Programming (OOP)

## Project Structure

VetAppointmentManager/
│
├── src/vetappointmentmanager/
│ │ VetAppointmentManager.java # Main entry point
│ │
│ ├── model/ # Core domain entities
│ │ ├── Appointment.java
│ │ ├── AppointmentStatus.java
│ │ ├── AppointmentType.java
│ │ ├── Client.java
│ │ └── Pet.java
│ │
│ ├── service/ # Business logic layer
│ │ ├── AppointmentService.java
│ │ └── ClientService.java
│ │
│ ├── repository/ # Data handling & initialization
│ │ ├── DataManager.java
│ │ └── DataInitializer.java
│ │
│ ├── ui/ # User interface layer
│ │ ├── console/ # Console-based menus
│ │ │ ├── MenuPrincipal.java
│ │ │ ├── MenuClients.java
│ │ │ ├── MenuPets.java
│ │ │ └── MenuAppointments.java
│ │ │
│ │ └── gui/ # Graphical interface (Java Swing)
│ │ ├── MainWindow.java
│ │ ├── ClientWindow.java
│ │ ├── PetWindow.java
│ │ ├── AppointmentWindow.java
│ │ └── WindowBase.java
│ │
│ ├── exception/ # Custom exception handling
│ │ ├── InvalidAppointmentException.java
│ │ ├── InvalidPhoneException.java
│ │ ├── InvalidRutException.java
│ │ └── PetNotFoundException.java
│ │
│ └── util/ # Utility classes
│ ├── PhoneValidator.java
│ └── RutValidator.java
│
├── data/ # CSV data storage
....

## Requirements

- Java 8 or higher
- Apache NetBeans (optional)
  
## Installation and Running
```bash
git clone https://github.com/hzvoltex69/VetAppointmentManager.git
cd VetAppointmentManager
```
Run with NetBeans
- Open Apache NeatBeans
- Click File -> Open Project
- Select the VetAppointmentManager folder
- Select 'VetAppointmentManager' as Main
- Press Run


## Future Improvements

- Database integration (SQL)

## AI Assistance

Parts of the user interface (UI) were implemented with the help of Claude (Anthropic AI).  
All integration and final adjustments were done by the project author.

## License

- This project is for educational purposes.
- Proyecto academico — Pontificia Universidad Catolica de Valparaiso (PUCV), 2026.
