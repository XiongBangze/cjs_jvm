package proxy.JDKProxy;


/**
 * 实现接口
 * 目标对象
 */
public class UserDao implements IUserDao {

    public void save() {
        System.out.println("----保存数据成功!----");
    }

}