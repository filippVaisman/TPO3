package application;

import application.Net.SimpleListener;
import application.Net.SimpleRequest;

import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {


    public static void main(String[] args) {


        try {
            SimpleListener l  = new SimpleListener(1221, SimpleListener.class);
            new Thread(()->{
                int i=0;
                while(i<10){
                    try {
                        l.listenConnection();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    i++;
                }
            }).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(123);
    }
}