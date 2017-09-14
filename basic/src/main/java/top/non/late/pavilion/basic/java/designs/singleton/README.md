看似只是最简单的一种设计模式，可细细挖掘，static、synchronized、volatile关键字、内部类、对象克隆、序列化、枚举类型、
反射和类加载机制等基础却又不易理解透彻的Java知识纷纷呼之欲出，让人不禁感叹Singleton单例模式是最适合作为考察应聘者Java基础的一道考题。
从表面上看，Singleton希望并限制该类的实例只能有一个，如JDK自带的Runtime类，其构造方式通常是一个private构造函数、static的该类实例、
以及返回该实例的getInstance方法。

那接下来我们就看看实现一个Singleton究竟有哪几种方式。

1. Eager Singleton
```java
public class EagerSingleton {
 
    private static final EagerSingleton INSTANCE = new EagerSingleton();
 
    private EagerSingleton() {
    }
 
    public static EagerSingleton getInstance() {
        return INSTANCE;
    }
}
```


这种称为“饿汉式”的实现方式可能是最简单也最常见的，顾名思义，该实例在类加载的时候就会自动创建不管之后是否被使用。
所以如果该类实例化的开销比较大，这种方式或许就不太理想，不过它的优点也很明显，即无需担心多线程同步获取该实例时可能出现的并发问题。

2. Lazy Singleton

```java
public class LazySingleton {
 
    private volatile static LazySingleton INSTANCE = null;
 
    private LazySingleton() {
    }
 
    public static synchronized LazySingleton getInstance() {
        if (INSTANCE == null)
            INSTANCE = new LazySingleton();
        return INSTANCE;
    }
}
```

这种方式也有个形象的名字“懒汉式”，既然觉得类加载时就完成实例化有点浪费，那不如将这一过程推迟到实际需要使用时，
可是在此值得注意的是为了避免多线程并发场景下可能导致的莫名其妙多创建出一个实例的弊端，getInstance方法必须标记为synchronized方法或采用synchronized代码块来加锁实现。
但是这种过度保护的代价是非常高昂的，其实只有当该实例未被创建时才有必要加锁控制并发，因此更多时候是没必要同步的，此类方式并不经济划算。

3. Lazy Singleton with Double Check

```java
public class LazySingletonWithDoubleCheck {
 
    private volatile static LazySingletonWithDoubleCheck INSTANCE = null;
 
    private LazySingletonWithDoubleCheck() {
    }
 
    public static LazySingletonWithDoubleCheck getInstance() {
        if (INSTANCE == null) {
            synchronized (LazySingletonWithDoubleCheck.class) {
                if (INSTANCE == null)
                    INSTANCE = new LazySingletonWithDoubleCheck();
            }
        }
        return INSTANCE;
    }
}
```

作为Lazy Singleton的改良版，这种采用了double-check的实现方式避免了对getInstance方法总是加锁。注意到尚未实例化时，存在两次检查的流程，
第一次检查如果发现该实例已经存在就可以直接返回，反正则加类锁并进行第二次检查，原因在于可能出现多个线程同时通过了第一次检查，
此时必须通过锁机制实现真正实例化时的排他性，保证只有一个线程成功抢占到锁并执行。此举即保证了线程安全，又将性能折损明显降低了，不失为比较理想的做法。

4. Inner Class Singleton

```java
public class InnerClassSingleton {
 
    private static class SingletonHolder {
        private static final InnerClassSingleton INSTANCE = new InnerClassSingleton();
    }
 
    private InnerClassSingleton() {
    }
 
    public static final InnerClassSingleton getInstance() {
        return SingletonHolder.INSTANCE;
    }
}
```

另外一种可以有效解决线程并发问题并延迟实例化的做法就是如上代码所示的利用静态内部类来实现。单例类的实例被包裹在内部类中，
因此该单例类加载时并不会完成实例化，直到有线程调用getInstance方法，内部类才会被加载并实例化单例类。这种做法应该说是比较令人满意的。

以上就是比较常见的实现Singleton的方式，这也是一般的Java面试所涉及的深度。可是带有好奇心的人不禁会琢磨所谓的单例真的可以保证全局唯一性吗？
能不能采用一些tricky 的方式去破坏这一核心属性呢？这才是本文着重介绍的部分，因为其覆盖了多个重要的知识点。
接下来我们就一起看看通过哪些看似合法的手段可以有效绕开传统Singleton实现中仅靠将构造函数私有化达成的单例从而创建出多个实例。

1. Break Singleton with Clonable

```java
public class ClonableSingleton implements Cloneable{
 
    private static final ClonableSingleton INSTANCE = new ClonableSingleton();
 
    private ClonableSingleton() {
    }
 
    public static ClonableSingleton getInstance() {
        return INSTANCE;
    }
 
    public Object clone() throws CloneNotSupportedException{
        return super.clone();
    }
}
```

没错，第一种比较容易想到的方式就是clone，Java中类通过实现Clonable接口并覆写clone方法就可以完成一个其对象的拷贝。
而当Singleton类为Clonable时也自然无法避免可利用这种方式被重新创建一份实例。通过以下的测试代码即可检验通过clone我们可以有效破坏单例。

```java
public static void checkClone() throws Exception {
    ClonableSingleton a = ClonableSingleton.getInstance();
    ClonableSingleton b = (ClonableSingleton) a.clone();
 
    assertEquals(a, b);
}
```

2. Break Singleton with Serialization

```java
public class SerializableSingleton implements Serializable{
 
    private static final long serialVersionUID = 6789088557981297876L;
 
    private static final SerializableSingleton INSTANCE = new SerializableSingleton();
 
    private SerializableSingleton() {
    }
 
    public static SerializableSingleton getInstance() {
        return INSTANCE;
    }
}
```

第二种破坏方式就是利用序列化与反序列化，当Singleton类实现了Serializable接口就代表它是可以被序列化的，该实例会被保存在文件中，
需要时从该文件中读取并反序列化成 对象。可就是在反序列化这一过程中不知不觉留下了可趁之机，因为默认的反序列化过程是绕开构造函数直接使用
字节生成一个新的对象。于是，Singleton在反序列化时被创造出第二个实例。通过如下代码可轻松实现这一行为，a与b最终并不相等。
```java
public static void checkSerialization() throws Exception {
    File file = new File("serializableSingleton.out");
    ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(
    file));
    SerializableSingleton a = SerializableSingleton.getInstance();
    out.writeObject(a);
    out.close();
 
    ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
    SerializableSingleton b = (SerializableSingleton) in.readObject();
    in.close();
 
    assertEquals(a, b);
}
```

3. Break Singleton with Reflection
```java
public static void checkReflection() throws Exception {
    EagerSingleton a = EagerSingleton.getInstance();
 
    Constructor<EagerSingleton> cons = EagerSingleton.class
        .getDeclaredConstructor();
    cons.setAccessible(true);
    EagerSingleton b = (EagerSingleton) cons.newInstance();
 
    assertEquals(a, b);
}
```

前两种破坏方式说到底都是通过避免与私有构造函数正面冲突的方式另辟蹊径来实现的，而这种方式就显得艺高人胆大，既然你是私有的不允许外界直接调用，
那么我就利用反射机制强行逼你就范：公开其访问权限。如此一来，原本看似安全的堡垒顷刻间沦为炮灰，Singleton再次沦陷。

4. Break Singleton with Classloaders
```java
public static void checkClassloader() throws Exception {
    String className = "fernando.lee.singleton.EagerSingleton";
    ClassLoader classLoader1 = new MyClassloader();
    Class<?> clazz1 = classLoader1.loadClass(className);
 
    ClassLoader classLoader2 = new MyClassloader();
    Class<?> clazz2 = classLoader2.loadClass(className);
 
    System.out.println("classLoader1 = " + clazz1.getClassLoader());
    System.out.println("classLoader2 = " + clazz2.getClassLoader());
 
    Method getInstance1 = clazz1.getDeclaredMethod("getInstance");
    Method getInstance2 = clazz2.getDeclaredMethod("getInstance");
    Object a = getInstance1.invoke(null);
    Object b = getInstance2.invoke(null);
 
    assertEquals(a, b);
}
```

Java中一个类并不是单纯依靠其全包类名来标识的，而是全包类名加上加载它的类加载器共同确定的。因此，
只要是用不同的类加载器加载的Singleton类并不认为是相同的，因此单例会再次被破坏，通过自定义编写的MyClassLoader即可实现。

由此看来，Singleton唯有妥善关闭了如上所述的诸多后门才能称得上真正的单例。笔者了解到通常有两种应对措施：现有基础上堵住所有漏洞和摈弃旧貌采取创新。
简而言之，第一种方式通过完善现有实现让克隆、序列化、反射和类加载器无从下手，第二种方式则采取枚举类型间接实现单例。

1. Safe Singleton
```java
public class SafeSingleton implements Serializable, Cloneable {
 
    private static final long serialVersionUID = -4147288492005226212L;
 
    private static SafeSingleton INSTANCE = new SafeSingleton();
 
    private SafeSingleton() {
        if (INSTANCE != null) {
            throw new IllegalStateException("Singleton instance Already created.");
        }
    }
 
    public static SafeSingleton getInstance() {
        return INSTANCE;
    }
 
    private Object readResolve() throws ObjectStreamException {
        return INSTANCE;
    }
 
    public Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException("Singleton can't be cloned");
    }
}
```

在原有Singleton的基础上完善若干方法即可实现一个安全的更为纯正的Singleton。
注意到当实例已经存在时试图通过调用私有构造函数会直接报错从而抵御了反射机制的入侵； 让调用clone方法直接报错避免了实例被克隆；
覆写readReslove方法直接返回现有的实例本身可以防止反序列化过程中生成新的实例。而对于不同类加载器导致的单例模式破坏笔者暂 
未亲测出切实可行的应对方案，还烦请大牛提供高见。

2. Enum Singleton
```java
public enum EnumSingleton{
    INSTANCE;
 
    private EnumSingleton(){
    }
}
```

采用枚举的方式实现Singleton非常简易，而且可直接通过EnumSingleton.INSTANCE获取该实例。
Java中所有定义为enum的类内部都继承了Enum类，而Enum具备的特性包括类加载是静态的来保证线程安全，
而且其中的clone方法是final的且直接抛出CloneNotSupportedException异常因而不允许拷贝，
同时与生俱来的序列化机制也是直接由JVM掌控的并不会创建出新的实例，此外Enum不能被显式实例化反射破坏也不起作用。
当然它也不是没有缺点，比如由于已经隐式继承Enum所以无法再继承其他类了（Java的单继承模式限制），
而且相信大多数人并不乐意仅仅为了实现一个纯正的Singleton就将更为习惯的class修改为enum。


##Reference
