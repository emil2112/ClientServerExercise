package demo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.Buffer;
import java.util.List;
import java.util.stream.Collectors;

public class TCPClient {
    private Socket clientSocket;
    private static PrintWriter out;
    private static BufferedReader in;

    public void startConnection (String ip, int port){
        try {
            clientSocket = new Socket(ip, port);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            out = new PrintWriter(clientSocket.getOutputStream(), true);
        }catch(IOException e){
            e.printStackTrace();
        }
        }

    public static void main(String[] args) {
        new TCPClient().startConnection("localhost", 12345);
//        out.println("Response from server = success!");
        try {
            String response = in.readLine();
            System.out.println(response);
            response = in.readLine();
            System.out.println("Response from server: "+response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
