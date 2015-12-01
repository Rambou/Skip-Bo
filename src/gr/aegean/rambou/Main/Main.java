package gr.aegean.rambou.Main;

import card.SkipBoSuper;

public class Main {

    public static void main(String[] args) {
        // Δημιουργία παικτών
        Player player1 = new Player("Rambou");
        Player player2 = new Player("Armageddonas");

        // Δημιουργία μηχανής
        GameEngine ge = new GameEngine(player1, player2);

        // Ποιές κάρτες έχουν απομείνει στην drawpile
        ge.printDrawpile();

        // Τύπωση μηνύματος σχετικά με τις πληροφορίες των παικτών
        ge.printPlayers();

        // Tύπωση των building piles
        ge.printBuidPiles();

        /*  Functional testing
         *  Δημιουργήθηκε ώστε να γίνει έλεγχος πως όλα βαίνουν καλά
         *  παρακάτω το παιχνίδι παίζεται αυτόματα από τους 2 παίκτες μέχρι
         *  να τελειώσει. Δεν έχει γίνει κάποιο τρελό AI...
         */
        while (!ge.GameOver()) {
            // Ποίος παίζει τώρα
            Player curPlayer = ge.whosPlaying();
            System.out.println(curPlayer);

            // Γέμισμα καρτών στα χέρια του πάικτη
            curPlayer.fillHand(ge.getDraw());

            // Έλεγχος αν η κάρτα στα χέρια ή στην stock είναι Super SkipBo
            for (int i = 0; i < curPlayer.getHand().size(); i++)
                if (curPlayer.getHand().get(i) instanceof SkipBoSuper) {
                    ((SkipBoSuper) curPlayer.getHand().get(i)).setNumber(5);
                }
            try {
                if (curPlayer.getStock().get() instanceof SkipBoSuper) {
                    ((SkipBoSuper) curPlayer.getStock().get()).setNumber(5);
                }
            } catch (Exception e) {
            }

            endTurn:
            while (true) {
                // Έλεγχος πρώτα αν ταιρίαζει η κάρτα από την stock στην building Pile
                for (int i = 0; i < 4; i++) {
                    if (ge.stockToBuild(curPlayer, i))
                        continue endTurn;
                }

                // Αλλιώς έλεγξε αν ταιριάζει κάποια κάρτα από τα χέρια του στην building Pile
                for (int i = 0; i < curPlayer.getHand().size(); i++) {
                    for (int j = 0; j < 4; j++)
                        if (ge.handToBuild(curPlayer, i, j))
                            continue endTurn;
                }

                // Αλλιώς κάποια κάρτα από την discard του στην building Pile
                for (int i = 0; i < 4; i++) {
                    for (int j = 0; j < 4; j++)
                        ge.discardToBuild(curPlayer, i, j);
                }

                // Διαφορετικά παίξε μια κάρτα από τα χέρια του και βάλτη στην discard
                for (int i = 0; i < 4; i++) {
                    if (curPlayer.getDiscard(i).isEmpty() || (curPlayer.getDiscard(i).size() == curPlayer.getDiscard((i + 1) % 4).size())) {
                        for (int j = 0; j < curPlayer.getHand().size(); j++) {
                            if (ge.handToDiscard(curPlayer, j, i)) {
                                break endTurn;
                            }
                        }
                    }
                }
                for (int j = 0; j < curPlayer.getHand().size(); j++) {
                    if (ge.handToDiscard(curPlayer, j, 0)) {
                        break endTurn;
                    }
                }

            }

            // Tύπωση των building piles
            ge.printBuidPiles();
        }

    }
}
