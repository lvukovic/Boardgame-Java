/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package java2game.Serialization;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java2game.Game;
import model.Igrac;
import model.PlocaZaIgru;

/**
 *
 * @author Luka
 */
public class SaveGame {
    //  Game game;

    public SaveGame(List<Igrac> igraci) throws IOException{
      //  this.game = game;
        try(ObjectOutputStream outStream = new ObjectOutputStream(new FileOutputStream("SavedGame.dat"))) {
            outStream.writeObject(igraci);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(SaveGame.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(SaveGame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
