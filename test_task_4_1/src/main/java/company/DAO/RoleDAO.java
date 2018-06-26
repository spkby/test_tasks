package company.DAO;

import company.Hibernate.SessionUtil;
import company.model.Role;
import org.hibernate.Session;
import org.hibernate.query.Query;

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
    public void remove(int id) {
        throw new IllegalStateException("no method");
    }

    @Override
    public void update(Role role) {
        throw new IllegalStateException("no method");
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

    public Role getByName(String name) {

        openTransactionSession();

        session = getSession();

        Query query = session.createNativeQuery("SELECT * from role WHERE name = ?1", Role.class);
        query.setParameter(1, name);
        List<Role> list = query.getResultList();

        closeTransactionSession();

        return list.size() == 0 ? null : list.get(0);
    }
}
