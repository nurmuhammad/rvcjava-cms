package rvc.cms;

import com.mitchellbosecke.pebble.PebbleEngine;
import com.mitchellbosecke.pebble.loader.*;
import com.mitchellbosecke.pebble.template.PebbleTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rvc.ModelAndView;
import rvc.TemplateEngine;
import rvc.cms.init.Config;

import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Map;

/**
 * @author nurmuhammad
 */

public class Pebble extends TemplateEngine {

    private static final Logger logger = LoggerFactory.getLogger(Pebble.class);

    public static Pebble instance = new Pebble();

    private PebbleEngine engine;

    public Pebble() {
        ArrayList list = new ArrayList();
//        list.add(new ClasspathLoader());
        FileLoader fileLoader = new FileLoader();
        fileLoader.setPrefix(Config.get("template.dir", ""));
        list.add(fileLoader);
        list.add(new StringLoader());
        Loader loader = new DelegatingLoader(list);

        engine = new PebbleEngine
                .Builder()
                .loader(loader)
                .build();
    }

    @Override
    public String render(ModelAndView modelAndView) {
        try {
            PebbleTemplate template = engine.getTemplate(modelAndView.getViewName());
            Writer writer = new StringWriter();
            if (modelAndView.getModel() instanceof Map) {
                template.evaluate(writer, (Map) modelAndView.getModel());
            } else {
                template.evaluate(writer);
            }
            return writer.toString();
        } catch (Exception e) {
            logger.error("Error when rendering templates.", e);
        }
        return null;
    }
}
