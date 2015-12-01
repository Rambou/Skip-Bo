package card;

public abstract class Card {
    private final Type type;

    public enum Type {
        NUMERIC, SKIPBO
    }

    public Card(Type type) {
        this.type = type;
    }

    public Boolean isType(Type type) {
        return (this.type == type);
    }

    @Override
    public String toString() {
        return type.toString();
    }
}