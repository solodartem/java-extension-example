package net.ifao.annotation.searcher.spring;

import net.ifao.annotation.searcher.AnnotationSearcher;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * This class provided spring context and on spring context implementation of {@link AnnotationSearcher}.
 * All methods are static.
 */
public class SpringAnnotationSearcherProvider {

    private static ApplicationContext applicationContext;

    public synchronized static ApplicationContext getApplicationContext() {
        if (applicationContext == null) {
            // This path should be the exclusive. if it will be duplicated it will be raise 2 context in one.
            applicationContext = new AnnotationConfigApplicationContext("net.ifao.spring.context");
        }
        return applicationContext;
    }

    public synchronized static AnnotationSearcher getAnnotationSearcherProvider() {
        return getApplicationContext().getBean(AnnotationSearcher.class);
    }
}
