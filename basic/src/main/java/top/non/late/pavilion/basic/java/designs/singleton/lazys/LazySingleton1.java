package top.non.late.pavilion.basic.java.designs.singleton.lazys;

/**
 * Created by yuhp@terminus.io on 2017/9/9.
 * Desc: 对于一些需要消费较多资源的单例，使用该模式。
 * 这是懒汉模式最简单的实现方式。可是十分影响性能！
 * 在每次获取实例时，都需要对整个对象加锁。
 * @see LazySingleton2 double-check
 */
public class LazySingleton1 {

    private static LazySingleton1 INSTANCE = null;

    private LazySingleton1() {
    }

    synchronized public static LazySingleton1 getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new LazySingleton1();
        }
        return INSTANCE;
    }

}
