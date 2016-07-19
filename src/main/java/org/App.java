package org;

import javassist.*;
import javassist.util.HotSwapper;
import net.openhft.compiler.CachedCompiler;
import net.openhft.compiler.CompilerUtils;
import org.xeustechnologies.jcl.JarClassLoader;

import javax.tools.ToolProvider;
import java.lang.instrument.Instrumentation;
import java.lang.reflect.Method;

/**
 * Created by Nurmuhammad on 28.06.2016.
 */


public class App {


    public static void main(String[] args) throws Exception {

        // dynamically you can call
        String className = "mypackage.MyClass";
        String javaCode = "package mypackage;\n" +
                "public class MyClass implements Runnable {\n" +
                "    public void run() {\n" +
                "        System.out.println(\"Hello World\");\n" +
                "    }\n" +
                "}\n";
        Class aClass = CompilerUtils.CACHED_COMPILER.loadFromJava(className, javaCode);
        Runnable runner = (Runnable) aClass.newInstance();

        runner.run();

        className = "mypackage.MyClass";
        javaCode = "package mypackage;\n" +
                "public class MyClass implements Runnable {\n" +
                "    public void run() {\n" +
                "        System.out.println(\"Hello World1\");\n" +
                "    }\n" +
                "}\n";

        CachedCompiler compiler = new CachedCompiler(null, null);
        aClass = compiler.loadFromJava(className, javaCode);
//        aClass = CompilerUtils.loadFromJava(className, javaCode);
        runner = (Runnable) aClass.newInstance();

        runner.run();




//        ToolProvider.getSystemJavaCompiler().

        final ClassPool ctPool = ClassPool.getDefault();
        Loader loader = new Loader(CachedCompiler.class.getClassLoader(), ctPool);

        CtClass ctClass = ctPool.makeClass("mypackage.MyClass3");

//        ctClass.setSuperclass(cl);
        ctClass.addInterface(ctPool.get("java.lang.Runnable"));
        CtField ctField = CtField.make("private int i=1;", ctClass);
        ctClass.addField(ctField);
        CtMethod ctMethod = CtMethod.make("public void setI(int i) { this.i = i; }", ctClass);
        ctClass.addMethod(ctMethod);

        CtMethod ctMethod2 = CtMethod.make("public int getI() { setI(12);return i++; }", ctClass);
        ctClass.addMethod(ctMethod2);

        ctMethod = CtMethod.make("public void run() { System.out.println(i); }", ctClass);
        ctClass.addMethod(ctMethod);

        Class aa = loader.loadClass("mypackage.MyClass3");
//        Class aa = ctClass.toClass();

//        ctClass.stopPruning(true);
        ctClass.debugWriteFile();
//        ctClass.writeFile();
//        ctClass.defrost();

        Object o = aa.newInstance();
        ((Runnable)o).run();

//        ctPool.makeClass()

        Object o1 = aa.getMethod("getI").invoke(o);
        System.out.println(o1);
        ((Runnable)o).run();

        CtMethod m = ctClass.getDeclaredMethod("getI");
        m.setBody("{setI(22);return i+=2;}");
        ctClass.debugWriteFile();

        loader = new Loader(ctPool);

//        Class aa2 = ctClass.toClass();
        Class aa2 = loader.loadClass("mypackage.MyClass3");

        Object o2 = aa2.newInstance();

//        ctPool.makeClass()

        Object o3 = aa2.getMethod("getI").invoke(o2);
        System.out.println(o3);
        ((Runnable)o2).run();


/*        final ClassPool ctPool2 = ClassPool.getDefault();

        aTmpl = new CtClassTemplate("mypackage.MyClass3");
        aTmpl.addImplements("java.lang.Runnable");
        aTmpl.addField("private int i=1;");
        aTmpl.addMethod("public void setI(int i) { this.i = i; }");
        aTmpl.addMethod("public int getI() { setI(22);return i++; }");
        aTmpl.addMethod("public void run() { System.out.println(i); }");

        final Class aa2 = aTmpl.createClass(ctPool2);
        Object o2 = aa.newInstance();

//        ctPool.makeClass()

        Object o3 = aa2.getMethod("getI").invoke(o2);
        System.out.println(o3);
        ((Runnable)o2).run();*/
    }

    public static class MyClass1 implements Runnable {
        public void run() {
            System.out.println("Hello World");
        }
    }


}
