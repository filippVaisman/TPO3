package application.Servers;


import application.Exceptions.LanguageServerNotFoundException;
import application.Json.JsonFormater;
import application.Json.JsonSerializer;
import application.Json.JsonMessageWrapper;
import application.Json.KeyValue;
import application.Net.SimpleListener;
import application.Net.SimpleRequest;

import java.io.IOException;
import java.util.ArrayList;


public class MainServer {

    private final int port = 1234;
    private final String ipMainServer = "localhost";
    private SimpleListener listener;
    private SimpleRequest requester;
    private ArrayList<LanguageServerIpPort> languageServerIpPorts;

    public MainServer(){
        languageServerIpPorts = new ArrayList<>();
        languageServerIpPorts.add(new LanguageServerIpPort(1236,"localhost","EN"));
        languageServerIpPorts.add(new LanguageServerIpPort(1237,"localhost","FR"));

        try {
            listener = new SimpleListener(port, SimpleListener.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private JsonMessageWrapper decodeJson(String json){
        JsonSerializer serializer = new JsonSerializer(json);
        String senderName = serializer.getElement("senderName");
        String word = serializer.getElement("word");
        String language = serializer.getElement("language");
        String port = serializer.getElement("port");
        String ip = serializer.getElement("ip");
        return new JsonMessageWrapper(senderName,word,language,port,ip);

    }

    public void startServer(){
        new Thread(()->{
            while(true){
                try {

                    String message = listener.listenConnection();
                    JsonMessageWrapper jsonMessage = decodeJson(message);
                    System.out.println("Language found " +findServer(jsonMessage.getlanguage()));
                    sendRequestToLanguageServer(findServer(jsonMessage.getlanguage()),jsonMessage.getWord());

                }catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private LanguageServerIpPort findServer(String code) throws Exception {
        LanguageServerIpPort result=null;
        for (LanguageServerIpPort l: languageServerIpPorts) {
            if(l.getCode().equals(code))
                result = l;
        }

        if(result==null)
            throw new LanguageServerNotFoundException();
        return result;
    }


    private void sendRequestToLanguageServer(LanguageServerIpPort data,String word){

        try {
            SimpleRequest request = new SimpleRequest(data.getIp(),data.getPort(),this.getClass());

            JsonFormater jsonFormater = new JsonFormater();
            jsonFormater.addValue(new KeyValue("senderName","mainserver"));
            jsonFormater.addValue(new KeyValue("word",word));
            jsonFormater.addValue(new KeyValue("language",data.getCode()));
            jsonFormater.addValue(new KeyValue("port",this.port+""));
            jsonFormater.addValue(new KeyValue("ip",this.ipMainServer));
            String json = jsonFormater.constructJson();

            request.sendRequest(json);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
