/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tesoro.inventory.control.persistence.models;

import java.io.Serializable;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries(@NamedQuery(name = "Item.findAll", query = "SELECT c FROM Item c"))
public class Item extends AbstractEntity implements Serializable{
    
    private StringProperty name;
    private StringProperty bottleSize;
    private DoubleProperty price;
    private ObjectProperty<Supplier> supplier;
    
    public Item(){
       this.name = new SimpleStringProperty();
       this.price = new SimpleDoubleProperty();
       this.bottleSize = new SimpleStringProperty();
       this.supplier = new SimpleObjectProperty<>();
    }
    
    
    @Column(name = "name")
    public String getName() {
        return nameProperty().get();
    }


    public void setName(String name) {
        this.nameProperty().set(name);
    }

    public String getBottleSize() {
        return bottleSizeProperty().get();
    }

    public void setBottleSize(String description) {
        this.bottleSizeProperty().set(description);
    }
    
     public Double getPrice(){
       return this.prizeProperty().get();
    }
    
    public void setPrice(Double price){
        this.prizeProperty().set(price);
    }

    @ManyToOne()
    @JoinColumn(name="supplier_id")
    public Supplier getSupplier() {
        return supplierProperty().get();
    }

    public void setSupplier(Supplier supplier) {
        this.supplierProperty().set(supplier);
    }

    public StringProperty nameProperty() {
        return name;
    }

    public StringProperty bottleSizeProperty() {
        return bottleSize;
    }

    public DoubleProperty prizeProperty() {
        return price;
    }

    public ObjectProperty<Supplier> supplierProperty() {
        return supplier;
    }
    
    @Override
    public String toString(){
        return getName();
    }
    
}
