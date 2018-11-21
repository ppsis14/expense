package csku.expense;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileDAOImp implements ExpenseDAO {
    private String status;

    public String getStatus() {
        return status;
    }
 
    @Override
    public void insertExpense(ExpenseList expenseList) {
        PrintWriter writer = null;
        try {
            writer = new PrintWriter("storeExpenseList.txt", "UTF-8");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        status = "Open file successfully";
        System.out.println(status);
        String text = expenseList.getDate() + "," + expenseList.getCategory() + "," + expenseList.getDetail() + "," + String.valueOf(expenseList.getAmount()) + "," + expenseList.getType();
        writeToFile(text);
        writer.close();
    }

    @Override
    public List<ExpenseList> getAllExpenseList() {
        List<ExpenseList> list = new ArrayList<>();
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
        finally {
            status = "Read file successfully";
            System.out.println(status);
        }
        return list;
    }

    @Override
    public void updateExpense(ExpenseList expenseList, String date, String category, String detail, double amount, String type) {

    }

    @Override
    public void deleteExpense(ExpenseList expenseList) {

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

}
