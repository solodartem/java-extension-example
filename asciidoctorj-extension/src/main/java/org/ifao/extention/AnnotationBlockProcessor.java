package org.ifao.extention;

import com.ifao.parser.JavaSourceClassVisitor;
import org.asciidoctor.ast.AbstractBlock;
import org.asciidoctor.extension.BlockProcessor;
import org.asciidoctor.extension.Reader;

import javax.xml.ws.BindingType;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class AnnotationBlockProcessor extends BlockProcessor {

    private static Map<String, Object> configs = new HashMap<String, Object>() {{
        put("contexts", Arrays.asList(":paragraph"));
        put("content_model", ":simple");
    }};

    public AnnotationBlockProcessor(String name, Map<String, Object> config) {
        super(name, configs);
    }

    @Override
    public Object process(AbstractBlock parent, Reader reader, Map<String, Object> attributes) {

        JavaSourceClassVisitor visitor = new JavaSourceClassVisitor(BindingType.class, Deprecated.class);

        try {
            Files.walkFileTree(Paths.get("C:\\dev\\src\\java-extension-example\\asciidoctor-with-extension-example\\src"), visitor);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(visitor.getParsingResults());

/*
        List<String> lines = reader.readLines();
        String upperLines = null;
        for (String line : lines) {
            if (upperLines == null) {
                upperLines = line.toUpperCase();
            } else {
                upperLines = upperLines + "\n" + line.toUpperCase();
            }
        }
*/

        return createBlock(parent, "paragraph", Arrays.asList(visitor.getParsingResults().toString()), attributes, new HashMap<>());
    }

}
