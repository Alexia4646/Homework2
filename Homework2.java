import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Homework2 extends JFrame implements ActionListener {
    // I am declaring the Swig components
    JLabel sizeLabel = new JLabel("Select Pizza Size:");
    JComboBox<String> sizeComboBox = new JComboBox<>(new String[]{"Small - $5", "Medium - $10", "Large - $15", "Super - $20"});
    JLabel toppingLabel = new JLabel("Select Toppings (Max 3):");
    JPanel toppingPanel = new JPanel();
    JCheckBox[] toppingCheckboxes;
    JLabel extraCheeseLabel = new JLabel("Extra Cheese");
    JCheckBox extraCheeseCheckbox = new JCheckBox("Add Extra Cheese");
    JButton calculateButton = new JButton("Calculate Total");
    JLabel totalPriceLabel = new JLabel("Total Price: $0.00");

    public Homework2() {
        super("BigY Store Pizza Ordering App");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(8, 1, 10, 10)); //I am setting the layout for the frame

        // I am setting the colors for Swing components
        sizeLabel.setForeground(Color.BLUE);
        toppingLabel.setForeground(Color.PINK);
        extraCheeseLabel.setForeground(Color.BLUE);
        calculateButton.setBackground(Color.BLUE);
        calculateButton.setForeground(Color.RED);
        totalPriceLabel.setForeground(Color.PINK);

        //I am initializing the topping checkboxes and adding them to the panel
        toppingPanel.setLayout(new GridLayout(1, 4, 5, 5));
        String[] toppings = {"Pepperoni", "Mushrooms", "Onions", "Olives", "Chicken"};
        toppingCheckboxes = new JCheckBox[toppings.length];
        for (int i = 0; i < toppings.length; i++) {
            toppingCheckboxes[i] = new JCheckBox(toppings[i]);
            toppingPanel.add(toppingCheckboxes[i]);
        }

        // I am adding action listener to the calculate button
        calculateButton.addActionListener(this);

        // I am adding Swing components to the frame
        add(sizeLabel);
        add(sizeComboBox);
        add(toppingLabel);
        add(toppingPanel);
        add(extraCheeseLabel);
        add(extraCheeseCheckbox);
        add(calculateButton);
        add(totalPriceLabel);
    }
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == calculateButton) {
                double totalPrice = calculateTotalPrice();
                totalPriceLabel.setText("Total Price: $" + String.format("%.2f", totalPrice));
            }
        }

       // Calculating the total price based on the option selected
    private double calculateTotalPrice() {
        String size = (String) sizeComboBox.getSelectedItem();
        double pizzaPrice = Double.parseDouble(size.substring(size.indexOf('$')+1));

        // I am calculating the topping cost
        double toppingCost = 0.0;
        int selectedToppings = 0;
        for (JCheckBox checkbox : toppingCheckboxes) {
            if (checkbox.isSelected()) {
                selectedToppings++;
                if(selectedToppings > 3) {
                    JOptionPane.showMessageDialog(this, "Maximum of 3 toppings allowed.");
                    return 0;
                }
                toppingCost += 0.5; // $0.50 are added for each topping
            }
        }

        // I am applying a discount if 3 toppings are selected
        if (selectedToppings == 3) {
            toppingCost -= 0.25;
        }
        // I am showing a message to tell the customers that extra cheese is free
        if (extraCheeseCheckbox.isSelected()) {
            JOptionPane.showMessageDialog(this, "Extra Cheese is free!");
        }
        // I am calculating the total price
        double totalPrice = pizzaPrice + toppingCost;

        return totalPrice;
    }
    public static void main(String[] args) {
        Homework2 homework2 = new Homework2();
        homework2.setVisible(true);
    }
}