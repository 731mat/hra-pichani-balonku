/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JOptionPane;

/**
 *
 * @author Matěj
 */
public class VykresliVrstvu extends Canvas implements Runnable {

    //runnable - vlákna 
    private boolean bezi;
    private float score;
    protected BufferedImage pozadi = null;

    private Hrac hrac;  // přidání dálšího členu
    ArrayList<VlastnostiPostavy> postavy;  // inicialozování arraylistu

    public VykresliVrstvu() {
        super(); // zavolá canvas
        this.setSize(new Dimension(800, 600));
        try {
            pozadi = ImageIO.read(new File("pozadi.png"));
        } catch (IOException ex) {
            System.out.println(ex);
        }

        this.bezi = false;

        this.hrac = new Hrac(this);
        this.addMouseMotionListener(this.hrac);
        this.postavy = new ArrayList<VlastnostiPostavy>(); // vytvoření arraylistu posle vlastnosti hrace

        this.naplnPole(); // naplni pole
    }

    @Override
    public void run() {
        while(true){
        long posledniCasSmycky = System.nanoTime();
        long poslednyCasVypisu = System.currentTimeMillis();
        double pocetSmycekZaSekundu = 0;
        double nanoSecSmycky = Math.pow(10, 9) / 60;//chci 60 smyček za sekundu a 10na9 je 1 nanosekunda -> kolik má být smycek za 1 sekundu
        int fps = 0;
        int smycka = 0;
        while (this.postavy.size() > 0) {
            long aktualniCasSmycky = System.nanoTime();
            pocetSmycekZaSekundu += (aktualniCasSmycky - posledniCasSmycky) / nanoSecSmycky;
            posledniCasSmycky = aktualniCasSmycky;
            while (pocetSmycekZaSekundu >= 1) { // zpracování smycek které se nestihnou zpracovat
                smycka++;
                pocetSmycekZaSekundu--;
                this.aktualizace();
            }

            fps++;
            this.render();

            if (System.currentTimeMillis() - poslednyCasVypisu > 1000) {
                poslednyCasVypisu += 1000;
                System.out.println("smycky: " + smycka + ", FPS: " + fps + " pocet objektu: " + this.postavy.size());
                fps = 0;
                smycka = 0;
                this.score++;

            }
        }
        if (this.postavy.size() == 0) {
            int reply = JOptionPane.showConfirmDialog(null, "Tvoje skóré je: " + this.score + "s\n chceš hru restartovat ?", "hraj znovu", JOptionPane.YES_NO_OPTION);
            if (reply == JOptionPane.YES_OPTION) {
                this.naplnPole();
                this.score = 0;
            } else {
                System.exit(0);
            }
        }}
    }

    public void start() {
        Thread vlakno1;
        vlakno1 = new Thread(this);
        vlakno1.start();

    }

    private void aktualizace() {
        hrac.aktualizace();
        for (VlastnostiPostavy e : this.postavy) {
            e.aktualizace();
            if (e.getXPozice() <= hrac.getXPozice() + hrac.getSirka() && e.getXPozice() + e.getSirka() > hrac.getXPozice()
                    && e.getYPozice() <= hrac.getYPozice() + hrac.getVyska() && e.getYPozice() + e.getVyska() > hrac.getYPozice()) {
                this.postavy.remove(e);
                return;
            }

        }

    }

    private void render() {
        BufferStrategy buffer = this.getBufferStrategy();
        if (buffer == null) {
            this.createBufferStrategy(3);
            return;
        }
        Graphics g = buffer.getDrawGraphics();
        //g.setColor(Color.white);
        //g.fillRect(0, 0, this.getWidth(), this.getHeight());
        g.drawImage(pozadi, 0, 0, null);

        for (VlastnostiPostavy e : this.postavy) {   // něco jako foreach - prochází pole a každé si ulozi do proměnné e(předem definované vlastnosti) a vyrendruje
            e.render(g);
        }

        hrac.render(g);  // vyrendrovani hrace

        g.setColor(Color.black);
        g.drawString("tvoje skóre: " + this.score, 20, 20);
        g.dispose();
        buffer.show();

    }

    public void naplnPole() {
        for (int i = 0; i < 100; i++) {
            this.postavy.add(new Nepritel(this)); // vložení nepřítele do arrylistu 
        }
    }

}
