package org.springcore.controlflow;

import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

import javax.lang.model.element.Modifier;
import java.io.IOException;
import java.nio.file.Paths;

public class ControlFlowGenerator {

    public static void main(String[] args) throws IOException {

        MethodSpec calculateMethod =
                MethodSpec.methodBuilder("calculate")
                        .addModifiers(Modifier.PUBLIC)
                        .returns(void.class)

                        .addStatement("int total = 0")

                        .beginControlFlow("for (int i = 1; i <= 5; i++)")
                        .addStatement("total += i")
                        .endControlFlow()

                        .beginControlFlow("if (total > 10)")
                        .addStatement("$T.out.println($S)", System.class, "Greater")
                        .nextControlFlow("else")
                        .addStatement("$T.out.println($S)", System.class, "Smaller")
                        .endControlFlow()

                        .build();


        MethodSpec mainMethod =
                MethodSpec.methodBuilder("main")
                        .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                        .returns(void.class)
                        .addParameter(String[].class, "args")
                        .addStatement("new Calculator().calculate()")
                        .build();


        TypeSpec calculator =
                TypeSpec.classBuilder("Calculator")
                        .addModifiers(Modifier.PUBLIC)
                        .addMethod(calculateMethod)
                        .addMethod(mainMethod)
                        .build();


        JavaFile javaFile = JavaFile.builder("com.example.control", calculator)
                .build();



        System.out.println(javaFile);

        javaFile.writeTo(Paths.get("src/main/java"));
    }
}