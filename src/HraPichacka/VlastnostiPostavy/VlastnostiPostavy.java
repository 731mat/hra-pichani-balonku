package HraPichacka.VlastnostiPostavy;

import HraPichacka.VykresliVrstvu;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author Matěj Hloušek
 */
abstract public class VlastnostiPostavy {
    protected int xPozice;
    protected int yPozice;
    protected Color color;
    protected int sirka;
    protected int vyska;
    protected VykresliVrstvu map;
    protected BufferedImage image = null;
        
    
    
    public VlastnostiPostavy( VykresliVrstvu l){
        this.map = l;

    }
    //abstraktví metoda třídy - neobsahuje žádné tělo 
    // odvozená třída musí tuto metodu předdefinovat
    abstract public void aktualizace();
    abstract public void render(Graphics g);
    
    
    // vrátí a nastaví pozici X
    public int getXPozice(){
        return xPozice;
    }
    public void setXPozice(int xPozice){
        this.xPozice= xPozice;
    }
    
    // vrátí a nastaví pozici Y
    public int getYPozice(){
        return yPozice;
    }
    public void setYPozice(int yPozice){
        this.xPozice= yPozice;
    }
    
    // vrátí a nastaví šířku objektu
    public int getSirka(){
        return sirka;
    }
    public void setSirka(int sirka){
        this.sirka= sirka;
    }
    
    // vrátí a nastaví výšku objektu
    public int getVyska(){
        return vyska;
    } 
    public void setVyska(int vyska){
        this.vyska= vyska;
    }
}
