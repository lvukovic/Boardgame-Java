/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author lukav
 */
public class Potez {

    public int getIDIgrac() {
        return IDIgrac;
    }

    public void setIDIgrac(int IDIgrac) {
        this.IDIgrac = IDIgrac;
    }

    public Field getTrenutnoStanje() {
        return trenutnoStanje;
    }

    public void setTrenutnoStanje(Field trenutnoStanje) {
        this.trenutnoStanje = trenutnoStanje;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
    int position;
    int IDIgrac;
    Field trenutnoStanje;

    public int getBrojPoteza() {
        return brojPoteza;
    }

    public void setBrojPoteza(int brojPoteza) {
        this.brojPoteza = brojPoteza;
    }
    int brojPoteza;
    public Potez() {
    }

    public Potez(int IDIgrac, int x, int y) {
        this.IDIgrac = IDIgrac;
        this.trenutnoStanje.setX(x);
        this.trenutnoStanje.setY(y);
    }
     public Potez(int IDIgrac, int brojPoteza, int x, int y, int pos) {
        this.IDIgrac = IDIgrac;
        this.trenutnoStanje.setX(x);
        this.trenutnoStanje.setY(y);
        this.brojPoteza=brojPoteza;
        this.position=pos;
    }
    
    
    
    
}
