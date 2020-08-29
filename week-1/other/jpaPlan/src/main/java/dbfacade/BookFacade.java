/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbfacade;

import entity.Book;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 *
 * @author Danie
 */
public class BookFacade {
    
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");      
        BookFacade facade = BookFacade.getBookFacade(emf);
        Book b1 = facade.addBook("Author 1");
        Book b2 = facade.addBook("Author 2");
        //Find book by ID
        System.out.println("Book1: "+facade.findBook(b1.getId()).getAuthor());
        System.out.println("Book2: "+facade.findBook(b2.getId()).getAuthor());
        //Find all books
        System.out.println("Number of books: "+facade.getAllBooks().size());

    }
    
    private static EntityManagerFactory emf;
    private static BookFacade instance;
    
    private BookFacade() {}
    
    /**
    * getBookFacade.
    * Is a singleton (only one instance).
    * @param _emf ensures that the EntityManagerFactory is the same 
    * throughout the BookFacade class.
    * @return the instance of BookFacade.
    */
    public static BookFacade getBookFacade(EntityManagerFactory _emf) {
        if(instance == null) {
            emf = _emf;
            instance = new BookFacade();
        }
        return instance;
    }
    
    /**
     * addBook.
     * Adds a book to the database.
     * @param author is used to create a Book instance.
     * @return the book that has been added to the DB.
     */
    public Book addBook(String author) {
        Book book = new Book(author);
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(book);
            em.getTransaction().commit();
            return book;
        } finally {
            em.close();
        }
    }
    
    /**
     * findBook.
     * Is used to get a book from the DB.
     * @param id the auto-incremented id from the DB.
     * @return the book linked to the id from the DB. 
     */
    public Book findBook(int id) {
        EntityManager em = emf.createEntityManager();
        try {
            Book book = em.find(Book.class, id);
            return book;
        } finally {
            em.close();
        }
    }
    
    /**
     * getAllBooks.
     * Is used to get a List of all the books in the DB.
     * @return a List of books.
     */
    public List<Book> getAllBooks() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Book> query = 
                    em.createQuery("Select book from Book book", Book.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }
    
}
