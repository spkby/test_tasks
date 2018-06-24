package company.DAO;

import company.Hibernate.SessionUtil;
import company.entity.Account;
import company.entity.Employee;
import org.hibernate.Session;

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

    public Account getAccountByEmployee(Employee employee){

        return getList().stream()
                .filter(s -> s.getEmployee().getId() == employee.getId())
                .limit(1)
                .collect(Collectors.toList())
                .get(0);
    }

    public Employee getEmployeeByAccount(Account account){

        return getById(account.getId()).getEmployee();
    }
}
