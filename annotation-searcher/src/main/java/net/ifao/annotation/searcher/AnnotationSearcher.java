package net.ifao.annotation.searcher;

import java.lang.annotation.Annotation;

/**
 * This class search all classes which has provided annotation
 * and call callback this each one to provided {@link Collector}
 */
public interface AnnotationSearcher {

    interface Collector {

        void collect(Class<?> clazz);
    }

    void collectAllClassesWithAnnotation(Class<? extends Annotation> annotation, Collector collector);
}
