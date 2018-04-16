package application.Servers;

public class LanguageServerIpPort {
    private int port;
    private String ip;
    private String code;


    public LanguageServerIpPort(int port, String ip, String code) {
        this.port = port;
        this.ip = ip;
        this.code = code;
    }


    public String getCode() {
        return code;
    }

    public int getPort() {
        return port;
    }

    public String getIp() {
        return ip;
    }

    @Override
    public String toString() {
        return "LanguageServerIpPort{" +
                "port=" + port +
                ", ip='" + ip + '\'' +
                ", code='" + code + '\'' +
                '}';
    }
}
