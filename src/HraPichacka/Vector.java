/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HraPichacka;

import java.util.Random;

/**
 *
 * @author Matěj
 */
public class Vector {

    private int x;
    private int y;

    public static final int xSmer = 1;
    public static final int ySmer = 2;

    public Vector(int x, int y) {
        this.x = (x == 0 ? ++x : x); //  nesmí být 0
        this.y = (y == 0 ? ++y : y);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void zmenaSmeru(int smer) {
        if (smer == Vector.xSmer) {
            this.x = -this.x;
        } else if (smer == Vector.ySmer) {
            this.y = -this.y;
        }
    }

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
