package company.DAO;

import company.Hibernate.SessionUtil;
import company.model.Department;
import company.model.Employee;
import company.model.Holiday;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;
import java.util.stream.Collectors;

public class HolidayDAO extends SessionUtil implements IDAO<Holiday> {

    private Session session;

    @Override
    public void add(Holiday holiday) {
        openTransactionSession();

        session = getSession();
        session.save(holiday);

        closeTransactionSession();
    }

    public void remove(int id) {
        openTransactionSession();

        session = getSession();

        Query query = session.createNativeQuery("DELETE from holiday WHERE id = ?1");
        query.setParameter(1, id);
        query.executeUpdate();

        closeTransactionSession();
    }

    @Override
    public void update(Holiday holiday) {
        openTransactionSession();

        session = getSession();
        session.update(holiday);

        closeTransactionSession();
    }

    @Override
    public Holiday getById(int id) {

        openTransactionSession();

        session = getSession();
        Holiday holiday = session.get(Holiday.class, id);

        closeTransactionSession();

        return holiday;
    }

    @Override
    public List<Holiday> getList() {
        openTransactionSession();

        session = getSession();

        List<Holiday> list = session.createNativeQuery("SELECT * from Holiday", Holiday.class).list();

        closeTransactionSession();

        return list;
    }

    public List<Holiday> getListByEmployee(Employee employee) {
        openTransactionSession();

        String sql = "SELECT * FROM holiday WHERE employee_id = ?1";
        session = getSession();

        Query query = session.createNativeQuery(sql, Holiday.class);
        query.setParameter(1, employee.getId());

        List<Holiday> list = query.getResultList();

        closeTransactionSession();

        return list;
    }

    public List<Holiday> getListByDepartment(Department department) {

        return getList().stream()
                .filter(h -> h.getEmployee().getDepartment().getId() == department.getId())
                .collect(Collectors.toList());
    }

    public void removeByEmployeeId(int employeeId) {
        openTransactionSession();

        session = getSession();

        Query query = session.createNativeQuery("DELETE from holiday WHERE employee_id = ?1");
        query.setParameter(1, employeeId);
        query.executeUpdate();

        closeTransactionSession();
    }
}
