public class Invoice {
    String title = "";
    double totalAmountDue = 0;

    public Invoice(String title, double totalAmountDue) {
        this.title = title;
        this.totalAmountDue = totalAmountDue;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public double getTotalAmountDue() {
        return totalAmountDue;
    }

}
