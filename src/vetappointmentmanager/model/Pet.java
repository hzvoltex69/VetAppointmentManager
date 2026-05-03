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
public class Pet {
    
    private int id;
    private String name;
    private String species;
    private int age;
    
    public Pet(int id, String name, String species, int age) {
        this.id = id;
        this.name = name;
        this.species = species;
        this.age = age;
    }
    
    public int getId() { 
        return this.id; 
    }
    public void setId(int id) { 
        this.id = id; 
    }
    
    public String getName() { 
        return this.name; 
    }
    public void setName(String name) { 
        this.name = name; 
    }
    
    public String getSpecies() { 
        return this.species; 
    }
    public void setSpecies(String species) { 
        this.species = species; 
    }
    
    public int getAge() { 
        return this.age; 
    }
    public void setAge(int age) { 
        this.age = age; 
    }
    
    //toString
    @Override
    public String toString() {
        return "Mascota id: " + id + "Nombre Mascota: " + name + " Especie: " + 
                species + " edad: " + age;
    }
}
