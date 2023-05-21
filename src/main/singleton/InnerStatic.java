package main.singleton;

class InnerStaticSingleton{
    
    private InnerStaticSingleton(){ System.out.println("constructor initialized..");}

    public static InnerStaticSingleton getInstance(){ return Impl.INSTANCE; }
    
    private static class Impl{
        private static final InnerStaticSingleton INSTANCE = new InnerStaticSingleton();
    }
}
public class InnerStatic {
    public static void main(String[] args) {
        InnerStaticSingleton singleton = InnerStaticSingleton.getInstance();
        InnerStaticSingleton singleton2 = InnerStaticSingleton.getInstance();
        System.out.println(singleton == singleton2);
    }
}
