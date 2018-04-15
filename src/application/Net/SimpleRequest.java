package application.Net;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class SimpleRequest {

    private Socket socket;

    public SimpleRequest(String destinationIp, int destinationPort) throws IOException {
        socket = new Socket(destinationIp,destinationPort);
    }

    public void sendRequest(String jsonMessage) throws IOException {

        OutputStream os = socket.getOutputStream();
        os.write(jsonMessage.getBytes());
        os.flush();
        //os.close();

    }


}
