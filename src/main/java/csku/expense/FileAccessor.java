package csku.expense;

import javafx.collections.ObservableList;

import java.io.*;
public class FileAccessor implements DataAccessor {
    ObservableList<ExpenseList> list;
    String status;

    public FileAccessor(ObservableList<ExpenseList> lists) {
        this.list = lists;
    }

    public String getStatus() {
        return status;
    }

    @Override
    public void getConnection() {
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
        writer.close();
    }

    @Override
    public void loadDataFrom() {
        readFormFile();
    }

    @Override
    public void storeDataTo() {
        for (ExpenseList e : list){
            String text = e.getDate() + "," + e.getCategory() + "," + e.getDetail() + "," + String.valueOf(e.getAmount()) + "," + e.getType();
            writeToFile(text);
        }
        status = "Write data to file successfully";
        System.out.println(status);
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
        finally {
            status = "Read file successfully";
            System.out.println(status);
        }
    }
}
