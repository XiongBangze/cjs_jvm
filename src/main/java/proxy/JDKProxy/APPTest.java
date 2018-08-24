package proxy.JDKProxy;

public class APPTest {

    public static void main(String[] args) {
        // 目标对象
        IUserDao target = new proxyUserDao();
        // 【原始的类型 class com.zhong.UserDao】
        System.out.println(target.getClass());

        // 给目标对象，创建代理对象
        IUserDao proxy = (IUserDao) new ProxyFactory(target).getProxyInstance();
        // class $Proxy0   内存中动态生成的代理对象
        System.out.println(proxy.getClass());

        // 执行方法   【代理对象】
        proxy.save();
    }
}
