package org.ifao.extention;

import net.ifao.annotation.searcher.AnnotationSearcher;
import net.ifao.annotation.searcher.spring.SpringAnnotationSearcherProvider;
import org.asciidoctor.ast.AbstractBlock;
import org.asciidoctor.extension.BlockProcessor;
import org.asciidoctor.extension.Reader;

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
        AnnotationSearcher appAnnotationSearcher = SpringAnnotationSearcherProvider.getAnnotationSearcherProvider();

        List<String> lines = reader.readLines();
        String upperLines = null;
        for (String line : lines) {
            if (upperLines == null) {
                upperLines = line.toUpperCase();
            }
            else {
                upperLines = upperLines + "\n" + line.toUpperCase();
            }
        }

        return createBlock(parent, "paragraph", Arrays.asList(upperLines), attributes, new HashMap<>());
    }

}
