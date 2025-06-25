public class ThreadLocalExample {
    // ThreadLocal variable: each thread will get its own copy
    private static ThreadLocal<String> userName = new ThreadLocal<>();

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            userName.set("Sam");
            System.out.println("Thread 1: " + userName.get());
        });
        Thread t2 = new Thread(() -> {
            userName.set("Aman");
            System.out.println("Thread 2: " + userName.get());
        });

        t1.start();
        t2.start();
    }
}
