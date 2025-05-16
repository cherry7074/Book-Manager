import ecs100.*;
/**
 * Write a description of class Book here.
 *
 * @author Katelyn Young
 * @version 7/04/2025
 */

public class Book {
    // instance variables
    private int id;
    private String title;
    private String author;
    private int quantity;
    private String image;
    static final String DEFAULT_IMAGE = "bookCover.jpg";
    private int clickBook = 0;
    

    /**
     * Constructor for objects of class Book
     */
    public Book(int key, String nm, String auth, int amount, String img){
        // initialise instance variables
        this.id = key;
        this.title = nm;
        this.author = auth;
        this.quantity = amount;
        if (img == null){
            this.image = this.DEFAULT_IMAGE; // Adds the defult book image when used clicks cancel
        } else{
            this.image = img;
        }
    }
    
    /**
     * Constructor overloading
     * Set DEFULT_IMAGE to object
     */
    public Book(int key, String nm, String auth, int quant){
        this(key, nm, auth, quant, DEFAULT_IMAGE);
    }
    
    /**
     * Display image on GUI
     */
    public void displayBook() {
        int locX = 100; // image x start position
        int locY = 100; // image y start position
        
        final double WIDTH = 250;
        final double HEIGHT = 300;
        
        UI.drawImage(this.image, locX, locY, WIDTH, HEIGHT);
    }
    
    /**
     * Get the Id
     */
    public int getId() {
        return this.id;
    }
    
    /**
     * Get the title of the book
     */
    public String getTitle() {
        return this.title;
    }
    
    /**
     * Get the author of the book
     */
    public String getAuthor() {
        return this.author;
    }
    
    /**
     * Get the number of likes
     */
    public int getQuantity() {
        return this.quantity;
    }
    
    /**
     * Checks if the mouse is over the book cover
     */
    public int likeBook(double a, double b){
        if ((a >= 100) &&(a <= 350) &&
            (b >= 100) && (b <= 400)){
                clickBook ++; 
        } 
        return clickBook;
    }
}