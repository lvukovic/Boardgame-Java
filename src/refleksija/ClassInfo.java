/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package refleksija;

import model.Igrac;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;
import java.util.logging.Level;
import java.util.logging.Logger;
import java2game.Game;
import java2game.Java2Game;
/**
 *
 * @author Luka
 */
public abstract class ClassInfo {
    
    public static void GetInfo() throws IOException{
        File file = new File("Reflekicja.txt");
        StringBuilder sb=new StringBuilder ();
         BufferedWriter writer = new BufferedWriter(new FileWriter("Reflekicja.txt", true));

     

        try {
            Class<?> klass = Class.forName("java2game.Igrac");
            try {
                Igrac igrac = (Igrac)klass.newInstance();
                Field[] variableField = klass.getDeclaredFields();
                Method[] methodField = klass.getMethods();
                
                sb.append("Varijable u klasi igrac\n");
                for (Field field : variableField) {
                    if (Modifier.isPublic(field.getModifiers())) {
                        sb.append(field.getName()+"\n");
                      
                    }
                }
                
                sb.append("\nMetode u klasi igrac\n");
                for (Method method : methodField) {
                   sb.append(method.getName()+"\n");
                }
                
                 for (Method metoda : methodField) {
                    if (Modifier.isPublic(metoda.getModifiers())) {
                        sb.append("\nMETODA: "+metoda.getName()+"\n");
                        if (metoda.getParameters().length > 0) {
                            sb.append("Metoda prima: "+"\n");
                            Parameter[] parameters = metoda.getParameters();
                            for (Parameter param : parameters) {
                               sb.append(param.getType() + " " + param.getName()+"\n");
                            }
                        } else {
                           sb.append("Metoda ne prima nista"+"\n");
                        }

                        sb.append("Metoda vraca: "+metoda.getReturnType()+"\n");
                   
                    }
             else {
                sb.append("Traženi objekt nije tipa 'Igrac'"+"\n");
                System.exit(0);
            }
                
            } }catch (InstantiationException | IllegalAccessException ex) {
                Logger.getLogger(Java2Game.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Java2Game.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        try {
            Class<?> klass = Class.forName("java2game.Game");
            try {
                Game game = (Game)klass.newInstance();
                Field[] variableField = klass.getDeclaredFields();
                Method[] methodField = klass.getMethods();
                
                sb.append("Varijable u klasi Game\n");
                for (Field field : variableField) {
                    if (Modifier.isPublic(field.getModifiers())) {
                        sb.append(field.getName()+"\n");
                      
                    }
                }
                
                sb.append("\nMetode u klasi Game\n");
                for (Method method : methodField) {
                   sb.append(method.getName()+"\n");
                }
                
                 for (Method metoda : methodField) {
                    if (Modifier.isPublic(metoda.getModifiers())) {
                        sb.append("METODA: "+metoda.getName()+"\n");
                        if (metoda.getParameters().length > 0) {
                            sb.append("Metoda prima: ");
                            Parameter[] parameters = metoda.getParameters();
                            for (Parameter param : parameters) {
                               sb.append(param.getType() + " " + param.getName()+"\n");
                            }
                        } else {
                           sb.append("Metoda ne prima nista");
                        }

                        sb.append("Metoda vraca: "+metoda.getReturnType()+"\n"+"\n");
                   
                    }
             else {
                sb.append("Traženi objekt nije tipa 'Game'"+"\n");
                System.exit(0);
            }
                
            } }catch (InstantiationException | IllegalAccessException ex) {
                Logger.getLogger(Java2Game.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Java2Game.class.getName()).log(Level.SEVERE, null, ex);
        }
        writer.write(sb.toString());

         writer.close();
    }
}
