import view.*;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.*;

public class Main extends JFrame {

	public Main() {
		// Thiet lap cac thuoc tinh co ban cho cua so chinh
		setTitle("Hệ Thống Quản Lý Bãi Đỗ Xe");
		setSize(800, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null); // Hien thi cua so o giua man hinh
		setLayout(new BorderLayout());

		initComponents();
	}

	private void initComponents() {
		// Thiet lap Panel phia tren chua nut Dang xuat
		JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		add(topPanel, BorderLayout.NORTH);

		// Tao JTabbedPane de chua cac man hinh chuc nang theo dang Tab
		JTabbedPane tabbedPane = new JTabbedPane();

		// Khoi tao cac Panel chuc nang
		CheckInPanel checkInPanel = new CheckInPanel();
		CheckOutPanel checkOutPanel = new CheckOutPanel();
		ReportPanel reportPanel = new ReportPanel();
		SearchPanel searchPanel = new SearchPanel();
		MonthlyCardRegisterPanel monthlyCardRegisterPanel = new MonthlyCardRegisterPanel();

		// Them cac Panel vao Tab
		tabbedPane.addTab("Quản lý Check-In", checkInPanel);
		tabbedPane.addTab("Quản lý Check-Out", checkOutPanel);
		tabbedPane.addTab("Đăng ký vé tháng", monthlyCardRegisterPanel);
		tabbedPane.addTab("Tra cứu/Tìm kiếm", searchPanel);
		tabbedPane.addTab("Báo Cáo Doanh Thu", reportPanel);

		// Them JTabbedPane vao vi tri trung tam cua MainFrame
		add(tabbedPane, BorderLayout.CENTER);
	}

	// Diem bat dau chuong trinh duoc chuyen sang man hinh dang nhap
	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			Main mainFrame = new Main();
			mainFrame.setVisible(true);
		});
	}
}