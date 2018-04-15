package application.Servers;

import application.Net.SimpleListener;
import application.Net.SimpleRequest;

import java.io.IOException;

public class MainServer {

    private final int port = 1234;
    private SimpleListener listener;
    private SimpleRequest requester;

    public MainServer(){
        try {
            listener = new SimpleListener(port, SimpleListener.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void startServer(){
        while(true){
            new Thread(()->{
                try {
                    listener.listenConnection();
                    //add method to parse json message
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }

}
