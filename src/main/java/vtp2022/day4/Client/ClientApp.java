package vtp2022.day4.Client;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.Console;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

// run mvn compile exec:java -Dexec.mainClass="vtp2022.day4.Client.ClientApp" -Dexec.args="0.0.0.0:3000"
public class ClientApp {
  public static void main(String[] args) {
  String arg = args[0];
  String[] arr = arg.split(":");
  String host = arr[0];
  Integer port = Integer.parseInt(arr[1]) ;

  try {
    //initiate connection
    Socket socket = new Socket(host,port);

    //define inputstream
    InputStream is = socket.getInputStream();
    BufferedInputStream bis = new BufferedInputStream(is);
    DataInputStream dis = new DataInputStream(bis);
    
    //define outputstream
    OutputStream os = socket.getOutputStream();
    BufferedOutputStream bos = new BufferedOutputStream(os);
    DataOutputStream dos = new DataOutputStream(bos);

    //read command from terminal
    Console cons = System.console();
    String input = cons.readLine("Send command to server > ");
    dos.writeUTF(input);
    dos.flush();

    //read response from server
    String response = dis.readUTF();
    if(response.contains("cookie-text")){
        System.out.println(response);
        String[] cookieValue = response.split(" ",2);
        System.out.println("Cookie from server >> "+ cookieValue[1]);
    }

    is.close();
    os.close();
    socket.close();

  } catch (NumberFormatException e){
    e.printStackTrace();
  } catch (IOException e){
    e.printStackTrace();
  }
    
    
}
}
