package HraPichacka;

import java.util.Random;

/**
 *
 * @author Matěj Hloušek
 */
public class Vector {
    
    private int x;
    private int y;
    
    // směr a rychlost výchozího vektoru 
    public static final int xSmer = 1; 
    public static final int ySmer = 2;

    //nastavení hodnot směrua rychlosti vektoru
    public Vector(int x, int y) {
        this.x = (x == 0 ? ++x : x); //  nesmí být 0
        this.y = (y == 0 ? ++y : y);
    }
    
    // vrátí hodnotu polohu X
    public int getX() {
        return x;
    }
    // vrátí hodnotu polohu Y
    public int getY() {
        return y;
    }
    
    // změní hodnotu jednoho směru na opačný s porovnámám výchozího vektoru
    public void zmenaSmeru(int smer) {
        if (smer == Vector.xSmer) {
            this.x = -this.x;
        } else if (smer == Vector.ySmer) {
            this.y = -this.y;
        }
    }
    
    // vygeneruje nový náhodný směr 
    // v programu nepoužito
    public void novySmer(int smer) {
        Random rand = new Random();
        if (smer == Vector.xSmer) {
            int novyX = rand.nextInt(5);          // random nove cislo do 4
            this.x = this.x < 0 ? -novyX : novyX; // pokud je smer zaporny tak zaporny zustne
        } else if (smer == Vector.ySmer) {
            int novyY = rand.nextInt(5);
            this.y = this.y < 0 ? -novyY : novyY;
        }
    }

}
