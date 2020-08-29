/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.google.gson.Gson;
import entity.Animal;
import java.util.List;
import java.util.Random;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author Danie
 */
@Path("animals_db")
public class AnimalFromDB {
    
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of AnimalFromDB
     */
    public AnimalFromDB() {
    }
    
    
    @Path("/animals")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getAnimals() {
      EntityManager em = emf.createEntityManager();
      try{
          em.getTransaction().begin();
          TypedQuery<Animal> query = em.createQuery("SELECT a FROM Animal a", Animal.class);
          List<Animal> animals = query.getResultList();
          em.getTransaction().commit();
          return new Gson().toJson(animals);
       } finally {
              em.close();
       }
    }
    
    @Path("/animalbyid/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getAnimalById(@PathParam("id") int id) {
      EntityManager em = emf.createEntityManager();
      try{
            em.getTransaction().begin();
            Animal animal = em.find(Animal.class, id);
            em.getTransaction().commit();
            return new Gson().toJson(animal);
       } finally{
            em.close();
       }
    }
    
    @Path("/animalbytype/{type}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getAnimalByType(@PathParam("type") String type) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            TypedQuery<Animal> query = 
                    em.createQuery("SELECT a FROM Animal a WHERE a.type = :TYPE", Animal.class);
            query.setParameter("TYPE", type);
            Animal animal = null;
            String result = "";
            try {
                animal = query.getSingleResult();
                result = new Gson().toJson(animal.toString());
            } catch(Exception ex) {
                result = new Gson().toJson(animal);
            }
            em.getTransaction().commit();
            return result;
        } finally {
            em.close();
        }
    }
    
    @Path("random_animal")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getRandomAnimal() {
        EntityManager em = emf.createEntityManager();
        int randNum = getRandIDFromDB();
        try {
            em.getTransaction().begin();
            TypedQuery<Animal> query = 
                    em.createQuery("SELECT a FROM Animal a WHERE a.id = :randNum", Animal.class);
            query.setParameter("randNum", randNum);
            Animal randAnimal = query.getSingleResult();
            em.getTransaction().commit();
            return new Gson().toJson(randAnimal.toString());
        } finally {
            em.close();
        }
        
    }
    
    public static int getRandIDFromDB() {
        int result = 0;
        int max;
        int min;
        
        EntityManager em = emf.createEntityManager();
        try {
            // Get max ID:
            em.getTransaction().begin();
            Query queryMax = em.createQuery("SELECT max(a.id) FROM Animal a");
            max = (int) queryMax.getSingleResult();
            System.out.println(max);
            em.getTransaction().commit();
            
            // Get min ID:
            em.getTransaction().begin();
            Query queryMin = em.createQuery("SELECT min(a.id) FROM Animal a");
            min = (int) queryMin.getSingleResult();
            System.out.println(min);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        
        // Generate a random number from the available ID's in the DB:
        /* 
            If there are holes in the DB's ID's, this code is not the best as it
            only takes care of the max and min ID in the DB.
        */
        Random rand = new Random();
        result = rand.nextInt(max - min + 1) + min;
        
        return result;
    }



}


