package card;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class UI extends JButton {

    public Font CARDS_FONT = new Font("Arial", 1, 15);
    public int ROUND_RADIUS = 10;
    public int EDGE_SHIFT = 1;
    private boolean clicked = false;
    private String path = "skipbo.png";
    private Color color = Color.BLACK;
    private Card card;
    private Type type;
    private int potition;

    public UI(Type type, int potition) {
        this.type = type;
        this.potition = potition;
    }

    public Type getTYPE() {
        return type;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public void setClickable() {
        clicked = !clicked;
    }

    public void setClickable(boolean value) {
        clicked = value;
        try {
            this.repaint();
        } catch (Exception e) {
        }
    }

    public int getPotition() {
        return potition;
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        // Περιεχόμενο
        GradientPaint bodyGradient;
        // Αν η κάρτα είναι κένη τότε είναι γκρί αλλιώς άσπρη
        if (card != null)
            bodyGradient = new GradientPaint(200 - this.getWidth() / 2, 0, Color.white, 200 - this.getWidth() / 2, this.getHeight() + 250, Color.gray);
        else
            bodyGradient = new GradientPaint(200 - this.getWidth() / 2, 0, Color.lightGray, 200 - this.getWidth() / 2, this.getHeight() + 250, Color.gray);
        g2d.setPaint(bodyGradient);
        g2d.fillRoundRect(0, 0, this.getWidth() - (EDGE_SHIFT), this.getHeight() - (EDGE_SHIFT), ROUND_RADIUS, ROUND_RADIUS);

        // Περίγραμμα
        g2d.setColor(Color.BLACK);
        g2d.drawRoundRect(0, 0, this.getWidth() - (EDGE_SHIFT), this.getHeight() - (EDGE_SHIFT), ROUND_RADIUS, ROUND_RADIUS);

        int sxPos1 = 5;
        int syPos1 = 15;
        int syPos2 = this.getHeight() - 5;

        // Έλεγχος αν έχει τεθεί κάρτα ώστε να τη ζωγραφίσουμε
        if (card == null) return;

        // Έλεγχος του τύπου της κάρτας που θα ζωγραφίσουμε
        if (card.isType(Card.Type.NUMERIC) && ((Numeric) card).getNumber() != -1) {
            int sxPos2 = this.getWidth() - (15 + ((((Numeric) card).getNumber().toString().length() - 1) * 5));
            g.setFont(CARDS_FONT);
            g.setColor(Color.black);
            g.drawString(((Numeric) card).getNumber().toString(), sxPos1, syPos1);
            g.drawString(((Numeric) card).getNumber().toString(), sxPos2, syPos2);
        } else if (card.isType(Card.Type.SKIPBO)) {
            g2d.setPaint(new GradientPaint(200 - this.getWidth() / 2, 0, Color.red, 200 - this.getWidth() / 2, this.getHeight() + 250, Color.white));
            g2d.fillRoundRect(0, 0, this.getWidth() - (EDGE_SHIFT), this.getHeight() - (EDGE_SHIFT), ROUND_RADIUS, ROUND_RADIUS);
            g.setFont(CARDS_FONT);
            g.setColor(Color.black);

            // Θέτουμε εικόνα png αν είναι skipbo
            // βρίσκουμε και θέτουμε τις διαστάσεις της ανάλογα με το μέγοθος του JButton
            double factor = 75 / 100.0;
            int imgDim;
            if (this.getWidth() < this.getHeight()) {
                imgDim = (int) (this.getWidth() * factor);
            } else {
                imgDim = (int) (this.getHeight() * factor);
            }

            int xPos = 1 + (this.getWidth() - imgDim) / 2;
            int yPos = 1 + (this.getHeight() - imgDim) / 2;

            try {
                g.drawImage(ImageIO.read(getClass().getResource(path)), xPos, yPos, imgDim, imgDim, this);
            } catch (IOException ex) {
                Logger.getLogger(this.getName()).log(Level.SEVERE, null, ex);
            }

            // Ανάλογα με τον τύπο του μπαλαντέρ την ζωγραφίζουμε
            if (((SkipBo) card).isType(SkipBo.Type.SUPER)) {
                int sxPos2 = this.getWidth() - (15 + (("Super".length() - 1) * 5));
                g.drawString("Super", sxPos1, syPos1);
                g.drawString("Super", sxPos2, syPos2);
            } else if (((SkipBo) card).isType(SkipBo.Type.ERASER)) {
                int sxPos2 = this.getWidth() - (15 + (("Eraser".length() - 1) * 5));
                g.drawString("Eraser", sxPos1, syPos1);
                g.drawString("Eraser", sxPos2, syPos2);
            }

            // Αλλιώς είναι SKIPBO και δεν γράφει τίποτα
        }
    }

    @Override
    protected void paintBorder(Graphics gOld) {
        Graphics2D g = (Graphics2D) gOld;

        // έλεγχος αν η κάρτα είναι επιλεγμένη οπότε ζωγραφίζουμε το frame
        color = (clicked) ? Color.YELLOW : Color.BLACK;

        // Πέρνουμε την τοποθεσία του ποντικιού για να καταλάβουμε αν η κάρτα είναι επιλεγμένη
        Point p = (this.getMousePosition());
        if (p != null) {
            if (this.contains(p)) {
                color = Color.YELLOW;
            }
        }

        // Βάφουμε το περίγραμμα
        g.setColor(color);
        g.drawRoundRect(0, 0, this.getWidth() - (EDGE_SHIFT), this.getHeight() - (EDGE_SHIFT), ROUND_RADIUS, ROUND_RADIUS);
    }

    @Override
    public Dimension getPreferredSize() {
        // Μέγεθος της κάρτας
        return new Dimension(50, 70);
    }

    public enum Type {
        STOCK, BUILDING, HAND, DISCARD
    }
}
