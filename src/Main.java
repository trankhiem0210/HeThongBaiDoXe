import view.*;
import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        // Sử dụng invokeLater để đảm bảo Thread-safe cho UI Swing
        SwingUtilities.invokeLater(() -> {
            // Tùy chỉnh giao diện theo hệ điều hành (Look and Feel)
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }

            // 1. Hiển thị thử màn hình đăng nhập
            LoginView loginView = new LoginView();
            loginView.setVisible(true);

            // 2. Hiển thị màn hình chính (Dashboard) với các chức năng
            showMainDashboard();
        });
    }

    private static void showMainDashboard() {
        JFrame mainFrame = new JFrame("Hệ thống Quản lý Bãi đỗ xe - Dashboard");
        mainFrame.setSize(800, 500);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setLocationRelativeTo(null);

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Xe Vào (Check-In)", new CheckInView());
        tabbedPane.addTab("Xe Ra (Check-Out)", new CheckOutView());
        tabbedPane.addTab("Đăng Ký Vé Tháng", new MonthlyCardRegisterView());
        tabbedPane.addTab("Tìm Kiếm", new SearchView());
        tabbedPane.addTab("Báo Cáo Thống Kê", new ReportView());

        mainFrame.add(tabbedPane);
        mainFrame.setVisible(true);
    }
}