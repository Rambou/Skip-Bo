package Piles;

public class NullPileException extends Exception {

    public NullPileException(String name) {
        super("Η " + name + " είναι άδεια.");
    }

}
