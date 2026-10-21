import java.util.HashMap;

public class Rq {
    private final String actionName;
    private final HashMap<String, String> paramsMap = new HashMap<>();

    public Rq(String cmd) {
        String[] parts = cmd.split("\\?", 2);
        actionName = parts[0];

        String str = parts.length == 2 ? parts[1] : "";

        String[] query = str.split("&");

        System.out.println("part0 : " + parts[0] + " parts1 : " + parts[1]);

        for(String param : query) {
            String[] paramUnit = param.split("=", 2);

            String key = paramUnit[0];
            String value = paramUnit.length == 2 ? paramUnit[1] : "";
            System.out.println("key : " + key + " value : " + value);
            paramsMap.put(key, value);
        }
    }

    public String getActionName() {
        return actionName;
    }

    public int getParamAsInt(String key, int n) {
        String value = paramsMap.get(key);

        if(value == null || value.isEmpty() || value.matches("[0-9]+")) {
            return -1;
        }

        return Integer.parseInt(value);
    }

    public String getParam(String key, String str2) {
        String value = paramsMap.get(key);

        if(value == null || value.isEmpty()) {
            return str2;
        }

        return value;
    }

}

