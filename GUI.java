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

    /**
    * Constructor for objects of class GUI.
    */
    public GUI() {
        // initialise instance variables
        books = new Manager();  //instantiate the books
        
        // Set up GUI
        UI.initialise();
        UI.addButton("Print All", books::printBooks);
        UI.addButton("Add", books::addBooks);
        UI.addButton("Find", books::findBooks);
        UI.addButton("Remove", books::removeBook);
        UI.setMouseListener(books::doMouse);
        UI.addButton("Quit", UI::quit);
    }

    /**
     * main routine.
     * @param args
     */
    public static void main(final String[] args) {
        new GUI();
    }
}