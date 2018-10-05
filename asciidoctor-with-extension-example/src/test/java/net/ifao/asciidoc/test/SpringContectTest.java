package net.ifao.asciidoc.test;

import net.ifao.annotation.searcher.searcher.AnnotationSearcher;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.net.URL;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {Config.class})
public class SpringContectTest {

    @Autowired
    AnnotationSearcher annotationSearcher;

    @Test
    public void test() {
        annotationSearcher.collectAllClassesWithAnnotation(Service.class, a -> System.out.println(a.getName()));
    }

    @Test
    public void testToFile() throws IOException {
        File file = ResourceUtils.getFile("classpath:Services.txt");
        System.out.println(file.getAbsolutePath());
        try (PrintWriter out = new PrintWriter(new FileOutputStream(file))) {
            annotationSearcher.collectAllClassesWithAnnotation(Service.class, a -> out.println(a.getName()));
        }
    }
}
