package com.ifao.parser;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.expr.AnnotationExpr;

import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

public class JavaSourcesParser {

    private final String className;
    private CompilationUnit compilationUnit;

    public JavaSourcesParser(Path classPath) throws FileNotFoundException {
        compilationUnit = JavaParser.parse(classPath.toFile());
        className = compilationUnit.getPrimaryType().get().getName().getIdentifier();
    }

    public boolean hasClassAnnotation(Class classAnnotation) {
        String classAnnotationName = classAnnotation.getName().substring(classAnnotation.getName().lastIndexOf(".")+1, classAnnotation.getName().length());
        for (AnnotationExpr t : compilationUnit.getClassByName(className).get().getAnnotations()) {
            if (t.getName().getIdentifier().equals(classAnnotationName))
                return true;
        }
        return false;
    }

    public String readClassName() {
        return compilationUnit.getClass().getCanonicalName();
    }

    public List<FieldDeclaration> readAnnotationComments(Class fieldAnnotation) {
        return compilationUnit.findAll(FieldDeclaration.class).stream()
                //.filter(f -> f.isPublic() && !f.getAnnotations().contains(fieldAnnotation))
                .collect(Collectors.toList());
    }
}
