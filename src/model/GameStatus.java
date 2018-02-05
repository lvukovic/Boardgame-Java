/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;

/**
 *
 * @author Luka
 */
public enum GameStatus implements Serializable{
    OVER(0),PLAYING(1);
    
    private int status;
    
    private GameStatus(int status){
        this.status = status;
    }
    
    public int getGameStatus(){
        return status;
    }
}
