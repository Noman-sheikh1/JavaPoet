package org.springcore.placeholders;

import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

import javax.lang.model.element.Modifier;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Date;

public class CombinedGenerator {

    public static void main(String[] args) throws IOException {

        // Method : display()
        MethodSpec displayMethod =
                MethodSpec.methodBuilder("display")
                        .addModifiers(Modifier.PUBLIC)
                        .returns(void.class)

                        // $L Example
                        .addStatement("int age = $L", 22)

                        // $S Example
                        .addStatement("String name = $S", "Noman")

                        // $T Example
                        .addStatement("$T today = new $T()", Date.class, Date.class)

                        // Print the name
                        .addStatement("$T.out.println(name)", System.class)

                        .build();

        // Method : main()
        MethodSpec mainMethod =
                MethodSpec.methodBuilder("main")
                        .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                        .returns(void.class)
                        .addParameter(String[].class, "args")
                        .addStatement("new Student().display()")
                        .build();

        // Class : Student
        TypeSpec student =
                TypeSpec.classBuilder("Student")
                        .addModifiers(Modifier.PUBLIC)
                        .addMethod(displayMethod)
                        .addMethod(mainMethod)
                        .build();

        // Create Java File
        JavaFile javaFile =
                JavaFile.builder("com.example.combined", student)
                        .build();

        // Print generated source code on terminal
        System.out.println(javaFile);

        // Generate .java file
        javaFile.writeTo(Paths.get("src/main/java"));
    }
}