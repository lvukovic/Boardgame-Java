/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package java2game.Serialization;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;
import java2game.Game;
import model.Igrac;
import model.PlayerOblik;
import javafx.scene.layout.GridPane;

/**
 *
 * @author Luka
 */
public class LoadGame {

    Game game;
    
    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
       
    }
    
    public LoadGame() {
    }

    public LoadGame(Game game,GridPane gpBoard) throws FileNotFoundException, IOException, ClassNotFoundException {
    this.game=game;
         this.game.setPloca(gpBoard);
        try(ObjectInputStream stream = new ObjectInputStream(new FileInputStream("SavedGame.dat"))) {
            
           while (true) {
                Object currentObject = stream.readObject();
                
                if (currentObject instanceof java.util.List) {
                   this.game.setPlayer1(((List<Igrac>)currentObject).get(0));
                   this.game.setPlayer2(((List<Igrac>)currentObject).get(1));
                   this.game.setCurrentPlayer(((List<Igrac>)currentObject).get(2));
                    if ( ((List<Igrac>)currentObject).get(2) == this.game.getPlayer1()) {
                        this.game.setIsPlayer1Turn(true);
                    }
                    else{
                         this.game.setIsPlayer1Turn(false);
                    }
                   
//                this.game.getPloca().getChildren().remove(this.game.getPlayer1().Oblik);
//                this.game.getPlayer1().currentPos.setX(this.game.getPlayer1().currentPos.getX());
//                this.game.getPlayer1().currentPos.setY(this.game.getPlayer1().currentPos.getY());
//                PlayerOblik oblik1=this.game.getPlayer1().Oblik;
//                this.game.getPloca().add(oblik1, this.game.getPlayer1().currentPos.getX(), this.game.getPlayer1().currentPos.getY());
//                
                    System.out.println("Ucitan je igrac: "+this.game.getPlayer1().Ime+", nalazi se na poziciji x="+this.game.getPlayer1().currentPos.getX()+", Y="+this.game.getPlayer1().currentPos.getY());
                   System.out.println("Ucitan je igrac: "+this.game.getPlayer2().Ime+", nalazi se na poziciji x="+this.game.getPlayer2().currentPos.getX()+", Y="+this.game.getPlayer2().currentPos.getY());
//                this.game.getPloca().getChildren().remove(this.game.getPlayer2().Oblik);
//                this.game.getPlayer2().currentPos.setX(this.game.getPlayer2().currentPos.getX());
//                this.game.getPlayer2().currentPos.setY(this.game.getPlayer2().currentPos.getY());
//                PlayerOblik oblik2=this.game.getPlayer2().Oblik;
//                this.game.getPloca().add(oblik2, this.game.getPlayer2().currentPos.getX(), this.game.getPlayer2().currentPos.getY());
//                
                
                }
           }
        
           } catch (Exception e) {
               System.out.println("Zavrseno je ucitavanje igraca u igru");
        }
    }
}
