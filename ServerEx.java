import java.io.*;
import java.net.*;
import java.util.*;
public class ServerEx {
  public static void main(String[] args) {
    BufferedReader in = null;
    BufferedWriter out = null;
    ServerSocket listener = null;
    Socket socket = null;
    Scanner scanner = new Scanner(System.in); 

    try {
      
      System.out.println("[김철수]를 초대했습니다. ");
      listener = new ServerSocket(9999); 
      System.out.println("[김철수]의 입장을 기다리고 있습니다.");
      socket = listener.accept(); 
      System.out.println("[김철수]가 입장하였습니다.\n");
      System.out.println("--------------------------------------------");

      in = new BufferedReader(new InputStreamReader(socket.getInputStream())); 
      out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())); 

      while (true) {

        DataInputStream sss = new DataInputStream(socket.getInputStream());
        String inputMessage = sss.readUTF();     
        
        if (inputMessage.equalsIgnoreCase("그래 안녕 또연락할게~")) {
           System.out.println("김철수와 채팅이 종료되었습니다."); 
           break; 
        }
        System.out.println("[김철수]: " + inputMessage); 
        System.out.print("[남연주]: "); 
        
        DataOutputStream www = new DataOutputStream(socket.getOutputStream());
        String outputMessage = scanner.nextLine();
        www.writeUTF(outputMessage); 
        www.flush();
      }
    } catch (IOException e) {
      System.out.println(e.getMessage());
    } finally {

      try {
        scanner.close(); 
        socket.close(); 
        listener.close(); 

      } catch (IOException e) {
        System.out.println("김철수와 채팅 중 오류가 발생했습니다.");
      }
    }
  }
}
