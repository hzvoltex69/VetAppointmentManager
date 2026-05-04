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
import vetappointmentmanager.util.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClientService {
    private Map<String, Client> clients;
    private int petIdCounter;
    
    public ClientService(Map<String, Client> clients) {
        this.clients = clients;
        this.petIdCounter = 1;
    }
    
    public void addClient(String rut, String name, String phone) 
            throws InvalidRutException, InvalidPhoneException {
        RutValidator.validate(rut);
        PhoneValidator.validate(phone);
        Client client = new Client(rut, name, phone);
        clients.put(rut, client);
        System.out.println("Cliente agregado!");
    }
    
    public void listClients() {
        if (clients.isEmpty()) {
            System.out.println("Lista clientes vacia");
            return;
        }
        
        for (Client client : clients.values()) {
            System.out.println(client);
        }
        
    }
    
    public Client searchClient(String rut) {
        Client client = clients.get(rut);
        if (client == null) {
            System.out.println("Cliente no existe");
            return null;
        }
        return client;
    }
    
    public void editClient(String rut, String name, String phone) 
                throws InvalidRutException, InvalidPhoneException {
        Client client = clients.get(rut);
        if (client == null) {
            System.out.println("Cliente no existe");
            return;
        }
        PhoneValidator.validate(phone);
        RutValidator.validate(rut);
        client.setName(name);
        client.setPhone(phone);
        client.setRut(rut);
        System.out.println("Cliente actualizado");
    }
    
    public void deleteClient(String rut) {
        if (!clients.containsKey(rut)) {
            System.out.println("Nice try diddy, cliente no existe!");
            return;
        }
        clients.remove(rut);
        System.out.println("Cliente eliminado!");
    }
    
    public void addPet(String rut, String name, String species, int age) 
                        throws PetNotFoundException {
        Client client = clients.get(rut);
        if (client == null) {
            throw new PetNotFoundException("Cliente: " + rut + " no existe");
        }
        
        Pet pet = new Pet(petIdCounter++, name, species, age);
        client.addPet(pet);
        System.out.println("Mascota agregada !");
    }
    
    public void listpets(String rut) throws PetNotFoundException {
        Client client = clients.get(rut);
        if (client == null) {
            throw new PetNotFoundException("Cliente: " + rut + " no existe");
        }
        if (client.getPets().isEmpty()) {
            System.out.println("Cliente sin mascotas :(");
            return;
        }
        
        for (Pet pet : client.getPets()) {
            System.out.println(pet);
        }
        
    }
    
    public Pet searchPet(String rut, int petId) throws PetNotFoundException {
        Client client = clients.get(rut);
        if (client == null) {
            throw new PetNotFoundException("Cliente: " + rut + " no existe");
        }
        for (Pet pet : client.getPets()) {
            if (pet.getId() == petId) {
                return pet;
            }
        }
        throw new PetNotFoundException("Mascota: " + petId + " no encontrada");
    }
    
    public void editPet(String rut, int petId, String name, String species, int age) 
                        throws PetNotFoundException {
        Pet pet = searchPet(rut, petId);
        pet.setName(name);
        pet.setSpecies(species);
        pet.setAge(age);
        System.out.println("Mascota actualizada !");
    }
    
    public void deletePet(String rut, int petId) throws PetNotFoundException {
        Client client = clients.get(rut);
        if (client == null) {
            throw new PetNotFoundException("No se pudo eliminar. Cliente " + rut + " no existe !");
        }
        
        Pet toRemove = searchPet(rut, petId);
        client.removePet(toRemove);
        System.out.println("Mascota eliminada !");
    }
    
    public Map<String, Client> getClients() {
        return clients;
    }
}
