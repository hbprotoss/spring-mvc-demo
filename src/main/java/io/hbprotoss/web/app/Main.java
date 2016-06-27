package io.hbprotoss.web.app;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by hbprotoss on 6/24/16.
 */
public class Main {
    public static void main(String[] args) throws ClassNotFoundException {
        ClassPathScanningCandidateComponentProvider scanner = new ClassPathScanningCandidateComponentProvider(false);
        scanner.addIncludeFilter(new AnnotationTypeFilter(Controller.class));
        for (BeanDefinition beanDefinition : scanner.findCandidateComponents("io.hbprotoss.web.controller")){
            String name = beanDefinition.getBeanClassName();
            Class clazz = Class.forName(name);
            System.out.println("controller: " + name);
            for (Method method: clazz.getDeclaredMethods()) {
                if (!method.isAnnotationPresent(RequestMapping.class)) {
                    continue;
                }

                System.out.println("-----------------------");
                RequestMapping mapping = method.getAnnotation(RequestMapping.class);
                System.out.println("path: " + Arrays.toString(mapping.value()));
                System.out.println("method: " + Arrays.toString(mapping.method()));
                System.out.println("params:");
                Class[] paramTypes = method.getParameterTypes();
                Annotation[][] annotations = method.getParameterAnnotations();
                for (int i = 0; i < method.getParameterCount(); i++) {
                    Class type = paramTypes[i];
                    System.out.println(String.format(
                            "name:arg%d, type: %s, annotations:%s",
                            i, type.getName(), Arrays.toString(annotations[i])
                    ));
                }
            }
            System.out.println();
        }
        System.out.println();
    }
}
