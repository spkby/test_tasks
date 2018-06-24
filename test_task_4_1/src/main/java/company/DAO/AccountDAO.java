package company.DAO;

import company.Hibernate.SessionUtil;
import company.model.Account;
import company.model.Employee;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;
import java.util.stream.Collectors;

public class AccountDAO extends SessionUtil implements IDAO<Account> {

    private Session session;

    @Override
    public void add(Account account) {
        openTransactionSession();

        session = getSession();
        session.save(account);

        closeTransactionSession();
    }

    @Override
    public void remove(Account account) {
        openTransactionSession();

        session = getSession();
        session.remove(account);

        closeTransactionSession();
    }

    @Override
    public void update(Account account) {
        openTransactionSession();

        session = getSession();
        session.update(account);

        closeTransactionSession();
    }

    @Override
    public Account getById(int id) {

        openTransactionSession();

        session = getSession();
        Account account = session.get(Account.class, id);

        closeTransactionSession();

        return account;
    }

    @Override
    public List<Account> getList() {
        openTransactionSession();

        session = getSession();

        List<Account> list = session.createNativeQuery("SELECT * from account", Account.class).list();

        closeTransactionSession();

        return list;
    }

    public Account getAccountByEmployee(Employee employee) {

        openTransactionSession();

        session = getSession();

        Query query = session.createNativeQuery("SELECT * from account WHERE employee_id = ?1", Account.class);
        query.setParameter(1, employee.getId());
        List<Account> list = query.getResultList();

        closeTransactionSession();

        return list.size() == 0 ? null : list.get(0);
    }

    public Employee getEmployeeByAccount(Account account) {

        return getById(account.getId()).getEmployee();
    }

    public Account getAccountByLogin(String login) {
        openTransactionSession();

        session = getSession();

        Query query = session.createNativeQuery("SELECT * from account WHERE login = ?1", Account.class);
        query.setParameter(1, login);
        List<Account> list = query.getResultList();

        closeTransactionSession();

        return list.size() == 0 ? null : list.get(0);
    }
}
