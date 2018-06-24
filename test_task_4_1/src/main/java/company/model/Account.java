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
        this.login = login;
        this.pass = pass;
        this.employee = employee;
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
        this.login = login;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
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
