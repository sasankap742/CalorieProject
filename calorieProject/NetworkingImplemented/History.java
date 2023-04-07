package edu.sdccd.cisc191.o.server;


import edu.sdccd.cisc191.o.DailyLog;
import edu.sdccd.cisc191.o.Request;

import java.net.*;
import java.io.*;

import java.util.ArrayList;

public class History {
    private ArrayList<DailyLog> logHistory = new ArrayList<>();

    private ServerSocket serverSocket;
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;

    public void start(int port) throws Exception {
        logHistory.add(new DailyLog());
        logHistory.add(new DailyLog());// adding temp logs

        serverSocket = new ServerSocket(port);
        clientSocket = serverSocket.accept();
        out = new PrintWriter(clientSocket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            Request request = Request.fromJSON(inputLine);
            DailyLog response = getLog(request.getLogEntryDay());
            out.println(DailyLog.toJSON(response));
        }
    }


    public DailyLog getLog(int entryAtDay){
       //For now add an empty dailylog to logHistory
        return logHistory.get(entryAtDay-1);
    }

    public void addEntry(){

    }

    public void viewLogs(){

    }

    public void stop() throws IOException {
        in.close();
        out.close();
        clientSocket.close();
        serverSocket.close();
    }


    public static void main(String[] args) {
        History server = new History();
        try {
            server.start(8888);
            server.stop();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
