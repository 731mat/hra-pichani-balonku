/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
 * @author Matěj
 */
public class Hrac extends VlastnostiPostavy implements MouseMotionListener {

    public Hrac(VykresliVrstvu l) {
        super(l);
        this.color = Color.GREEN;
        this.sirka = 10;
        this.vyska = 15;
        this.xPozice = 800 / 2 - this.sirka / 2;// spřed obrazovky
        this.yPozice = 600 / 2 - this.vyska / 2;
        
        try {
            this.image = ImageIO.read(new File("jehla.png"));
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
        g.setColor(this.color);
        //g.fillRect(this.xPozice, this.yPozice, this.sirka, this.vyska);
        g.drawImage(image, this.xPozice-5, this.yPozice-20, null);

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
