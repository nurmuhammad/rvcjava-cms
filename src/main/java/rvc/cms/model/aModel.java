package rvc.cms.model;

import org.reflections.Reflections;
import rvc.cms.init.Config;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author nurmuhammad
 */

public abstract class aModel implements Serializable {

    public static Map<Class<? extends aModel>, String> tableNames = new HashMap<>();

    private static Map<Class<? extends aModel>, Map<String, String>> columnMaps;

    public static Map<Class<? extends aModel>, Map<String, String>> getColumnMaps() {

        if (columnMaps != null) {
            return columnMaps;
        }

        columnMaps = new HashMap<>();

        Reflections reflections = new Reflections(Config.get("scan.package", "com"));

        Set<Class<? extends aModel>> classes = reflections.getSubTypesOf(aModel.class);

        classes.stream().forEach(aModel::getColumnMaps);

        return columnMaps;
    }

    public static Map<Class<? extends aModel>, Map<String, String>> getColumnMaps(Class aClass) {
        if (columnMaps.containsKey(aClass)) return columnMaps;
        final String regex = "([a-z])([A-Z])";
        final String replacement = "$1_$2";
        for (Field field : aClass.getFields()) {
            String name = field.getName();
            name = name.replaceAll(regex, replacement).toLowerCase();
            if (field.getName().equals(name)) continue;
            Map<String, String> map = columnMaps.get(aClass);
            if (map == null) {
                map = new HashMap<>();
                columnMaps.put(aClass, map);
            }
            map.put(name, field.getName());
        }

        return columnMaps;
    }

    public static String table(Class clazz) {
        String table = tableNames.get(clazz);
        if (table != null) return table;
        try {
            Field f = rvc.cms.model.Field.class.getDeclaredField("table");
            f.setAccessible(true);
            table = f.get(null).toString();
            tableNames.put(clazz, table);
            return table;
        } catch (Exception ignored) {}

        table = clazz.getSimpleName();
        final String regex = "([a-z])([A-Z])";
        final String replacement = "$1_$2";
        table = table.replaceAll(regex, replacement).toLowerCase();
        if (!table.endsWith("s")) table += "s";
        tableNames.put(clazz, table);
        return table;
    }
}
