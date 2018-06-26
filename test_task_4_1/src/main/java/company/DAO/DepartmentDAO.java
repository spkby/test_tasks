package company.DAO;

import company.Hibernate.SessionUtil;
import company.model.Account;
import company.model.Department;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class DepartmentDAO extends SessionUtil implements IDAO<Department> {

    private Session session;

    @Override
    public void add(Department department) {
        openTransactionSession();

        session = getSession();
        session.save(department);

        closeTransactionSession();
    }

    @Override
    public void remove(int id) {
        throw new IllegalStateException("no method");
    }

    @Override
    public void update(Department department) {
        throw new IllegalStateException("no method");
    }

    @Override
    public Department getById(int id) {

        openTransactionSession();

        session = getSession();
        Department department = session.get(Department.class, id);

        closeTransactionSession();

        return department;
    }

    @Override
    public List<Department> getList() {
        openTransactionSession();

        session = getSession();

        List<Department> list = session.createNativeQuery("SELECT * FROM Department", Department.class).list();

        closeTransactionSession();

        return list;
    }

    public Department getByName(String name) {

        openTransactionSession();

        session = getSession();

        Query query = session.createNativeQuery("SELECT * from Department WHERE name = ?1", Department.class);
        query.setParameter(1, name);
        List<Department> list = query.getResultList();

        closeTransactionSession();

        return list.size() == 0 ? null : list.get(0);
    }


}
