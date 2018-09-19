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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;


public class MainPageController implements Initializable {
    // home tab
    @FXML private Label showUserLogin;
    @FXML private Label balanceLabelHomePage;
    @FXML private Button logoutBtnHome;
    @FXML private Label balanceLabelListPage;
    @FXML private Label balanceLabelAddPage;

    // add tab function

    //@FXML private ChoiceBox<String> categories;
    @FXML private TextField categories;
    @FXML private TextField detailLabel;
    @FXML private TextField amountLabel;
    @FXML private DatePicker dateLabel;
    @FXML private TextField kindOfExpense;
    @FXML private Button addBtn;

    // list tab
    @FXML private TableView<ExpenseList> expenseListTable;
    @FXML private TableColumn<ExpenseList, String> date;
    @FXML private TableColumn<ExpenseList, String> type;
    @FXML private TableColumn<ExpenseList, String> details;
    @FXML private TableColumn<ExpenseList, Double> amount;
    @FXML private TableColumn<ExpenseList, String> chooseType;


    private Users users = new Users("1" ,"6499", "Thikamporn", 200.0);
    private ObservableList<ExpenseList> list = FXCollections.observableArrayList();

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
    void handleAddBtn(ActionEvent event) {
        //String out = categories.getValue();
        list.add(new ExpenseList(getDate(), categories.getText(), detailLabel.getText(), Double.parseDouble(amountLabel.getText()), kindOfExpense.getText()));
        if (kindOfExpense.getText().equals("In"))users.addIncome(Double.parseDouble(amountLabel.getText()));
        else if (kindOfExpense.getText().equals("Ex"))users.addExpense(Double.parseDouble(amountLabel.getText()));
        setBalanceAllPage(users.getUserBalance());
        clearData();

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        showUserLogin.setText(users.getName().toUpperCase());
        setBalanceAllPage(users.getUserBalance());


        /*ObservableList<String> choices = FXCollections.observableArrayList("Food & Drink", "Groceries","Home", "Bill & Free", "Transport", "Shopping","Entertainment");
        categories.getSelectionModel().selectFirst();
        categories.setValue("Food & Drink");
        categories.getItems().addAll(choices);*/

        date.setCellValueFactory(new PropertyValueFactory<>("Date"));
        type.setCellValueFactory(new PropertyValueFactory<>("Categories"));
        details.setCellValueFactory(new PropertyValueFactory<>("Detail"));
        amount.setCellValueFactory(new PropertyValueFactory<>("Amount"));
        chooseType.setCellValueFactory(new PropertyValueFactory<>("In/Ex"));

        expenseListTable.setItems(list);
        expenseListTable.setEditable(true);


    }

    private void setBalanceAllPage(double currentBalance){
        balanceLabelHomePage.setText("TH" + currentBalance);
        balanceLabelAddPage.setText("TH" + currentBalance);
        balanceLabelListPage.setText("TH" + currentBalance);
    }

    private String getDate(){
        LocalDate localDate = dateLabel.getValue();
        return localDate.toString();
    }

    private void clearData(){
        //categories.setValue("Food & Drink");
        categories.clear();
        kindOfExpense.clear();
        detailLabel.clear();
        amountLabel.clear();
    }

}

