package company.DAO;

import company.Hibernate.SessionUtil;
import company.entity.Employee;
import company.entity.Holiday;
import org.hibernate.Session;

import java.util.List;
import java.util.stream.Collectors;

public class HolidayDAO extends SessionUtil implements IDAO<Holiday> {

    private Session session;

    @Override
    public void add(Holiday holiday) {
        openTransactionSession();

        session = getSession();
        session.save(holiday);

        closeTransactionSesstion();
    }

    @Override
    public void remove(Holiday holiday) {
        openTransactionSession();

        session = getSession();
        session.remove(holiday);

        closeTransactionSesstion();
    }

    @Override
    public void update(Holiday holiday) {
        openTransactionSession();

        session = getSession();
        session.update(holiday);

        closeTransactionSesstion();
    }

    @Override
    public Holiday getById(int id) {

        openTransactionSession();

        session = getSession();
        Holiday holiday = session.get(Holiday.class, id);

        closeTransactionSesstion();

        return holiday;
    }

    @Override
    public List<Holiday> getList() {
        openTransactionSession();

        session = getSession();

        List<Holiday> list = session.createQuery("SELECT * FROM holiday", Holiday.class).list();

        closeTransactionSesstion();

        return list;
    }

    public List<Holiday> getListByEmployee(Employee employee) {

        List<Holiday> list = getList().stream()
                .filter(h -> h.getEmployee().getId() == employee.getId())
                .collect(Collectors.toList());
        closeTransactionSesstion();

        return list;
    }
}
