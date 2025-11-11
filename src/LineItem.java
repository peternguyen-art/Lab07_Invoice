public class LineItem {
    private int quantity;
    private Product product;
    private double calculatedTotal;

    public LineItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
        this.calculatedTotal = product.getUnitPrice() * quantity;
    }

    // Getters
    public int getQuantity() {
        return quantity;
    }

    public Product getProduct() {
        return product;
    }

    public double getCalculatedTotal() {
        return calculatedTotal;
    }

    // Helper method to format the line item as a string
    public String getLineItemString() {
        return String.format("%-15s %-5d $%-8.2f $%.2f",
                product.getName(),
                quantity,
                product.getUnitPrice(),
                calculatedTotal);
    }
}