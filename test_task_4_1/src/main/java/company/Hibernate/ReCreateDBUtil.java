package company.Hibernate;

public class ReCreateDBUtil {

    public static void main(String[] args) {
        CreateDBUtil.main(new String[]{"hibernate-create_drop.cfg.xml"});
        CreateDBUtil.main(new String[]{"hibernate-create.cfg.xml"});
    }
}