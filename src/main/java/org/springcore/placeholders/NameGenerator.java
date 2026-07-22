package org.springcore.placeholders;

import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

import javax.lang.model.element.Modifier;
import java.io.IOException;
import java.nio.file.Paths;

public class NameGenerator {

    public static void main(String[] args) throws IOException {

        // Method : square()
        MethodSpec squareMethod =
                MethodSpec.methodBuilder("square")
                        .addModifiers(Modifier.PUBLIC)
                        .returns(int.class)
                        .addParameter(int.class, "n")
                        .addStatement("return n * n")
                        .build();

        // Method : calculate()
        MethodSpec calculateMethod =
                MethodSpec.methodBuilder("calculate")
                        .addModifiers(Modifier.PUBLIC)
                        .returns(int.class)
                        .addStatement("return $N(5)", squareMethod)
                        .build();

        // Method : main()
        MethodSpec mainMethod =
                MethodSpec.methodBuilder("main")
                        .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                        .returns(void.class)
                        .addParameter(String[].class, "args")
                        .addStatement("$T.out.println(new Calculator().calculate())", System.class)
                        .build();

        // Class : Calculator
        TypeSpec calculator =
                TypeSpec.classBuilder("Calculator")
                        .addModifiers(Modifier.PUBLIC)
                        .addMethod(squareMethod)
                        .addMethod(calculateMethod)
                        .addMethod(mainMethod)
                        .build();

        // Create Java File
        JavaFile javaFile =
                JavaFile.builder("com.example.name", calculator)
                        .build();

        // Print generated source code
        System.out.println(javaFile);

        // Generate Java File
        javaFile.writeTo(Paths.get("src/main/java"));
    }
}