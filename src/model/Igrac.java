/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


/**
 *
 * @author Luka
 */
public class Igrac implements Serializable{
    public int id;
    public String Ime;
    public PlayerOblik Oblik;
    public Field currentPos;

    public Igrac(String Ime) {
        this.Ime = Ime;
        this.currentPos=new Field(0,0);
        this.Oblik=new PlayerOblik(20, 20);
    }
    public Igrac(){
        
    }
    
    public Igrac(int ID,String Ime, javafx.scene.paint.Color boja){
        this.id=ID;
        this.Ime=Ime;
        this.Oblik = new PlayerOblik(20, 20);
        this.Oblik.setFill(boja);
        this.currentPos=new Field(0,0);
      
    }
    
}
