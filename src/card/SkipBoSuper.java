package card;

public class SkipBoSuper extends SkipBo {
    private Integer Number;

    public SkipBoSuper() {
        super(Type.SUPER);
        System.out.println(this.toString() + " created!");
    }

    public void setNumber(Integer number) {
        this.Number = number;
    }

    public Integer getNumber() {
        return this.Number;
    }

    @Override
    public String toString() {
        return super.toString() + " Super card" + ((Number == null) ? "" : " of number " + getNumber());
    }
}
