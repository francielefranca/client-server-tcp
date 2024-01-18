import java.net.*;
import java.io.*;
import java.util.Scanner;

public class ClientTcp {
    public static void main (String[] args) {
        Socket socket = null;
        int serverPort = 1234;
        Scanner sc = new Scanner(System.in);
        
        try{
            socket = new Socket(args[0], serverPort);

            DataInputStream entrada = new DataInputStream(socket.getInputStream());
            DataOutputStream saida = new DataOutputStream(socket.getOutputStream()); 

            while(true){
                System.out.println();
                System.out.println("Formato da mensagem pode variar de acordo com a figura:");
                System.out.println("Formato 1: localhost <circulo> <raio>");
                System.out.println("Formato 2: localhost <triangulo> <base> <altura>");
                System.out.println("Formato 3: localhost <quadrado> <lado>");
                System.out.println();

                String mensagem = sc.nextLine();

                saida.writeUTF(mensagem);
                String data = entrada.readUTF();
                System.out.println("Resultado da area da figura = " + data); 
            }

            //String data = entrada.readUTF();
        } catch (UnknownHostException ex) {
            ex.printStackTrace();
        }catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
