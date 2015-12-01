package Piles;

import card.Card;
import card.Numeric;

import java.util.LinkedList;

public class Stock implements PileInterface {

    public static final int MAX = 30;
    private final LinkedList<Card> Cards;

    public Stock() {
        Cards = new LinkedList();
    }

    @Override
    public boolean add(Card card) {
        try {
            if (isFull())
                throw new FullPileException(this.getClass().getSimpleName(), MAX);
            // Προσθήκη κάρτας στην στοίβα
            return Cards.add(card);
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    @Override
    public Card get() throws NullPileException {
        if (isEmpty())
            throw new NullPileException(this.getClass().getSimpleName());
        // Επιστρέφει πάνω κάρτα
        return Cards.getLast();
    }

    @Override
    public boolean isEmpty() {
        // Έλεγχος αν η στοίβα είναι άδεια
        return Cards.isEmpty();
    }

    @Override
    public boolean isFull() {
        // Έλεγχος Αν η στοίβα έχει γεμίσει
        return (Cards.size() == MAX);
    }

    @Override
    public String toString() {
        return Cards.size() + " cards in StockPile" + ((isEmpty()) ? "" : ", " + Cards.getLast().toString() + " on top");
    }

    public boolean remove() {
        if (isEmpty())
            return false;
        // Διαγραφή κάρτας από την στοίβα
        Cards.removeLast();
        return true;
    }

    public int size() {
        return Cards.size();
    }

    public int countPoints() {
        int i = 0;
        // Μετράει τους πόντους για όλες τις κάρτες
        for (Card c : Cards) {
            // Αν η κάρτα είναι αριθμός προσθέτει τον αριθμό διαφορετικά +15 για ειδική κάρτα
            i += c.isType(Card.Type.NUMERIC) ? ((Numeric) c).getNumber() : 15;
        }

        return i;
    }
}
