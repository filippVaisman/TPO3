package application.Json;

import java.util.ArrayList;

public class JsonFormater {
    private String json;
    private ArrayList<String> values;

    public JsonFormater(){
        json="";
        values = new ArrayList<>();
    }

    public void addValue(KeyValue ... keyValues ){

        for (KeyValue keyValue: keyValues) {
            values.add("\""+keyValue.getKey()+"\":\""+keyValue.getValue()+"\"");
        }

    }

    public String constructJson(){
        String result= "{";
        for(int i=0;i<values.size()-1;i++){
            result+=values.get(i)+",";
        }

        result+=values.get(values.size()-1)+"}";

        return result;

    }

}
