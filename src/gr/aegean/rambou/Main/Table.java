package gr.aegean.rambou.Main;

import Piles.NullPileException;
import card.UI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Table extends JFrame {

    private final Player player1;
    private final Player player2;
    private JPanel jPanel1;
    private JPanel jPanel2;
    private UI p1_hand1;
    private UI p1_hand2;
    private UI p1_hand3;
    private UI p1_hand4;
    private UI p1_hand5;
    private JLabel lbl_player2;
    private JLabel lbl_player1;
    private JLabel lbl_points1;
    private JLabel lbl_points2;
    private UI building1;
    private UI building2;
    private UI building3;
    private UI building4;
    private UI p2_hand1;
    private UI p2_hand2;
    private UI p2_hand3;
    private UI p2_hand4;
    private UI p2_hand5;
    private UI p1_discard1;
    private UI p1_discard2;
    private UI p1_discard3;
    private UI p1_discard4;
    private UI p2_discard1;
    private UI p2_discard2;
    private UI p2_discard3;
    private UI p2_discard4;
    private UI p1_stock;
    private UI p2_stock;
    private Player curPlayer;
    private UI selected;
    private GameEngine ge;

    public Table(String Player1, String Player2) throws NullPileException {
        // Τίτλος παραθύρου
        super("Παιχνίδι - Ταμπλό");

        initComponents();

        // Δημιουργία παικτών
        player1 = new Player(Player1);
        player2 = new Player(Player2);

        // Δημιουργία μηχανής
        ge = new GameEngine(player1, player2);

        // Ποιές κάρτες έχουν απομείνει στην drawpile
        ge.printDrawpile();

        // Τύπωση μηνύματος σχετικά με τις πληροφορίες των παικτών
        ge.printPlayers();

        // Tύπωση των building piles
        ge.printBuidPiles();

        // Θέτουμε τα ονόματα των παικτών στα JLabel components
        lbl_player1.setText(player1.getName());
        lbl_player2.setText(player2.getName());

        // Θέτουμε τις Stock κάρτες στα αντίστοιχα JButton
        p1_stock.setCard(player1.getStock().get());
        p2_stock.setCard(player2.getStock().get());

        whosPlaying();
    }

    private void whosPlaying() {
        // Ποίος παίζει τώρα
        curPlayer = ge.whosPlaying();
        System.out.println("Τώρα παίζει: " + curPlayer);

        if (curPlayer.equals(player1)) {
            p2_hand1.setEnabled(false);
            p2_hand2.setEnabled(false);
            p2_hand3.setEnabled(false);
            p2_hand4.setEnabled(false);
            p2_hand5.setEnabled(false);
            p2_stock.setEnabled(false);
            p2_discard1.setEnabled(false);
            p2_discard2.setEnabled(false);
            p2_discard3.setEnabled(false);
            p2_discard4.setEnabled(false);

            p1_discard1.setEnabled(true);
            p1_discard2.setEnabled(true);
            p1_discard3.setEnabled(true);
            p1_discard4.setEnabled(true);
            p1_hand1.setEnabled(true);
            p1_hand2.setEnabled(true);
            p1_hand3.setEnabled(true);
            p1_hand4.setEnabled(true);
            p1_hand5.setEnabled(true);
            p1_stock.setEnabled(true);
        } else {
            p1_discard1.setEnabled(false);
            p1_discard2.setEnabled(false);
            p1_discard3.setEnabled(false);
            p1_discard4.setEnabled(false);
            p1_hand1.setEnabled(false);
            p1_hand2.setEnabled(false);
            p1_hand3.setEnabled(false);
            p1_hand4.setEnabled(false);
            p1_hand5.setEnabled(false);
            p1_stock.setEnabled(false);

            p2_hand1.setEnabled(true);
            p2_hand2.setEnabled(true);
            p2_hand3.setEnabled(true);
            p2_hand4.setEnabled(true);
            p2_hand5.setEnabled(true);
            p2_stock.setEnabled(true);
            p2_discard1.setEnabled(true);
            p2_discard2.setEnabled(true);
            p2_discard3.setEnabled(true);
            p2_discard4.setEnabled(true);
        }

        // Γέμισμα καρτών στα χέρια του πάικτη
        curPlayer.fillHand(ge.getDraw());

        // Θέτουμε τις κάρτες στα χέρια του παίκτη
        RearrangeHandCards();
    }

    private void RearrangeHandCards() {
        // Έλεγχος αν όλες οι κάρτες του χεριού του παίκτη που παίζει έχουν αδειάσει ο παίκτης παίρνει καινούργιες
        // Γέμισμα καρτών στα χέρια του πάικτη
        if (p1_hand1.getCard() == null && p1_hand2.getCard() == null && p1_hand3.getCard() == null && p1_hand4.getCard() == null && p1_hand5.getCard() == null && curPlayer.equals(player1))
            curPlayer.fillHand(ge.getDraw());
        else if (p2_hand1.getCard() == null && p2_hand2.getCard() == null && p2_hand3.getCard() == null && p2_hand4.getCard() == null && p2_hand5.getCard() == null && curPlayer.equals(player2))
            curPlayer.fillHand(ge.getDraw());

        if (curPlayer.equals(player1)) {
            // Θέτουμε τις κάρτες στα αντίστοιχα JButton
            p1_hand1.setCard(player1.getHand().get(0));
            p1_hand2.setCard(player1.getHand().get(1));
            p1_hand3.setCard(player1.getHand().get(2));
            p1_hand4.setCard(player1.getHand().get(3));
            p1_hand5.setCard(player1.getHand().get(4));
        } else {
            p2_hand1.setCard(player2.getHand().get(0));
            p2_hand2.setCard(player2.getHand().get(1));
            p2_hand3.setCard(player2.getHand().get(2));
            p2_hand4.setCard(player2.getHand().get(3));
            p2_hand5.setCard(player2.getHand().get(4));
        }
        this.repaint();
    }

    private void initComponents() {
        lbl_player1 = new javax.swing.JLabel();
        lbl_points1 = new javax.swing.JLabel();
        lbl_player2 = new javax.swing.JLabel();
        lbl_points2 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        p1_discard1 = new UI(UI.Type.DISCARD, 0);
        p1_discard2 = new UI(UI.Type.DISCARD, 1);
        p1_discard3 = new UI(UI.Type.DISCARD, 2);
        p1_discard4 = new UI(UI.Type.DISCARD, 3);
        building1 = new UI(UI.Type.BUILDING, 0);
        building2 = new UI(UI.Type.BUILDING, 1);
        building4 = new UI(UI.Type.BUILDING, 2);
        building3 = new UI(UI.Type.BUILDING, 3);
        p2_discard1 = new UI(UI.Type.DISCARD, 0);
        p2_discard2 = new UI(UI.Type.DISCARD, 1);
        p2_discard3 = new UI(UI.Type.DISCARD, 2);
        p2_discard4 = new UI(UI.Type.DISCARD, 3);
        p1_hand1 = new UI(UI.Type.HAND, 0);
        p1_hand2 = new UI(UI.Type.HAND, 1);
        p1_hand3 = new UI(UI.Type.HAND, 2);
        p1_hand4 = new UI(UI.Type.HAND, 3);
        p1_hand5 = new UI(UI.Type.HAND, 4);
        p2_hand1 = new UI(UI.Type.HAND, 0);
        p2_hand2 = new UI(UI.Type.HAND, 1);
        p2_hand3 = new UI(UI.Type.HAND, 2);
        p2_hand4 = new UI(UI.Type.HAND, 3);
        p2_hand5 = new UI(UI.Type.HAND, 4);
        p2_stock = new UI(UI.Type.STOCK, 0);
        p1_stock = new UI(UI.Type.STOCK, 0);

        p1_discard1.addActionListener(new DiscardActionListener(p1_discard1));
        p1_discard2.addActionListener(new DiscardActionListener(p1_discard2));
        p1_discard3.addActionListener(new DiscardActionListener(p1_discard3));
        p1_discard4.addActionListener(new DiscardActionListener(p1_discard4));
        building1.addActionListener(new BuildingActionListener(building1));
        building2.addActionListener(new BuildingActionListener(building2));
        building3.addActionListener(new BuildingActionListener(building3));
        building4.addActionListener(new BuildingActionListener(building4));
        p2_discard1.addActionListener(new DiscardActionListener(p2_discard1));
        p2_discard2.addActionListener(new DiscardActionListener(p2_discard2));
        p2_discard3.addActionListener(new DiscardActionListener(p2_discard3));
        p2_discard4.addActionListener(new DiscardActionListener(p2_discard4));
        p1_hand1.addActionListener(new HandActionListener(p1_hand1));
        p1_hand2.addActionListener(new HandActionListener(p1_hand2));
        p1_hand3.addActionListener(new HandActionListener(p1_hand3));
        p1_hand4.addActionListener(new HandActionListener(p1_hand4));
        p1_hand5.addActionListener(new HandActionListener(p1_hand5));
        p2_hand1.addActionListener(new HandActionListener(p2_hand1));
        p2_hand2.addActionListener(new HandActionListener(p2_hand2));
        p2_hand3.addActionListener(new HandActionListener(p2_hand3));
        p2_hand4.addActionListener(new HandActionListener(p2_hand4));
        p2_hand5.addActionListener(new HandActionListener(p2_hand5));
        p2_stock.addActionListener(new StockActionListener(p2_stock));
        p1_stock.addActionListener(new StockActionListener(p1_stock));

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Τοποθεσία εκκίνησης παραθύρου στην οθόνη

        // ιδιότητες παραθύρου
        setVisible(true);
        setResizable(false);

        lbl_player1.setText("Όνομα Παίκτη 1");
        lbl_points1.setText("Πόντοι: 0");
        lbl_player2.setText("Όνομα Παίκτη 2");
        lbl_points2.setText("Πόντοι: 0");

        // Στοίχιση όλων των components με την βοήθεια του GroupLayout
        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(p1_hand1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(49, Short.MAX_VALUE))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(20, 20, 20)
                                        .addComponent(p1_hand2, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addContainerGap(39, Short.MAX_VALUE)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(30, 30, 30)
                                        .addComponent(p1_hand3, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addContainerGap(29, Short.MAX_VALUE)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addContainerGap(40, Short.MAX_VALUE)
                                        .addComponent(p1_hand4, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(19, 19, 19)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addContainerGap(50, Short.MAX_VALUE)
                                        .addComponent(p1_hand5, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(9, 9, 9)))
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(p1_hand1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(71, Short.MAX_VALUE))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(21, 21, 21)
                                        .addComponent(p1_hand2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addContainerGap(61, Short.MAX_VALUE)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(31, 31, 31)
                                        .addComponent(p1_hand3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addContainerGap(51, Short.MAX_VALUE)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(41, 41, 41)
                                        .addComponent(p1_hand4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addContainerGap(41, Short.MAX_VALUE)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addContainerGap(51, Short.MAX_VALUE)
                                        .addComponent(p1_hand5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(31, 31, 31)))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(p2_hand1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(49, Short.MAX_VALUE))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(20, 20, 20)
                                        .addComponent(p2_hand2, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addContainerGap(39, Short.MAX_VALUE)))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(30, 30, 30)
                                        .addComponent(p2_hand3, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addContainerGap(29, Short.MAX_VALUE)))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                        .addContainerGap(40, Short.MAX_VALUE)
                                        .addComponent(p2_hand4, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(19, 19, 19)))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                        .addContainerGap(50, Short.MAX_VALUE)
                                        .addComponent(p2_hand5, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(9, 9, 9)))
        );
        jPanel2Layout.setVerticalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(p2_hand1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(71, Short.MAX_VALUE))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(21, 21, 21)
                                        .addComponent(p2_hand2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addContainerGap(61, Short.MAX_VALUE)))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(31, 31, 31)
                                        .addComponent(p2_hand3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addContainerGap(51, Short.MAX_VALUE)))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(41, 41, 41)
                                        .addComponent(p2_hand4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addContainerGap(41, Short.MAX_VALUE)))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                        .addContainerGap(51, Short.MAX_VALUE)
                                        .addComponent(p2_hand5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(31, 31, 31)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(lbl_player2)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addComponent(lbl_points2))
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(p2_stock, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(p2_discard1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(p2_discard2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(p2_discard3, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(p2_discard4, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(0, 0, Short.MAX_VALUE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addGap(6, 6, 6)
                                                                .addComponent(building1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(building2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(building3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(building4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(lbl_player1)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addComponent(lbl_points1))
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(p1_stock, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(p1_discard1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(p1_discard2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(p1_discard3, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(p1_discard4, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(13, 13, 13)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(lbl_player1)
                                                        .addComponent(lbl_points1))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(p1_stock, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                                .addComponent(p1_discard1, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addComponent(p1_discard2, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addComponent(p1_discard3, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addComponent(p1_discard4, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                .addGap(34, 34, 34)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(building1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(building2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(building3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(building4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(69, 69, 69)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(lbl_player2)
                                                        .addComponent(lbl_points2))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                                        .addComponent(p2_discard1, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(p2_discard2, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(p2_discard3, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(p2_discard4, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addGap(0, 42, Short.MAX_VALUE))
                                                        .addComponent(p2_stock, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                .addContainerGap())
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );

        pack();
    }

    private boolean gameEnd() {
        if (ge.GameOver()) {
            // Θέσε τους πόντους
            lbl_points1.setText("Πόντοι: " + player1.getPoints().toString());
            lbl_points2.setText("Πόντοι: " + player2.getPoints().toString());

            // Δείξε μήνυμα στον χρήστη με τον νικητή
            Player winner = ge.getWinner();
            JOptionPane.showMessageDialog(null, "Το παιχνίδι τελείωσε, με νικητή τον " + winner.getName() + ", με " + winner.getPoints() + " πόντους και " + winner.getStock().size() + " καρτες");

            // Επαναφορά παιχνιδιού για άλλο γύρο
            // Δημιουργία Παιχνιδιού από την αρχή
            ge.newGame();

            // Θέτουμε τις κάρτες στα αντίστοιχα JButton
            p1_hand1.setCard(player1.getHand().get(0));
            p1_hand2.setCard(player1.getHand().get(1));
            p1_hand3.setCard(player1.getHand().get(2));
            p1_hand4.setCard(player1.getHand().get(3));
            p1_hand5.setCard(player1.getHand().get(4));

            p2_hand1.setCard(player2.getHand().get(0));
            p2_hand2.setCard(player2.getHand().get(1));
            p2_hand3.setCard(player2.getHand().get(2));
            p2_hand4.setCard(player2.getHand().get(3));
            p2_hand5.setCard(player2.getHand().get(4));

            // Θέτουμε τις Stock κάρτες στα αντίστοιχα JButton
            try {
                p1_stock.setCard(player1.getStock().get());
                p2_stock.setCard(player2.getStock().get());
            } catch (NullPileException e) {
                e.printStackTrace();
            }

            // Οι υπόλοιπες είναι Null
            p1_discard1.setCard(null);
            p1_discard2.setCard(null);
            p1_discard3.setCard(null);
            p1_discard4.setCard(null);
            p2_discard1.setCard(null);
            p2_discard2.setCard(null);
            p2_discard3.setCard(null);
            p2_discard4.setCard(null);
            building1.setCard(null);
            building2.setCard(null);
            building3.setCard(null);
            building4.setCard(null);

            whosPlaying();
            return true;
        }
        return false;
    }

    class DiscardActionListener implements ActionListener {

        private final UI card;

        public DiscardActionListener(UI card) {
            this.card = card;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            // Αν η κάρτα είναι κένη δεν υπάρχει νοήμα επιλογής
            if (card.getCard() == null && selected == null)
                return;

            // Θέσε την κατάσταση επιλογής της κάρτας
            card.setClickable();

            if (selected == null) {
                // Αν καμία κάρτα δεν είναι επιλεγμένη θέσε αυτήν ως επιλεγμένη
                selected = card;
            } else {
                // Αν η κάρτα είναι η ίδια η κάρτα απλά απεπέλεξε την
                if (selected.equals(card)) {
                    selected.setClickable(false);
                    selected = null;
                    return;
                }
                // Αν η κάρτα είναι τύπου discard τότε δεν μπορεί να γίνει συνναλλαγή μεταξύ Discard καρτών ή Stock
                if (selected.getTYPE() == UI.Type.DISCARD || selected.getTYPE() == UI.Type.STOCK) {
                    card.setClickable(false);
                    return;
                }
                System.out.print(selected.getTYPE());

                // Έλεγχος αν η κάρτα είναι κενή - δεν υπάρχει κάρτα
                if (selected.getCard() != null) {
                    if (ge.handToDiscard(curPlayer, selected.getPotition(), card.getPotition())) {
                        card.setCard(selected.getCard());
                        selected.setCard(null);
                        card.setClickable(false);
                        selected.setClickable(false);
                        selected = null;
                        // Ο γύρος του παίκτη ολοκληρώθηκε, άλλαξε παίκτη
                        whosPlaying();
                    }
                }
            }

            if (gameEnd()) return;
        }
    }

    class StockActionListener implements ActionListener {

        private final UI card;

        public StockActionListener(UI card) {
            this.card = card;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            card.setClickable();
            if (selected == null) {
                selected = card;
            } else {
                card.setClickable(false);
                selected.setClickable(false);
                selected = null;
            }
        }

    }

    class BuildingActionListener implements ActionListener {

        private final UI card;

        public BuildingActionListener(UI card) {
            this.card = card;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            // Πρέπει να έχει επιλεχθεί κάποια κάρτα (component)
            if (selected != null) {
                selected.setClickable(false);
                // Πρέπει η κάρτα να μην είναι κενή
                if (selected.getCard() != null) {
                    // Έλεγχος από που προέρχεται η κάρτα
                    if (selected.getTYPE().equals(UI.Type.HAND)) {
                        if (ge.handToBuild(curPlayer, selected.getPotition(), card.getPotition())) {
                            try {
                                card.setCard(ge.getBuidling(card.getPotition()).get());
                            } catch (NullPileException e1) {
                                card.setCard(null);
                            }
                            selected.setCard(null);
                            selected = null;
                        }
                    } else if (selected.getTYPE().equals(UI.Type.DISCARD)) {
                        if (ge.discardToBuild(curPlayer, selected.getPotition(), card.getPotition())) {
                            try {
                                card.setCard(ge.getBuidling(card.getPotition()).get());
                            } catch (NullPileException e1) {
                                card.setCard(null);
                            }
                            try {
                                selected.setCard(curPlayer.getDiscard(selected.getPotition()).get());
                            } catch (NullPileException e1) {
                                selected.setCard(null);
                            }
                            selected = null;
                        }
                    } else if (selected.getTYPE().equals(UI.Type.STOCK)) {
                        if (ge.stockToBuild(curPlayer, card.getPotition())) {
                            try {
                                card.setCard(ge.getBuidling(card.getPotition()).get());
                            } catch (NullPileException e1) {
                                card.setCard(null);
                            }
                            try {
                                selected.setCard(curPlayer.getStock().get());
                            } catch (NullPileException e1) {
                                selected.setCard(null);
                            }
                            selected = null;
                        }
                    }
                }
                RearrangeHandCards();
            }
            if (gameEnd()) return;
        }

    }

    class HandActionListener implements ActionListener {

        private final UI card;

        public HandActionListener(UI card) {
            this.card = card;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            // Αν η κάρτα είναι κένη δεν υπάρχει νοήμα επιλογής
            if (card.getCard() == null)
                return;

            card.setClickable();
            if (selected == null) {
                selected = card;
            } else {
                card.setClickable(false);
                selected.setClickable(false);
                selected = null;
            }
        }

    }
}
