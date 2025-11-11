import java.util.ArrayList;
import java.util.List;

public class Invoice {
    private String title;
    private double totalAmtDue;
    private Customer customer;
    private List<LineItem> lineItems;

    public Invoice(String title, Customer customer) {
        this.title = title;
        this.customer = customer;
        this.lineItems = new ArrayList<>();
        this.totalAmtDue = 0.0;
    }

    public void addLineItem(LineItem item) {
        lineItems.add(item);
        recalculateTotal();
    }

    public double getTotalAmtDue() {
        return totalAmtDue;
    }

    public void recalculateTotal() {
        this.totalAmtDue = 0.0;
        for (LineItem item : lineItems) {
            this.totalAmtDue += item.getCalculatedTotal();
        }
    }

    public String generateInvoiceText() {
        StringBuilder sb = new StringBuilder();

        sb.append("=========================================\n");
        sb.append(String.format("%20s\n", title.toUpperCase()));
        sb.append("-----------------------------------------\n");
        sb.append(customer.getCustomerBlock()).append("\n");
        sb.append("=========================================\n");

        sb.append(String.format("%-15s %-5s %-8s %s\n", "Item", "Qty", "Price", "Total"));
        sb.append("-----------------------------------------\n");

        for (LineItem item : lineItems) {
            sb.append(item.getLineItemString()).append("\n");
        }

        sb.append("=========================================\n");

        sb.append(String.format("%-28s $%.2f\n", "AMOUNT DUE:", totalAmtDue));
        sb.append("=========================================\n");

        return sb.toString();
    }
}