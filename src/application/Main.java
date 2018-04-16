package application;
import application.Servers.MainServer;

import java.io.IOException;

public class Main {


    public static void main(String[] args) {

//        MainServer mainServer = new MainServer();
//        mainServer.startServer();

        Client client = new Client();
        client.translate("kot","EN");
//        client.translate("Pojechac","EN");



    }
}