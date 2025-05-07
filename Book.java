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
    private int numLikes;
    private String image;
    static final String DEFAULT_IMAGE = "bookCover.jpg";
    private int clickBook = 0;

    /**
     * Constructor for objects of class Book.
     * @param key
     * @param nm
     * @param auth
     * @param likes
     * @param img
     */
    public Book(final int key, final String nm, final String auth,
    final int likes, final String img) {
        // initialise instance variables
        this.id = key;
        this.title = nm;
        this.author = auth;
        this.numLikes = likes;
        if (img == null) {
            this.image = this.DEFAULT_IMAGE; // Add default book image
        } else {
            this.image = img;
        }
    }

    /**
     * Constructor overloading.
     * Set DEFULT_IMAGE to object
     * @param key
     * @param nm
     * @param auth
     * @param likes
     */
    public Book(final int key, final String nm, final String auth,
    final int likes) {
        this(key, nm, auth, likes, DEFAULT_IMAGE);
    }

    /**
     * Display image on GUI.
     */
    public void displayBook() {
        int locX = 100; // image x start position
        int locY = 100; // image y start position

        final double WIDTH = 250;
        final double HEIGHT = 300;

        UI.drawImage(this.image, locX, locY, WIDTH, HEIGHT);
    }

    /**
     * Get the Id.
     * @return id
     */
    public int getId() {
        return this.id;
    }

    /**
     * Get the title of the book.
     * @return title
     */
    public String getTitle() {
        return this.title;
    }

    /**
     * Get the author of the book.
     * @return author
     */
    public String getAuthor() {
        return this.author;
    }

    /**
     * Get the number of likes.
     * @return numLikes
     */
    public int getLikes() {
        return this.numLikes;
    }

    /**
     * Checks if the mouse is over the book cover.
     * @return clickBook
     * @param a
     * @param b
     */
    public int likeBook(final double a, final double b) {
        if ((a >= 100) && (a <= 350) && (b >= 100) && (b <= 400)) {
            clickBook++;
        }
        return clickBook;
    }
}
