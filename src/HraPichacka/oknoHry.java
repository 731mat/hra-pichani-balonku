/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HraPichacka;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Matěj
 */
public class oknoHry extends JFrame {
    public static final String nazevOkna= "Píchání";

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Manin kde se zavolá inicializace
        oknoHry hra = new oknoHry();  // vytvoření instannce třídy
        hra.init();
    }
    
    public void init(){
        // metoda okna 
        VykresliVrstvu vrstva = new VykresliVrstvu();
        this.add(vrstva);  // přidání komponentu
        this.pack();
        this.setTitle(oknoHry.nazevOkna); // nazev okna
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // zavření
        this.setLayout(new BorderLayout());
        this.setResizable(false);  // zakážeme roztahování
        this.setVisible(true); // nystavení viditelnosti
        vrstva.start();
    }
    
}
