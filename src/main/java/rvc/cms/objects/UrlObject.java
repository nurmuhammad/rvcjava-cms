package rvc.cms.objects;

/**
 * Created by Nurmuhammad on 05.07.2016.
 */
public class UrlObject {

    String domain;
    String method;
    String uri;
    String acceptType;
    long cacheExpire;
    boolean gzip = false;

    String returnType; //plain text, template, dynamic with java code, file, sql
    String template;
    String renderType; // json, xml, plain

    String javaCode;

    String filepath; // if result is file

    byte[] result;

}
