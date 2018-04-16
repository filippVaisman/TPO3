package Test;

import application.Json.JsonFormater;
import application.Json.JsonSerializer;
import application.Json.KeyValue;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class JsonTest{

    @Test
    public void jsonFormatTest(){
        JsonFormater formater = new JsonFormater();

        formater.addValue(new KeyValue("Vova","pidor"));
        formater.addValue(new KeyValue("Philipp","net"));

        assertEquals("{\"Vova\":\"pidor\",\"Philipp\":\"net\"}",formater.constructJson());

    }

    @Test
    public void jsonSerializerTest(){
        String json = "{" +
                "\"example\":\"not example\"," +
                "\"example2\":\"example2 value\"" +
                "}";
        System.out.println(json);
        JsonSerializer serializer = new JsonSerializer(json);

        assertEquals("not example",serializer.getElement("example"));
        assertEquals("example2 value",serializer.getElement("example2"));
        try {
            serializer.getElement("example3");
            fail( "My method didn't throw when I expected it to" );
        }catch (NullPointerException exception){

        }

    }
}
