/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package java2game;

import model.PlayerOblik;
import model.Field;
import model.GameStatus;
import model.Igrac;
import java.io.Serializable;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import static java2game.ServerMain.socket;
import javafx.scene.control.Alert;
import javafx.scene.layout.GridPane;
import model.Potez;

/**
 *
 * @author Luka
 */
public class Game implements Serializable{
 Igrac Player1=new Igrac(1,"Plavi", javafx.scene.paint.Color.DARKBLUE);
Igrac Player2=new Igrac(2,"Crveni", javafx.scene.paint.Color.DARKRED );
GridPane ploca;
boolean isPlayer1Turn=true;
Igrac currentPlayer=Player1;

public boolean isGaveOver=false;

List<Potez> odigraniPotezi;

    public int getPlayer1pos() {
        return Player1pos;
    }

    public void setPlayer1pos(int Player1pos) {
        this.Player1pos = Player1pos;
    }

    public int getPlayer2pos() {
        return Player2pos;
    }

    public void setPlayer2pos(int Player2pos) {
        this.Player2pos = Player2pos;
    }

private int Player1pos=0;
private int Player2pos=0;

Socket socket=new Socket();

    public GridPane getPloca() {
        return ploca;
    }

    public void setPloca(GridPane ploca) {
        this.ploca = ploca;
    }

   
  private GameStatus status = GameStatus.PLAYING;

  
   public Igrac getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(Igrac currentPlayer) {
        this.currentPlayer = currentPlayer;
    }
    public Igrac getPlayer1() {
        return Player1;
    }

    public void setPlayer1(Igrac Player1) {
        this.Player1 = Player1;
    }

    public Igrac getPlayer2() {
        return Player2;
    }

    public void setPlayer2(Igrac Player2) {
        this.Player2 = Player2;
    }

    public boolean isIsPlayer1Turn() {
        return isPlayer1Turn;
    }

    public void setIsPlayer1Turn(boolean isPlayer1Turn) {
        this.isPlayer1Turn = isPlayer1Turn;
    }

    public GameStatus getStatus() {
        return status;
    }

    public void setStatus(GameStatus status) {
        this.status = status;
    }

    public Game() {
    }
  
  
  
    public Game(GridPane gpBoard) {
        this.ploca=gpBoard;
        odigraniPotezi=new ArrayList<>();
   // gpBoard.add(Player1.Oblik, Player1.currentPos.getX(), Player1.currentPos.getX());
   // gpBoard.add(Player2.Oblik, Player2.currentPos.getX(), Player2.currentPos.getX());
    }

   public void player1Turn( List<Field> Polja, int broj) {
        synchronized (this) {
             currentPlayer=Player2;
             Player1pos+=broj;
           
            
            if (isPlayer1Turn == false) {
                try {
                    System.out.println("Waiting other player turn to be over");
                    wait();
                    
                } catch (Exception e) {
                    System.out.println("Problem in method player1Turn");
                    System.out.println(e.getMessage());
                }
            }
            
            if (isPlayer1Turn) {
             
               
               
               // Potez p=new Potez(1,Player1.currentPos.getX(),Player1.currentPos.getY());
              
                   if (Player1pos==62) {
                 Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Pobjednik");
            alert.setHeaderText(null);
            alert.setContentText("Igrac: 'Plavi' je pobjednik ");
            isGaveOver=true;
            alert.showAndWait();
            }
                   else if (Player1pos>62) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Prevelik broj");
            alert.setHeaderText(null);
            alert.setContentText("Dobili ste prevlik broj na kocki");
         
            alert.showAndWait();
            Player1pos-=broj;
                }
                   else {
                        ploca.getChildren().remove(Player1.Oblik);
              //  int tempX=Polja.get(broj+Player1.currentPos.getY()).getX();
                Player1.currentPos.setX(Polja.get(Player1pos).getX());
                Player1.currentPos.setY(Polja.get(Player1pos).getY());
                PlayerOblik novi=Player1.Oblik;
                        ploca.add(novi, Player1.currentPos.getX(), Player1.currentPos.getY());
                          Potez p=new Potez();
               p.setIDIgrac(1);
               Field currentField=new Field(Player1.currentPos.getX(),Player1.currentPos.getY());
               p.setTrenutnoStanje(currentField) ;
               odigraniPotezi.add(p);
                   }
               
             
            }
              
            isPlayer1Turn = false;
            
            notifyAll();
        }}
   
   public void player2Turn( List<Field> Polja, int broj) {
        synchronized (this) {
            currentPlayer=Player1;
             Player2pos+=broj;
            if (isPlayer1Turn == true) {
                try {
                    System.out.println("Waiting other player turn to be over");
                    wait();
                } catch (Exception e) {
                    System.out.println("Problem in method player1Turn");
                    System.out.println(e.getMessage());
                }
            }
            if (!isPlayer1Turn) {
              
               
            if (Player2pos==62) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Pobjednik");
            alert.setHeaderText(null);
            alert.setContentText("Igrac: 'Crveni' je pobjednik ");
            alert.showAndWait();
            isGaveOver=true;
            }
            else if (Player2pos>62) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Prevelik broj");
            alert.setHeaderText(null);
            alert.setContentText("Dobili ste prevlik broj na kocki");
         
            alert.showAndWait();
            Player2pos-=broj;
                }
                 else{
                       ploca.getChildren().remove(Player2.Oblik);
                Player2.currentPos.setX(Polja.get(Player2pos).getX());
                Player2.currentPos.setY(Polja.get(Player2pos).getY());
                PlayerOblik novi=Player2.Oblik;
                      ploca.add(novi, Player2.currentPos.getX(), Player2.currentPos.getY()); Potez p=new Potez();
               p.setIDIgrac(2);
               Field currentField=new Field(Player2.currentPos.getX(),Player2.currentPos.getY());
               p.setTrenutnoStanje(currentField) ;
               odigraniPotezi.add(p);
                 }  
                
              
//             ploca.add(novi, 0, broj);
               
            }
            isPlayer1Turn = true;
           
            notifyAll();
            }
            
        }
    
    public void Loadaj(Igrac player1, Igrac player2){
                ploca.getChildren().remove(Player1.Oblik);
                Player1.currentPos.setX(player1.currentPos.getX());
                Player1.currentPos.setY(player1.currentPos.getY());
                PlayerOblik oblik1=player1.Oblik;
                ploca.add(oblik1, Player1.currentPos.getX(), Player1.currentPos.getY());
                
                 ploca.getChildren().remove(Player2.Oblik);
                Player1.currentPos.setX(player2.currentPos.getX());
                Player1.currentPos.setY(player2.currentPos.getY());
                PlayerOblik oblik2=player2.Oblik;
                ploca.add(oblik2, Player1.currentPos.getX(), Player1.currentPos.getY());
    }

    void RefreshLoad(Potez zadnji, Potez predzadnji) {
        
         if (zadnji.getIDIgrac()==1||predzadnji.getIDIgrac()==1) {
               Potez temp=new Potez();
              if (zadnji.getIDIgrac()==1) {
                  temp=zadnji;
              }
              else{
                  temp=predzadnji;
              }
                ploca.getChildren().remove(Player1.Oblik);
              //  int tempX=Polja.get(broj+Player1.currentPos.getY()).getX();
                Player1.currentPos.setX(temp.getTrenutnoStanje().getX());
                Player1.currentPos.setY(temp.getTrenutnoStanje().getY());
                PlayerOblik novi=Player1.Oblik;
                ploca.add(novi, Player1.currentPos.getX(), Player1.currentPos.getY());
                Player1pos=temp.getPosition();
             //   System.out.println(temp.getPosition());
            }
          if (zadnji.getIDIgrac()==2||predzadnji.getIDIgrac()==2) {
              Potez temp=new Potez();
              if (zadnji.getIDIgrac()==2) {
                  temp=zadnji;
              }
              else{
                  temp=predzadnji;
              }
            ploca.getChildren().remove(Player2.Oblik);
              //  int tempX=Polja.get(broj+Player1.currentPos.getY()).getX();
                Player2.currentPos.setX(temp.getTrenutnoStanje().getX());
                Player2.currentPos.setY(temp.getTrenutnoStanje().getY());
                PlayerOblik novi=Player2.Oblik;
                ploca.add(novi, Player2.currentPos.getX(), Player2.currentPos.getY());
                Player2pos=temp.getPosition();
               // System.out.println(temp.getPosition());
        }
         
         
    }

    public void OdigrajPotez(Potez current){
        Igrac tempPlayer=new Igrac();
        try {
              
        if (current.getIDIgrac()==1) {
            tempPlayer=Player1;
              ploca.getChildren().remove(Player1.Oblik);
              //  int tempX=Polja.get(broj+Player1.currentPos.getY()).getX();
                Player1.currentPos.setX(current.getTrenutnoStanje().getX());
                Player1.currentPos.setY(current.getTrenutnoStanje().getY());
                PlayerOblik novi=Player1.Oblik;
                ploca.add(novi, Player1.currentPos.getX(), Player1.currentPos.getY());
               // Player1pos+=
        }
        else{
            tempPlayer=Player2;  
            ploca.getChildren().remove(Player2.Oblik);
              //  int tempX=Polja.get(broj+Player1.currentPos.getY()).getX();
                Player2.currentPos.setX(current.getTrenutnoStanje().getX());
                Player2.currentPos.setY(current.getTrenutnoStanje().getY());
                PlayerOblik novi=Player2.Oblik;
                ploca.add(novi, Player2.currentPos.getX(), Player2.currentPos.getY());
               // Player1pos+=
            
        } 
        } catch (Exception e) {
            System.out.println("Prosli ste sve poteze!");
        }
    
             
              
            }
            

    }

    
