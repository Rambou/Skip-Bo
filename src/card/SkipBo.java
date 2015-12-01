package card;

public class SkipBo extends Card {

    private final Type type;

    public enum Type {
        SUPER, ERASER
    }

    public SkipBo() {
        super(Card.Type.SKIPBO);
        this.type = null;
        System.out.println(this.toString() + " card created!");
    }

    public SkipBo(Type type) {
        //super((tp == Type.SUPER) ? Card.Type.SKIPBO_SUPER : Card.Type.SKIPBO_ERASER);
        super(Card.Type.SKIPBO);
        this.type = type;
    }

    public Boolean isType(Type type) {
        return (this.type == type);
    }
}
