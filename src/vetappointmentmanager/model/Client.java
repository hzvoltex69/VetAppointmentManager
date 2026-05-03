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
import java.util.ArrayList;
import java.util.List;

public class Client {
    private String rut;
    private String name;
    private String phone;
    private List<Pet> pets;
    
    //Constructor 
    public Client(String rut, String name, String phone) {
        this.rut = rut;
        this.name = name;
        this.phone = phone;
        this.pets = new ArrayList<>();
    }
    
    //getters a& setters
    public String getRut() {
        return this.rut;
    }
    public void setRut(String rut) {
        this.rut = rut;
    }
    
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    
    public String getPhone() {
        return this.phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    public List<Pet> getPets() {
        return pets;
    }
    public void setPets(List<Pet> pets) {
        this.pets = pets;
    }
    
    //Pet Management
    public void addPet(Pet pet) {
        pets.add(pet);
    }
    public void removePet(Pet pet) {
        pets.remove(pet);
    }
    
    //toString
    @Override
    public String toString() {
        return "Rut cliente: " + getRut() + " Nombre: " + getName() +
                " Celular: " + getPhone() + " Mascotas: " + pets.size();
    }
}
