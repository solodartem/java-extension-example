package net.ifao.annotation.searcher.spring;

import net.ifao.annotation.searcher.AnnotationSearcher;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;

/**
 * This is a spring implementation of {@link AnnotationSearcher}
 */
@Component
public class SpringContextAnnotationSearcher implements ApplicationContextAware, AnnotationSearcher {

    ApplicationContext applicationContext;

    public SpringContextAnnotationSearcher() {
        int i = 0;

        ++i;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public void collectAllClassesWithAnnotation(Class<? extends Annotation> annotation, Collector collector) {
        applicationContext.getBeansWithAnnotation(annotation)
                .values()
                .stream()
                .forEach(a -> collector.collect(a.getClass()));
    }
}
