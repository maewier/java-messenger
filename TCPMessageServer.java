import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPMessageServer {

   public static void main(String[]args) throws Exception {

       String message;

       ServerSocket serverSocket = new ServerSocket(6790);

       while(true) {

           Socket connectionSocket = serverSocket.accept();
           BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
           DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());

           message = inFromClient.readLine();
           outToClient.writeBytes("-" + message + '\n');

       }
   }
}
