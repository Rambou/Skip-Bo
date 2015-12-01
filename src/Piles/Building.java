package Piles;

import card.Card;
import card.Numeric;
import card.SkipBo;
import card.SkipBoSuper;

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

        // �� � ������ ����� ������ �������� ��
        if (isFull()) {
            this.cards.removeAll(cards);
        }

        // �� � ������ ����� ����� ���� ���� ��� � ������� ��� ���������� ������ ���� ������ ����� 0, ������ ���� ��� ������ ��� ������
        int current = 0;
        if (!isEmpty()) {
            current = ((Numeric) cards.getLast()).getNumber();
        }

        // ������� ��� ��� ���� ��� ������ ��� ���������� ������� ��� �������� ��� ���� ������
        if (card.isType(Card.Type.NUMERIC)) {
            if (((Numeric) card).getNumber() == current + 1) {
                cards.add(card);
            } else {
                // System.out.println("Cannot add a card less or equal than " + ((Numeric) cards.getLast()).getNumber());
                return false;
            }
        } else { // �� � ����� ����� ��������� ������ ��� ���� ��� ��� �������� �����
            if (((SkipBo) card).isType(SkipBo.Type.SUPER)) {
                // �� ����� ����� �������� �� �� ������� ��� ������� ����� ����������� ���������� �� ���������
                if (isEmpty())
                    cards.add(new Numeric(((SkipBoSuper) card).getNumber()));
                else
                    cards.add(new Numeric(current + 1));
            } else if (((SkipBo) card).isType(SkipBo.Type.ERASER)) {
                // ������� ��� �������
                this.cards.removeAll(cards);
            } else { // �� ����� ��������� ������ ���� 1
                cards.add(new Numeric(current + 1));
            }
        }
        return true;
    }

    @Override
    public Card get() throws NullPileException {
        if (isEmpty())
            throw new NullPileException(this.getClass().getSimpleName());
        // ��������� ��� ���� ������ ���� ������
        return cards.getLast();
    }

    @Override
    public boolean isEmpty() {
        // ������� �� � ������ ����� �����
        return this.cards.isEmpty();
    }

    @Override
    public boolean isFull() {
        // �� ����� ����� ���� ��� ����� ������
        if (isEmpty()) {
            return false;
        }

        // �� � ����� ���� ���� ������ ����� ������� ��� 12 ���� ����� ������
        if (cards.getLast() instanceof Numeric) {
            Numeric card = (Numeric) cards.getLast();
            return card.getNumber() == 12;
        } else { // ������ �� ����� ��������� ����� eraser ���� � ������ ����� ������
            SkipBo card = (SkipBo) cards.getLast();
            return card.isType(SkipBo.Type.ERASER);
        }
    }

    @Override
    public String toString() {
        return cards.toString();
    }
}
