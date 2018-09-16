package csku.expense;

public class ExpenseList {
    private String catagory;
    private String detail;
    private double amount;
    private String date;

    public ExpenseList(String catagory, String detail, double amount, String date) {
        this.catagory = catagory;
        this.detail = detail;
        this.amount = amount;
        this.date = date;
    }

    public String getCatagory() {
        return catagory;
    }

    public void setCatagory(String catagory) {
        this.catagory = catagory;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
