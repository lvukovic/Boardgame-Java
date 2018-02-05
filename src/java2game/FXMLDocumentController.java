/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package java2game;

import Networking.RmiInterface;
import helper.XMLHelper;
import threads.BaciKockuThread;
import refleksija.ClassInfo;
import model.Field;
import model.Igrac;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java2game.Serialization.LoadGame;
import java2game.Serialization.SaveGame;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import model.Potez;
import java2game.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;

/**
 *
 * @author Luka
 */
public class FXMLDocumentController implements Initializable {
    private int brojMicanja;
    private int counterPoteza=0;
    private int[] brojevi=Java2Game.primljeniBrojevi;
     List<Potez> Potezi ;
     int pointer=-3;
     boolean pomakniZa2=false;
     
 public List<Field> IgracePolje=new ArrayList<>();
 
 public List<Field> getIgracePolje(){
     return IgracePolje;
 }
 
 @FXML
 private Label lblKojiIgrac;
 
    @FXML
    private TextField tfRezultatKocke1;
     @FXML
    private TextField tfRezultatKocke2;
    
    @FXML
    private AnchorPane apMain;
    
    @FXML
    GridPane gpBoard;
    
    Game game;
    
    @FXML
    ImageView imgPrvaKocka;
     @FXML
    ImageView imgDrugaKocka;
     
     @FXML 
     Button btnBaciKocku;
    
      @FXML 
     Button btnBaciKocku2;
      
      @FXML
      Button btnUnatrag;
              
     @FXML
     Button btnNaprijed;
      
       @FXML 
     Button btnOsvjezi;
      
      @FXML 
      TextArea msgBox;
    public void returnMicanja(){
        try {
            Registry registry = LocateRegistry.getRegistry(ServerMain.RMI_PORT);
            RmiInterface stub = (RmiInterface) registry.lookup("RmiInterface");
            int[] micanja = stub.ReturnBrojevi() ;
            brojevi=micanja;
       
            
        } catch (RemoteException ex) {
            ex.getMessage();
        } catch (NotBoundException ex) {
           ex.getMessage();
        }
    }
    @FXML protected void btnBaciKockuAction(ActionEvent event) throws InterruptedException, IOException {
        if (counterPoteza>0) {
            returnMicanja();
        }
        else{
            brojevi=Java2Game.primljeniBrojevi;
        }

        Game game=new Game(gpBoard);
        BaciKockuThread thread=new BaciKockuThread(imgPrvaKocka, "Prva kocka je bacena Thread", 1,msgBox,tfRezultatKocke1,brojevi[0]);
        btnBaciKocku.setDisable(true);
        thread.start();
//ServerMain.Kreni();
//     if (ObaGumbaStisnuta()) {
//            if (Integer.parseInt(tfRezultatKocke1.getText())>Integer.parseInt(tfRezultatKocke2.getText())) {
//                msgBox.appendText("Rezultat na prvoj kocki je veci, stoga, pomaknite se "+Integer.parseInt(tfRezultatKocke1.getText())+" polja.");
//            }
//            else if (Integer.parseInt(tfRezultatKocke1.getText())<Integer.parseInt(tfRezultatKocke2.getText())) {
//                msgBox.appendText("Rezultat na drugoj kocki je veci, stoga, pomaknite se "+Integer.parseInt(tfRezultatKocke2.getText())+" polja.");
//            }
//            else {
//                 msgBox.appendText("Rezultati su jednaki, pokanite se "+Integer.parseInt(tfRezultatKocke1.getText())+" polja.");
//            }
//        }
     
    }
    @FXML protected void btnBaciKocku2Action(ActionEvent event) throws InterruptedException {
          if (counterPoteza>0) {
          //  returnMicanja();
        }
        else{
            brojevi=Java2Game.primljeniBrojevi;
        }
     BaciKockuThread thread=new BaciKockuThread(imgDrugaKocka,"Druga kocka je bacena Thread",2,msgBox,tfRezultatKocke2,brojevi[1]);
     btnBaciKocku2.setDisable(true);
     thread.start();

    }
    
     @FXML protected void btnOsvjeziAction(ActionEvent event)  {
         try {
              
              if (counterPoteza>0) {
            returnMicanja();
        }
        else{
            brojevi=Java2Game.primljeniBrojevi;
        }
      if (brojevi[0]>brojevi[1]) {
             brojMicanja=brojevi[0];
         }
                 else{
                      brojMicanja=brojevi[1];
                 }
         if (game.currentPlayer==game.Player1) {
                game.player1Turn( IgracePolje, brojMicanja);
                counterPoteza++;
          }
            else if ((game.currentPlayer==game.Player2)) {
                 game.player2Turn( IgracePolje, brojMicanja);
                       counterPoteza++;
            }
         
          if (game.currentPlayer==game.Player1) {
              
            lblKojiIgrac.setText("Plavi igrac");
        }
        else if (game.currentPlayer==game.Player2) {
             lblKojiIgrac.setText("Crveni igrac");
        }
         } catch (Exception e) {
             e.printStackTrace();
         }
          
          
    }
    
    
    @FXML protected void btnPomakniSeAction(ActionEvent event) {
       
      if (ObaGumbaStisnuta()) {
            if (Integer.parseInt(tfRezultatKocke1.getText())>Integer.parseInt(tfRezultatKocke2.getText())) {
                msgBox.appendText("Rezultat na prvoj kocki je veci, stoga, pomaknite se "+Integer.parseInt(tfRezultatKocke1.getText())+" polja.\n\n");
                brojMicanja=Integer.parseInt(tfRezultatKocke1.getText());
            }
            else if (Integer.parseInt(tfRezultatKocke1.getText())<Integer.parseInt(tfRezultatKocke2.getText())) {
                msgBox.appendText("Rezultat na drugoj kocki je veci, stoga, pomaknite se "+Integer.parseInt(tfRezultatKocke2.getText())+" polja.\n\n");
              brojMicanja=Integer.parseInt(tfRezultatKocke2.getText());
            }
            else {
                 msgBox.appendText("Rezultati su jednaki, pokanite se "+Integer.parseInt(tfRezultatKocke1.getText())+" polja.\n\n");
              brojMicanja=Integer.parseInt(tfRezultatKocke1.getText());
            }
            if (game.currentPlayer==game.Player1) {

                game.player1Turn( IgracePolje, brojMicanja);
                counterPoteza++;
          }
            else if ((game.currentPlayer==game.Player2)) {
                 game.player2Turn( IgracePolje, brojMicanja);
                 counterPoteza++;
            }
             if (game.currentPlayer==game.Player1) {
              
            lblKojiIgrac.setText("Plavi igrac");
        }
        else if (game.currentPlayer==game.Player2) {
             lblKojiIgrac.setText("Crveni igrac");
        }
            if (game.isGaveOver) {
              btnBaciKocku.setDisable(true);
              btnBaciKocku2.setDisable(true);
          }
       
        }
      else{
          System.out.println("Nisu stisnuti gumbi");
          
      }
      
      
    }
    
   
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        loadajPlocu();
        try {
            Java2Game.getGameData();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    private  boolean ObaGumbaStisnuta() {
        if (btnBaciKocku.isDisabled()||btnBaciKocku2.isDisabled()) {
            btnBaciKocku.setDisable(false);
            btnBaciKocku2.setDisable(false);
            return true;
            
        }
        else return false;
    }

        
     public void saveGameAction() throws IOException{
         
         System.out.println("uso sam u save game");
          saveXML();
         
        List<Igrac> lista=new ArrayList<>();
        lista.add(game.Player1);
        lista.add(game.Player2);
        lista.add(game.currentPlayer);
        SaveGame save = new SaveGame(lista);
        
       
    }
     
      public void loadGameAction() throws IOException, FileNotFoundException, ClassNotFoundException{
//         loadajPlocu();
//        LoadGame loaded= new LoadGame(this.game, this.gpBoard);
//        
//        this.game=loaded.getGame();
//        
//        game.Loadaj(loaded.getGame().getPlayer1(), loaded.getGame().getPlayer2());
        
        FileChooser choser = new FileChooser();
        choser.setTitle("ODABERI DATOTEKU");
        File load = choser.showOpenDialog(gpBoard.getScene().getWindow());
        if (load != null) {
            
            readFromXMLNew(load);
        }
    }
     
     public void createInfoFile() throws IOException{
         ClassInfo.GetInfo();
     }

    private void loadajPlocu() {
        Pane p=new Pane();
        p.setStyle("-fx-background-image: url('1.png');"+ "-fx-background-size: cover;");
       
        Pane p2=new Pane();
        p2.setStyle("-fx-background-image: url('/ploca/2.png');"+ "-fx-background-size: cover;");
        
        Pane p3=new Pane();
        p3.setStyle("-fx-background-image: url('/ploca/3.png');"+ "-fx-background-size: cover;");
        
        Pane p4=new Pane();
        p4.setStyle("-fx-background-image: url('/ploca/4.png');"+ "-fx-background-size: cover;");
        
        Pane p5=new Pane();
        p5.setStyle("-fx-background-image: url('/ploca/5.png');"+ "-fx-background-size: cover;");
       
        
        Pane p6=new Pane();
        p6.setStyle("-fx-background-image: url('/ploca/6.png');"+ "-fx-background-size: cover;");
        
        Pane p7=new Pane();
        p7.setStyle("-fx-background-image: url('/ploca/7.png');"+ "-fx-background-size: cover;");
        
        Pane p8=new Pane();
        p8.setStyle("-fx-background-image: url('/ploca/8.png');"+ "-fx-background-size: cover;");
        
        Pane p9=new Pane();
        p9.setStyle("-fx-background-image: url('/ploca/9.png');"+ "-fx-background-size: cover;");
        
         Pane p10=new Pane();
        p10.setStyle("-fx-background-image: url('/ploca/10.png');"+ "-fx-background-size: cover;");
        
         Pane p11=new Pane();
        p11.setStyle("-fx-background-image: url('/ploca/11.png');"+ "-fx-background-size: cover;");
        
         Pane p12=new Pane();
        p12.setStyle("-fx-background-image: url('/ploca/12.png');"+ "-fx-background-size: cover;");
        
         Pane p13=new Pane();
        p13.setStyle("-fx-background-image: url('/ploca/13.png');"+ "-fx-background-size: cover;");
        
         Pane p14=new Pane();
        p14.setStyle("-fx-background-image: url('/ploca/14.png');"+ "-fx-background-size: cover;");
        
         Pane p15=new Pane();
        p15.setStyle("-fx-background-image: url('/ploca/15.png');"+ "-fx-background-size: cover;");
        
         Pane p16=new Pane();
        p16.setStyle("-fx-background-image: url('/ploca/16.png');"+ "-fx-background-size: cover;");
        
         Pane p17=new Pane();
        p17.setStyle("-fx-background-image: url('/ploca/17.png');"+ "-fx-background-size: cover;");
        
         Pane p18=new Pane();
        p18.setStyle("-fx-background-image: url('/ploca/18.png');"+ "-fx-background-size: cover;");
        
         Pane p19=new Pane();
        p19.setStyle("-fx-background-image: url('/ploca/19.png');"+ "-fx-background-size: cover;");
        
         Pane p20=new Pane();
        p20.setStyle("-fx-background-image: url('/ploca/20.png');"+ "-fx-background-size: cover;");
        
         Pane p21=new Pane();
        p21.setStyle("-fx-background-image: url('/ploca/21.png');"+ "-fx-background-size: cover;");
        
         Pane p22=new Pane();
        p22.setStyle("-fx-background-image: url('/ploca/22.png');"+ "-fx-background-size: cover;");
        
         Pane p23=new Pane();
        p23.setStyle("-fx-background-image: url('/ploca/23.png');"+ "-fx-background-size: cover;");
        
         Pane p24=new Pane();
        p24.setStyle("-fx-background-image: url('/ploca/24.png');"+ "-fx-background-size: cover;");
        
         Pane p25=new Pane();
        p25.setStyle("-fx-background-image: url('/ploca/25.png');"+ "-fx-background-size: cover;");
        
         Pane p26=new Pane();
        p26.setStyle("-fx-background-image: url('/ploca/26.png');"+ "-fx-background-size: cover;");
        
         Pane p27=new Pane();
        p27.setStyle("-fx-background-image: url('/ploca/27.png');"+ "-fx-background-size: cover;");
        
         Pane p28=new Pane();
        p28.setStyle("-fx-background-image: url('/ploca/28.png');"+ "-fx-background-size: cover;");
        
         Pane p29=new Pane();
        p29.setStyle("-fx-background-image: url('/ploca/29.png');"+ "-fx-background-size: cover;");
        
         Pane p30=new Pane();
        p30.setStyle("-fx-background-image: url('/ploca/30.png');"+ "-fx-background-size: cover;");
        
       
      gpBoard.add(p,0,0);
      gpBoard.add(p2,0,1);
      gpBoard.add(p3,0,2);
      gpBoard.add(p4,0,3);
      gpBoard.add(p5,0,4);
      gpBoard.add(p6,0,5);
      gpBoard.add(p7,0,6);
      gpBoard.add(p8,0,7);
      gpBoard.add(p9,1,7);
      gpBoard.add(p10,1,6);
      gpBoard.add(p11,1,5);
          gpBoard.add(p12,1,4);
          gpBoard.add(p13,1,3);
          gpBoard.add(p14,1,2);
          gpBoard.add(p15,1,1);
          gpBoard.add(p16,1,0);
          gpBoard.add(p17,2,0);
          gpBoard.add(p18,2,1);
          gpBoard.add(p19,2,2);
          gpBoard.add(p20,2,3);
          gpBoard.add(p21,2,4);
          gpBoard.add(p22,2,5);
          gpBoard.add(p23,2,6);
          gpBoard.add(p24,2,7);
          gpBoard.add(p25,3,7);
          gpBoard.add(p26,3,6);
          gpBoard.add(p27,3,5);
          gpBoard.add(p28,3,4);
          gpBoard.add(p29,3,3);
          gpBoard.add(p30,3,2);
          
          
          
          
      
     IgracePolje.add(new Field(0,0));
     IgracePolje.add(new Field(0,1));
     IgracePolje.add(new Field(0,2));
     IgracePolje.add(new Field(0,3));
     IgracePolje.add(new Field(0,4));
     IgracePolje.add(new Field(0,5));
     IgracePolje.add(new Field(0,6));
     IgracePolje.add(new Field(0,7));
     IgracePolje.add(new Field(1,7));
     IgracePolje.add(new Field(1,6));
     IgracePolje.add(new Field(1,5));

     IgracePolje.add(new Field(1,4));
     IgracePolje.add(new Field(1,3));
     IgracePolje.add(new Field(1,2));
     IgracePolje.add(new Field(1,1));
     IgracePolje.add(new Field(1,0));
     IgracePolje.add(new Field(2,0));
     IgracePolje.add(new Field(2,1));
     IgracePolje.add(new Field(2,2));
     IgracePolje.add(new Field(2,3));
     IgracePolje.add(new Field(2,4));
     IgracePolje.add(new Field(2,5));
     IgracePolje.add(new Field(2,6));
     IgracePolje.add(new Field(2,7));
     IgracePolje.add(new Field(3,7));
     IgracePolje.add(new Field(3,6));
     IgracePolje.add(new Field(3,5));
     IgracePolje.add(new Field(3,4));
     
     
     
         Pane p31=new Pane();
        p31.setStyle("-fx-background-image: url('/ploca/31.png');"+ "-fx-background-size: cover;");
       
        Pane p32=new Pane();
        p32.setStyle("-fx-background-image: url('/ploca/32.png');"+ "-fx-background-size: cover;");
        
        Pane p33=new Pane();
        p33.setStyle("-fx-background-image: url('/ploca/33.png');"+ "-fx-background-size: cover;");
        
        Pane p34=new Pane();
        p34.setStyle("-fx-background-image: url('/ploca/34.png');"+ "-fx-background-size: cover;");
        
        Pane p35=new Pane();
        p35.setStyle("-fx-background-image: url('/ploca/35.png');"+ "-fx-background-size: cover;");
       
        
        Pane p36=new Pane();
        p36.setStyle("-fx-background-image: url('/ploca/36.png');"+ "-fx-background-size: cover;");
        
        Pane p37=new Pane();
        p37.setStyle("-fx-background-image: url('/ploca/37.png');"+ "-fx-background-size: cover;");
        
        Pane p38=new Pane();
        p38.setStyle("-fx-background-image: url('/ploca/38.png');"+ "-fx-background-size: cover;");
        
        Pane p39=new Pane();
        p39.setStyle("-fx-background-image: url('/ploca/39.png');"+ "-fx-background-size: cover;");
        
         Pane p40=new Pane();
        p40.setStyle("-fx-background-image: url('/ploca/40.png');"+ "-fx-background-size: cover;");
        
         Pane p41=new Pane();
        p41.setStyle("-fx-background-image: url('/ploca/41.png');"+ "-fx-background-size: cover;");
        
         Pane p42=new Pane();
        p42.setStyle("-fx-background-image: url('/ploca/42.png');"+ "-fx-background-size: cover;");
        
         Pane p43=new Pane();
        p43.setStyle("-fx-background-image: url('/ploca/43.png');"+ "-fx-background-size: cover;");
        
         Pane p44=new Pane();
        p44.setStyle("-fx-background-image: url('/ploca/44.png');"+ "-fx-background-size: cover;");
        
         Pane p45=new Pane();
        p45.setStyle("-fx-background-image: url('/ploca/45.png');"+ "-fx-background-size: cover;");
        
         Pane p46=new Pane();
        p46.setStyle("-fx-background-image: url('/ploca/46.png');"+ "-fx-background-size: cover;");
        
         Pane p47=new Pane();
        p47.setStyle("-fx-background-image: url('/ploca/47.png');"+ "-fx-background-size: cover;");
        
         Pane p48=new Pane();
        p48.setStyle("-fx-background-image: url('/ploca/48.png');"+ "-fx-background-size: cover;");
        
         Pane p49=new Pane();
        p49.setStyle("-fx-background-image: url('/ploca/49.png');"+ "-fx-background-size: cover;");
        
         Pane p50=new Pane();
        p50.setStyle("-fx-background-image: url('/ploca/50.png');"+ "-fx-background-size: cover;");
        
         Pane p51=new Pane();
        p51.setStyle("-fx-background-image: url('/ploca/51.png');"+ "-fx-background-size: cover;");
        
         Pane p52=new Pane();
        p52.setStyle("-fx-background-image: url('/ploca/52.png');"+ "-fx-background-size: cover;");
        
         Pane p53=new Pane();
        p53.setStyle("-fx-background-image: url('53.png');"+ "-fx-background-size: cover;");
        
         Pane p54=new Pane();
        p54.setStyle("-fx-background-image: url('/ploca/54.png');"+ "-fx-background-size: cover;");
        
         Pane p55=new Pane();
        p55.setStyle("-fx-background-image: url('/ploca/55.png');"+ "-fx-background-size: cover;");
        
         Pane p56=new Pane();
        p56.setStyle("-fx-background-image: url('/ploca/56.png');"+ "-fx-background-size: cover;");
        
         Pane p57=new Pane();
        p57.setStyle("-fx-background-image: url('/ploca/57.png');"+ "-fx-background-size: cover;");
        
         Pane p58=new Pane();
        p58.setStyle("-fx-background-image: url('/ploca/58.png');"+ "-fx-background-size: cover;");
        
         Pane p59=new Pane();
        p59.setStyle("-fx-background-image: url('/ploca/59.png');"+ "-fx-background-size: cover;");
        
         Pane p60=new Pane();
        p60.setStyle("-fx-background-image: url('/ploca/60.png');"+ "-fx-background-size: cover;");
        
         Pane p61=new Pane();
        p61.setStyle("-fx-background-image: url('/ploca/61.png');"+ "-fx-background-size: cover;");
        
         Pane p62=new Pane();
        p62.setStyle("-fx-background-image: url('/ploca/62.png');"+ "-fx-background-size: cover;");
        
         Pane p63=new Pane();
        p63.setStyle("-fx-background-image: url('/ploca/63.png');"+ "-fx-background-size: cover;");
        
         Pane p64=new Pane();
        p64.setStyle("-fx-background-image: url('/ploca/64.png');"+ "-fx-background-size: cover;");
        
       
      gpBoard.add(p31,3,3);
      gpBoard.add(p32,3,2);
      gpBoard.add(p33,3,1);
      gpBoard.add(p34,3,0);
      gpBoard.add(p35,4,0);
      gpBoard.add(p36,4,1);
      gpBoard.add(p37,4,2);
      gpBoard.add(p38,4,3);
      gpBoard.add(p39,4,4);
      gpBoard.add(p40,4,5);
      gpBoard.add(p41,4,6);
          gpBoard.add(p42,4,7);
          gpBoard.add(p43,5,7);
          gpBoard.add(p44,5,6);
          gpBoard.add(p45,5,5);
          gpBoard.add(p46,5,4);
          gpBoard.add(p47,5,3);
          gpBoard.add(p48,5,2);
          gpBoard.add(p49,5,1);
          gpBoard.add(p50,5,0);
          gpBoard.add(p51,6,0);
          gpBoard.add(p52,6,1);
          gpBoard.add(p53,6,2);
          gpBoard.add(p54,6,3);
          gpBoard.add(p55,6,4);
          gpBoard.add(p56,6,5);
          gpBoard.add(p57,6,6);
          gpBoard.add(p58,6,7);
          gpBoard.add(p59,7,7);
          gpBoard.add(p60,7,6);
          gpBoard.add(p61,7,5);
          gpBoard.add(p62,7,4);
          gpBoard.add(p63,7,3);
          gpBoard.add(p64,7,2);
          
          
          
      
     IgracePolje.add(new Field(3,3));
     IgracePolje.add(new Field(3,2));
     IgracePolje.add(new Field(3,1));
     IgracePolje.add(new Field(3,0));
     IgracePolje.add(new Field(4,0));
     IgracePolje.add(new Field(4,1));
     IgracePolje.add(new Field(4,2));
     IgracePolje.add(new Field(4,3));
     IgracePolje.add(new Field(4,4));
     IgracePolje.add(new Field(4,5));
     IgracePolje.add(new Field(4,6));

     IgracePolje.add(new Field(4,7));
     IgracePolje.add(new Field(5,7));
     IgracePolje.add(new Field(5,6));
     IgracePolje.add(new Field(5,5));
     IgracePolje.add(new Field(5,4));
     IgracePolje.add(new Field(5,3));
     IgracePolje.add(new Field(5,2));
     IgracePolje.add(new Field(5,1));
     IgracePolje.add(new Field(5,0));
     IgracePolje.add(new Field(6,0));
     IgracePolje.add(new Field(6,1));
     IgracePolje.add(new Field(6,2));
     IgracePolje.add(new Field(6,3));
     IgracePolje.add(new Field(6,4));
     IgracePolje.add(new Field(6,5));
     IgracePolje.add(new Field(6,6));
     IgracePolje.add(new Field(6,7));
     IgracePolje.add(new Field(7,7));
     IgracePolje.add(new Field(7,6));
     IgracePolje.add(new Field(7,5));
     IgracePolje.add(new Field(7,4));
     IgracePolje.add(new Field(7,3));
     IgracePolje.add(new Field(7,2));
     IgracePolje.add(new Field(7,1));
     IgracePolje.add(new Field(7,0));
     
    game=new Game(gpBoard);
     if (game.currentPlayer==game.Player1) {
            lblKojiIgrac.setText("Plavi igrac");
        }
        else if (game.currentPlayer==game.Player2) {
             lblKojiIgrac.setText("Crveni igrac");
        }
    }
  
    
     @FXML
    public void saveXML() {

//         for (Potez p : game.odigraniPotezi) {
//             System.out.println(p.getIDIgrac() + " "+"y: "+p.getTrenutnoStanje().getY()+", x: "+p.getTrenutnoStanje().getX());
//         }
         
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.newDocument();

            //Root element +
            Element rootEl = XMLHelper.rootElement(doc);

            //Move +          
           // Element moveEl = XMLHelper.moveElement(doc, rootEl);

            
            Element listMoves = doc.createElement("Moves");
            rootEl.appendChild(listMoves);
            int tempBrojPoteza=0;
            for (Potez potez : game.odigraniPotezi) {
               
                Element moveElement = doc.createElement("Move");
                listMoves.appendChild(moveElement);
                moveElement.setAttribute("ID", new Integer(potez.getIDIgrac()).toString());
                moveElement.setAttribute("POTEZ", String.valueOf(tempBrojPoteza++));
           
                XMLHelper.xElement(doc,moveElement,  potez.getTrenutnoStanje().getX());
                XMLHelper.yElement(doc,moveElement,  potez.getTrenutnoStanje().getY());
                if (potez.getIDIgrac()==1) {
                     XMLHelper.posElement(doc,moveElement,game.getPlayer1pos());
                }
                else{
                    XMLHelper.posElement(doc,moveElement,game.getPlayer2pos());
                }
      
                }

//            Element move2 = XMLHelper.moveElement(doc, listMoves);
//            rootEl.appendChild(listMoves);
        

            TransformerFactory trFactory = TransformerFactory.newInstance();
            Transformer transformer = trFactory.newTransformer();
            DOMSource source = new DOMSource(doc);

            Scanner scanner = new Scanner(System.in);
            System.out.println("Ime datoteke: ");
            String fileName = scanner.nextLine();

            StreamResult result = new StreamResult(new File(fileName + ".xml"));
            transformer.transform(source, result);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Uspješno spremanje");
            alert.setHeaderText(null);
            alert.setContentText("XML datoteka je uspješno spremljena!");

            alert.showAndWait();

        } catch (Exception e) {
        }

    }

    
   
     
    @FXML
    public void readFromXMLNew(File f){
     Potezi=new ArrayList();
         try {
            DocumentBuilder docBuild = getDB();
            Document doc = docBuild.parse(f);
            org.w3c.dom.Node root = doc.getDocumentElement();


	
	doc.getDocumentElement().normalize();

	//System.out.println("Root element :" + doc.getDocumentElement().getNodeName());

	NodeList nList = doc.getElementsByTagName("Move");

	System.out.println("----------------------------");
        
	for (int temp = 0; temp < nList.getLength(); temp++) {
          //  System.out.println(nList.getLength());
		org.w3c.dom.Node nNode = nList.item(temp);

		//System.out.println("\nCurrent Element :" + nNode.getNodeName());

		if (nNode.getNodeType() == org.w3c.dom.Node.ELEMENT_NODE) {

			Element eElement = (Element) nNode;

//			System.out.println("Player id : " + eElement.getAttribute("ID"));
//                      System.out.println("Potez : " + eElement.getAttribute("POTEZ"));
//			System.out.println("X : " + eElement.getElementsByTagName("Xkoordinata").item(0).getTextContent());
//			System.out.println("Y : " + eElement.getElementsByTagName("Ykoordinata").item(0).getTextContent());
			
                        Potez tempPotez=new Potez();
                        tempPotez.setIDIgrac(Integer.parseInt(eElement.getAttribute("ID")));
                        tempPotez.setBrojPoteza(Integer.parseInt(eElement.getAttribute("POTEZ")));
                        Field currentField=new Field((Integer.parseInt(eElement.getElementsByTagName("Xkoordinata").item(0).getTextContent())),(Integer.parseInt(eElement.getElementsByTagName("Ykoordinata").item(0).getTextContent())));
                        tempPotez.setTrenutnoStanje(currentField);
                        tempPotez.setPosition(Integer.parseInt(eElement.getElementsByTagName("Position").item(0).getTextContent()));
                        Potezi.add(tempPotez);
		}
              
	}
        //  System.out.println(Potezi.get(Potezi.size()) +" " + Potezi.get(Potezi.size()-1));
               game.RefreshLoad(Potezi.get(Potezi.size()-1), Potezi.get(Potezi.size()-2) );
                     pointer+=Potezi.size();
    } catch (Exception e) {
	e.printStackTrace();
    }
         
//         for (Potez potez : Potezi) {
//            System.out.println(potez.getIDIgrac() + " "+"y: "+potez.getTrenutnoStanje().getY()+", x: "+potez.getTrenutnoStanje().getX());
//        }
  
    }
     
     
    private static DocumentBuilder getDB() throws ParserConfigurationException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setIgnoringElementContentWhitespace(true);
        factory.setIgnoringComments(true);

        DocumentBuilder builder = factory.newDocumentBuilder();
        return builder;
    }
    
     public void btnUnatragAction() throws IOException{
         pomakniZa2=true;
         try {
             if (Potezi.get(pointer-1)!=null) {
                 game.OdigrajPotez(Potezi.get(pointer));
                pointer--;
             }
             
         } catch (Exception e) {
             System.out.println("Prosli ste sve poteze");
         }
         

    }
      public void btnNaprijedAction() throws IOException{
          if (pomakniZa2) {
              pointer+=2;

              
          }
          try {
              
              if (Potezi.get(pointer+1)!=null) {
                 pointer++;
                 game.OdigrajPotez(Potezi.get(pointer)); 
              }
              
              else{
                  System.out.println("Dosli ste do zadnjeg moguceg poteza");
              }
              
              if (pomakniZa2) {
                pomakniZa2=false;
                pointer-=2;
              }
         
          } catch (Exception e) {
              System.out.println("Prosli ste sve poteze");
          }
      
       
        
       
    }
}
