package application.Net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class SimpleListener {
    private ServerSocket serverSocket;
    private Class className;

    public SimpleListener(int port, Class className) throws IOException {
        serverSocket = new ServerSocket(port);
        this.className = className;
    }

    public String listenConnection() throws IOException {

        Socket socket = serverSocket.accept();

        log("get new connection on port " + socket.getPort());

        InputStream is = socket.getInputStream();

        return getMessage(is);

    }


    private String getMessage(InputStream inputStream) throws IOException {
        int symb=1;
        StringBuffer result = new StringBuffer();

        InputStreamReader ir = new InputStreamReader(inputStream);

        log("Reading message");

        int i = 0;
        while ((symb = ir.read())!=125){
            result.append((char)symb);
        }
        log("message is: "+result.toString()+"}");
        return result.toString()+"}";

    }
    private void log(String message){
        System.out.println(className.getName() + ": " + message);
    }

}
