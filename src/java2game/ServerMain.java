/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package java2game;

import Networking.RmiInterface;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.AlreadyBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Hashtable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import threads.BaciKockuThread;

/**
 *
 * @author lukav
 */
public class ServerMain implements RmiInterface {

    public static final int PORT = 4545;
    public static final int RMI_PORT = 9991;

    static ServerSocket serverSocket = null;
    static Socket socket = null;

    static ObjectOutputStream objOutStream;
    static ObjectInputStream objInStream;

    static Boolean prviUse = true;
    // serverSocket = new ServerSocket(PORT);

    public static String CONFIG_PATH = "./";
    public static String CONFIG_NAME = "conf.ini";

    
  public  static int[] RMIRandoms=new int[2];
   public  static int RMICounter=0;
   
    public static void main(String[] args) throws IOException, ClassNotFoundException, NamingException {
        savePortNmToFile();
        loadPortNumberFromConfig("./conf.ini");
        
        runRMI();
       
        Kreni();
    }



    static int getRandomBroj() {
        int broj = 0;
        for (int i = 0; i < ((int) (200 * Math.random())); i++) {

            broj = 0 + (int) (Math.random() * 5)+1;

        }
        return broj;
    }

    public static void Kreni() throws IOException {
        System.out.println("Server se pokrenuo");
        //    if (prviUse) {
        serverSocket = new ServerSocket(PORT);
        //   }

        int[] polje = napuniPolje();

        while (true) {

            socket = serverSocket.accept();
            //    if (prviUse) {
            objOutStream = new ObjectOutputStream(socket.getOutputStream());
            objInStream = new ObjectInputStream(socket.getInputStream());
            //      prviUse=false;
            //      }
            // polje=napuniPolje();

            objOutStream.writeObject(polje);
            System.out.println("polje posla");

        }

    }

    public static int[] napuniPolje() {
        int[] polje = new int[2];
        int dummy = getRandomBroj();
        polje[0] = dummy;
        int dummy2 = getRandomBroj();
        polje[1] = dummy2;
        return polje;
    }

    public static void savePortNmToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(CONFIG_NAME))) {
            writer.write("4545");
            System.out.println("File with port sucessfuly saved!");
        } catch (Exception e) {
            System.out.println("PROBLEM WITH SAVING PORT NUMBER TO FILE!!\n" + e.getMessage());
        }
    }

    public static int loadPortNumberFromConfig(String fileName) throws NamingException {
        int port = 0;

        Hashtable env = new Hashtable();
        env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.fscontext.RefFSContextFactory");
        env.put(Context.PROVIDER_URL, "file:" + CONFIG_PATH);

        Context context = new InitialContext(env);
        Object readObject = context.lookup(fileName);
        File file = (File) readObject;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            port = Integer.parseInt(reader.readLine());
         //   System.out.println(port);
        } catch (Exception e) {
            System.out.println("Problem with reading port number! \n" + e.getMessage());
        }
        return port;
    }
    
    
  public static void runRMI() {
        try {
            ServerMain obj = new ServerMain();
            RmiInterface stub = (RmiInterface) UnicastRemoteObject.exportObject((Remote) obj, 0);
            Registry registry = LocateRegistry.createRegistry(RMI_PORT);
            registry.bind("RmiInterface", stub);
        } catch (RemoteException ex) {
            Logger.getLogger(ServerMain.class.getName()).log(Level.SEVERE, null, ex);
        } catch (AlreadyBoundException ex) {
            Logger.getLogger(ServerMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @Override
    public int[] ReturnBrojevi() throws RemoteException {
       
        if (RMICounter==0) {
            RMIRandoms=napuniPolje();
            RMICounter=2;
        }
         RMICounter--;
       return RMIRandoms;
    }
    
    

}
