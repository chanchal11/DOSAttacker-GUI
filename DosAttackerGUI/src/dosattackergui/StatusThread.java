/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dosattackergui;

import java.io.IOException;
import java.net.ConnectException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author root
 */
public class StatusThread extends Thread{
    JLabel label;
    String addr;
    int port;
    StatusThread(JLabel label,String addr,int port)
    {
        this.label=label;
        this.addr=addr;
	this.port=port;
    }
    public void run()
    {
       while(true)
       {
           try{
               Thread.sleep(500);
               new Socket(addr,port);
           }
           catch(ConnectException e){
               label.setText("Attack Success");
           } catch (IOException ex) {
               label.setText(ex.getMessage());
           } catch (InterruptedException ex) {
               label.setText(ex.getMessage());
           }
           label.setText("Attacking...");
       }
    }
}
