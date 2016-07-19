package org;

import groovy.lang.GroovyShell;
import javassist.*;
import net.openhft.compiler.CachedCompiler;

import java.lang.reflect.Method;

/**
 * Created by Nurmuhammad on 02.07.2016.
 */
public class GroovyApp {
    public static void main(String[] args) throws Exception {
        GroovyShell shell = new GroovyShell();

        Object script = shell.evaluate("def sayHello() { " +
                "println 'Hello from Groovy script!' }; this");
        Method m = script.getClass().getMethod("sayHello");

        Object mytest = MyTest.class.newInstance();
        Method my = mytest.getClass().getDeclaredMethod("sayHello");


        final ClassPool ctPool = ClassPool.getDefault();
        Loader loader = new Loader(CachedCompiler.class.getClassLoader(), ctPool);

        CtClass ctClass = ctPool.makeClass("mypackage.MyClass3");

        CtMethod ctMethod = CtMethod.make("public void sayHello(){ System.out.println(\"Hello from Groovy script!\");; }", ctClass);
        ctClass.addMethod(ctMethod);
        Class aa = loader.loadClass("mypackage.MyClass3");
        ctClass.debugWriteFile();
        Object o = aa.newInstance();
        Method m3 = o.getClass().getDeclaredMethod("sayHello");


        System.out.println("start testing");
        System.out.println("sleep for a while");
        int i=0;
        while (true){if(i++>1000) break;}
        Thread.sleep(1000);
        while (true){if(i++>1000) break;}
        System.out.println("wake up");

        long j = System.currentTimeMillis();

        i=0;
        while (true){
            System.out.println("Hello from Groovy script!");
            if(i++>1000) break;
        }
        System.out.println(System.currentTimeMillis()-j);


        long k = System.currentTimeMillis();
        i=0;
        while (true){

            my.invoke(mytest);
            if(i++>1000) break;
        }

        System.out.println(System.currentTimeMillis()-k);


        long l = System.currentTimeMillis();
        i=0;
        while (true){

            m.invoke(script);
            if(i++>1000) break;
        }

        System.out.println(System.currentTimeMillis()-l);


        long s = System.currentTimeMillis();
        i=0;
        while (true){
            m3.invoke(o);
            if(i++>1000) break;
        }
        System.out.println(System.currentTimeMillis()-s);


    }

    static class MyTest{
        public void sayHello(){
            System.out.println("Hello from Groovy script!");
        }
    }
}
