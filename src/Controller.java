import javafx.event.ActionEvent;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public interface Controller {
    public void setMainController(MainController mainController);
    public void cancel(ActionEvent e);
    public TextField getFirstNameTextField();
    public TextField getLastNameTextField();
    public TextField getEmailTextField();
    public DatePicker getBirthDatePicker();
    public TextField getAddressTextField();
}
