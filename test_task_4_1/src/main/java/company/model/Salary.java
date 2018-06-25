package company.model;

import javax.persistence.*;

@Entity
@Table(name = "salary")
public class Salary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "quantity", nullable = false)
    private int quantity;

    public Salary() {
    }

    public Salary(int quantity) {
        setQuantity(quantity);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Invalid salary quantity: non-positive, quantity: " + quantity);
        }
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Salary{" +
                "id=" + id +
                ", quantity=" + quantity +
                '}';
    }
}
