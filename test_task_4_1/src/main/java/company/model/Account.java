package company.model;

import javax.persistence.*;

@Entity
@Table(name = "account")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "login", length = 20, nullable = false, unique = true)
    private String login;

    @Column(name = "pass", length = 20, nullable = false)
    private String pass;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Employee employee;

    public Account() {
    }

    public Account(String login, String pass, Employee employee) {
        setLogin(login);
        setPass(pass);
        setEmployee(employee);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        if (login.isEmpty()) {
            throw new IllegalArgumentException("Invalid account login: empty");
        }
        if (login.length() > 20) {
            throw new IllegalArgumentException("Invalid account login: length more 20");
        }
        this.login = login;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        if (pass.isEmpty()) {
            throw new IllegalArgumentException("Invalid account pass: empty");
        }
        if (pass.length() > 20) {
            throw new IllegalArgumentException("Invalid account pass: length more 20");
        }
        this.pass = pass;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        if (employee == null) {
            throw new IllegalArgumentException("Invalid account employee: null");
        }
        this.employee = employee;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", pass='" + pass + '\'' +
                ", employee=" + employee +
                '}';
    }
}
