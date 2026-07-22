package org.springcore.basic;

import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

import javax.lang.model.element.Modifier;
import java.io.IOException;
import java.nio.file.Paths;

public class HelloWorldGenerator {

    public static void main(String[] args) throws IOException {

        MethodSpec mainMethod =
                MethodSpec.methodBuilder("main")
                        .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                        .returns(void.class)
                        .addParameter(String[].class, "args")
                        .addStatement("$T.out.println($S)",
                                System.class,
                                "Hello JavaPoet!")
                        .build();

        TypeSpec helloWorld =
                TypeSpec.classBuilder("HelloWorld")
                        .addModifiers(Modifier.PUBLIC, Modifier.FINAL)
                        .addMethod(mainMethod)
                        .build();

        JavaFile javaFile =
                JavaFile.builder(
                                "com.example.helloworld",
                                helloWorld)
                        .build();

        javaFile.writeTo(Paths.get("src/main/java"));
        javaFile.writeTo(System.out);
    }

}