import javafx.beans.property.SimpleStringProperty;

public class Contact {
    private SimpleStringProperty firstName, lastName, email, birth, address;
    private int age;

    public Contact(String firstName, String lastName, String email, String birth, String address) {
        this.firstName = new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);
        this.email = new SimpleStringProperty(email);
        this.birth = new SimpleStringProperty(birth);
        this.address = new SimpleStringProperty(address);
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

    public String getBirth() {
        return birth.get();
    }

    public String getAddress() {
        return address.get();
    }

    public int getAge() {
        return age;
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

    public void setBirth(String birth) {
        this.birth = new SimpleStringProperty(birth);
    }

    public void setAddress(String address) {
        this.address = new SimpleStringProperty(address);
    }

    private Integer calculateAge(Integer age) {
        return age;
    }

    
}
