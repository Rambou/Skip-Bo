package Piles;

import card.Card;

import java.util.ArrayList;

public class HandCollection implements PileInterface {

    public static final Integer MAX = 5;
    private ArrayList<Card> cards;

    public HandCollection() {
        cards = new ArrayList<>();
    }

    @Override
    public boolean add(Card card) {
        try {
            // Έλεγχος Αν "η παλάμη του χεριού" :) LOL, έχει γεμίσει
            if (isFull())
                throw new FullPileException(this.getClass().getSimpleName(), MAX);

            return cards.add(card);
        } catch (FullPileException e) {
            System.out.println(e);
            return false;
        }
    }

    @Override
    public Card get() throws NullPileException {
        if (isEmpty())
            throw new NullPileException(this.getClass().getSimpleName());

        return this.cards.get(this.cards.size() - 1);
    }

    public Card get(int i) {
        try {
            return this.cards.get(i);
        } catch (Exception e) {
            return null;
        }
    }

    public void remove(int i) {
        this.cards.remove(i);
    }

    public int size() {
        return this.cards.size();
    }

    @Override
    public boolean isEmpty() {
        return (this.cards.isEmpty());
    }

    @Override
    public boolean isFull() {
        return this.cards.size() == MAX;
    }

    @Override
    public String toString() {
        return (isEmpty()) ? "empty hand" : cards.toString() + " cards in hand";
    }
}
