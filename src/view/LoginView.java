package view;

import javax.swing.*;

public class LoginView extends JFrame {
    // Các thành phần giao diện
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JButton btnLogin;
    private JLabel lblErrorMessage;

    public LoginView() {
        setTitle("Đăng nhập hệ thống");
        setSize(350, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Tạm thời dùng DISPOSE để không tắt luôn Main app khi đóng
        setLocationRelativeTo(null);
        setLayout(new java.awt.GridLayout(3, 2, 10, 10));

        add(new JLabel("Tên đăng nhập:"));
        txtUsername = new JTextField();
        add(txtUsername);

        add(new JLabel("Mật khẩu:"));
        txtPassword = new JPasswordField();
        add(txtPassword);

        btnLogin = new JButton("Đăng nhập");
        add(btnLogin);

        lblErrorMessage = new JLabel(" ");
        add(lblErrorMessage);
    }
}