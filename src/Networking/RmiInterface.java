/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Networking;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author lukav
 */
public interface RmiInterface extends Remote{
    int[] ReturnBrojevi() throws RemoteException;
}
