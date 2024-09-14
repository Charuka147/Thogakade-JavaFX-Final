package controller.Customer;

import com.jfoenix.controls.JFXTextField;
import controller.Customer.CustomerController;
import controller.Customer.CustomerService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Customer;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class CustomerFormController implements Initializable {

    public JFXTextField txtId;
    public JFXTextField txtAddress;
    public JFXTextField txtName;
    public ComboBox cmbTitle;
    public DatePicker dateDob;
    public TableColumn colSalary;
    public TableColumn colCity;
    public TableColumn colProvince;
    public TableColumn colPostalCode;
    public JFXTextField txtSalary;
    public JFXTextField txtCity;
    public JFXTextField txtProvince;
    public JFXTextField txtPostalCode;
    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colDob;

    @FXML
    private TableColumn<?, ?> colID;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableView<Customer> tblCustomer;

     CustomerService service = CustomerController.getInstance();

    @FXML
    void btnRelaodOnAction(ActionEvent event) {
        loadTable();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colDob.setCellValueFactory(new PropertyValueFactory<>("birthday"));
        colSalary.setCellValueFactory(new PropertyValueFactory<>("salary"));
        colCity.setCellValueFactory(new PropertyValueFactory<>("city"));
        colProvince.setCellValueFactory(new PropertyValueFactory<>("province"));
        colPostalCode.setCellValueFactory(new PropertyValueFactory<>("postalCode"));
        ObservableList<Customer> customerObservableList = FXCollections.observableArrayList();

        ObservableList<String> titles = FXCollections.observableArrayList();
        titles.add("MR.");
        titles.add("MISS");
        titles.add("MRS");
        cmbTitle.setItems(titles);

        tblCustomer.getSelectionModel().selectedItemProperty().addListener(((observableValue, oldValue, newValue) -> {
            setTextToValues(newValue);
        }));
        loadTable();

    }

    private void setTextToValues(Customer newValue) {
        txtId.setText(newValue.getId());
        txtName.setText(newValue.getName());
        txtAddress.setText(newValue.getAddress());
        cmbTitle.setValue(newValue.getTitle());
        dateDob.setValue(newValue.getBirthday());
        txtSalary.setText(String.valueOf(newValue.getSalary()));
        txtCity.setText(newValue.getCity());
        txtProvince.setText(newValue.getProvince());
        txtPostalCode.setText(newValue.getPostalCode());

    }

    public void btnAddOnAction(ActionEvent actionEvent) {
        Customer customer = new Customer(
                txtId.getText(),
                cmbTitle.getValue().toString(),
                txtName.getText(),
                dateDob.getValue(),
                Double.parseDouble(txtSalary.getText()),
                txtAddress.getText(),
                txtCity.getText(),
                txtProvince.getText(),
                txtPostalCode.getText()
        );
        if (service.addCustomer(customer)){
            new Alert(Alert.AlertType.INFORMATION,"Customer Added!!!").show();
        }else{
            new Alert(Alert.AlertType.ERROR,"Customer Not Found!!").show();
        }
   }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
        if (service.deleteCustomer(txtId.getText())){
            new Alert(Alert.AlertType.INFORMATION,"Customer Deleted").show();
        }else{
            new Alert(Alert.AlertType.ERROR).show();
        }

    }

    public void btnSearchOnAction(ActionEvent actionEvent) {
    }

    private void loadTable() {
        ObservableList<Customer> customerObservableList = service.getAll();
        tblCustomer.setItems(customerObservableList);
    }
}
