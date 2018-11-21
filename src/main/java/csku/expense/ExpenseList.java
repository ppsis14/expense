package csku.expense;

public class ExpenseList {
    private String date;
    private String category;
    private String detail;
    private double amount;
    private String type;


    public ExpenseList(String date, String category, String detail, double amount, String type) {
        this.date = date;
        this.category = category;
        this.detail = detail;
        this.amount = amount;
        this.type = type;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "ExpenseList{" +
                "date='" + date + '\'' +
                ", category='" + category + '\'' +
                ", detail='" + detail + '\'' +
                ", amount=" + amount +
                ", type='" + type + '\'' +
                '}';
    }
}
