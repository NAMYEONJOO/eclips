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

      System.out.println("[������]�� ����� �ʴ��߽��ϴ�. ������ ���Ѵٸ� ä���� �Է��ϼ���. \n");
      System.out.println("--------------------------------------------");
      socket = new Socket("localhost", 9999); 
      in = new BufferedReader(new InputStreamReader(socket.getInputStream())); 
      out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())); 

      while (true) {
        System.out.print("[��ö��]: "); 
        DataOutputStream aaa = new DataOutputStream(socket.getOutputStream());
        String outputMessage = scanner.nextLine(); 
        aaa.writeUTF(outputMessage); 
        aaa.flush();

        if (outputMessage.equalsIgnoreCase("�׷� �ȳ� �ǿ����Ұ�~")) { 
          aaa.writeUTF(outputMessage+"\n"); 
          aaa.flush();
          break; 
        }

        DataInputStream kkk = new DataInputStream(socket.getInputStream());
        String inputMessage = kkk.readUTF();
        System.out.println("[������]: " + inputMessage); 
      }
    } catch (IOException e) {
      System.out.println(e.getMessage());
    } finally {
      try {
        scanner.close();
        if(socket != null) socket.close(); 
      } catch (IOException e) {
        System.out.println("[������]�� ä�� �� ������ �߻��߽��ϴ�.");
      }
    }
  }
}
