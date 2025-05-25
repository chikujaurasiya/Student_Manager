import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class StudentManagementGUI {
    private StudentManager manager;

    public StudentManagementGUI() {
        manager = new StudentManager();
        JFrame frame = new JFrame("Student Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 400);

        // Panels and layout
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 2, 10, 10));

        JTextField rollField = new JTextField();
        JTextField nameField = new JTextField();
        JTextField ageField = new JTextField();

        JTextArea outputArea = new JTextArea();
        outputArea.setEditable(false);

        // Buttons
        JButton addBtn = new JButton("Add Student");
        JButton viewBtn = new JButton("View All");
        JButton searchBtn = new JButton("Search");
        JButton deleteBtn = new JButton("Delete");

        // Input Fields
        panel.add(new JLabel("Roll Number:"));
        panel.add(rollField);
        panel.add(new JLabel("Name:"));
        panel.add(nameField);
        panel.add(new JLabel("Age:"));
        panel.add(ageField);

        // Add buttons
        panel.add(addBtn);
        panel.add(viewBtn);
        panel.add(searchBtn);
        panel.add(deleteBtn);

        frame.add(panel, BorderLayout.NORTH);
        frame.add(new JScrollPane(outputArea), BorderLayout.CENTER);

        // Button actions
        addBtn.addActionListener(e -> {
            try {
                int roll = Integer.parseInt(rollField.getText());
                String name = nameField.getText();
                int age = Integer.parseInt(ageField.getText());
                manager.addStudent(new Student(roll, name, age));
                outputArea.setText("✅ Student added!\n");
            } catch (Exception ex) {
                outputArea.setText("❌ Invalid input.\n");
            }
        });

        viewBtn.addActionListener(e -> {
            StringBuilder sb = new StringBuilder();
            for (Student s : manager.getAllStudents()) {
                sb.append(s).append("\n");
            }
            outputArea.setText(sb.length() > 0 ? sb.toString() : "❌ No students found.\n");
        });

        searchBtn.addActionListener(e -> {
            try {
                int roll = Integer.parseInt(rollField.getText());
                Student s = manager.searchStudent(roll);
                if (s != null) {
                    outputArea.setText("🔍 Found: " + s + "\n");
                } else {
                    outputArea.setText("❌ Student not found.\n");
                }
            } catch (Exception ex) {
                outputArea.setText("❌ Invalid input.\n");
            }
        });

        deleteBtn.addActionListener(e -> {
            try {
                int roll = Integer.parseInt(rollField.getText());
                boolean deleted = manager.deleteStudent(roll);
                outputArea.setText(deleted ? "🗑️ Student deleted.\n" : "❌ Student not found.\n");
            } catch (Exception ex) {
                outputArea.setText("❌ Invalid input.\n");
            }
        });

        frame.setVisible(true);
    }
}
