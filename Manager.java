import java.util.HashMap;
import ecs100.*;
/**
 * Write a description of class Manager here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Manager {
    // instance variables
    private HashMap<Integer, Book> library; // Declaring the HashMap(list)
    private int currBookId; // Stores the current id of the book being added
    private Book currBook; // Stores the instance of the current Book

    // Stores the values for adding a book
    private String author;
    private int quantity;

    /**
     * Constructor for objects of class Manager.
     */
    public Manager() {
        // initialise instance variables
        library = new HashMap<Integer, Book>();

        // Create Books
        Book b1 = new Book(1, "The Wicked King", "Holly Black", 12);
        Book b2 = new Book(2, "Binding 13", "Chole Walsh", 1);
        Book b3 = new Book(3, "Harry Potter", "JK. Rowling", 36);

        // Add books to the library
        library.put(1, b1);
        library.put(2, b2);
        library.put(3, b3);

        this.currBookId = 3; // Stores the current book Id number
    }

    /**
     * Menu to print all books and call appropriate methods.
     */
    public void menu() {
        String choice;
        boolean valid = true;
        String title;
        do {
            UI.println("(A)dd a book");
            UI.println("(F)ind a book");
            UI.println("(P)rint all books");
            UI.println("(R)emove a book");
            UI.println("(Q)quit");

            choice = UI.askString("Enter a Choice: ");
            if (choice.equalsIgnoreCase("A")) {
                // Add Book
                addBooks();
            } else if (choice.equalsIgnoreCase("F")) {
                // Find Book
                title = UI.askString("Search book title: ");
                author = UI.askString("Author of that book: ");
                if (findBook(title, author)) {
                    UI.println("Book: " + currBook.getTitle() + " found!");
                }
            } else if (choice.equalsIgnoreCase("P")) {
                // Print all books
                printBooks();
            } else if (choice.equalsIgnoreCase("Q")) {
                // Quit the UI
                UI.println("Goodbye!");
                UI.quit();
            } else if (choice.equalsIgnoreCase("R")) {
                //Remove a book from Library
                removeBook();
            } else {
                UI.println("Invalid Response!");
            }
        } while (!choice.equalsIgnoreCase("Q"));
    }

    /**
     * Increment the book Id by one.
     */
    public void setBookId() {
        this.currBookId += 1;
    }

    /**
     * Add a book to the HashMap.
     * @param title
     * @param author
     * @param quantity
     * @param img
     */
    public void addBook(final String title, final String author,
    final int quantity, final String img) {
        this.setBookId();
        
        library.put(currBookId, new Book(currBookId, title,
        author, quantity, img));
    }
    
    /**
     *Add a book to collection.
     */
    public void addBooks() {
        // force a range of quantity
        final int MAX_QUANTITY = 99; // max quantity it can add to the hashmap
        int quantity;
        String name;
        String author;

        //Ask the user for book name and author
        name = getString("Title: ");
        author = getString("Author: ");
        if (this.validBook(name, author)) {
            UI.println("Book has already been added.");
        } else {
            //Check boundaries for the number of books added
            do {
                quantity = UI.askInt("Quantity: ");
                if ((quantity > 0) && (quantity <= MAX_QUANTITY)) {
                    UI.println("Added");
                } else if (quantity > MAX_QUANTITY) {
                    UI.println("Must be less than 100");
                } else if (quantity < 1) {
                    UI.println("Must be greater than 0");
                } else {
                    UI.println("Must be a number!");
                }
            } while (0 > quantity || quantity > MAX_QUANTITY);
            // add a book image for display
            String imgFileName = UIFileChooser.open("Choose Image File: ");

            // add books with images
            this.addBook(name, author, quantity, imgFileName);
        }
    }

    /**
     * Find a book based on it's name.
     * @param name
     * @return false
     **/
     public boolean findBook(final String name, final String author) {
         // Find book
         for (int bookId: library.keySet()) {
             if (library.get(bookId).getTitle().toLowerCase()
             .equals(name.toLowerCase()) 
             && (library.get(bookId).getAuthor().equals(author))) {
                 currBook = library.get(bookId);
                 //library.get(bookId).displayBook();
                 return true;
             }
         }
         return false;
     }
     
    /**
     * Finds book based on name.
     * Prints out the author and qty if found
     */
    public void findBooks() {
        String bookName = UI.askString("Name of Book: ");
        String bookAuthor = UI.askString("Author of Book: ");
        // Refer back to Manager method findBook to return true or false
        if (this.findBook(bookName.toLowerCase().trim(), bookAuthor)) {
            UI.println("Found Book!");
            getBook();
            currBook.displayBook();
            UI.println("Title: " + currBook.getTitle());
            UI.println("Author: " + currBook.getAuthor());
            UI.println("Quantity: " + currBook.getQuantity());
        } else {
            UI.println("That book does not exist!");
        }
    }

    /**
     * Doesn't allow the same book to be added.
     */
    public boolean validBook(final String title, final String author) {
        //Checks each book in the library to see if there are duplicates
        for (Book eachBook: library.values()){
            if (eachBook.getTitle().toLowerCase().equals(title.toLowerCase())
            && eachBook.getAuthor().toLowerCase().equals(author.toLowerCase())){
                return true;
            }
        }
        return false;
    }

    /**
     * Print all books in the Library.
     */
    public void printBooks() {
        //Print all books
        int numList = 1;
        for (int bookId: library.keySet()) {
            UI.println(numList++ + " Details:");
            UI.println(library.get(bookId).getTitle() + " by "
                        + library.get(bookId).getAuthor() + "\nQuantity: "
                        + library.get(bookId).getQuantity());
        }
    }

    /**
     * Remove book from library.
     */
    public void removeBook() {
        String title = UI.askString("Title: ");
        String author = UI.askString("Author: ");
        findBook(title, author);

        if (findBook(title, author)) {
            library.remove(currBook.getId());
            currBook = null;
            UI.println(title + " was Removed");
        } else {
            UI.println("Cannot find that book");
        }
        }
        
    /**
     * Add likes when click the book cover.
     * @param action
     * @param x
     * @param y
     */
    public void doMouse(final String action, final double x, final double y) {
        //UI.println(action + ":" + x + ":" + y);
        int getLikes = 0;
        if (action.equals("clicked") && (currBook != null)) {
            getLikes = currBook.likeBook(x, y);
            UI.println("Likes: " + getLikes++);
        }
    }
        
    /**
     * Validates String.
     * @param prompt
     * @return string
     */
    public String getString(final String prompt) {
        String string;
        do {
            string = UI.askString(prompt).trim();
            if (string.isEmpty()) {
                UI.println("Please input something!");
            }
        } while (string.isEmpty());
        return string;
    }

     /**
     * Book Getter.
     * @return an instance of Book class
     */
    public Book getBook() {
        return this.currBook;
    }
    
    public static void main(final String[] args) {
        new Manager();
    }
    }