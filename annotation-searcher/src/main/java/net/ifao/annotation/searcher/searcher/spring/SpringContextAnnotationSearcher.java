package net.ifao.annotation.searcher.searcher.spring;

import net.ifao.annotation.searcher.searcher.AnnotationSearcher;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;

@Component
public class SpringContextAnnotationSearcher implements ApplicationContextAware, AnnotationSearcher {

    ApplicationContext applicationContext;

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
