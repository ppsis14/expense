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
        String text = expenseList.getDate() + "," + expenseList.getCategory() + "," + expenseList.getDetail() + "," + String.valueOf(expenseList.getAmount()) + "," + expenseList.getType();
        String filename = "storeExpenseList.txt";
        try {
            PrintWriter outputStream = new PrintWriter(new BufferedWriter( new FileWriter(filename, true)));
            outputStream.println(text);
            status = "Write to file successfully";
            System.out.println(status);
            outputStream.flush();
            outputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
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
            status = "Read from file successfully";
            System.out.println(status);
        }
        return list;
    }

    @Override
    public void updateExpense(ExpenseList expenseList, String date, String category, String detail, double amount, String type) {
        List<ExpenseList> list = getAllExpenseList();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getDate().equals(expenseList.getDate()) && list.get(i).getCategory().equals(expenseList.getCategory()) && list.get(i).getDetail().equals(expenseList.getDetail()) && list.get(i).getAmount()==expenseList.getAmount() && list.get(i).getType().equals(expenseList.getType()))
            {
                list.get(i).setDate(date);
                list.get(i).setCategory(category);
                list.get(i).setDetail(detail);
                list.get(i).setAmount(amount);
                list.get(i).setType(type);
            }
        }
        status = "Update to file successfully";
        System.out.println(status);
    }

    @Override
    public void deleteExpense(ExpenseList expenseList) {
        List<ExpenseList> list = getAllExpenseList();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getDate().equals(expenseList.getDate()) && list.get(i).getCategory().equals(expenseList.getCategory()) && list.get(i).getDetail().equals(expenseList.getDetail()) && list.get(i).getAmount()==expenseList.getAmount() && list.get(i).getType().equals(expenseList.getType()))
            list.remove(i);
        }
        status = "Delete from file successfully";
        System.out.println(status);
    }
}
