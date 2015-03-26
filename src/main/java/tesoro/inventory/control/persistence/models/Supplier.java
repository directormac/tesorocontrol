/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tesoro.inventory.control.persistence.models;

import java.io.Serializable;
import java.util.List;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Supplier")
@NamedQueries(@NamedQuery(name = "Supplier.findAll", query = "SELECT c FROM Supplier c"))
public class Supplier extends AbstractEntity implements Serializable{
    
    private StringProperty name;
    private StringProperty address;
    private StringProperty phoneNumber;
    private ListProperty<Item> items;
    
    
    public Supplier(){
        this.name = new SimpleStringProperty();
        this.address = new SimpleStringProperty();
        this.phoneNumber = new SimpleStringProperty();
        this.items = new SimpleListProperty<>();
    }
     
    public String getName() {
        return nameProperty().get();
    }
    
    public void setName(String name) {
        this.nameProperty().set(name);
    }

    public String getAddress() {
        return address.get();
    }

    public void setAddress(String address) {
        this.address.set(address);
    }

    public String getPhoneNumber() {
        return phoneNumber.get();
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber.set(phoneNumber);
    }

    
    @OneToMany(mappedBy = "supplier")
    public List<Item> getItems() {
        return items.get();
    }

    public void setItems(List<Item> items) {
        this.items.set(FXCollections.observableList(items));
    }
    
   
    @Override
    public String toString(){
        return getName();
    }

    public StringProperty nameProperty() {
        return name;
    }
    
}
