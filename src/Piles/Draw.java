package Piles;

import card.*;

import java.util.Collections;
import java.util.LinkedList;

public class Draw implements PileInterface {

    public final static int MAX = 160;
    private final LinkedList<Card> Cards;

    public Draw() {
        this.Cards = new LinkedList<>();

        // ���������� ��� ��������� ��� ���������
        generate();
        shuffle();
    }

    private void generate() {
        // ���������� ��� 144 ������ ����������� ��� 1-12
        for (int i = 0; i < 12; i++)
            for (int j = 1; j < 13; j++)
                Cards.add(new Numeric(j));

        // ���������� ��� Skip Bo ������
        for (int i = 0; i < 8; i++)
            Cards.add(new SkipBo());

        // ���������� ��� Skip Bo - Super ������
        for (int i = 0; i < 4; i++)
            Cards.add(new SkipBoSuper());

        // ���������� ��� Skip Bo - Eraser ������
        for (int i = 0; i < 4; i++)
            Cards.add(new SkipBoEraser());
    }

    public void shuffle() {
        // ��������� ������ �� ������ �����
        Collections.shuffle(Cards);
    }

    @Override
    public boolean add(Card card) {
        try {
            // ������� �� � ������ ���� �������
            if (isFull())
                throw new FullPileException(this.getClass().getSimpleName(), MAX);
            // �������� ������
            return this.Cards.add(card);
        } catch (FullPileException e) {
            System.out.println(e);
            return false;
        }
    }

    @Override
    public Card get() throws NullPileException {
        // ������� �� � ������ ����� ����� �� ��������� ���������
        if (isEmpty())
            throw new NullPileException(this.getClass().getSimpleName());

        System.out.print(this.Cards.getLast().toString() + ",");
        // ��������� ��� ������ ��� �������� ���
        return this.Cards.removeLast();
    }

    @Override
    public boolean isEmpty() {
        // ������� �� � ������ ����� �����
        return this.Cards.isEmpty();
    }

    @Override
    public boolean isFull() {
        // ������� �� � ������ ���� �������
        return this.Cards.size() == MAX;
    }

    @Override
    public String toString() {
        return "There are " + Cards.size() + " cards in the drawpile: " + Cards.toString();
    }
}
