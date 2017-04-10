public class InvokevirtualVsInvokeinterface {
    private static interface I {
        public int getInteger ();
    }

    private static class A implements I {
        public int getInteger () { return 0; }
    }

    private static class B extends A { }

    static I i = new B();
    static A a = new B();

    public static void main(String[] args) {
        {
            long tm1 = System.nanoTime();
            for (long k = 0; k < 4000000000L; ++k) {
                a.getInteger();
            }
            long tm2 = System.nanoTime();
            System.out.println("invokevirtual took " + (Math.abs(tm2 - tm1) / 1000) + " us");
        }

        {
            long tm1 = System.nanoTime();
            for (long k = 0; k < 4000000000L; ++k) {
                i.getInteger();
            }
            long tm2 = System.nanoTime();
            System.out.println("invokeinterface took " + (Math.abs(tm2 - tm1) / 1000) + " us");
        }
        {
            long tm1 = System.nanoTime();
            for (long k = 0; k < 4000000000L; ++k) {
                a.getInteger();
            }
            long tm2 = System.nanoTime();
            System.out.println("invokevirtual took " + (Math.abs(tm2 - tm1) / 1000) + " us");
        }

        {
            long tm1 = System.nanoTime();
            for (long k = 0; k < 4000000000L; ++k) {
                i.getInteger();
            }
            long tm2 = System.nanoTime();
            System.out.println("invokeinterface took " + (Math.abs(tm2 - tm1) / 1000) + " us");
        }
        {
            long tm1 = System.nanoTime();
            for (long k = 0; k < 4000000000L; ++k) {
                ((Runnable)i::getInteger).run();

            }
            long tm2 = System.nanoTime();
            System.out.println("invokedynamic took " + (Math.abs(tm2 - tm1) / 1000) + " us");
        }

        // Output on Intel Xeon X5570 @ 2.93GHz:
        // invokevirtual took 41170 us
        // invokeinterface took 66305 us
    }
}