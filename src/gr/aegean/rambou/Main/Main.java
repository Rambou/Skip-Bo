package gr.aegean.rambou.Main;

import Piles.NullPileException;
import card.SkipBoSuper;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class Main extends JFrame {

    // Γραφικά στοιχεία
    private JButton btn_start;
    private JTextField txt_player1;
    private JTextField txt_player2;
    private JLabel lb_player1;
    private JLabel lb_player2;

    private Main() {
        // Τίτλος παραθύρου
        super("Επιλογή παικτών");

        // Εικονίδιο παραθύρου
        try {
            setIconImage(ImageIO.read(getClass().getClassLoader().getResource("card/logo.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Αρχικοποίηση γραφικών στοιχείων
        txt_player1 = new javax.swing.JTextField();
        lb_player1 = new javax.swing.JLabel();
        txt_player2 = new javax.swing.JTextField();
        lb_player2 = new javax.swing.JLabel();
        btn_start = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Τοποθεσία εκκίνησης παραθύρου στην οθόνη

        // ιδιότητες παραθύρου
        setVisible(true);
        setResizable(false);

        lb_player1.setText("Παίκτης 1");
        lb_player2.setText("Παίκτης 2");
        txt_player1.setText("Μαρία");
        txt_player2.setText("Γιώργος");
        btn_start.setText("Νέο Παιχνίδι");

        // Listeners γραφικών στοιχείων
        btn_start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Έλεγχος για διαφορετικά ονόματα παικτών
                if (txt_player1.getText().trim().equals(txt_player2.getText())) {
                    JOptionPane.showMessageDialog(btn_start,
                            "Οι δυο παίκτες πρέπει να έχουν διαφορετικό όνομα.",
                            "Προσοχή",
                            JOptionPane.WARNING_MESSAGE);
                } else {
                    try {
                        Table table = new Table(txt_player1.getText(), txt_player2.getText());
                    } catch (NullPileException e1) {
                    }
                    dispose();
                }
            }
        });

        // Στοίχιση στοιχείων
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(lb_player1)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(txt_player1, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(lb_player2)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(txt_player2, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btn_start)
                                .addGap(75, 75, 75))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(txt_player1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lb_player1))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(txt_player2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lb_player2))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btn_start)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }

    public static void main(String[] args) {
        // Δημιουργία Παραθύρου
        new Main();

        // Check for testing
        if (args.length == 0 || !args[0].equals("test")) return;

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
