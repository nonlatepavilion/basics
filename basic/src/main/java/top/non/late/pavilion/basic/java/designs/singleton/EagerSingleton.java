package top.non.late.pavilion.basic.java.designs.singleton;

/**
 * Created by nonlatepavilion on 2017/9/9.
 * Desc: 饿汉模式 该模式下，无需关心多线程同步获取实例产生的并发问题
 * java中, {@link Runtime}采用该单例模式
 *
 * @see Runtime
 */
public class EagerSingleton {

    private static final EagerSingleton INSTANCE = new EagerSingleton();

    /**
     * Don't let anyone else instantiate this class
     */
    private EagerSingleton() {

    }

    public static EagerSingleton instance() {
        return INSTANCE;
    }

}
