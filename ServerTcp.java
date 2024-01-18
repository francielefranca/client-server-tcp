import java.net.*;
import java.io.*;
import java.util.StringTokenizer;

public class ServerTcp {
    public static void main(String[] args) {
        int serverPort = 1234;
        ServerSocket listenSocket = null;
        Socket clientSocket = null;

        try{
            listenSocket = new ServerSocket(serverPort);

            while(true){
                System.out.println("Aguardando mensagens dos clientes...");
                clientSocket = listenSocket.accept();
                System.out.println("Mensagem recebida... Servidor INICIADO");
                Connection c = new Connection(clientSocket);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class Connection extends Thread {
    DataInputStream entrada;
    DataOutputStream saida;
    Socket clientSocket;

    public Connection(Socket aClientSocket) throws IOException{
        clientSocket = aClientSocket;
        entrada = new DataInputStream(clientSocket.getInputStream());
        saida = new DataOutputStream(clientSocket.getOutputStream());
        this.start();
    }
    
    public void run(){
        try{
            String data = entrada.readUTF(); 
            System.out.println("Mensagem recebida: " + data);

            double result = 0;

            StringTokenizer st = new StringTokenizer(data);

            String maquina = st.nextToken();
            String figura = st.nextToken();
            double raio = 0;
            double base = 0;
            double altura = 0;
            double lado = 0;

            if(figura.equals("circulo")){
                raio = Double.parseDouble(st.nextToken());
                result = raio * raio * Math.PI;
            }else if(figura.equals("triangulo")){
                base = Double.parseDouble(st.nextToken());
                altura = Double.parseDouble(st.nextToken());
                result = (base * altura)/2;
            }else if(figura.equals("quadrado")){
                lado = Double.parseDouble(st.nextToken());
                result = lado * lado;
            }else{
                System.out.println("Mensagem inválida!!");
            }
            
            System.out.println("Enviando a área do " + figura + " ...");
            saida.writeUTF(Double.toString(result));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
