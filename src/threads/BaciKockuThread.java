/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threads;


import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author Luka
 */
public class BaciKockuThread extends Thread{

    public Image[] getDice() {
        return dice;
    }

    public void setDice(Image[] dice) {
        this.dice = dice;
    }

    public ImageView getImage() {
        return image;
    }

    public void setImage(ImageView image) {
        this.image = image;
    }

    public int getDobiveniBroj() {
        return DobiveniBroj;
    }

    public void setDobiveniBroj(int DobiveniBroj) {
        this.DobiveniBroj = DobiveniBroj;
    }
    
    Image[] dice=new Image[6];
       TextArea msgBox;
    ImageView image;
    int DobiveniBroj;
    int brojKocke;
    TextField tfRezultatKocke;
    
    public BaciKockuThread(ImageView image, String Ime,int Broj,TextArea msgBox,TextField tfRezultatKocke, int brojMicanja){
        
        super(Ime);
        this.image=image;
        this.brojKocke=Broj;
          this.msgBox = msgBox;
          this.tfRezultatKocke=tfRezultatKocke;
          this.DobiveniBroj=brojMicanja;
    }
    
     @Override
    public void run() {
       ZavrtiKocku(DobiveniBroj);
         msgBox.appendText("Na kocki broj: "+brojKocke+" dobili ste broj: "+new Integer(DobiveniBroj).toString()+"\n");
//         msgBox.setDisable(true);
         tfRezultatKocke.setText(new Integer(DobiveniBroj).toString());
         tfRezultatKocke.setDisable(true);
        
    }

    private int ZavrtiKocku(int Dobivenibroj) {
        for (int i = 0; i < 6; i++) {
              Image image = new Image("dice"+(i+1)+".jpg") ;
            dice[i]=image;
      
         }
         int broj=0;
                 for (int i = 0; i < ((int)(200*Math.random())); i++) {
         
                broj=0 + (int)(Math.random() * 5); 
               image.setImage((javafx.scene.image.Image)dice[broj]); 
               broj=DobiveniBroj;
             try {
                 Thread.sleep(100);
             } catch (InterruptedException ex) {
                 Logger.getLogger(BaciKockuThread.class.getName()).log(Level.SEVERE, null, ex);
             }
             
             
        }
                  image.setImage((javafx.scene.image.Image)dice[broj-1]);
     return broj;
                 
    }
    
    
    
}
