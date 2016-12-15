package greetingPages;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

public class Template {

    private final Map<String, String> map;

    public Template() {
        map = new LinkedHashMap<>();
    }

    public void add(String parameter, String value) {
        map.put(parameter, value);
    }

    public String getValue(String param) throws ServletException, IOException {
        for (String parameter : map.keySet()) {
            if (parameter.equals(param)) {
                return map.get(parameter);
            }
        }
        return null;
    }
}