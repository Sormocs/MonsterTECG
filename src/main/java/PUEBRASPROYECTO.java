import cartas.Minions;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import listas.*;
import manejo.json.Json;

public class PUEBRASPROYECTO {

    public static void main(String[] args) throws JsonProcessingException {

        Stack stack = new Stack();
        stack.Llenar();

        System.out.println(stack.getTop());

        JsonNode nodo = (JsonNode) stack.getTop();

        Minions minion = Json.fromJson(nodo,Minions.class);
        //minion.hola();


    }
}
