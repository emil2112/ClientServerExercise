package demo;

import javax.print.attribute.standard.DateTimeAtCreation;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;

public class SimpleDemo {
    private ServerSocket server;

    public void run(int port){
        try {
            server = new ServerSocket(port);
            System.out.println("Server ready to receive requests");
            while(true) {
                Socket clientSocket = server.accept();

                Runnable clientHandler = new ClientHandler(clientSocket);
                new Thread(clientHandler).start();
            }
//            String inputLine;
//            while((inputLine = in.readLine()) != null){
//                System.out.println("Message from client: " + inputLine);
//                out.println(inputLine);
//            }
//            String request = in.readLine();
//            System.out.println("Message from client: " + request);
//            out.println("Response form server");
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new SimpleDemo().run(12345);
    }

    private static class ClientHandler implements Runnable{
        private Socket clientSocket;
        public ClientHandler(Socket socket){
            this.clientSocket = socket;
        }
        @Override
        public void run(){
                try {
                    System.out.println("Client registered \r\n " + clientSocket.getRemoteSocketAddress() +"\r\n Type exit to quit.");
                    PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                    BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                    while(true) {
                        String userInput = in.readLine();
                        if (userInput.equals("exit")) {
                            break;
                        }
                        System.out.println("Message from client: " + userInput);
                        out.println("Response from server: " + userInput);
                        if(userInput.equals("exit")){
                            break;
                        }
                        if(userInput.contains("GET")) {

                            out.println(helloRequest());
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

        }
    }
    public static String helloRequest(){
        return "HTTP/1.1 200 OK\r\n"+ "Date: " + LocalDateTime.now() + "\r\n" + "Server: localhost\r\n" +
                "Content-Type: text/html; charset=UTF-8" + "Content-Length: 12345"+ "\r\n" + "\r\nHello World!";
    }

    public static String
}
