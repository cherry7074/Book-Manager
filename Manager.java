import java.util.HashMap;
import ecs100.*;
import java.util.Scanner;
/**
 * Write a description of class Manager here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Manager
{
    // instance variables 
    private HashMap<Integer, Book> library; // Declaring the HashMap(list)
    private int currBookId; // Stores the current id number of the book being added
    private Book currBook; // Stores the instance of the current Book
    
    // Stores the values for adding a book

    private String author;
    private int likes;
    
    /**
     * Constructor for objects of class Manager
     */
    public Manager()
    {
        // initialise instance variables
        library = new HashMap<Integer, Book>();
        
        // Create Books
        Book b1 = new Book(1, "The Wicked King", "Holly Black", 12);
        Book b2 = new Book(2, "Eleanor Oliphant is Completely Fine", "Gail Honeyman", 1);
        Book b3 = new Book(3, "Harry Potter", "JK. Rowling", 36);
        
        // Add books to the library
        library.put(1, b1);
        library.put(2, b2);
        library.put(3, b3);
        
        this.currBookId = 3; // Stores the current book Id number
    }
     
    /**
     * Menu to print all books and call appropriate methods
     */
    public void menu() {
        String choice;
        boolean valid = false;
        String title;
        do{
            UI.println("(A)dd a book");
            UI.println("(F)ind a book");
            UI.println("(P)rint all books");
            UI.println("(Q)quit");
            
            choice = UI.askString("Enter a Choice: ");
            
            if (choice.equalsIgnoreCase("A")) {
                // Add Book
                do {
                     title = UI.askString("Book Title: ").trim();
                    if (!title.isEmpty()) {
                        author = UI.askString("Author Name: ");
                        //addBook(title, author, likes);
                        if (!author.isEmpty()) {
                            likes = UI.askInt("Quantity: ");
                            if (!findBook(title) && !findBook(author)){
                                valid = true;
                            }
                        }
                    } 
                }while (valid = false);
            } else if (choice.equalsIgnoreCase("F")) {
                // Find Book
                title = UI.askString("Search book title: ");
                if (findBook(title)) {
                    UI.println("Book: " + currBook.getTitle() + " found!");
                }
            } else if (choice.equalsIgnoreCase("P")) {
                // Print all books
                printBooks();
            } else if (choice.equalsIgnoreCase("Q")) {
                // Quit the UI
                UI.println("Goodbye!");
                UI.quit();
            } else {
                UI.println("Invalid Response!");
            }
        } while(!choice.equalsIgnoreCase("Q")); 
    }
    
    /**
     * Increment the book Id by one 
     */
    public void setBookId(){
        this.currBookId += 1;
        
    }
    
    /**
     * Add a book to the HashMap
     */
    public void addBook(String title, String author, int likes, String img) {
        this.setBookId();
        
        library.put(currBookId, new Book(currBookId, title, author, likes, img));
    }
    
    /**
     * Find a book based on it's name
     **/
     public boolean findBook(String name) {
         // Find book
         for (int bookId: library.keySet()) {
             if (library.get(bookId).getTitle().toLowerCase().equals(name.toLowerCase())) {
                 currBook = library.get(bookId);
                 //library.get(bookId).displayBook(); // Show book cover on canvas
                 return true;
             }
         }
         return false;
     }
    
    /**
     * Print all books in the Library
     */
    public void printBooks() {
        //Print all books
        for (int bookId: library.keySet()) {
            UI.println(bookId + " Details:" );
            UI.println(library.get(bookId).getTitle() + " by "
                        + library.get(bookId).getAuthor() + ", Quanitity: "
                        + library.get(bookId).getLikes());
        }
    }
    
     /**
     * Book Getter
     * @return an instance of Book class
     */
    public Book getBook(){
        return this.currBook;
    }
    }

