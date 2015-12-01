package Piles;

import card.*;

import java.util.Collections;
import java.util.LinkedList;

public class Draw implements PileInterface {

    public final static int MAX = 160;
    private final LinkedList<Card> Cards;

    public Draw() {
        this.Cards = new LinkedList<>();

        // Δημιουργία και ανακάτεμα της τράπουλας
        generate();
        shuffle();
    }

    private void generate() {
        // Δημιουργία των 144 καρτών αριθμημένων από 1-12
        for (int i = 0; i < 12; i++)
            for (int j = 1; j < 13; j++)
                Cards.add(new Numeric(j));

        // Δημιουργία των Skip Bo καρτών
        for (int i = 0; i < 8; i++)
            Cards.add(new SkipBo());

        // Δημιουργία των Skip Bo - Super καρτών
        for (int i = 0; i < 4; i++)
            Cards.add(new SkipBoSuper());

        // Δημιουργία των Skip Bo - Eraser καρτών
        for (int i = 0; i < 4; i++)
            Cards.add(new SkipBoEraser());
    }

    public void shuffle() {
        // Ανακάτεμα καρτών με τυχαίο τρόπο
        Collections.shuffle(Cards);
    }

    @Override
    public boolean add(Card card) {
        try {
            // Έλεγχος Αν η στοίβα έχει γεμίσει
            if (isFull())
                throw new FullPileException(this.getClass().getSimpleName(), MAX);
            // Προσθήκη κάρτας
            return this.Cards.add(card);
        } catch (FullPileException e) {
            System.out.println(e);
            return false;
        }
    }

    @Override
    public Card get() throws NullPileException {
        // Έλεγχος Αν η στοίβα είναι άδεια με επιστροφή σφάλματος
        if (isEmpty())
            throw new NullPileException(this.getClass().getSimpleName());

        System.out.print(this.Cards.getLast().toString() + ",");
        // Επιστροφή της κάρτας και διαγραφή της
        return this.Cards.removeLast();
    }

    @Override
    public boolean isEmpty() {
        // Έλεγχος Αν η στοίβα είναι άδεια
        return this.Cards.isEmpty();
    }

    @Override
    public boolean isFull() {
        // Έλεγχος Αν η στοίβα έχει γεμίσει
        return this.Cards.size() == MAX;
    }

    @Override
    public String toString() {
        return "There are " + Cards.size() + " cards in the drawpile: " + Cards.toString();
    }
}
