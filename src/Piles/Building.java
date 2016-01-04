package Piles;

import card.Card;
import card.Numeric;
import card.SkipBo;
import card.SkipBoSuper;

import javax.swing.*;
import java.util.LinkedList;

public class Building implements PileInterface {
    private final LinkedList<Card> cards;

    public Building() {
        this.cards = new LinkedList<>();
    }

    @Override
    public boolean add(Card card) {
        if (card == null)
            return false;

        // Αν η στοίβα είναι γεμάτη καθάρισε τη
        if (isFull()) {
            this.cards.removeAll(cards);
        }

        // Αν η στοίβα είναι άδεια τότε θέσε ότι ο αριθμός της υπάρχουσας κάρτας στην στοίβα είναι 0, αλλιώς πάρε τον αριθμό της κάρτας
        int current = 0;
        if (!isEmpty()) {
            current = ((Numeric) cards.getLast()).getNumber();
        }

        // Έλεγχος για τον τύπο της κάρτας και κατάλληλος έλεγχος για εισαγωγή της στην στοίβα
        if (card.isType(Card.Type.NUMERIC)) {
            if (((Numeric) card).getNumber() == current + 1) {
                cards.add(card);
            } else {
                // System.out.println("Cannot add a card less or equal than " + ((Numeric) cards.getLast()).getNumber());
                return false;
            }
        } else { // Αν η κάρτα είναι Μπαλαντέρ έλεγξε τον τύπο του και αναλόγως πράξε
            if (((SkipBo) card).isType(SkipBo.Type.SUPER)) {
                // Αν είναι άδεια ξεκινάει με το νούμερο που δηλώνει αυτός διαφορετικά λειτουργεί ως μπαλαντέρ
                if (isEmpty()) {
                    String[] numbers = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"};
                    String Number = (String) JOptionPane.showInputDialog(null,
                            "Τι θέλεις να κάνεις την Super SkipBo;",
                            "Επίλεξε αριθμό",
                            JOptionPane.QUESTION_MESSAGE,
                            null,
                            numbers,
                            numbers[0]);
                    ((SkipBoSuper) card).setNumber(Integer.valueOf(Number));
                    cards.add(new Numeric(((SkipBoSuper) card).getNumber()));
                } else
                    cards.add(new Numeric(current + 1));
            } else if (((SkipBo) card).isType(SkipBo.Type.ERASER)) {
                // Αν είναι ήδη άδεια τότε χρησιμοποιείτε σαν μια απλή SkipBo
                if (isEmpty())
                    cards.add(new Numeric(current + 1));
                else
                    // Άδεισμα της στοίβας
                    this.cards.removeAll(cards);
            } else { // Αν είναι μπαλαντέρ αύξηση κατά 1
                cards.add(new Numeric(current + 1));
            }
        }
        return true;
    }

    @Override
    public Card get() throws NullPileException {
        if (isEmpty())
            throw new NullPileException(this.getClass().getSimpleName());
        // Επιστροφή της πάνω κάρτας στην στοίβα
        return cards.getLast();
    }

    @Override
    public boolean isEmpty() {
        // Έλεγχος αν η στοίβα είναι άδεια
        return this.cards.isEmpty();
    }

    @Override
    public boolean isFull() {
        // Αν είναι άδεια τότε δεν είναι γεμάτη
        if (isEmpty()) {
            return false;
        }

        // Αν η κάρτα μέσα στην στοίβα είναι αριθμός και 12 τότε είναι γεμάτη
        if (cards.getLast() instanceof Numeric) {
            Numeric card = (Numeric) cards.getLast();
            return card.getNumber() == 12;
        } else { // Αλλιώς αν είναι μπαλαντέρ τύπου eraser τότε η στοίβα είναι γεμάτη
            SkipBo card = (SkipBo) cards.getLast();
            return card.isType(SkipBo.Type.ERASER);
        }
    }

    @Override
    public String toString() {
        return cards.toString();
    }
}
