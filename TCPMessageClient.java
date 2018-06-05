import java.io.*;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;


public class TCPMessageClient {

    public static void main(String[]args)throws IOException{

        String message;
        String getMessage;
        String current = System.getProperty("user.dir");
        Path chatHistory = Paths.get(current, "chatHistory.txt");

        while(true) {

            Socket clientSocket = new Socket("localhost", 6790);
            BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
            DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
            BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            message = inFromUser.readLine();
            outToServer.writeBytes(message + "\n");
            getMessage = inFromServer.readLine();

            try(BufferedWriter chatHist = Files.newBufferedWriter(chatHistory, StandardOpenOption.APPEND, StandardOpenOption.CREATE)) {
                chatHist.write(LocalDateTime.now() + "\n");
                chatHist.write(getMessage + "\n");
            }

            System.out.println(getMessage);
            clientSocket.close();
        }
    }
}
