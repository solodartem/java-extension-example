package net.ifao.annotation.searcher.searcher;

import java.lang.annotation.Annotation;

public interface AnnotationSearcher {

    interface Collector {

        void collect(Class<?> clazz);
    }

    void collectAllClassesWithAnnotation(Class<? extends Annotation> annotation, Collector collector);
}
