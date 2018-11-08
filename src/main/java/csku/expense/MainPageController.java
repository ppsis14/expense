package csku.expense;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
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

    @FXML
    public void handleEditBtn(ActionEvent event) {
        expenseListTable.setEditable(true);

        date.setCellFactory(TextFieldTableCell.forTableColumn());
        date.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<ExpenseList, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<ExpenseList, String> event) {
                event.getTableView().getItems().get(event.getTablePosition().getRow()).setDate(event.getNewValue());
            }
        });

        type.setCellFactory(TextFieldTableCell.forTableColumn());
        type.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<ExpenseList, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<ExpenseList, String> event) {
                event.getTableView().getItems().get(event.getTablePosition().getRow()).setCategory(event.getNewValue());
            }
        });

        details.setCellFactory(TextFieldTableCell.forTableColumn());
        details.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<ExpenseList, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<ExpenseList, String> event) {
                event.getTableView().getItems().get(event.getTablePosition().getRow()).setDetail(event.getNewValue());
            }
        });


        chooseType.setCellFactory(TextFieldTableCell.forTableColumn());
        chooseType.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<ExpenseList, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<ExpenseList, String> event) {
                event.getTableView().getItems().get(event.getTablePosition().getRow()).setType(event.getNewValue());
            }
        });

    }

    @FXML
    public void handleSaveBtn(ActionEvent event) {
        //expenseListTable.setEditable(false);
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

    public void writeToFile(String str){
        String filename = "storeExpenseList.txt";
        try {
            PrintWriter outputStream = new PrintWriter(new BufferedWriter( new FileWriter(filename, true)));
            outputStream.println(str);
            outputStream.flush();
            outputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readFormFile(){
        String filename = "storeExpenseList.txt";
        String FieldDelimiter = ",";

        BufferedReader br;

        try {
            br = new BufferedReader(new FileReader(filename));

            String line;
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(FieldDelimiter, -1);
                list.add(new ExpenseList(fields[0], fields[1], fields[2], Double.parseDouble(fields[3]), fields[4]));
            }

        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    public void handleShowDataFormTextBtn(ActionEvent event) {
        //readFormFile();
        dataAccessor.loadDataFrom();
    }

    @FXML
    public void handleAddBtn(ActionEvent event) {
        list.add(new ExpenseList(getDate(), categories.getValue(), detailLabel.getText(), parseDouble(amountLabel.getText()), typeOfExpense.getValue()));
        if (typeOfExpense.getValue().equals("Income"))users.addIncome(parseDouble(amountLabel.getText()));
        else if (typeOfExpense.getValue().equals("Expense"))users.addExpense(parseDouble(amountLabel.getText()));
        setBalanceAllPage(users.getUserBalance());
        String text = getDate() + "," + categories.getValue() + "," + detailLabel.getText() + "," + Double.parseDouble(amountLabel.getText()) + "," + typeOfExpense.getValue();
        writeToFile(text);
        clearData();

    }

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



        //expenseListTable.getColumns().setAll(date, type, details, amount, chooseType);

    }

    private void setBalanceAllPage(double currentBalance){
        balanceLabelAddPage.setText(currentBalance + " BHT ");
    }

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

