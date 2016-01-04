package gr.aegean.rambou.Main;

import Piles.*;

public class Player {

    private final String Name;
    private Integer Points;
    private Stock stock;
    private Discard[] discard;
    private HandCollection hand;

    public Player(String name) {
        this.Name = name;
        this.Points = 0;
        this.hand = new HandCollection();
        this.stock = new Stock();
        this.discard = new Discard[4];
        for (int i = 0; i < 4; i++)
            discard[i] = new Discard();
        System.out.println("Player " + name + " created!");
    }

    public Integer getPoints() {
        return Points;
    }

    public void setPoints(int points) {
        this.Points += points;
    }

    // Τοποθέτηση συγκεκριμένης κάρτας από το χέρι σε συγκεκριμένο Pile στο discard
    public boolean handToDiscard(int card, int pile) {
        try {
            this.discard[pile].add(hand.get(card));
            hand.remove(card);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    // Τοποθέτηση συγκεκριμένης κάρτας από συγκεκριμένο Pile του discard στο Building
    public boolean discardToBuild(Building bpile, int pile) {
        try {
            if (discard[pile].isEmpty())
                return false;

            /* Προσπαθεί να προσθέσει κάρτα από συγκεκριμένη discard στο build pile.
            Αν τα καταφέρει επιστρέφει true και αφαιρεί την κάρτα από το stock */
            if (bpile.add(discard[pile].get())) {
                discard[pile].remove();
                return true;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }

    // Τοποθέτηση συγκεκριμένης συγκεκριμένης κάρτας από το χέρι στο Building
    public boolean handToBuild(Building bpile, int card) {
        try {
            if (hand.isEmpty())
                return false;

            /* Προσπαθεί να προσθέσει κάρτα από το χέρι στο build pile.
            Αν τα καταφέρει επιστρέφει true και αφαιρεί την κάρτα από το stock */
            if (bpile.add(hand.get(card))) {
                hand.remove(card);
                return true;
            }
        } catch (Exception e) {
            System.out.println("Χεράκι: " + e);
            e.printStackTrace();
        }
        return false;
    }

    // Τοποθέτηση κάρτας από το stock στο Building pile
    public boolean stockToBuild(Building bpile) {
        try {
            /* Προσπαθεί να προσθέσει την κάρτα από το stock στο build pile.
            Αν τα καταφέρει επιστρέφει true και αφαιρεί την κάρτα από το stock */
            if (bpile.add(stock.get())) {
                stock.remove();
                return true;
            }
        } catch (NullPileException e) {
            System.out.println("StockToBuild: " + e);
        }
        return false;
    }

    public void fillHand(Draw dpile) {
        System.out.print("Μοιράστηκαν στα χέρια του " + this.getName() + " οι κάρτες ");
        // Γεμίζει το χέρι με κάρτες από το drawpile
        try {
            while (!hand.isFull()) {
                hand.add(dpile.get());
            }
            System.out.println("");
        } catch (NullPileException e) {
            System.out.println("\nΗ draw άδειασε, τελευταίος γύρος!");
        }
    }

    public boolean won() {
        return this.stock.isEmpty();
    }

    public Discard getDiscard(int i) {
        return discard[i];
    }

    public Stock getStock() {
        return stock;
    }

    public void setStock(Draw dpile) {
        // Κατασκευάζει το stock του παίκτη από το drawpile
        try {
            while (!stock.isFull()) {
                stock.add(dpile.get());
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public HandCollection getHand() {
        return hand;
    }

    public String getName() {
        return Name;
    }

    // Τύπωση μηνύματος σχετικά με τις πληροφορίες των παικτών
    public String getDiscard() {
        String temp = "Οι 4 Discard piles:\n";
        for (int i = 0; i < 4; i++)
            temp += discard[i].toString() + "\n";
        return temp;
    }

    @Override
    public String toString() {
        return "Player " + Name + " with " + (Points == 0 ? "no" : Points) + " points and " +
                stock + ", \n" + hand.toString() + ", \n" + getDiscard();
    }
}
