import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class InvoiceGUI extends JFrame {
    private JPanel mainPanel;
    private JTextField productNameField, unitPriceField, quantityField;
    private JTextArea lineItemDisplayArea, invoiceDisplayArea;
    private JButton addLineItemButton, generateInvoiceButton;

    private List<LineItem> temporaryLineItems = new ArrayList<>();

    public InvoiceGUI() {
        super("Invoice Data Entry and Display");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        JPanel entryPanel = new JPanel(new GridLayout(4, 2, 5, 5));
        entryPanel.setBorder(BorderFactory.createTitledBorder("Add Line Item"));

        productNameField = new JTextField("Toaster");
        unitPriceField = new JTextField("29.95");
        quantityField = new JTextField("3");

        entryPanel.add(new JLabel("Product Name:"));
        entryPanel.add(productNameField);
        entryPanel.add(new JLabel("Unit Price ($):"));
        entryPanel.add(unitPriceField);
        entryPanel.add(new JLabel("Quantity:"));
        entryPanel.add(quantityField);

        addLineItemButton = new JButton("Add Item");
        entryPanel.add(addLineItemButton);

        mainPanel.add(entryPanel, BorderLayout.NORTH);

        // --- 2. Line Item Display Panel ---
        lineItemDisplayArea = new JTextArea(5, 40);
        lineItemDisplayArea.setEditable(false);
        lineItemDisplayArea.setText(String.format("%-15s %-5s %-8s %s\n", "Item", "Qty", "Price", "Total"));

        EmptyBorder emptyBorder = new EmptyBorder(10, 10, 10, 10);

        generateInvoiceButton = new JButton("Generate Final Invoice");

        JPanel centerPanel = new JPanel(new BorderLayout(5, 5));
        centerPanel.add(entryPanel, BorderLayout.NORTH);
        centerPanel.setBorder(emptyBorder);
        centerPanel.add(generateInvoiceButton, BorderLayout.SOUTH);
        centerPanel.add(new JScrollPane(lineItemDisplayArea), BorderLayout.CENTER);

        mainPanel.add(centerPanel, BorderLayout.CENTER);


        // --- 3. Final Invoice Display Area ---
        JPanel invoicePanel = new JPanel(new BorderLayout(5, 5));
        invoicePanel.setBorder(emptyBorder);

        invoiceDisplayArea = new JTextArea(15, 45);
        invoiceDisplayArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        invoiceDisplayArea.setEditable(false);
        invoicePanel.add(new JScrollPane(invoiceDisplayArea), BorderLayout.CENTER);

        mainPanel.add(invoicePanel, BorderLayout.SOUTH);

        // --- Assemble Main Frame ---
        add(mainPanel);

        // --- Action Listeners ---
        addLineItemButton.addActionListener(e -> addLineItem());
        generateInvoiceButton.addActionListener(e -> generateInvoice());

        pack();
        setLocationRelativeTo(null); // Center the frame
        setVisible(true);
    }

    private void addLineItem() {
        try {
            String name = productNameField.getText().trim();
            double price = Double.parseDouble(unitPriceField.getText().trim());
            int qty = Integer.parseInt(quantityField.getText().trim());

            if (name.isEmpty() || price <= 0 || qty <= 0) {
                JOptionPane.showMessageDialog(this, "Please enter valid product details.", "Input Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Product newProduct = new Product(name, price);
            LineItem newItem = new LineItem(newProduct, qty);
            temporaryLineItems.add(newItem);

            // Update line item display
            lineItemDisplayArea.append(newItem.getLineItemString() + "\n");

            // Clear fields for next entry
            productNameField.setText("");
            unitPriceField.setText("");
            quantityField.setText("");

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Unit Price and Quantity must be valid numbers.", "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void generateInvoice() {
        if (temporaryLineItems.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please add at least one line item before generating the invoice.", "Generation Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Address sampleAddress = new Address("100 Main Street", "", "Anytown", "CA", "98765");
        Customer sampleCustomer = new Customer("Sam's Small Appliances", sampleAddress);

        Invoice invoice = new Invoice("INVOICE", sampleCustomer);

        for (LineItem item : temporaryLineItems) {
            invoice.addLineItem(item);
        }

        invoiceDisplayArea.setText(invoice.generateInvoiceText());

        System.out.println("\n--- Console Output ---\n");
        System.out.println(invoice.generateInvoiceText());
    }

}