package edu.sdccd.cisc191.o.client;

import edu.sdccd.cisc191.o.DailyLog;
import edu.sdccd.cisc191.o.Request;

import java.net.*;
import java.io.*;

public class User {
    private String userName;

    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;

    public void startConnection(String ip, int port) throws IOException {
        clientSocket = new Socket(ip, port); //Client Socket created to given port&ip
        out = new PrintWriter(clientSocket.getOutputStream(), true); //output from client to the other end of socket(server)
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream())); //input from the other end of the socket(server)
    }

    public DailyLog sendRequest() throws Exception {
        out.println(Request.toJSON(new Request(1)));  //outputting to server a JSON serialized  object of Request
        return DailyLog.fromJSON(in.readLine()); //outputting to server a JSON serialized  object of Request
    }

    public void logout() throws IOException {
        //Close all the I/O streams and disconnect clientSocket
        in.close();
        out.close();
        clientSocket.close();
    }


    public static void main(String[] args) {
        User client = new User();
        try {
            client.startConnection("127.0.0.1", 8888);
            System.out.println(client.sendRequest().toString());
            client.logout();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void editLog(){

    }

}
