package company.DAO;

import company.Hibernate.SessionUtil;
import company.entity.Department;
import org.hibernate.Session;

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
    public void remove(Department department) {
        openTransactionSession();

        session = getSession();
        session.remove(department);

        closeTransactionSession();
    }

    @Override
    public void update(Department department) {
        openTransactionSession();

        session = getSession();
        session.update(department);

        closeTransactionSession();
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
}
