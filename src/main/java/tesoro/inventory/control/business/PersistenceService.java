/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tesoro.inventory.control.business;

import java.util.List;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import tesoro.inventory.control.persistence.models.Item;
import tesoro.inventory.control.persistence.models.Purchase;
import tesoro.inventory.control.persistence.models.Stock;
import tesoro.inventory.control.persistence.models.Supplier;


public class PersistenceService {
    
    private EntityManager em;
    private EntityTransaction et;
    
    private ListProperty<Item> items;
    private ListProperty<Purchase> purchases;
    private ListProperty<Stock> stocks;
    private ListProperty<Supplier> suppliers;
    
    @PostConstruct
    public void init(){
        this.em = (EntityManager) Persistence.createEntityManagerFactory("InventoryControlPU").createEntityManager();
        this.et = em.getTransaction();
        this.items = new SimpleListProperty<>();
        this.purchases = new SimpleListProperty<>();
        this.stocks = new SimpleListProperty<>();
        this.suppliers = new SimpleListProperty<>();
        items.addListener(new ListChangeListener<Item>() {
            @Override
            public void onChanged(ListChangeListener.Change<? extends Item> c) {
                    while (c.next()) {
                    if(c.wasAdded()){
                        List added = c.getAddedSubList();
                        for(Object r: added){
                            et.begin();
                            em.persist((Item)r);
                            et.commit();
                        }
                    }else if(c.wasRemoved()){
                        List removed = c.getRemoved();
                        for(Object r : removed){
                            et.begin();
                            em.remove((Item)r);
                            et.commit();
                        }
                    }
             }
            }
        }
        );
        
        stocks.addListener(new ListChangeListener<Stock>() {
            @Override
            public void onChanged(ListChangeListener.Change<? extends Stock> c) {
                    while (c.next()) {
                    if(c.wasAdded()){
                        List added = c.getAddedSubList();
                        for(Object r: added){
                            et.begin();
                            em.persist((Stock)r);
                            et.commit();
                        }
                    }else if(c.wasRemoved()){
                        List removed = c.getRemoved();
                        for(Object r : removed){
                            et.begin();
                            em.remove((Stock)r);
                            et.commit();
                        }
                    }
             }
            }
        }
        );
        
        suppliers.addListener(new ListChangeListener<Supplier>() {
            @Override
            public void onChanged(ListChangeListener.Change<? extends Supplier> c) {
                    while (c.next()) {
                    if(c.wasAdded()){
                        List added = c.getAddedSubList();
                        for(Object r: added){
                            et.begin();
                            em.persist((Supplier)r);
                            et.commit();
                        }
                    }else if(c.wasRemoved()){
                        List removed = c.getRemoved();
                        for(Object r : removed){
                            et.begin();
                            em.remove((Supplier)r);
                            et.commit();
                        }
                    }
             }
            }
        }
        );
        
        purchases.addListener(new ListChangeListener<Purchase>() {
            @Override
            public void onChanged(ListChangeListener.Change<? extends Purchase> c) {
                    while (c.next()) {
                    if(c.wasAdded()){
                        List added = c.getAddedSubList();
                        for(Object r: added){
                            et.begin();
                            em.persist((Purchase)r);
                            et.commit();
                        }
                    }else if(c.wasRemoved()){
                        List removed = c.getRemoved();
                        for(Object r : removed){
                            et.begin();
                            em.remove((Purchase)r);
                            et.commit();
                        }
                    }
             }
            }
        }
        );
        
    }
  
    public ObservableList<Item> getItems(){
//        Task task = new Task<ObservableList<Item>>() {
//            @Override public ObservableList<Item> call() {
//               items.set(FXCollections.observableList(em.createNamedQuery("Item.findAll").getResultList()));
//               return items;
//            }
//        };
//        new Thread(task).start();
        items.set(FXCollections.observableList(em.createNamedQuery("Item.findAll").getResultList()));
        return items;
    }
    
    public ObservableList<Purchase> getPurchases(){
        purchases.set(FXCollections.observableList(em.createNamedQuery("Purchase.findAll").getResultList()));
        return purchases;
    }
    
    public ObservableList<Stock> getStocks(){
        stocks.set(FXCollections.observableList(em.createNamedQuery("Stock.findAll").getResultList()));
        return stocks;
    }
    
    public ObservableList<Supplier> getSuppliers(){
//        Task task = new Task<ObservableList<Supplier>>() {
//            @Override public ObservableList<Supplier> call() {
//               suppliers.set(FXCollections.observableList(em.createNamedQuery("Supplier.findAll").getResultList()));
//               return suppliers;
//            }
//        };
//        new Thread(task).start();
        suppliers.set(FXCollections.observableList(em.createNamedQuery("Supplier.findAll").getResultList()));
        return suppliers;
    }
    
    public void updateItem(Item item){
        et.begin();
        em.merge(item);
        et.commit();
    }
    
    public void updateStock(Stock stock){
        et.begin();
        em.merge(stock);
        et.commit();
    }
    
    public void updatePurchase(Purchase purchase){
        et.begin();
        em.merge(purchase);
        et.commit();
    }
    
    public void updateSupplier(Supplier supplier){
        et.begin();
        em.merge(supplier);
        et.commit();
    }
    
    
}
