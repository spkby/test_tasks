package company.DAO;

import company.Hibernate.SessionUtil;
import company.model.Role;
import org.hibernate.Session;

import java.util.List;

public class RoleDAO extends SessionUtil implements IDAO<Role> {

    private Session session;

    @Override
    public void add(Role role) {
        openTransactionSession();

        session = getSession();
        session.save(role);

        closeTransactionSession();
    }

    @Override
    public void remove(Role role) {
        openTransactionSession();

        session = getSession();
        session.remove(role);

        closeTransactionSession();
    }

    @Override
    public void update(Role role) {
        openTransactionSession();

        session = getSession();
        session.update(role);

        closeTransactionSession();
    }

    @Override
    public Role getById(int id) {

        openTransactionSession();

        session = getSession();
        Role role = session.get(Role.class, id);

        closeTransactionSession();

        return role;
    }

    @Override
    public List<Role> getList() {
        openTransactionSession();

        session = getSession();

        List<Role> list = session.createNativeQuery("SELECT * from Role", Role.class).list();

        closeTransactionSession();

        return list;
    }
}
