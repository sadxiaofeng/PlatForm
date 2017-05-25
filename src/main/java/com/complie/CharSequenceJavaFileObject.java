package com.complie;

import javax.tools.SimpleJavaFileObject;
import java.net.URI;

/**
 * Created by 钱逊 on 2017/5/24.
 */
public class CharSequenceJavaFileObject extends SimpleJavaFileObject {
    private CharSequence content;


    public CharSequenceJavaFileObject(String className,
                                      CharSequence content) {
        super(URI.create("string:///" + className.replace('.', '/')
                + Kind.SOURCE.extension), Kind.SOURCE);
        this.content = content;
    }

    @Override
    public CharSequence getCharContent(
            boolean ignoreEncodingErrors) {
        return content;
    }
}
