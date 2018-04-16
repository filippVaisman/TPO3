package application;

import application.Json.JsonFormater;
import application.Json.KeyValue;
import application.Net.SimpleRequest;

import java.io.IOException;

public class Client {
    //TODO: make ip not local
    final private String ipMainServer = "192.168.0.94";

    final private String ip= "localhost";
    final private int port = 1235;
    final private int portMainServer = 1234;
    private String [] languages;

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


    public void translate(String word,String language){
        sendRequestTomMainServer(construateJson(word,language));
    }



}
