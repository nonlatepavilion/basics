package top.non.late.pavilion.basic;

import org.junit.Test;
import top.non.late.pavilion.basic.java.designs.singleton.lazys.LazySingleton3;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by yuhp@terminus.io on 2017/9/10.
 * Desc:
 */
public class Tests {

    @Test
    public void test() {

        ExecutorService service = Executors.newFixedThreadPool(10000);
        service.submit((Runnable) LazySingleton3::getInstance);
    }

}
