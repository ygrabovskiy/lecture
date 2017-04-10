public class InvokeInterface {
    private static Parent  parent = new Parent();
    private static IParent  iparent = new Parent();
    public static void main(String... args) {
        ((Runnable)parent::name).run();
        iparent.name();
        parent.name();
    }
}
