package proxy.JDKProxy;

public class proxyUserDao implements IUserDao {
    @Override
    public void save() {
        System.out.println("proxy-----");
    }
}
