import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

class Student {
    int id;
    String name;
    int age;
    double marks;

    Student(int id, String name, int age, double marks) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.marks = marks;
    }
}

public class StudentManagementSystemSwing extends JFrame {
    private ArrayList<Student> students = new ArrayList<>();
    private DefaultTableModel tableModel;
    private JTable table;

    public StudentManagementSystemSwing() {
        setTitle("Student Management System");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Layout
        setLayout(new BorderLayout());

        // Table
        String[] columns = {"ID", "Name", "Age", "Marks"};
        tableModel = new DefaultTableModel(columns, 0);
        table = new JTable(tableModel);
        add(new JScrollPane(table), BorderLayout.CENTER);

        // Buttons Panel
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());

        JButton addBtn = new JButton("Add Student");
        JButton viewBtn = new JButton("View All");
        JButton updateBtn = new JButton("Update Student");
        JButton deleteBtn = new JButton("Delete Student");
        JButton searchBtn = new JButton("Search Student");

        panel.add(addBtn);
        panel.add(viewBtn);
        panel.add(updateBtn);
        panel.add(deleteBtn);
        panel.add(searchBtn);

        add(panel, BorderLayout.SOUTH);

        // Button Actions
        addBtn.addActionListener(e -> addStudent());
        viewBtn.addActionListener(e -> viewStudents());
        updateBtn.addActionListener(e -> updateStudent());
        deleteBtn.addActionListener(e -> deleteStudent());
        searchBtn.addActionListener(e -> searchStudent());

        setVisible(true);
    }

    private void addStudent() {
        try {
            int id = Integer.parseInt(JOptionPane.showInputDialog(this, "Enter ID:"));
            String name = JOptionPane.showInputDialog(this, "Enter Name:");
            int age = Integer.parseInt(JOptionPane.showInputDialog(this, "Enter Age:"));
            double marks = Double.parseDouble(JOptionPane.showInputDialog(this, "Enter Marks:"));

            students.add(new Student(id, name, age, marks));
            JOptionPane.showMessageDialog(this, "Student added successfully!");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Invalid input!");
        }
    }

    private void viewStudents() {
        tableModel.setRowCount(0); // Clear table
        for (Student s : students) {
            tableModel.addRow(new Object[]{s.id, s.name, s.age, s.marks});
        }
    }

    private void updateStudent() {
        try {
            int id = Integer.parseInt(JOptionPane.showInputDialog(this, "Enter ID to update:"));
            for (Student s : students) {
                if (s.id == id) {
                    s.name = JOptionPane.showInputDialog(this, "Enter new Name:", s.name);
                    s.age = Integer.parseInt(JOptionPane.showInputDialog(this, "Enter new Age:", s.age));
                    s.marks = Double.parseDouble(JOptionPane.showInputDialog(this, "Enter new Marks:", s.marks));
                    JOptionPane.showMessageDialog(this, "Student updated!");
                    viewStudents();
                    return;
                }
            }
            JOptionPane.showMessageDialog(this, "Student not found!");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Invalid input!");
        }
    }

    private void deleteStudent() {
        try {
            int id = Integer.parseInt(JOptionPane.showInputDialog(this, "Enter ID to delete:"));
            for (int i = 0; i < students.size(); i++) {
                if (students.get(i).id == id) {
                    students.remove(i);
                    JOptionPane.showMessageDialog(this, "Student deleted!");
                    viewStudents();
                    return;
                }
            }
            JOptionPane.showMessageDialog(this, "Student not found!");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Invalid input!");
        }
    }

    private void searchStudent() {
        String name = JOptionPane.showInputDialog(this, "Enter Name to search:");
        tableModel.setRowCount(0);
        boolean found = false;
        for (Student s : students) {
            if (s.name.equalsIgnoreCase(name)) {
                tableModel.addRow(new Object[]{s.id, s.name, s.age, s.marks});
                found = true;
            }
        }
        if (!found) JOptionPane.showMessageDialog(this, "Student not found!");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(StudentManagementSystemSwing::new);
    }
}
