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
        // �������� ������ (����� ������������)
        return cards.add(card);
    }

    @Override
    public Card get() throws NullPileException {
        // ������� �� ����� �����
        if (isEmpty())
            throw new NullPileException(this.getClass().getSimpleName());
        // ��������� ��� ���� ������ ���� ������
        return cards.getLast();
    }

    public void remove() {
        // �������� ��� ���� ������ ���� ������
        this.cards.removeLast();
    }

    public int size() {
        return cards.size();
    }

    @Override
    public boolean isEmpty() {
        // ������� �� � ������ ����� �����
        return this.cards.isEmpty();
    }

    @Override
    public boolean isFull() {
        // � ������ ��� ������� ����
        return false;
    }

    @Override
    public String toString() {
        return cards.toString();
    }
}
