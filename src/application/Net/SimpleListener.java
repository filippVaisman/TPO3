package application.Net;

import java.io.IOException;
import java.io.InputStream;
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

        System.out.println(socket.getPort());

        InputStream is = socket.getInputStream();
        socket.close();
        return getMessage(is);

    }


    private String getMessage(InputStream inputStream) throws IOException {
        int symb;
        StringBuffer result = new StringBuffer();
        while ((symb = (inputStream.read()))!=-1){
            result.append((char)symb);
        }

        return result.toString();

    }
    private void log(String message){
        System.out.println(className.getName() + ": " + message);
    }

}
