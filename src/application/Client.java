package application;

import application.Json.JsonFormater;
import application.Json.JsonSerializer;
import application.Json.KeyValue;
import application.Net.SimpleListener;
import application.Net.SimpleRequest;

import java.io.IOException;

public class Client {
    //TODO: make ip not local
    final private String ipMainServer = "localhost";

    final private String ip= "localhost";
    final private int port = 1235;
    final private int portMainServer = 1234;
    private String [] languages;
    SimpleListener listener;

    public Client(){
        try {
            listener = new SimpleListener(this.port,this.getClass());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void sendRequestTomMainServer(String json){
        try {
            SimpleRequest requestToMain = new SimpleRequest(ipMainServer,portMainServer,this.getClass());
            requestToMain.sendRequest(json);


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String construateJson(String word,String language){

        JsonFormater jsonFormater = new JsonFormater();
        jsonFormater.addValue(new KeyValue("senderName","client"));
        jsonFormater.addValue(new KeyValue("word",word));
        jsonFormater.addValue(new KeyValue("language",language));
        jsonFormater.addValue(new KeyValue("port",this.port+""));
        jsonFormater.addValue(new KeyValue("ip",this.ip));

        return jsonFormater.constructJson();

    }

    private String listenTranslateResponse(){
        System.out.println("Client listener started");
                try {
                    return listener.listenConnection();
                } catch (Exception e) {
                    e.printStackTrace();
                }
        return null;
    }


    public String translate(String word,String language){
        System.out.println("Request sent");
        sendRequestTomMainServer(construateJson(word,language));
        System.out.println("Client translate want sent");
//        listenTranslateResponse();
        String response = listenTranslateResponse();
        System.out.println(response);
        JsonSerializer serializer = new JsonSerializer(response);

        return serializer.getElement("word");
    }



}
