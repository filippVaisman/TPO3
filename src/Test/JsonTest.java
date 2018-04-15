package Test;

import application.Json.JsonFormater;
import application.Json.KeyValue;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class JsonTest{

    @Test
    public void doJsonTest(){
        JsonFormater formater = new JsonFormater();

        formater.addValue(new KeyValue("Vova","pidor"));
        formater.addValue(new KeyValue("Philipp","net"));

        assertEquals("{\"Vova\":\"pidor\",\"Philipp\":\"net\",}",formater.constructJson());

    }
}
