package view;

import model.User;
import repository.DataStorage;

import javax.swing.*;
import java.awt.*;

public class LoginPanel extends JFrame {
    private JTextField txtUserName;
    private JPasswordField txtPassword;
    private JButton btnLogin;
    private JLabel lblMessage;

    public LoginPanel() {
        setTitle("Đăng Nhập Hệ Thống");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Giua man hinh
        setLayout(new BorderLayout());

        initComponents();
    }

    private void initComponents() {
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

        JLabel lblTitle = new JLabel("ĐĂNG NHẬP", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 24));
        mainPanel.add(lblTitle, BorderLayout.NORTH);

        JPanel formPanel = new JPanel(new GridLayout(3, 2, 10, 20));

        formPanel.add(new JLabel("Tên đăng nhập:"));
        txtUserName = new JTextField();
        formPanel.add(txtUserName);

        formPanel.add(new JLabel("Mật khẩu:"));
        txtPassword = new JPasswordField();
        formPanel.add(txtPassword);

        formPanel.add(new JLabel("")); // Placeholder
        btnLogin = new JButton("Đăng Nhập");
        formPanel.add(btnLogin);

        mainPanel.add(formPanel, BorderLayout.CENTER);

        lblMessage = new JLabel(" ", SwingConstants.CENTER);
        lblMessage.setForeground(Color.RED);
        mainPanel.add(lblMessage, BorderLayout.SOUTH);

        add(mainPanel);

        // Su kien dang nhap
        btnLogin.addActionListener(e -> performLogin());
    }

    private void performLogin() {
        String username = txtUserName.getText().trim();
        String password = new String(txtPassword.getPassword());

        if (username.isEmpty() || password.isEmpty()) {
            lblMessage.setText("Vui lòng nhập đầy đủ thông tin!");
            return;
        }
        DataStorage storage = DataStorage.getInstance();

        User loggedInUser = storage.getUsers().stream()
                .filter(u -> u.getUserName().equals(username) && u.getPassword().equals(password))
                .findFirst().orElse(null);

        if (loggedInUser != null) {
            // Dang nhap thanh cong: Dong LoginFrame va mo MainFrame
            this.dispose();

            SwingUtilities.invokeLater(() -> {
                Main main = new Main(loggedInUser);
                main.setVisible(true);
            });
        } else {
            lblMessage.setText("Sai tên đăng nhập hoặc mật khẩu!");
        }
    }


    }


