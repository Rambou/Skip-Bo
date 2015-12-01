package Piles;

import card.Card;

public interface PileInterface {

    boolean add(Card card);

    Card get() throws NullPileException;

    boolean isEmpty();

    boolean isFull();

}
