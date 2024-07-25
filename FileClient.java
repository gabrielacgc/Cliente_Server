import java.io.*;
import java.net.*;

public class FileClient {
    public static void main(String[] args) {
        String serverAddress = "10.160.222.30";
        int port = 12345;
        String filePath = "arquivo.txt";  // Caminho para o arquivo a ser enviado
        
        try {
            Socket socket = new Socket(serverAddress, port);
            
            // ler o arquivo 
            FileInputStream fileIn = 
                new FileInputStream(filePath);
                // enviar para o server 
            DataOutputStream dataOut = 
                new DataOutputStream(socket.getOutputStream());
             
            System.out.println("Conectado ao servidor " 
                + serverAddress + " na porta " + port);
            // ler o arquivo, armazena no servidor. Quanto ele vai ler 
            int bytesRead;
            byte[] buffer = new byte[4096];
            
            while ((bytesRead = fileIn.read(buffer)) != -1) {
                dataOut.write(buffer, 0, bytesRead);
            }
            
            System.out.println("Arquivo enviado com sucesso.");
        } catch (UnknownHostException e) {
            System.out.println("Host desconhecido: " + serverAddress);
        } catch (IOException e) {
            System.out.println("Erro de I/O ao enviar o arquivo: " 
                + e.getMessage());
        }
    }
}