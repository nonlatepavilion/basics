package top.non.late.pavilion.basic.java.designs.singleton.lazys;

/**
 * Created by yuhp@terminus.io on 2017/9/9.
 * Desc: 懒汉模式 double-check
 * 在这种模式下，只有在第一次获取实例，即实例对象为空时，会产生锁的行为，可以大大降低性能消耗。
 * 还有利用java 语义特性，通过inner class实现singleton
 *
 * @see LazySingleton3
 */
public class LazySingleton2 {

    private static LazySingleton2 INSTANCE = null;

    private LazySingleton2() {
    }

    public static LazySingleton2 getInstance() {
        if (INSTANCE == null) {
            synchronized (LazySingleton2.class) {
                INSTANCE = new LazySingleton2();
            }
        }
        return INSTANCE;
    }

}
