/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tesoro.inventory.control.business;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;


public class PersistenceService {
    private EntityManager em;
    private EntityTransaction et;

    @PostConstruct
    public void init(){
        this.em = (EntityManager) Persistence.createEntityManagerFactory("InventoryControlPU").createEntityManager();
        this.et = em.getTransaction();
    }
    
    public EntityManager getManager(){
        return this.em;
    }
    
    public EntityTransaction getTransaction(){
        return this.et;
    }
}
