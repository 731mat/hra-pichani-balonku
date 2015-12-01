package HraPichacka.VlastnostiPostavy;

import HraPichacka.VykresliVrstvu;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JLayeredPane;

/**
 *
 * @author Matěj Hloušek
 */
public class Hrac extends VlastnostiPostavy implements MouseMotionListener {

    public Hrac(VykresliVrstvu l) {
        super(l);   // vykreslí pčedchozí vrstvu
        this.color = Color.GREEN; //pomyslná barva // lze zapnou
        this.sirka = 10;
        this.vyska = 15;
        this.xPozice = 800 / 2 - this.sirka / 2;// střed obrazovky
        this.yPozice = 600 / 2 - this.vyska / 2;
        
        try {
            this.image = ImageIO.read(new File("jehla.png"));  // nastavení obrázku
        } catch (IOException ex) {
            System.out.println(ex);
        }

    }

    @Override
    public void aktualizace() {
    }

    @Override
    public void render(Graphics g) {
        //vyrendrování hrace podle udajů
        g.setColor(this.color); // nastavení barvy pomyslného čtverce (viz. 45 řádek podtím)
        //g.fillRect(this.xPozice, this.yPozice, this.sirka, this.vyska);  // vykreslení čtverce který říká jakým prostorem balónky zasáhne
        g.drawImage(image, this.xPozice-5, this.yPozice-20, null);  // vykreslení obrázku

    }

    @Override
    public void mouseDragged(MouseEvent m) {
        this.mouseMoved(m); // aby nefungovalo kliknutí myši
    }

    @Override
    public void mouseMoved(MouseEvent m) {
        //nastavení pozice
        this.xPozice = m.getX() - this.sirka / 2;
        this.yPozice = m.getY() - this.vyska / 2;
    }

}
