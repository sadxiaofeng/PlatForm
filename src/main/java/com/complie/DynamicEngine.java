package com.complie;

import javax.tools.*;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 钱逊 on 2017/5/24.
 */
public class DynamicEngine {
    private static DynamicEngine ourInstance = new DynamicEngine();

    private static JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();

    public static DynamicEngine getInstance() {
        return ourInstance;
    }
    private URLClassLoader parentClassLoader;
    private String classpath;
    private DynamicEngine() {
        parentClassLoader = (URLClassLoader) getClass().getClassLoader();
        buildClassPath();
    }
    private void buildClassPath() {
        classpath = null;
        StringBuilder sb = new StringBuilder();
        for (URL url : parentClassLoader.getURLs()) {
            String p = url.getFile();
            sb.append(p).append(File.pathSeparator);
        }
        classpath = sb.toString();
    }
    public Object javaCodeToObject(String fullClassName, String javaCode) throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        long start = System.currentTimeMillis();
        String instance = "";

        DiagnosticCollector<JavaFileObject> diagnostics = new DiagnosticCollector<JavaFileObject>();
        ClassFileManager fileManager = new ClassFileManager(compiler.getStandardFileManager(diagnostics, null, null));

        List<JavaFileObject> jfiles = new ArrayList<JavaFileObject>();
        jfiles.add(new CharSequenceJavaFileObject(fullClassName, javaCode));

        List<String> options = new ArrayList<String>();
        options.add("-encoding");
        options.add("UTF-8");
        options.add("-classpath");
        options.add(classpath);

        JavaCompiler.CompilationTask task = compiler.getTask(null, fileManager, diagnostics, options, null, jfiles);
        boolean success = task.call();

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(bos));
        ByteArrayOutputStream err = new ByteArrayOutputStream();
        System.setErr(new PrintStream(err));
        if (success) {
            JavaClassObject jco = fileManager.getJavaClassObject();
            DynamicClassLoader dynamicClassLoader = new DynamicClassLoader(parentClassLoader);
            Class clazz = dynamicClassLoader.loadClass(fullClassName,jco);

            Method mainMethod=clazz.getMethod("main", String[].class);
            mainMethod.invoke(null, new Object[]{new String[0]});
            long end = System.currentTimeMillis();
//            System.out.println("javaCodeToObject use:"+(end-start)+"ms");
            instance += bos.toString();
            instance += err.toString();
        } else {
            String error = "";
            for (Diagnostic diagnostic : diagnostics.getDiagnostics()) {
                error = error + compilePrint(diagnostic);
            }
            instance+=error;
        }
        return instance;
    }

    private String compilePrint(Diagnostic diagnostic) {
//        System.out.println("Code:" + diagnostic.getCode());
//        System.out.println("Kind:" + diagnostic.getKind());
//        System.out.println("Position:" + diagnostic.getPosition());
//        System.out.println("Start Position:" + diagnostic.getStartPosition());
//        System.out.println("End Position:" + diagnostic.getEndPosition());
//        System.out.println("Source:" + diagnostic.getSource());
//        System.out.println("Message:" + diagnostic.getMessage(null));
//        System.out.println("LineNumber:" + diagnostic.getLineNumber());
//        System.out.println("ColumnNumber:" + diagnostic.getColumnNumber());
        StringBuffer res = new StringBuffer();
        res.append("Code:[" + diagnostic.getCode() + "]\n");
        res.append("Kind:[" + diagnostic.getKind() + "]\n");
        res.append("Position:[" + diagnostic.getPosition() + "]\n");
        res.append("Start Position:[" + diagnostic.getStartPosition() + "]\n");
        res.append("End Position:[" + diagnostic.getEndPosition() + "]\n");
        res.append("Source:[" + diagnostic.getSource() + "]\n");
        res.append("Message:[" + diagnostic.getMessage(null) + "]\n");
        res.append("LineNumber:[" + diagnostic.getLineNumber() + "]\n");
        res.append("ColumnNumber:[" + diagnostic.getColumnNumber() + "]\n");
        return res.toString();
    }
}
