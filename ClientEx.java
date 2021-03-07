import java.io.*;
import java.net.*;
import java.util.*;
public class ClientEx {
  public static void main(String[] args) {
    BufferedReader in = null;
    BufferedWriter out = null;
    Socket socket = null;
    Scanner scanner = new Scanner(System.in); 

    try {

      System.out.println("[남연주]가 당신을 초대했습니다. 입장을 원한다면 채팅을 입력하세요. \n");
      System.out.println("--------------------------------------------");
      socket = new Socket("localhost", 9999); 
      in = new BufferedReader(new InputStreamReader(socket.getInputStream())); 
      out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())); 

      while (true) {
        System.out.print("[김철수]: "); 
        DataOutputStream aaa = new DataOutputStream(socket.getOutputStream());
        String outputMessage = scanner.nextLine(); 
        aaa.writeUTF(outputMessage); 
        aaa.flush();

        if (outputMessage.equalsIgnoreCase("그래 안녕 또연락할게~")) { 
          aaa.writeUTF(outputMessage+"\n"); 
          aaa.flush();
          break; 
        }

        DataInputStream kkk = new DataInputStream(socket.getInputStream());
        String inputMessage = kkk.readUTF();
        System.out.println("[남연주]: " + inputMessage); 
      }
    } catch (IOException e) {
      System.out.println(e.getMessage());
    } finally {
      try {
        scanner.close();
        if(socket != null) socket.close(); 
      } catch (IOException e) {
        System.out.println("[남연주]와 채팅 중 오류가 발생했습니다.");
      }
    }
  }
}
