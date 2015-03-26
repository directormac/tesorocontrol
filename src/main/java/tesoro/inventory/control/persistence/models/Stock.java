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
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name = "Stock")
public class Stock  extends AbstractEntity implements Serializable{
    
    private ObjectProperty<Item> item;
    private IntegerProperty quantity;
    private ObjectProperty<Date> dateArrived;
    
    public Stock(){
        this.item = new SimpleObjectProperty<>();
        this.quantity = new SimpleIntegerProperty();
        this.dateArrived = new SimpleObjectProperty<>(new Date());
    }
    
    
    @Temporal(TemporalType.DATE)
    @Column(name = "date_arrived")    
    public Date getDateArrived(){
       return this.dateArrived.get();
    }
    
    public void setDateArrived(Date date){
        this.dateArrived.set(date);
    }

    @OneToOne
    @JoinColumn(name = "item_id")
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
