package application.Net;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class SimpleRequest {

    private Socket socket;
    private Class className;

    public SimpleRequest(String destinationIp, int destinationPort, Class className) throws IOException {
        socket = new Socket(destinationIp,destinationPort);
        this.className = className;
    }

    public void sendRequest(String jsonMessage) throws IOException {

        OutputStream os = socket.getOutputStream();
        os.write(jsonMessage.getBytes());
        log("request sended");
        os.flush();
    }

    private void log(String message){
        System.out.println(className.getName() + ": " + message);
    }

}
