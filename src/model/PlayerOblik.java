/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author Luka
 */
public class PlayerOblik extends  Rectangle implements Serializable{
    
    public PlayerOblik(int x,int y){
        super(x,y);
    }
}
