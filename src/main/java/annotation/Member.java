package annotation;

public class Member {

    @Student
    String student1;

    @Student(10)
    String student2;

    @Student(name = "Tom", age = 17)
    String student3;

    @Student(name = "jack", other = @Test(isMonitor = true, region = "Canada"))
    String student4;
}
