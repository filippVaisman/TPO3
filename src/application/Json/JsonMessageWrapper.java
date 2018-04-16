package application.Json;

public class JsonMessageWrapper {

    private String senderName;
    private String word;
    private String language;
    private String port;
    private String ip;

    public JsonMessageWrapper(String senderName, String word, String language, String port, String ip) {
        this.senderName = senderName;
        this.word = word;
        this.language = language;
        this.port = port;
        this.ip = ip;
    }

    public String getSenderName() {
        return senderName;
    }

    public String getWord() {
        return word;
    }

    public String getlanguage() {
        return language;
    }

    public String getPort() {
        return port;
    }

    public String getIp() {
        return ip;
    }

    @Override
    public String toString() {
        return "JsonMessageWrapper{" +
                "senderName='" + senderName + '\'' +
                ", word='" + word + '\'' +
                ", language='" + language + '\'' +
                ", port='" + port + '\'' +
                ", ip='" + ip + '\'' +
                '}';
    }

}
