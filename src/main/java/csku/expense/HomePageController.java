package csku.expense;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class HomePageController implements Initializable {

    @FXML private Label showUserLogin;
    @FXML private Label balanceLabelHomePage;
    @FXML private Button logoutBtnHome;
    @FXML private Label balanceLabelListPage;
    @FXML private Label balanceLabelAddPage;

    // add tab function
    private ObservableList<String> choices = FXCollections.observableArrayList("Food & Drink", "Groceries","Home", "Bill & Free", "Transport", "Shopping","Entertainment");
    @FXML private ChoiceBox<String> catagories;
    @FXML private TextField detailLabel;
    @FXML private TextField amountLabel;
    @FXML private DatePicker dateLabel;
    @FXML private Button addIncomeBtn;
    @FXML private Button addExpenseBtn;

    // list tab
    @FXML private TableView<ExpenseList> tableIncome;
    @FXML private TableView<?> tableExpense;



    Users users = new Users(1 ,"6499", "Thikamporn", 200.0);


    @FXML
    void handleLogoutBtnHomeTab(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        logoutBtnHome.getScene().getWindow().hide();
        Stage loginWindow = new Stage();
        Parent root = loader.load(getClass().getResource("/loginPage.fxml"));
        Scene scene = new Scene(root);
        loginWindow.setScene(scene);
        loginWindow.show();
        loginWindow.setResizable(true);
    }


    @FXML
    void handleAddExpenseBtn(ActionEvent event) {

    }

    @FXML
    void handleAddIncomeBtn(ActionEvent event) {
        setDetail();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        showUserLogin.setText(users.getName().toUpperCase());
        setBalanceAllPage(users.getUserBalance());
        catagories.setValue("Food & Drink");
        catagories.setItems(choices);



    }

    private void setBalanceAllPage(double currentBalance){
        balanceLabelHomePage.setText("TH" + currentBalance);
        balanceLabelAddPage.setText("TH" + currentBalance);
        balanceLabelListPage.setText("TH" + currentBalance);
    }

    private void setDetail(){
       /* catagories.getValue();
        detailLabel.getText();
        amountLabel.getText();*/
        showDate();

    }

    private void showDate(){
        LocalDate localDate = dateLabel.getValue();
        System.out.println(localDate.toString());
    }

}
