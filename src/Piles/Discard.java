package Piles;

import card.Card;

import java.util.LinkedList;

public class Discard implements PileInterface {

    private final LinkedList<Card> cards;

    public Discard() {
        this.cards = new LinkedList<>();
    }

    @Override
    public boolean add(Card card) {
        // Στοίβαξη κάρτας (χωρίς περιορισμούς)
        return cards.add(card);
    }

    @Override
    public Card get() throws NullPileException {
        // Έλεγχος αν είναι άδεια
        if (isEmpty())
            throw new NullPileException(this.getClass().getSimpleName());
        // Επιστροφή της πάνω κάρτας στην στοίβα
        return cards.getLast();
    }

    public void remove() {
        // Διαγραφή της πάνω κάρτας στην στοίβα
        this.cards.removeLast();
    }

    public int size() {
        return cards.size();
    }

    @Override
    public boolean isEmpty() {
        // Έλεγχος Αν η στοίβα είναι άδεια
        return this.cards.isEmpty();
    }

    @Override
    public boolean isFull() {
        // η στοίβα δεν γεμίζει ποτέ
        return false;
    }

    @Override
    public String toString() {
        return cards.toString();
    }
}
