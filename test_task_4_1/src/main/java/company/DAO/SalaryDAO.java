package company.DAO;

import company.Hibernate.SessionUtil;
import company.entity.Salary;
import org.hibernate.Session;

import java.util.List;

public class SalaryDAO extends SessionUtil implements IDAO<Salary> {

    private Session session;

    @Override
    public void add(Salary salary) {
        openTransactionSession();

        session = getSession();
        session.save(salary);

        closeTransactionSesstion();
    }

    @Override
    public void remove(Salary salary) {
        openTransactionSession();

        session = getSession();
        session.remove(salary);

        closeTransactionSesstion();
    }

    @Override
    public void update(Salary salary) {
        openTransactionSession();

        session = getSession();
        session.update(salary);

        closeTransactionSesstion();
    }

    @Override
    public Salary getById(int id) {

        openTransactionSession();

        session = getSession();
        Salary salary = session.get(Salary.class, id);

        closeTransactionSesstion();

        return salary;
    }

    @Override
    public List<Salary> getList() {
        openTransactionSession();

        session = getSession();

        List<Salary> list = session.createQuery("SELECT * FROM salary", Salary.class).list();

        closeTransactionSesstion();

        return list;
    }
}
