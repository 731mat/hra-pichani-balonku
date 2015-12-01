package HraPichacka;

import HraPichacka.VlastnostiPostavy.Hrac;
import HraPichacka.VlastnostiPostavy.Nepritel;
import HraPichacka.VlastnostiPostavy.VlastnostiPostavy;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

/**
 *
 * @author Matěj Hloušek
 */
 
public class VykresliVrstvu extends Canvas implements Runnable {

    //runnable - vlákna 
    private float score;
    protected BufferedImage pozadi = null;

    private Hrac hrac;  // přidání hráče do hry
    ArrayList<VlastnostiPostavy> postavy;  // inicialozování arraylistu pro všechny balonky

    public VykresliVrstvu() {
        super(); // zavolá canvas
        this.setSize(new Dimension(800, 600)); // nystavení velikosti canvasu
        try {
            pozadi = ImageIO.read(new File("pozadi.png")); // načtení obrázku
        } catch (IOException ex) {
            System.out.println(ex);
        }


        this.hrac = new Hrac(this); // vytvoření kráče
        this.addMouseMotionListener(this.hrac);  // naslouchání myši
        this.postavy = new ArrayList<VlastnostiPostavy>(); // vytvoření arraylistu pole vlastnosti hrace

        this.naplnPole(); // naplni pole
    }

    @Override
    public void run() {
        
        // nekonečná smyčka hry
        while(true){
            // vytvoření časových proměnných
            long posledniCasSmycky = System.nanoTime();
            long poslednyCasVypisu = System.currentTimeMillis();
            double pocetSmycekZaSekundu = 0;
            double nanoSecSmycky = Math.pow(10, 9) / 60;//chci 60 smyček za sekundu a 10na9 je 1 nanosekunda -> kolik má být smycek za 1 sekundu
            int fps = 0;
            int smycka = 0;
            
            // smyčka hry kokud nepropíchnu všechny balonky
            while (this.postavy.size() > 0) {
                // výpočet času na smyšku
                long aktualniCasSmycky = System.nanoTime();
                pocetSmycekZaSekundu += (aktualniCasSmycky - posledniCasSmycky) / nanoSecSmycky;
                posledniCasSmycky = aktualniCasSmycky;
                
                // zpracování smycek které se nestihnou zpracovat
                // z důvodu rychlosti hry na výkonu jakéhokoliv pc
                // na každém pc proběhne za 1 s stejně smyček
                while (pocetSmycekZaSekundu >= 1) { 
                    smycka++;
                    pocetSmycekZaSekundu--;
                    this.aktualizace();
                }
    
                fps++;
                // vyrendruje canvas
                this.render();
                
                //každou sekundu přidá score a vypíše na obrazovku
                if (System.currentTimeMillis() - poslednyCasVypisu > 1000) {
                    poslednyCasVypisu += 1000;
                    System.out.println("smycky: " + smycka + ", FPS: " + fps + " pocet objektu: " + this.postavy.size());
                    fps = 0;
                    smycka = 0;
                    this.score++;
    
                }
            }//while hry
            
            // když je smyčka hry ukončena vypíšu dialogové okno se score a možnost opakování
            if (this.postavy.size() == 0) {
                int reply = JOptionPane.showConfirmDialog(null, "Tvoje skóré je: " + this.score + "s\n chceš hru restartovat ?", "hraj znovu", JOptionPane.YES_NO_OPTION);
                if (reply == JOptionPane.YES_OPTION) {
                    this.naplnPole();
                    this.score = 0;
                } else {
                    System.exit(0);
                }
            }
        }// hlavní while
    }

    // vytvoření vlákna
    public void start() {
        Thread vlakno1;
        vlakno1 = new Thread(this);
        vlakno1.start();

    }
    
    
    private void aktualizace() {
        //akualizje polohu myši a hráče
        hrac.aktualizace();
        
        // projde list a každý prvek 
        for (VlastnostiPostavy e : this.postavy) {
            e.aktualizace(); // zaktualozuje -- přičte vektor
            // zkontroluje zda se objekt hrac a jakýkoliv objekt z pole  navzájem nehotkli // kontroluje to podle čtverce
            if (e.getXPozice() <= hrac.getXPozice() + hrac.getSirka() && e.getXPozice() + e.getSirka() > hrac.getXPozice()
                    && e.getYPozice() <= hrac.getYPozice() + hrac.getVyska() && e.getYPozice() + e.getVyska() > hrac.getYPozice()) {
                this.postavy.remove(e); // vymyže objekt z pole
                return; // zamezí smazání více objektů
            }
        }
    }

    // vyrendrování canvasu
    private void render() {
        // vytvoření třech bafrů pro canvas
        BufferStrategy buffer = this.getBufferStrategy();
        if (buffer == null) {
            this.createBufferStrategy(3);
            return;
        }
        // nastavení do kterého bafru budu kreslit
        Graphics g = buffer.getDrawGraphics();
        
        // kdyby náhodou jsme chtěli bílé pozadí
        //g.setColor(Color.white);
        //g.fillRect(0, 0, this.getWidth(), this.getHeight());
        
        // překreslení pozadí // vymazání canvasu
        g.drawImage(pozadi, 0, 0, null);

        for (VlastnostiPostavy e : this.postavy) {   // foreach - prochází pole a každé si ulozi do proměnné e(předem definované vlastnosti) a vyrendruje
            e.render(g);
        }

        hrac.render(g);  // vyrendrovani hrace
        
        // nystavení barvy pro text
        g.setColor(Color.black);
        g.drawString("tvoje skóre: " + this.score, 20, 20); // výpis score
        g.dispose(); // ukončení kreslení
        buffer.show(); // vyměnění bafru a vykreslení najednou

    }


    // naplení pole balonkami
    public void naplnPole() {
        for (int i = 0; i < 100; i++) {
            this.postavy.add(new Nepritel(this)); // vložení nepřítele do arrylistu 
        }
    }

}
