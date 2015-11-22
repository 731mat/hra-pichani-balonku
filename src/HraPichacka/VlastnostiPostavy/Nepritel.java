/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HraPichacka.VlastnostiPostavy;

import HraPichacka.Vector;
import HraPichacka.VykresliVrstvu;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;

/**
 *
 * @author Matěj
 */
public class Nepritel extends VlastnostiPostavy {

    private Vector SmerovyVektor;  // proměnná smeru pohybu

    public Nepritel(VykresliVrstvu l) {
        super(l);

        Color[] barvy = new Color[]{Color.RED, Color.BLUE, Color.YELLOW, Color.BLACK, Color.PINK, Color.ORANGE};
        Random rand = new Random();
        this.color = barvy[rand.nextInt(barvy.length - 1)];
        
        this.SmerovyVektor = new Vector (rand.nextInt(4), rand.nextInt(4)); // vložení hodnot jako směr pohybu

        this.xPozice = rand.nextInt(750);
        this.yPozice = rand.nextInt(550);

        this.sirka = 35;
        this.vyska = 40;
        
        try {
            this.image = ImageIO.read(new File("balonek"+rand.nextInt(7)+".png"));
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public void aktualizace() {
        this.xPozice += this.SmerovyVektor.getX();  // generování pohybu
        this.yPozice += this.SmerovyVektor.getY();
        
        
        //když narazí do pravé hravy když do levé tak je to ElseIF a když narazi tak znemi smer na opavni 
        if(this.xPozice + this.sirka > 800){
            this.xPozice = 800 - this.sirka;
            this.SmerovyVektor.zmenaSmeru(Vector.xSmer);// tento vekktor když narazí tak se vyvolá funkce zmena smeru která zmeni
                                                        // znamenko u souradnice na např. zaporne
        }else if(this.xPozice <0){
            this.xPozice = 0;
            this.SmerovyVektor.zmenaSmeru(Vector.xSmer);
        }
        
        //když narazi do spodni hrany a do horni
        if(this.yPozice + this.vyska > 600){
            this.yPozice = 600 - this.vyska;
            this.SmerovyVektor.zmenaSmeru(Vector.ySmer);
            
        }else if(this.yPozice <0){
            this.yPozice = 0;
            this.SmerovyVektor.zmenaSmeru(Vector.ySmer);
        }
    }

    @Override
    public void render(Graphics g) {
        //vyrendrování nepritele podle udajů
        g.setColor(this.color);
        //g.fillRect(this.xPozice, this.yPozice, this.sirka, this.vyska);
        g.drawImage(image, this.xPozice-5, this.yPozice-5, null);
    }

}
