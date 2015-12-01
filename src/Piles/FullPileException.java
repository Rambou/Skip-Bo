package Piles;

public class FullPileException extends Exception {

    public FullPileException() {
        super("Η στοίβα έχει γεμίσει");
    }

    public FullPileException(String name, int MAX) {
        super("Η " + name + " έχει γεμίσει, μέγιστος αριθμός " + MAX + " κάρτες.");
    }

}