package enums;

public class Install {

    private String user;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public static void main(String[] args) {
        Install connection = EnumSingleIntall.InstallSingle.getConnection();
        Install connection1 = EnumSingleIntall.InstallSingle.getConnection();
        Install connection2 = EnumSingleIntall.InstallSingle.getConnection();
        Install connection3 = EnumSingleIntall.InstallSingle.getConnection();
    }
}
