package rvc.cms;

import com.google.common.base.Charsets;
import com.google.common.hash.HashCode;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;
import crypt.BCryptPasswordEncoder;
import rvc.Context;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Collection;
import java.util.Date;
import java.util.Map;

/**
 * @author nurmuhammad
 */

public class $ {

    static BCryptPasswordEncoder crypt = new BCryptPasswordEncoder();

    public static String encode(String password) {
        return crypt.encode(password);
    }

    public static boolean matches(CharSequence rawPassword, String encodedPassword) {
        return crypt.matches(rawPassword, encodedPassword);
    }

    public static int timestamp() {
        return (int) (System.currentTimeMillis() / 1000);
    }

    public static Date date(int timestamp) {
        return new Date(timestamp * 1000);
    }


    public static boolean isEmpty(Object value) {
        if (value == null) return true;
        if (value instanceof String)
            return ((String) value).trim().length() == 0;
        if (value instanceof Collection) return ((Collection) value).isEmpty();
        return (value instanceof Map) && ((Map) value).isEmpty();
    }

    public static String md5(String value) {
        HashFunction hf = Hashing.md5();
        HashCode hc = hf.newHasher()
                .putString(value, Charsets.UTF_8)
                .hash();

        return hc.toString();
    }

    public static String b64encode(String s) {
        return Base64.getEncoder().encodeToString(s.getBytes(StandardCharsets.UTF_8));
    }

    public static String b64decode(String s) {
        return new String(Base64.getDecoder().decode(s), StandardCharsets.UTF_8);
    }

    public static String xor(String input, String KEY) {
        char[] key = KEY.toCharArray();
        StringBuilder ret = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            ret.append((char) (input.charAt(i) ^ key[i % key.length]));
        }
        return ret.toString();
    }

    public static <T> T get(Class<T> type) {
        return Context.get(type);
    }
}
