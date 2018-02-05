/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.awt.Panel;
import java.io.Serializable;
import javafx.scene.layout.Pane;

/**
 *
 * @author Luka
 */
public class Field extends Pane  implements Serializable{
    private int x;
    private int y;

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Field() {
    }

    public Field(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    
}
