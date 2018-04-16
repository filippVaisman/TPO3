package application.Servers;

import application.Exceptions.WordNotFoundException;
import application.Json.JsonFormater;
import application.Json.JsonSerializer;
import application.Json.KeyValue;
import application.Net.SimpleListener;
import application.Net.SimpleRequest;

import java.io.*;
import java.net.Socket;

public class LanguageServer {

    private String languageCode;
    private String ip;
    private int port;
    private SimpleListener simpleListener;

    private final int clientPort = 1235;
    private String jsonPath;
    private JsonSerializer serializer;

    public LanguageServer(String languageCode, String ip, int port,String jsonPath) {
        try {
            simpleListener = new SimpleListener(port,this.getClass());
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.jsonPath = jsonPath;
        this.languageCode = languageCode;
        this.ip = ip;
        this.port = port;
        installJsonFile();
    }

    public void startServer() throws IOException {

        new Thread(()->{
            while(true){
                SimpleRequest simpleRequest = null;
                try {
                    simpleRequest = new SimpleRequest("localhost",clientPort,this.getClass());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                JsonFormater formater=null;
                try {
                    String json = simpleListener.listenConnection();
                    JsonSerializer serializer = new JsonSerializer(json);
                    formater = new JsonFormater();

                    String word = serializer.getElement("word");

                    if(word == null)
                        throw new WordNotFoundException();
                    try {
                        formater.addValue(new KeyValue("word", translate(word)));
                    }catch (NullPointerException e){
                        formater.addValue(new KeyValue("word","not found"));
                        try {
                            simpleRequest.sendRequest(formater.constructJson());
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                    }
                    String jsonResponse = formater.constructJson();
                    simpleRequest.sendRequest(jsonResponse);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (WordNotFoundException e) {
                    formater.addValue(new KeyValue("word","not found"));
                    try {
                        simpleRequest.sendRequest(formater.constructJson());
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        }).start();
    }

    private void installJsonFile(){
        File file = new File(jsonPath);
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String tmp="";
            String text="";
            while((tmp=br.readLine())!=null){
                text+=tmp;
            }
            serializer = new JsonSerializer(text);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String translate(String word){
        return serializer.getElement(word);
    }
}