package org.springcore.placeholders;

import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

import javax.lang.model.element.Modifier;
import java.io.IOException;
import java.nio.file.Paths;

public class LiteralGenerator {

    public static void main(String[] args) throws IOException {

        // Method : details()
        MethodSpec detailsMethod =
                MethodSpec.methodBuilder("details")
                        .addModifiers(Modifier.PUBLIC)
                        .returns(void.class)

                        // $L Examples
                        .addStatement("int age = $L", 22)
                        .addStatement("double cgpa = $L", 8.61)
                        .addStatement("boolean placed = $L", true)

                        .addStatement("$T.out.println($S + age)", System.class, "Age : ")
                        .addStatement("$T.out.println($S + cgpa)", System.class, "CGPA : ")
                        .addStatement("$T.out.println($S + placed)", System.class, "Placed : ")

                        .build();

        // Method : main()
        MethodSpec mainMethod =
                MethodSpec.methodBuilder("main")
                        .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                        .returns(void.class)
                        .addParameter(String[].class, "args")
                        .addStatement("new Student().details()")
                        .build();

        // Class : Student
        TypeSpec student =
                TypeSpec.classBuilder("Student")
                        .addModifiers(Modifier.PUBLIC)
                        .addMethod(detailsMethod)
                        .addMethod(mainMethod)
                        .build();

        // Create Java File
        JavaFile javaFile =
                JavaFile.builder("com.example.literal", student)
                        .build();

        // Print generated source code
        System.out.println(javaFile);

        // Generate Java file
        javaFile.writeTo(Paths.get("src/main/java"));
    }
}