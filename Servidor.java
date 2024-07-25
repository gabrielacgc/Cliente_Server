import java.io.*;
import java.net.*;
/**
 * Servidor que recebe mensagens de um cliente e envia de volta 
 */
public class Servidor {
    public static void main(String[] args) {
        try {
            //cria um socket servidor na porta 12345
            //para receber conexões de clientes
            ServerSocket serverSocket = new ServerSocket(12345);
         
            System.out.println("Servidor iniciado na porta 12345.");
            
            //aceita conexões de clientes
            //e cria um socket para se comunicar com o cliente
            //e envia de volta a mensagem recebida
            //em um loop infinito
            while (true) {
                try (
                    //cria um socket para se comunicar com o cliente
                    Socket clientSocket = serverSocket.accept();
                    
                    //objeto utilizado para enviar dados ao cliente
                    //que se conectou
                    PrintWriter out = 
                        new PrintWriter(clientSocket.getOutputStream(), true);
                    
                    //objeto utilizado para realizar a leitura de dados
                    //vindos do cliente
                    BufferedReader in = 
                        new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))
                    ) {
                     
                    //lê a mensagem enviada pelo cliente
                    //e envia de volta a mesma mensagem
                    //em um loop infinito
                    String inputLine;
                    while ((inputLine = in.readLine()) != null) {
                        System.out.println("Recebido: " + inputLine);
                        out.println("Eco: " + inputLine);

                    }
                    System.out.println("Arquivo recebido com sucesso");
                } catch (IOException e) {
                    System.out.println("Erro ao receber o arquivo: " + e.getMessage());
                    
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao iniciar o servidor: " + e.getMessage());
        }
    }
}