package application;

import application.Net.SimpleListener;

import java.io.IOException;

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