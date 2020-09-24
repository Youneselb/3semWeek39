/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dat3.jpademo.entities;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 *
 * @author Yones
 */
public class Tester {
    
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        EntityManager em = emf.createEntityManager();
        
        Person p1 = new Person("Jønke", 1963);
        Person p2 = new Person("Blondie", 1959);
        
        Address a1 = new Address("Store Torv 1", 2322, "Nr. Snede");
        Address a2 = new Address("Langgade 34", 1212, "Valby");
        
        p1.setAddress(a1);
        p2.setAddress(a2);
                
        Fee f1 = new Fee(100);
        Fee f2 = new Fee(200);
        
        p1.AddFee(f1);
        p2.AddFee(f2);
        
        SwimStyle s1 = new SwimStyle("Crawl");
        SwimStyle s2 = new SwimStyle("Butterfly");
        SwimStyle s3 = new SwimStyle("Breast stroke");
        
        
        p1.AddSwimStyle(s1);
        p1.AddSwimStyle(s3);
        p2.AddSwimStyle(s2);
       
        
        em.getTransaction().begin();
        em.persist(p1);
        em.persist(p2);
      
          em.getTransaction().begin();
        p1.removeSwimStyle(s2);
        em.getTransaction().commit();
        
        
        
        em.getTransaction().commit();
        System.out.println("p1: " + p1.getP_id() + ", " +p1.getName());
        System.out.println("p2: " + p2.getP_id() + ", " +p2.getName());
        
        System.out.println("Hvem har betalt f2? Det har: " + f2.getPerson().getName());
        
        TypedQuery<Fee> q1 = em.createQuery("SELECT f FROM Fee f", Fee.class);
        List<Fee> fees = q1.getResultList();
        
        for(Fee f: fees ) { 
        System.out.println(f.getPerson().getName() + "; " + f.getAmount() + f.getPayDate());
        }
        
    }
    
}
