package gr.aegean.rambou.Main;

import Piles.Building;
import Piles.Draw;

public class GameEngine {
    private final Player player1;
    private final Player player2;
    private Draw drawpile;
    private int turn;
    private final Building[] buidling;

    public GameEngine(Player p1, Player p2) {
        this.player1 = p1;
        this.player2 = p2;
        this.buidling = new Building[4];
        for (int i = 0; i < 4; i++)
            buidling[i] = new Building();
        this.turn = 0;

        // Δημιουργία στοίβας όλων των καρτών του παιχνιδιού
        this.drawpile = new Draw();
        System.out.println("\n[Η τράπουλα ανακατεμένη]:\n" + drawpile);

        // Δημιουργία Stockpiles των παικτών
        player1.setStock(drawpile);
        player2.setStock(drawpile);

        // Μοίρασμα καρτών στα χέρια των παικτών
        System.out.println("\n[Μοίρασμα καρτών στα χέρια των παικτών]:");
        player1.fillHand(drawpile);
        player2.fillHand(drawpile);
    }

    public void newGame() {
        // Καθαρισμός και αρχικοποίηση Building Pile
        for (int i = 0; i < 4; i++)
            buidling[i] = new Building();
        this.turn = 0;

        // Δημιουργία στοίβας όλων των καρτών του παιχνιδιού
        this.drawpile = new Draw();

        // Δημιουργία Stockpiles των παικτών
        player1.setStock(drawpile);
        player2.setStock(drawpile);

        // Μοίρασμα καρτών στα χέρια των παικτών
        System.out.println("\n[Μοίρασμα καρτών στα χέρια των παικτών]:");
        player1.fillHand(drawpile);
        player2.fillHand(drawpile);
    }

    public Player whosPlaying() {
        return ((turn++ % 2) == 0) ? player1 : player2;
    }

    public void printDrawpile() {
        System.out.println("\n" + drawpile);
    }

    public void printPlayers() {
        // Τύπωση μηνύματος σχετικά με τις πληροφορίες των παικτών
        System.out.println("\n" + player1.toString());
        System.out.println(player2.toString());
    }

    // Τύπωση μηνύματος σχετικά με τις πληροφορίες των παικτών
    public void printBuidPiles() {
        System.out.println("\nΟι 4 Building piles:");
        for (int i = 0; i < 4; i++)
            System.out.println(buidling[i].toString());
    }

    public boolean placeCardToBuild(int player, int hand, int discard, int bpile) {
        // έλεγχος αν το παινχίδι έχει τελειώσει
        if (GameOver()) {
            return false;
        }

        try {
            if (hand > -1) {
                this.buidling[bpile].add(((player == 1) ? player1 : player2).getHand().get(hand));
            } else {
                this.buidling[bpile].add(((player == 1) ? player1 : player2).getDiscard(discard).get());
            }
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }

    }

    public boolean GameOver() {
        // έλεγχος αν το παινχίδι έχει τελειώσει
        if (player1.won() || player2.won()) {
            System.out.println("Το παιχνίδι τελείωσε, με νικητή τον " + (player1.won() ? player1.getName() : player2.getName()));
            return true;
        }

        // Αν οι κάρτες στην drawpile έχουν τελειώσει
        if (drawpile.isEmpty()) {
            // Tύπωση των building piles
            printPlayers();
            printBuidPiles();

            Player winner;
            int points;
            if ((player1.getStock().size() < player2.getStock().size())) {
                points = player2.getStock().countPoints() - player1.getStock().countPoints();
                player1.setPoints((points < 50) ? 50 : points);
                winner = player1;
            } else {
                points = player1.getStock().countPoints() - player2.getStock().countPoints();
                player2.setPoints((points < 50) ? 50 : points);
                winner = player2;
            }

            System.out.println("Το παιχνίδι τελείωσε, με νικητή τον " + winner.getName() + ", με " + winner.getPoints() + " πόντους και " + winner.getStock().size() + " καρτες");
            return true;
        }

        return false;
    }

    public boolean stockToBuild(Player player, int bpile) {
        return player.stockToBuild(this.buidling[bpile]);

    }

    public boolean handToBuild(Player player, int hand, int bpile) {
        return player.handToBuild(this.buidling[bpile], hand);
    }

    public boolean handToDiscard(Player player, int hand, int dpile) {
        player.handToDiscard(hand, dpile);
        return true;
    }

    public boolean discardToBuild(Player player, int discard, int bpile) {
        return player.discardToBuild(this.buidling[bpile], discard);
    }

    public Draw getDraw() {
        return drawpile;
    }
}
