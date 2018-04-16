package application;
import application.Servers.LanguageServer;
import application.Servers.MainServer;

import java.io.IOException;

public class Main {


    public static void main(String[] args) {

        MainServer mainServer = new MainServer();
        mainServer.startServer();
        LanguageServer englishServer = new LanguageServer("EN","localhost",1236,"src/application/JsonFiles/EN.json");
        try {
            englishServer.startServer();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Client client = new Client();
        System.out.println(client.translate("kot","EN"));

        System.out.println(client.translate("czerwony","EN"));
        System.out.println(client.translate("2131wadwd","EN"));
//        System.out.println(client.translate("niebieski","EN"));




    }
}