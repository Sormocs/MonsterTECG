package manejo.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.BufferedReader;
import java.io.FileReader;

public class Json {
    private static ObjectMapper objectMapper = getDefaultObjectMapper();

    private static ObjectMapper getDefaultObjectMapper() {
        ObjectMapper defaultObjectMapper = new ObjectMapper();
        return defaultObjectMapper;
    }

    public static JsonNode parse(String jsonSource) throws JsonProcessingException{
        //De Json a String.
        return objectMapper.readTree(jsonSource);
    }

    public static <A> A fromJson(JsonNode node, Class<A> clazz) throws JsonProcessingException{
        //De JsonNode a Objeto
        return objectMapper.treeToValue(node,clazz);
    }

    public static JsonNode toJson(Object o){
        //De objeto a JsonNode
        return objectMapper.valueToTree(o);
    }

    public static String generateString(JsonNode node, boolean pretty) throws JsonProcessingException{
        //De Json node a string
        ObjectWriter objectWriter = objectMapper.writer();
        if(pretty){
            objectWriter = objectWriter.with(SerializationFeature.INDENT_OUTPUT);
        }
        return objectWriter.writeValueAsString(node);
    }

    public static String ReadJson(String file){
        //Lee el archivo.json
        String json = new String();
        try{
            BufferedReader br = new BufferedReader(new FileReader("prueba.json"));

            try{
                StringBuilder sb = new StringBuilder();
                String line = br.readLine();

                while (line != null){
                    sb.append(line);
                    sb.append(System.lineSeparator());
                    line = br.readLine();
                }
                json = sb.toString();

            } finally {
                br.close();
            }

            return json;

        }catch (Exception e){
            e.printStackTrace();
        }

        return json;
    }
}
