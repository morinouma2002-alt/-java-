import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

class Transaction {
    String date;
    String category;
    int amount;
    String memo;

    Transaction(String date, String category, int amount, String memo) {
        this.date = date;
        this.category = category;
        this.amount = amount;
        this.memo = memo;
    }
}

public class Main1 {
    static ArrayList<Transaction> transactions = new ArrayList<>();

    static JLabel incomeLabel;
    static JLabel expenseLabel;
    static JLabel balanceLabel;
    static DefaultTableModel tableModel;

    public static void main(String[] args) {
        JFrame frame = new JFrame("家計簿アプリ");
        frame.setSize(800, 550);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JLabel titleLabel = new JLabel("家計簿アプリ", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Meiryo", Font.BOLD, 28));
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        JPanel inputPanel = new JPanel(new GridLayout(5, 2, 8, 8));
        inputPanel.setBorder(BorderFactory.createTitledBorder("入力"));

        JTextField dateField = new JTextField("2026-05-13");
        JTextField categoryField = new JTextField();
        JTextField amountField = new JTextField();
        JTextField memoField = new JTextField();

        JButton addButton = new JButton("追加する");

        inputPanel.add(new JLabel("日付"));
        inputPanel.add(dateField);

        inputPanel.add(new JLabel("カテゴリ"));
        inputPanel.add(categoryField);

        inputPanel.add(new JLabel("金額"));
        inputPanel.add(amountField);

        inputPanel.add(new JLabel("メモ"));
        inputPanel.add(memoField);

        inputPanel.add(new JLabel("支出は -500、収入は 5000"));
        inputPanel.add(addButton);

        mainPanel.add(inputPanel, BorderLayout.WEST);

        JPanel centerPanel = new JPanel(new BorderLayout(10, 10));

        JPanel summaryPanel = new JPanel(new GridLayout(1, 3, 10, 10));

        incomeLabel = new JLabel("収入: 0円", SwingConstants.CENTER);
        expenseLabel = new JLabel("支出: 0円", SwingConstants.CENTER);
        balanceLabel = new JLabel("残高: 0円", SwingConstants.CENTER);

        incomeLabel.setFont(new Font("Meiryo", Font.BOLD, 18));
        expenseLabel.setFont(new Font("Meiryo", Font.BOLD, 18));
        balanceLabel.setFont(new Font("Meiryo", Font.BOLD, 18));

        summaryPanel.add(incomeLabel);
        summaryPanel.add(expenseLabel);
        summaryPanel.add(balanceLabel);

        centerPanel.add(summaryPanel, BorderLayout.NORTH);

        String[] columnNames = {"日付", "カテゴリ", "金額", "メモ"};
        tableModel = new DefaultTableModel(columnNames, 0);
        JTable table = new JTable(tableModel);
        table.setFont(new Font("Meiryo", Font.PLAIN, 14));
        table.setRowHeight(25);

        JScrollPane scrollPane = new JScrollPane(table);
        centerPanel.add(scrollPane, BorderLayout.CENTER);

        mainPanel.add(centerPanel, BorderLayout.CENTER);

        addButton.addActionListener(e -> {
            String date = dateField.getText();
            String category = categoryField.getText();
            String amountText = amountField.getText();
            String memo = memoField.getText();

            if (date.isEmpty() || category.isEmpty() || amountText.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "日付・カテゴリ・金額を入力してください");
                return;
            }

            int amount;

            try {
                amount = Integer.parseInt(amountText);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "金額は数字で入力してください。例: -500 または 5000");
                return;
            }

            Transaction t = new Transaction(date, category, amount, memo);
            transactions.add(t);

            tableModel.addRow(new Object[]{
                    t.date,
                    t.category,
                    t.amount + "円",
                    t.memo
            });

            updateSummary();

            categoryField.setText("");
            amountField.setText("");
            memoField.setText("");
        });

        frame.add(mainPanel);
        frame.setVisible(true);
    }

    static void updateSummary() {
        int income = 0;
        int expense = 0;

        for (int i = 0; i < transactions.size(); i++) {
            int amount = transactions.get(i).amount;

            if (amount >= 0) {
                income += amount;
            } else {
                expense += amount;
            }
        }

        incomeLabel.setText("収入: " + income + "円");
        expenseLabel.setText("支出: " + Math.abs(expense) + "円");
        balanceLabel.setText("残高: " + (income + expense) + "円");
    }
}
