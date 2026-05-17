/*
Задание 3
На вход в качестве аргументов программы поступают три пути к файлу (в приложении к заданию находятся примеры этих файлов):
●	values.json содержит результаты прохождения тестов с уникальными id
●	tests.json содержит структуру для построения отчета на основе прошедших тестов (вложенность может быть большей, чем в примере)
●	report.json - сюда записывается результат.
Напишите программу, которая формирует файл report.json с заполненными полями value для структуры tests.json на основании values.json.
Структура report.json такая же, как у tests.json, только заполнены поля “value”.

На вход программы передается три пути к файлу!


 */

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Task3 {

    public static void main(String[] args) throws IOException {
        if(args.length<3) {
            System.out.println("Нужны три аргумента");
            return;
        }
        String valuesPath = args[0];
        String testsPath = args[1];
        String reportPath = args[2];

        ObjectMapper mapper = new ObjectMapper();

        JsonNode valuesRoot = mapper.readTree(new File(valuesPath));
        Map<Integer, String> idToValue = new HashMap<>();
        if(valuesRoot.has("values") && valuesRoot.get("values").isArray()) {
            for (JsonNode i : valuesRoot.get("values")) {
                int id = i.get("id").asInt();
                String value = i.get("value").asText();
                idToValue.put(id, value);
            }
        }

        JsonNode testsRoot = mapper.readTree(new File(testsPath));
        fillValues(testsRoot, idToValue);

        mapper.writerWithDefaultPrettyPrinter()
                .writeValue(new File(reportPath), testsRoot);
    }

    private static void fillValues(JsonNode node, Map<Integer, String> idToValue) {
        if(node.isArray()) {
            for(JsonNode child : node) {
                fillValues(child, idToValue);
            }
        } else if(node.isObject()) {
            JsonNode idNode = node.get("id");
            if(idNode != null && idNode.isInt()) {
                int id = idNode.asInt();
                if(node.has("value") && idToValue.containsKey(id)) {
                    ((ObjectNode) node).put("value", idToValue.get(id));
                }
            }

            node.fields().forEachRemaining(e -> fillValues(e.getValue(), idToValue));
        }
    }
}
