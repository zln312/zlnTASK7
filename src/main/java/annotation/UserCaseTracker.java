package annotation;

import java.lang.reflect.Method;

public class UserCaseTracker {
    public static void trackUser(Class<?> cl) {

        for (Method m : cl.getDeclaredMethods()) {

            if (m.isAnnotationPresent(UseCase.class)) {  //是否存在UseCase注解
                UseCase uc = m.getAnnotation(UseCase.class);//获取UseCase注解
                System.out.println("id: "+uc.id() + " description: " + uc.description());
            }
        }
    }

    public static void main(String[] args) {
        trackUser(User.class);
    }
}
