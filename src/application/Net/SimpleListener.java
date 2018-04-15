package application.Net;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class SimpleListener {
    private ServerSocket serverSocket;

    public SimpleListener(int port) throws IOException {
        serverSocket = new ServerSocket(port);
    }

    public String listenConnection() throws IOException {

        Socket socket = serverSocket.accept();
        System.out.println(socket.getPort());
        InputStream is = socket.getInputStream();
        socket.close();
        return "";

    }


    private String getMessage(InputStream inputStream) throws IOException {
        int symb;
        StringBuffer result = new StringBuffer();
        while ((symb = (inputStream.read()))!=-1){
            result.append((char)symb);
        }

        return result.toString();

    }

}
