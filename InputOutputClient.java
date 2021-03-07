package test;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class InputOutputClient {

  public static void main(String[] args) {

    Socket socket = null;
    
    OutputStream outputStream = null;
    DataOutputStream dataOutputStream = null;

    InputStream inputStream = null;
    DataInputStream dataInputStream = null;
    
    Scanner scanner = null;

 try {

      socket = new Socket("localhost", 9000); 
      System.out.println("서버연결완료");

      outputStream= socket.getOutputStream();
      dataOutputStream = new DataOutputStream(outputStream);

      inputStream= socket.getInputStream();
      dataInputStream = new DataInputStream(inputStream);

      scanner = new Scanner(System.in); 

      while (true) {

        System.out.print("보내기>>"); 

        String outMessage = scanner.nextLine(); 
        dataOutputStream.writeUTF(outMessage); 
        dataOutputStream.flush();

        String receviedMessage = dataInputStream.readUTF(); 

        System.out.println("서버: " + receviedMessage); 

        if (outMessage.equals ("bye")) { 
          //out.write(outputMessage+"\n"); 
          //out.flush();
          break;
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    } finally {

      try {
        if (dataInputStream != null) dataInputStream.close();
        if (dataOutputStream != null) dataOutputStream.close();
        if (inputStream != null) inputStream.close();
        if (outputStream != null) outputStream.close();
       
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }
}
