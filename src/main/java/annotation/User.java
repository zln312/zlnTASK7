package annotation;

public class User {
    @UseCase(id=10,description = "test1 description")
    public void test1() {
    }

    @UseCase(id=20)
    public void test2() {
    }

    @UseCase(id=30,description = "test3 description")
    public void test3() {
    }
}
