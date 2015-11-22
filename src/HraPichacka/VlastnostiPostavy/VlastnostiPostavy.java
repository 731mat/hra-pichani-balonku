/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
 * @author Matěj
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
    abstract public void aktualizace();
    abstract public void render(Graphics g);
    
    
    
    public int getXPozice(){
        return xPozice;
    }
    public void setXPozice(int xPozice){
        this.xPozice= xPozice;
    }
    
    
    public int getYPozice(){
        return yPozice;
    }
    public void setYPozice(int yPozice){
        this.xPozice= yPozice;
    }
    
    
    public int getSirka(){
        return sirka;
    }
    public void setSirka(int sirka){
        this.sirka= sirka;
    }
    
    
    public int getVyska(){
        return vyska;
    } 
    public void setVyska(int vyska){
        this.vyska= vyska;
    }
    
    
    
    
    
  
    
}
