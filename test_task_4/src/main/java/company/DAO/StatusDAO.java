package company.DAO;

import company.Hibernate.SessionUtil;
import company.model.Status;
import org.hibernate.Session;

import java.util.List;

public class StatusDAO extends SessionUtil implements IDAO<Status> {

    private Session session;

    @Override
    public void add(Status status) {
        openTransactionSession();

        session = getSession();
        session.save(status);

        closeTransactionSession();
    }

    @Override
    public void remove(int id) {
        throw new IllegalStateException("no method");
    }

    @Override
    public void update(Status status) {
        throw new IllegalStateException("no method");
    }

    @Override
    public Status getById(int id) {

        openTransactionSession();

        session = getSession();
        Status status = session.get(Status.class, id);

        closeTransactionSession();

        return status;
    }

    @Override
    public List<Status> getList() {
        openTransactionSession();

        session = getSession();

        List<Status> list = session.createNativeQuery("SELECT * FROM Status", Status.class).list();

        closeTransactionSession();

        return list;
    }
}
