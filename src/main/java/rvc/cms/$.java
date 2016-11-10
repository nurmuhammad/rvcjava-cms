package rvc.cms;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import java.util.*;

/**
 * @author nurmuhammad
 */

public class $ extends rvc.$ {

    public static Map<String, String> settings2map(String settings) {
        if (settings == null || settings.length() < 2) return new HashMap<>();
        return Splitter.on(";;\n").withKeyValueSeparator(":==").split(settings);
    }

    public static String map2settings(Map map) {
        if (map == null) return "";
        Joiner.MapJoiner mapJoiner = Joiner.on(";;\n").withKeyValueSeparator(":==");
        return mapJoiner.join(map);
    }

    public static Object[] a(Object... objects){
        return objects;
    }
}
