import java.io.*;
import java.net.*;

/** 
 * Cliente que se conecta a um servidor e envia mensagens
 */
public class Cliente {
    public static void main(String[] args) {

        try {
            //cria um socket para se conectar ao servidor
            //que está em execução no mesmo host, na porta 12345
            Socket socket = new Socket("localhost", 12345);

            //objeto utilizado para enviar dados ao servidor
            PrintWriter out = 
                new PrintWriter(socket.getOutputStream(), true);

            //objeto utilizado para realizar a leitura de dados
            //vindos do servidor
            BufferedReader in = 
                new BufferedReader(new InputStreamReader(socket.getInputStream()));
             
            //objeto utilizado para realizar a 
            //leitura de dados vindos do teclado 
            BufferedReader stdIn = 
                new BufferedReader(new InputStreamReader(System.in));
                      
            //realiza a leitura de dados do teclado
            //e envia ao servidor e imprime a resposta
            //do servidor
            String userInput;
            while ((userInput = stdIn.readLine()) != null) {
                //envia a mensagem ao servidor
                out.println(userInput);
                //imprime a resposta do servidor
                System.out.println("Resposta do servidor: " + in.readLine());
            }

        } catch (UnknownHostException e) {
            //caso haja erro de host desconhecido
            //possivelmente o servidor não está em execução
            System.err.println("Host desconhecido.");
            System.exit(1);
        } catch (IOException e) {
            //caso haja erro de I/O ao conectar ao host
            //possivelmente o servidor não está em execução
            System.err.println("Erro de I/O ao conectar ao host.");
            System.exit(1);
        }
    }
}