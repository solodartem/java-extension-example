package com.ifao.parser;

import com.github.javaparser.ast.body.FieldDeclaration;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.LinkedList;
import java.util.List;

public class JavaSourceClassVisitor extends SimpleFileVisitor<Path> {

    public static class JavaSourcesParsingResults {
        private String canonicalClassName;
        private List<FieldDeclaration> fieldDeclarations;

        public JavaSourcesParsingResults(String canonicalClassName, List<FieldDeclaration> fieldDeclarations) {
            this.canonicalClassName = canonicalClassName;
            this.fieldDeclarations = fieldDeclarations;
        }

        public String getCanonicalClassName() {
            return canonicalClassName;
        }

        public List<FieldDeclaration> getFieldDeclarations() {
            return fieldDeclarations;
        }

        @Override
        public String toString() {
            return "JavaSourcesParsingResults{" +
                    "canonicalClassName='" + canonicalClassName + '\'' +
                    ", fieldDeclarations=" + fieldDeclarations +
                    '}';
        }
    }

    private Class classAnnotation;
    private Class fieldAnnotation;

    private List<JavaSourcesParsingResults> parsingResults = new LinkedList<>();

    public JavaSourceClassVisitor(Class classAnnotation, Class fieldAnnotation) {
        this.classAnnotation = classAnnotation;
        this.fieldAnnotation = fieldAnnotation;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        if (file.getName(file.getNameCount() - 1).toString().endsWith(".java")) {
            JavaSourcesParser javaSourcesParser = new JavaSourcesParser(file);
            if (javaSourcesParser.hasClassAnnotation(classAnnotation)) {
                parsingResults.add(new JavaSourcesParsingResults(javaSourcesParser.readClassName(), javaSourcesParser.readAnnotationComments(fieldAnnotation)));
            }
        }
        return super.visitFile(file, attrs);
    }

    public List<JavaSourcesParsingResults> getParsingResults() {
        return parsingResults;
    }
}
