/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tesoro.inventory.control.persistence.models;

import java.io.Serializable;
import java.util.Date;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@NamedQueries(@NamedQuery(name = "Purchase.findAll", query = "SELECT c FROM Purchase c"))
public class Purchase extends AbstractEntity implements Serializable{
    
    private ObjectProperty<Item> item;
    private IntegerProperty quantity;
    private ObjectProperty<Date> datePurchased;
    
    public Purchase(){
        this.item = new SimpleObjectProperty<>();
        this.quantity = new SimpleIntegerProperty();
        this.datePurchased = new SimpleObjectProperty<>(new Date());
    }
    
    
    @Temporal(TemporalType.DATE)
    @Column(name = "date_arrived")    
    public Date getDatePurchased(){
       return this.datePurchased.get();
    }
    
    public void setDatePurchased(Date date){
        this.datePurchased.set(date);
    }

    @OneToOne(targetEntity = Item.class)
    public Item getItem(){
        return this.item.get();
    }
  
    public void setItem(Item item){
        this.item.set(item);
    }
    
    
    public Integer getQuantity() {
        return quantity.get();
    }

    public void setQuantity(Integer quantity) {
        this.quantity.set(quantity);
    }
}
