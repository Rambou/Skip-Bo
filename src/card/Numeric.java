package card;

public class Numeric extends Card {
    private final int Number;

    public Numeric(int number) {
        super(Type.NUMERIC);
        this.Number = number;
        System.out.println(this.toString() + " created!");
    }

    public Integer getNumber() {
        return Number;
    }

    @Override
    public String toString() {
        return super.toString() + " card of number " + getNumber();
    }
}
