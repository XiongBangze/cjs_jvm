package enums;

public enum EnumSingleIntall {
    InstallSingle;

    private static Install  install= new Install();

    public Install getConnection() {
        return install;
    }
}


