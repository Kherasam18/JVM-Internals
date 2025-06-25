import java.lang.ref.*;

public class ReferenceDemo {

    static class MyObject {
        private final String name;
        MyObject(String name) {
            this.name = name;
        }

        @Override
        protected void finalize() throws Throwable {
            System.out.println("Finalizing " + name);
        }

        @Override
        public String toString() {
            return "MyObject: " + name;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        // Create a reference queue for tracking
        ReferenceQueue<MyObject> refQueue = new ReferenceQueue<>();

        // 1. Strong Reference
        MyObject strong = new MyObject("StrongRef");
        System.out.println("Strong reference: " + strong);

        // 2. Soft Reference
        SoftReference<MyObject> softRef = new SoftReference<>(new MyObject("SoftRef"), refQueue);
        System.out.println("Soft reference: " + softRef.get());

        // 3. Weak Reference
        WeakReference<MyObject> weakRef = new WeakReference<>(new MyObject("WeakRef"), refQueue);
        System.out.println("Weak reference: " + weakRef.get());

        // 4. Phantom Reference
        PhantomReference<MyObject> phantomRef = new PhantomReference<>(new MyObject("PhantomRef"), refQueue);
        System.out.println("Phantom reference always returns null: " + phantomRef.get());

        // Suggest GC (no guarantee)
        System.out.println("Forcing GC");
        System.gc();

        // Allow time for GC to happen
        Thread.sleep(2000);

        System.out.println("After GC:");
        System.out.println("Soft ref: " + softRef.get());
        System.out.println("Weak ref: " + weakRef.get());
        System.out.println("Phantom ref (always null): " + phantomRef.get());

        // Check ReferenceQueue
        Reference<? extends MyObject> ref;
        while ((ref = refQueue.poll()) != null) {
            System.out.println("Reference enqueued for GC: " + ref.getClass().getSimpleName());
        }
    }
}
