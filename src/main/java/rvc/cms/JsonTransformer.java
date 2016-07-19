package rvc.cms;

import com.google.gson.Gson;
import rvc.ResponseTransformer;

/**
 * @author nurmuhammad
 *
 */

public class JsonTransformer implements ResponseTransformer {

    private Gson gson = new Gson();

    @Override
    public String transform(Object object) throws Exception {
        return gson.toJson(object);
    }
}
