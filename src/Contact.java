import java.time.LocalDate;
import java.time.Period;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

public class Contact {
    private SimpleStringProperty firstName, lastName, email, address;
    private SimpleObjectProperty<LocalDate> birth;
    private int age;

    public Contact(String firstName, String lastName, String email, LocalDate birth, String address) {
        this.firstName = new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);
        this.email = new SimpleStringProperty(email);
        this.birth = new SimpleObjectProperty<LocalDate>(birth);
        this.address = new SimpleStringProperty(address);
        if (birth != null) {
            this.age = calculateAge(birth);
        }
    }

    public String getFirstName() {
        return firstName.get();
    }

    public String getLastName() {
        return lastName.get();
    }

    public String getEmail() {
        return email.get();
    }

    public LocalDate getBirth() {
        return birth.get();
    }

    public String getAddress() {
        return address.get();
    }

    public int getAge() {
        return age;
    }

    public void setAge() {
        this.age = calculateAge(this.getBirth());
    }

    public void setFirstName(String firstName) {
        this.firstName = new SimpleStringProperty(firstName);
    }

    public void setLastName(String lastName) {
        this.lastName = new SimpleStringProperty(lastName);
    }

    public void setEmail(String email) {
        this.email = new SimpleStringProperty(email);
    }

    public void setBirth(LocalDate birth) {
        this.birth = new SimpleObjectProperty<LocalDate>(birth);
    }

    public void setAddress(String address) {
        this.address = new SimpleStringProperty(address);
    }

    public int calculateAge(LocalDate date) {
        LocalDate now = LocalDate.now();
        return Period.between(date, now).getYears();
    }

}
