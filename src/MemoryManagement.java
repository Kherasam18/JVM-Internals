public class MemoryManagement {
    public static void main(String[] args) {
        String stringLiteral = "24";
        int primitiveVar = 10;
        Person perobj = new Person();
        MemoryManagement memObj = new MemoryManagement();
        memObj.memoryMaangementTest(perobj);
    }


    private void memoryMaangementTest(Person perobj) {
        Person perobj2 = perobj;
        String stringLiteral2 = "24";
        String stringLiteral3 = new String("24");
    }
}


class Person{
}
