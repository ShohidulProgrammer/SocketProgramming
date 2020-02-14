import java.net.*;  
import java.io.*;
class Server{  
    public static void main(String args[])throws Exception{ 
        try    
        {
            ServerSocket ss = new ServerSocket(3333); 
            Socket s = ss.accept(); 
            DataInputStream din = new DataInputStream(s.getInputStream());
            DataOutputStream dout=new DataOutputStream(s.getOutputStream()); 
            BufferedReader br=new BufferedReader(new InputStreamReader(System.in));  
    
    String str="",str2="";  
    while(!str.equals("stop")){  
    // client msg reading
        str = din.readUTF();
    str = str.toUpperCase();
    System.out.println("Client Says: "+str); 
    
    
    // msg read from text to RAM
    str2=br.readLine(); 
    // msg out
    dout.writeUTF(str2);  
    dout.flush();  
    }  
   }
    catch(Exception e){
        }
    }
}  
