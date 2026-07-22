package org.springcore.placeholders;

import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

import javax.lang.model.element.Modifier;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Date;

public class TypeGenerator {

    public static void main(String[] args) throws IOException {

        // Method : today()
        MethodSpec todayMethod =
                MethodSpec.methodBuilder("today")
                        .addModifiers(Modifier.PUBLIC)
                        .returns(Date.class)

                        // $T Example
                        .addStatement("return new $T()", Date.class)

                        .build();

        // Method : main()
        MethodSpec mainMethod =
                MethodSpec.methodBuilder("main")
                        .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                        .returns(void.class)
                        .addParameter(String[].class, "args")

                        // Print the returned Date
                        .addStatement("$T.out.println(new Demo().today())", System.class)

                        .build();

        // Class : Demo
        TypeSpec demo =
                TypeSpec.classBuilder("Demo")
                        .addModifiers(Modifier.PUBLIC)
                        .addMethod(todayMethod)
                        .addMethod(mainMethod)
                        .build();

        // Create Java File
        JavaFile javaFile =
                JavaFile.builder("com.example.type", demo)
                        .build();



        // Generate Java file
        javaFile.writeTo(Paths.get("src/main/java"));
    }
}