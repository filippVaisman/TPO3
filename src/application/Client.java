package application;

import application.Net.SimpleRequest;

import java.io.IOException;

public class Client {
    //TODO: make ip ok
    final private String ipMainServer = "localhost";
    final private int port = 1235;
    final private int portMainServer = 1234;
    private String [] languages;

    public void sendRequestTomMainServer(String json){
        try {
            SimpleRequest requestToMain = new SimpleRequest(ipMainServer,portMainServer,this.getClass());
            requestToMain.sendRequest(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public void translate(String word){
        //TODO: make json here
        sendRequestTomMainServer("");
    }
}
