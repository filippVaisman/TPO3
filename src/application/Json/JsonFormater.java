package application.Json;

public class JsonFormater {
    private String json;
    private String values;

    public JsonFormater(){
        json="";
        values="";
    }

    public void addValue(KeyValue ... keyValues ){

        for (KeyValue keyValue: keyValues) {
            values+="\""+keyValue.getKey()+"\":\""+keyValue.getValue()+"\",";
        }

    }

    public String constructJson(){
        return "{"+values+"}";
    }

}
