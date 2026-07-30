package org.example;

import com.squareup.javapoet.*;
import javax.lang.model.element.Modifier;
import java.io.IOException;

public class RestApiGenerator {

    public static void main(String[] args) throws IOException {

        String[] entities = {"Employee", "Student", "Product"};

        for (String entity : entities) {

            // @GetMapping Method
            MethodSpec getMethod = MethodSpec.methodBuilder("getAll" + entity + "s")
                    .addAnnotation(ClassName.get("org.springframework.web.bind.annotation", "GetMapping"))
                    .addModifiers(Modifier.PUBLIC)
                    .returns(String.class)
                    .addStatement("return $S", "Fetching all " + entity + "s")
                    .build();

            // @PostMapping Method
            MethodSpec postMethod = MethodSpec.methodBuilder("save" + entity)
                    .addAnnotation(ClassName.get("org.springframework.web.bind.annotation", "PostMapping"))
                    .addModifiers(Modifier.PUBLIC)
                    .returns(String.class)
                    .addParameter(String.class, entity.toLowerCase())
                    .addStatement("return $S", entity + " Saved Successfully")
                    .build();

            // Controller Class
            TypeSpec controller = TypeSpec.classBuilder(entity + "Controller")
                    .addModifiers(Modifier.PUBLIC)
                    .addAnnotation(ClassName.get("org.springframework.web.bind.annotation", "RestController"))
                    .addAnnotation(
                            AnnotationSpec.builder(
                                            ClassName.get("org.springframework.web.bind.annotation", "RequestMapping"))
                                    .addMember("value", "$S", "/" + entity.toLowerCase())
                                    .build())
                    .addMethod(getMethod)
                    .addMethod(postMethod)
                    .build();

            JavaFile javaFile = JavaFile.builder("com.demo.controller", controller)
                    .build();

            javaFile.writeTo(System.out);
        }
    }
}