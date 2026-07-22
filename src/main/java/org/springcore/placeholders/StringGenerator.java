package org.springcore.placeholders;

import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

import javax.lang.model.element.Modifier;
import java.io.IOException;
import java.nio.file.Paths;

public class StringGenerator {

    public static void main(String[] args) throws IOException {

        // Method : greet()
        MethodSpec greetMethod =
                MethodSpec.methodBuilder("greet")
                        .addModifiers(Modifier.PUBLIC)
                        .returns(String.class)

                        // $S Example
                        .addStatement("return $S", "Welcome to JavaPoet")

                        .build();

        // Method : main()
        MethodSpec mainMethod =
                MethodSpec.methodBuilder("main")
                        .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                        .returns(void.class)
                        .addParameter(String[].class, "args")

                        // $T Example
                        .addStatement("$T.out.println(new Greeting().greet())", System.class)

                        .build();

        // Class : Greeting
        TypeSpec greeting =
                TypeSpec.classBuilder("Greeting")
                        .addModifiers(Modifier.PUBLIC)
                        .addMethod(greetMethod)
                        .addMethod(mainMethod)
                        .build();

        // Create Java File
        JavaFile javaFile =
                JavaFile.builder("com.example.string", greeting)
                        .build();

        // Print generated source code
        System.out.println(javaFile);

        // Generate Java File
        javaFile.writeTo(Paths.get("src/main/java"));
    }
}