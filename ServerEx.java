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
      
      System.out.println("[��ö��]�� �ʴ��߽��ϴ�. ");
      listener = new ServerSocket(9999); 
      System.out.println("[��ö��]�� ������ ��ٸ��� �ֽ��ϴ�.");
      socket = listener.accept(); 
      System.out.println("[��ö��]�� �����Ͽ����ϴ�.\n");
      System.out.println("--------------------------------------------");

      in = new BufferedReader(new InputStreamReader(socket.getInputStream())); 
      out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())); 

      while (true) {

        DataInputStream sss = new DataInputStream(socket.getInputStream());
        String inputMessage = sss.readUTF();     
        
        if (inputMessage.equalsIgnoreCase("�׷� �ȳ� �ǿ����Ұ�~")) {
           System.out.println("��ö���� ä���� ����Ǿ����ϴ�."); 
           break; 
        }
        System.out.println("[��ö��]: " + inputMessage); 
        System.out.print("[������]: "); 
        
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
        System.out.println("��ö���� ä�� �� ������ �߻��߽��ϴ�.");
      }
    }
  }
}
