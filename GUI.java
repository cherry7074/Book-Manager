import ecs100.*;
/**
 * Write a description of class GUI here.
 *
 * @author Katelyn Young
 * @version 10/04/2025
 */
public class GUI {
  // instance variables
  private Manager books;
  private Book book;

  /**
  * Constructor for objects of class GUI.
  */
  public GUI() {
    // initialise instance variables
    books = new Manager();  //instantiate the books

    // Set up GUI
    UI.initialise();
    UI.addButton("Print All", books::printBooks);
    UI.addButton("Add", this::addBooks);
    UI.addButton("Find", this::findBook);
    UI.addButton("Remove", books::removeBook);
    UI.setMouseListener(this::doMouse);
    UI.addButton("Quit", UI::quit);
  }

    /**
     *Add a book to collection.
     */
    public void addBooks() {
        // force a range of quantity
        final int MAX_QUANTITY = 99; // max quantity it can add to the hashmap
        int numLikes;
        String name;
        String author;
        boolean repeatedBook = false;

        //Ask the user for book name and author
        do {
            name = getString("Title: ");
            author = getString("Author: ");
            if ((this.books.findBook(name)
            && this.books.findBook(author))) {
                UI.println("Book and Author");
                repeatedBook = true;
            }
        } while (repeatedBook);

        //Check boundaries for the number of books added
        do {
            numLikes = UI.askInt("Quantity: ");
            if ((numLikes > 0) && (numLikes <= MAX_QUANTITY)) {
                UI.println("Added");
            } else if (numLikes > MAX_QUANTITY) {
                UI.println("Must be less than 100");
            } else if (numLikes < 1) {
                UI.println("Must be greater than 0");
            } else {
                UI.println("Must be a number!");
            }
        } while (0 > numLikes || numLikes > MAX_QUANTITY);
        // add a book image for display
        String imgFileName = UIFileChooser.open("Choose Image File: ");

        // add books with images
        this.books.addBook(name, author, numLikes, imgFileName);
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
     * Finds book based on name.
     * Prints out the author and qty if found
     */
    public void findBook() {
        String bookName = UI.askString("Name of Book: ");
        // Refer back to Manager method findBook to return true or false
        if (this.books.findBook(bookName.toLowerCase().trim())) {
            UI.println("Found Book!");

            this.book = books.getBook();
            this.book.displayBook();
            UI.println("Author: " + this.book.getAuthor());
            UI.println("Quantity: " + this.book.getLikes());
        } else {
            UI.println("That book does not exist!");
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
        if (action.equals("clicked") && (this.book != null)) {
            int getLikes = this.book.likeBook(x, y);
            UI.println("Likes: " + getLikes);
        }
    }

    /**
     * main routine.
     * @param args
     */
    public static void main(final String[] args) {
        new GUI();
    }
}
