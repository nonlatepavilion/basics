package top.non.late.pavilion.basic.java.designs.singleton.lazys;

/**
 * Created by nonlatepavilion on 2017/9/9.
 * Desc: 懒汉模式 inner class
 *
 * 单例类的实例被包裹在内部类中，因此该单例类加载时并不会完成实例化，直到有线程调用getInstance方法，内部类才会被加载并实例化单例类???
 *
 * you should know: java 类的加载机制
 */
public class LazySingleton3 {

    /**
     * inner static class
     */
    private static class SingletonHolder {
        private static final LazySingleton3 INSTANCE = new LazySingleton3();

    }

    private LazySingleton3() {
        System.out.println("really?");
    }

    public static LazySingleton3 getInstance() {
        return SingletonHolder.INSTANCE;
    }

}
