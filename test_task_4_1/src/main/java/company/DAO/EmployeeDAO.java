package company.DAO;

import company.Hibernate.SessionUtil;
import company.model.Employee;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class EmployeeDAO extends SessionUtil implements IDAO<Employee> {

    private Session session;

    @Override
    public void add(Employee employee) {
        openTransactionSession();

        session = getSession();
        session.save(employee);

        closeTransactionSession();
    }

    @Override
    public void remove(int id) {
        openTransactionSession();

        session = getSession();

        Query query = session.createNativeQuery("DELETE from employee WHERE id = ?1");
        query.setParameter(1, id);
        query.executeUpdate();

        closeTransactionSession();
    }

    @Override
    public void update(Employee employee) {
        openTransactionSession();

        session = getSession();

        Query query = session.createNativeQuery("UPDATE employee SET name = ?1, " +
                "birthday = ?2, department_id = ?3, role_id = ?4 WHERE id = ?5");
        query.setParameter(1, employee.getName());
        query.setParameter(2, employee.getBirthday());
        query.setParameter(3, employee.getDepartment().getId());
        query.setParameter(4, employee.getRole().getId());
        query.setParameter(5, employee.getId());
        query.executeUpdate();

        closeTransactionSession();
    }

    @Override
    public Employee getById(int id) {

        openTransactionSession();

        session = getSession();
        Employee employee = session.get(Employee.class, id);

        closeTransactionSession();

        return employee;
    }

    @Override
    public List<Employee> getList() {
        openTransactionSession();

        session = getSession();

        List<Employee> list = session.createNativeQuery("SELECT * from Employee", Employee.class).list();

        closeTransactionSession();

        return list;
    }

    public List<Employee> getListByDepartment(int id) {
        openTransactionSession();

        String sql = "SELECT * FROM employee WHERE department_id = ?1";
        session = getSession();

        Query query = session.createNativeQuery(sql, Employee.class);
        query.setParameter(1, id);

        List<Employee> list = query.getResultList();

        closeTransactionSession();

        return list;
    }

    public int getCountByRole(int id) {
        openTransactionSession();

        String sql = "SELECT * FROM employee WHERE role_id = ?1";
        session = getSession();

        Query query = session.createNativeQuery(sql, Employee.class);
        query.setParameter(1, id);

        List<Employee> list = query.getResultList();

        closeTransactionSession();

        return list.size();
    }
}
