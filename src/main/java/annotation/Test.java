package annotation;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


//元注解@Target定义了注解的使用范围

/* Method declaration 方法声明*/
//@Target(ElementType.METHOD)

/* Annotation type declaration 注释类型声明*/
//@Target(ElementType.ANNOTATION_TYPE)

/* Class, interface (including annotation type), or enum declaration 类、接口(包括注释类型)或枚举声明*/
/*@Target(ElementType.TYPE)

/* Constructor declaration 构造函数声明*/
//@Target(ElementType.CONSTRUCTOR)

/* Field declaration (includes enum constants) 字段声明(包括枚举常量)*/
@Target(ElementType.FIELD)

/* Local variable declaration 局部变量声明*/
//@Target(ElementType.LOCAL_VARIABLE)

/* Package declaration 包装声明*/
//@Target(ElementType.PACKAGE)

/* Formal parameter declaration 正式的参数声明*/
//@Target(ElementType.PARAMETER)

@Retention(RetentionPolicy.RUNTIME)
public @interface Test {
    boolean isMonitor() default false;

    String region() default "";

    String hobby() default "";

}
