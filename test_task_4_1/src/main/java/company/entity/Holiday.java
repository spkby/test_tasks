package company.entity;

import javax.persistence.*;
import java.sql.Date;
import java.util.Set;

@Entity
@Table(name = "holiday")
public class Holiday {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "date_to", nullable = false)
    private Date dateTo;

    @Column(name = "date_from", nullable = false)
    private Date dateFrom;

    /*@ManyToMany(mappedBy = "holidays")
    private Set<Employee> employees;*/

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Employee employee;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Status status;

    public Holiday() {
    }

    public Holiday(Date dateTo, Date dateFrom, Employee employee, Status status) {
        this.dateTo = dateTo;
        this.dateFrom = dateFrom;
        this.employee = employee;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDateTo() {
        return dateTo;
    }

    public void setDateTo(Date dateTo) {
        this.dateTo = dateTo;
    }

    public Date getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
