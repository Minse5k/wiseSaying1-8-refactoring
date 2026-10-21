import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class Rq {
    private final String actionName;
    private final Map<String, String> paramsMap;

    public Rq(String cmd) {
        String[] parts = cmd.split("\\?", 2);
        actionName = parts[0];

        String str = parts.length == 2 ? parts[1] : "";

        String[] query = str.split("&");

        paramsMap = Arrays.stream(query)
                .filter(param -> !param.isEmpty())
                .map(param -> param.split("=", 2))
                .collect(Collectors.toMap(
                        paramUnit -> paramUnit[0],
                        paramUnit -> paramUnit.length==2 ? paramUnit[1] : ""
                ));

        for(String param : query) {
            if(param.isEmpty()) continue;

            String[] paramUnit = param.split("=", 2);

            String key = paramUnit[0];
            String value = paramUnit.length == 2 ? paramUnit[1] : "";

            paramsMap.put(key, value);
        }
    }

    public String getActionName() {
        return actionName;
    }

    public int getParamAsInt(String key, int n) {
        try {
            String value = paramsMap.get(key);
            return Integer.parseInt(value);
        } catch(NumberFormatException e) {
            return n;
        }
    }

    public String getParam(String key, String str) {
        return paramsMap.getOrDefault(key, str);
    }
}

