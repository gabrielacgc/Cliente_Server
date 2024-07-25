import java.io.*;
import java.net.*;

public class FileServer {
    public static void main(String[] args) {
        int port = 12345;
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("Servidor aguardando conexões na porta " + port);
            
            while (true) {
                try {
                    Socket clientSocket = serverSocket.accept();
                    InputStream in = clientSocket.getInputStream();
                    DataInputStream dataIn = new DataInputStream(in);
                    FileOutputStream fileOut = new FileOutputStream("received_file.txt");

                    System.out.println("Conexão estabelecida com " + clientSocket.getInetAddress().getHostAddress());
                    
                    int bytesRead;
                    byte[] buffer = new byte[4096];
                    
                    while ((bytesRead = dataIn.read(buffer)) != -1) {
                        fileOut.write(buffer, 0, bytesRead);
                    }
                    
                    System.out.println("Arquivo recebido com sucesso.");
                } catch (IOException e) {
                    System.out.println("Erro ao receber o arquivo: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao iniciar o servidor: " + e.getMessage());
        }
    }
}