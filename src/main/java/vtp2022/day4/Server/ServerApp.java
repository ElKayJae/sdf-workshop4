package vtp2022.day4.Server;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerApp {
    public String input;
    public Socket socket;
    
    // run mvn compile exec:java -Dexec.mainClass="vtp2022.day4.Server.ServerApp" -Dexec.args="3000 C:\Users\lowke\Desktop\NUSISS\sdf-workshop4\src\main\java\vtp2022\day4\Server\cookie_file.txt"    
    public static void main(String[] args) {

        String serverPort = args[0];
        String cookieFilePath = args [1];
        try {
            System.out.println("Cookie Server started at "+ serverPort);
            //initiate connection
            ServerSocket server = new ServerSocket(Integer.parseInt(serverPort));
            Socket socket = server.accept();
            System.out.println("Connected");

            //define inputstream
            InputStream is = socket.getInputStream();
            BufferedInputStream bis = new BufferedInputStream(is);
            DataInputStream dis = new DataInputStream(bis);
            
            //define outpustream
            OutputStream os = socket.getOutputStream();
            BufferedOutputStream bos = new BufferedOutputStream(os);
            DataOutputStream dos = new DataOutputStream(bos);


            //read from inputstream
            String requestFromClient = dis.readUTF();
            System.out.println("Received request from client: " + requestFromClient);


            //working with inputstream message
            if (requestFromClient.equals("get-cookie")){
                System.out.println("file -> " +cookieFilePath);

                String randomCookie = Cookie.getRandomCookie(cookieFilePath);
                System.out.println(randomCookie);
                dos.writeUTF("cookie-text " + randomCookie);
                dos.flush();
            } else {
                dos.writeUTF("Invalid Command!");
                dos.flush();
            }

            is.close();
            os.close();
            socket.close();

        }catch(NumberFormatException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }
           
      
    }        
        
          
}

