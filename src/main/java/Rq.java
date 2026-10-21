import java.lang.invoke.StringConcatException;
import java.util.HashMap;

public class Rq {
    private final String actionName;
    private final HashMap<String, String> paramsMap = new HashMap<>();

    public Rq(String cmd) {
        String[] parts = cmd.split("\\?", 2);
        actionName = parts[0];

        String str = parts.length == 2 ? parts[1] : "";

        String[] query = str.split("&");

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

