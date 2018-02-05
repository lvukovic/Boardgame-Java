/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package java2game;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Luka
 */
public class Java2Game extends Application {
    public static Socket socket = null;
     
    public static ObjectInputStream objInStream=null;
    public static ObjectOutputStream objOutStream=null;
    
   public static int[] primljeniBrojevi;
    
   static Boolean prviUse=true;
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }
 public static void getGameData() throws ClassNotFoundException, IOException {
       
        System.out.println("---- CLIENT ----");
        try {
          //  if (prviUse) {
            socket = new Socket("localhost", ServerMain.PORT);
            System.out.println("Client is sucessfuly connected");

           
           
            objOutStream = new ObjectOutputStream(socket.getOutputStream());
            objInStream = new ObjectInputStream(socket.getInputStream());
          //  prviUse=false;
         //   }
           
            
          

            System.out.println("Reciving field...");
            if (prviUse) {
            int[] polje=(int[])objInStream.readObject();
            socket.close();
            primljeniBrojevi=polje;
            System.out.println(polje[0] + " "+ polje[1]);
            }
            else{
                primljeniBrojevi=ServerMain.napuniPolje();
                System.out.println(primljeniBrojevi[0] + " "+ primljeniBrojevi[1]);
            }
            
        } catch (IOException ex) {
            ex.getMessage();
        } 
        
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);

        
    }
    public int[] ReturnPolje(){
        if (!prviUse) {
            primljeniBrojevi=ServerMain.napuniPolje();
        }
        return primljeniBrojevi;
    }
    
}
