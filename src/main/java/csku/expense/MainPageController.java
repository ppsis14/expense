package csku.expense;

import com.jfoenix.controls.JFXButton;
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
import java.io.*;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import static java.lang.Double.parseDouble;

public class MainPageController implements Initializable {
    // home tab
    @FXML private Label showUserLogin;
    @FXML private JFXButton logoutBtn;
    @FXML private Label balanceLabelAddPage;

    // add tab function
    @FXML private ChoiceBox<String> categories;
    @FXML private ChoiceBox<String> typeOfExpense;
    @FXML private TextField detailLabel;
    @FXML private TextField amountLabel;
    @FXML private DatePicker dateLabel;
    @FXML private JFXButton addBtn;
    @FXML private JFXButton updateBtn;


    // list tab
    @FXML private TableView<ExpenseList> expenseListTable;
    @FXML private TableColumn<ExpenseList, String> date = new TableColumn<>("Date");
    @FXML private TableColumn<ExpenseList, String> type = new TableColumn<>("Categories");
    @FXML private TableColumn<ExpenseList, String> details = new TableColumn<>("Detail");
    @FXML private TableColumn<ExpenseList, Double> amount = new TableColumn<>("Amount");
    @FXML private TableColumn<ExpenseList, String> chooseType = new TableColumn<>("In/Ex");
    @FXML private JFXButton editBtn;
    @FXML private JFXButton saveBtn;
    @FXML private JFXButton showBtn;
    @FXML private ComboBox<String> totalByTypeOfExpense;
    @FXML private Label showTotal;



    private Users users = new Users("1" ,"6499", "Thikamporn", 200.0);
    private DataAccessor dataAccessor;
    private ObservableList<ExpenseList> list = FXCollections.observableArrayList();

    public void setDataAccessor(DataAccessor dataAccessor) {
        this.dataAccessor = dataAccessor;
    }

    public ExpenseList selectObject(){
        return expenseListTable.getSelectionModel().getSelectedItem();
    }

    public int positionSelected(){
        return expenseListTable.getSelectionModel().getSelectedIndex();
    }

    @FXML
    public void handleEditBtn(ActionEvent event) {
        expenseListTable.setEditable(true);
        System.out.println(positionSelected());
        ExpenseList editExpense = selectObject();
        dateLabel.setValue(LocalDate.parse(editExpense.getDate()));
        categories.setValue(editExpense.getCategory());
        detailLabel.setText(editExpense.getDetail());
        amountLabel.setText(String.valueOf(editExpense.getAmount()));
        typeOfExpense.setValue(editExpense.getType());

    }

    // update data after edit in table
    @FXML
    public void handleUpdateBtn(ActionEvent event) {
        list.set(positionSelected(), new ExpenseList(getDate(), categories.getValue(), detailLabel.getText(), parseDouble(amountLabel.getText()), typeOfExpense.getValue()));

        expenseListTable.setEditable(false);
    }

    // save data to file of database
    @FXML
    public void handleSaveBtn(ActionEvent event) {
        dataAccessor.storeDataTo();
    }

    @FXML
    public void handleLogoutBtnHomeTab(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        logoutBtn.getScene().getWindow().hide();
        Stage loginWindow = new Stage();
        Parent root = loader.load(getClass().getResource("/loginPage.fxml"));
        Scene scene = new Scene(root);
        loginWindow.setScene(scene);
        loginWindow.show();
        loginWindow.setResizable(true);
    }

    // show data that store in from database or file
    @FXML
    public void handleShowDataFormTextBtn(ActionEvent event) {
        dataAccessor.loadDataFrom();
    }

    // add expense list to table
    @FXML
    public void handleAddBtn(ActionEvent event) {
        list.add(new ExpenseList(getDate(), categories.getValue(), detailLabel.getText(), parseDouble(amountLabel.getText()), typeOfExpense.getValue()));
        if (typeOfExpense.getValue().equals("Income"))users.addIncome(parseDouble(amountLabel.getText()));
        else if (typeOfExpense.getValue().equals("Expense"))users.addExpense(parseDouble(amountLabel.getText()));
        setBalanceAllPage(users.getUserBalance());
        clearData();

    }

    // show total income or total expense
    @FXML
    public void handleShowTotalFollowTypeOfExpense(ActionEvent event) {
        if (totalByTypeOfExpense.getValue().equals("Income"))
            showTotal.setText(String.valueOf(users.getTotalIncome()));
        else if (totalByTypeOfExpense.getValue().equals("Expense"))
            showTotal.setText(String.valueOf(users.getTotalExpense()));
        else showTotal.setText("");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        showUserLogin.setText(users.getName().toUpperCase());
        setBalanceAllPage(users.getUserBalance());
        expenseListTable.setEditable(false);

        ObservableList<String> choices = FXCollections.observableArrayList("Food & Drink", "Groceries","Home", "Bill & Free", "Transport", "Shopping","Entertainment");
        categories.getSelectionModel().selectFirst();
        categories.setValue("Food & Drink");
        categories.getItems().addAll(choices);

        ObservableList<String> typeExpense = FXCollections.observableArrayList("Income", "Expense");
        typeOfExpense.getSelectionModel().selectFirst();
        typeOfExpense.setValue("Income");
        typeOfExpense.getItems().addAll(typeExpense);

        ObservableList<String> totalOfExpense = FXCollections.observableArrayList("None", "Income", "Expense");
        totalByTypeOfExpense.getSelectionModel().selectFirst();
        totalByTypeOfExpense.setValue("None");
        totalByTypeOfExpense.setItems(totalOfExpense);


        date.setCellValueFactory(new PropertyValueFactory<>("date"));
        type.setCellValueFactory(new PropertyValueFactory<>("category"));
        details.setCellValueFactory(new PropertyValueFactory<>("detail"));
        amount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        chooseType.setCellValueFactory(new PropertyValueFactory<>("type"));

        expenseListTable.setItems(list);

        dataAccessor = new FileAccessor(list);
        setDataAccessor(new DatabaseAccessor(list));
        dataAccessor.getConnection();

    }

    private void setBalanceAllPage(double currentBalance){
        balanceLabelAddPage.setText(currentBalance + " BHT ");
    }

    // to transform date to string
    private String getDate(){
        LocalDate localDate = dateLabel.getValue();
        return localDate.toString();
    }

    private void clearData(){
        categories.setValue("Food & Drink");
        typeOfExpense.setValue("Income");
        totalByTypeOfExpense.setValue("None");
        detailLabel.clear();
        amountLabel.clear();
    }

}

