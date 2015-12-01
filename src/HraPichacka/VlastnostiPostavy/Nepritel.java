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
 * @author Matěj Hloušek
 */
public class Nepritel extends VlastnostiPostavy {

    private Vector SmerovyVektor;  // proměnná smeru pohybu

    public Nepritel(VykresliVrstvu l) {
        super(l); // vykreslení vyrstvy
        // pole barev
        Color[] barvy = new Color[]{Color.RED, Color.BLUE, Color.YELLOW, Color.BLACK, Color.PINK, Color.ORANGE};
        Random rand = new Random();  // náhodné číslo
        this.color = barvy[rand.nextInt(barvy.length - 1)]; // nastavení narvy z polebarev vybráné náhodným číslem
        
        this.SmerovyVektor = new Vector (rand.nextInt(4), rand.nextInt(4)); // vložení náhodných hodnot jako směr pohybu
                                                                            // čím větčí tím rychlejší 

        // vygenerování základní (výchozí) pozice
        this.xPozice = rand.nextInt(750); 
        this.yPozice = rand.nextInt(550);
        
        // nastavení velikosti zásahového pole 
        this.sirka = 35;
        this.vyska = 40;
        
        try {
            this.image = ImageIO.read(new File("balonek"+rand.nextInt(7)+".png"));  // načtení náhodného obrázku 
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public void aktualizace() {
        
        // generování pohybu (přičte hodnotu předem vygenerováného směrového vektoru)
        this.xPozice += this.SmerovyVektor.getX(); 
        this.yPozice += this.SmerovyVektor.getY();
        
        
        //když narazí do pravé hravy tak se směrovému vektoru změní znaménko jedné souřadnice
        if(this.xPozice + this.sirka > 800){
            this.xPozice = 800 - this.sirka; // vrátím objekt do okna
            this.SmerovyVektor.zmenaSmeru(Vector.xSmer);// tento vekktor když narazí tak se vyvolá funkce zmena smeru která zmeni
                                                        // znamenko u souradnice na např. zaporne
        }else if(this.xPozice < 0){        //když narazí do levé hravy tak se směrovému vektoru změní znaménko jedné souřadnice
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
