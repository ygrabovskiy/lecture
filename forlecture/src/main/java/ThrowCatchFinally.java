public class ThrowCatchFinally {
    private void x(){}
    private void y(){}

    public void foo(){
        try{
            x();
        }finally {
            y();
        }
    }
}
