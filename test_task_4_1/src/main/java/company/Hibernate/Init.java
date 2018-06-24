package company.Hibernate;

import company.entity.Department;
import company.entity.Role;
import company.entity.Status;
import org.hibernate.Session;

public class Init {

    public static void main(String[] args) {

        fillConstantData();
    }

    public static void fillConstantData() {

        try {
            Session session = HibernateUtil.getSessionFactory().openSession();

            session.beginTransaction();

            Status status = new Status();
            status.setName("pending");
            session.save(status);

            status = new Status();
            status.setName("accepted");
            session.save(status);

            status = new Status();
            status.setName("denied");
            session.save(status);

            Role role = new Role();
            role.setName("regular");
            session.save(role);

            role = new Role();
            role.setName("lead");
            session.save(role);

            role = new Role();
            role.setName("manager");
            session.save(role);

            Department department = new Department();
            department.setName("Management");
            session.save(department);

            department = new Department();
            department.setName("Finance");
            session.save(department);

            department = new Department();
            department.setName("IT");
            session.save(department);

            System.out.println(session.get(Role.class, 1));
            System.out.println(session.get(Role.class, 4));

            session.getTransaction().commit();

        } finally {
            HibernateUtil.shutdown();
        }
    }
}