package annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Student {

    int value() default 0;

    String name() default "";

    int age() default 0;

    Test other() default @Test; //注解元素中可以定义注解类型
}
