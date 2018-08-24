package proxy.CglibProxy;

public class AppTest {


    public static void main(String[] args) {
        //目标对象
        UserDao target = new UserDao();

        //代理对象
        UserDao proxy = (UserDao)new CglibProxyFactory(target).getProxyInstance();

        //执行代理对象的方法
        proxy.save();
    }
}
