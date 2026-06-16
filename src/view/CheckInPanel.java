package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.*;

import controller.CheckInController;
import enums.VehicleType;

public class CheckInPanel extends JPanel {
	private JTextField txtPlateNumber;
	private JComboBox<VehicleType> cmbVehicleType;
	private JButton btnCheckIn;
	private JButton btnClear;
	private JTextArea txtResult;
	private JLabel lblSlotInfo;
	
	private CheckInController checkInController;

	public CheckInPanel() {
		checkInController = new CheckInController();
		initComponents();
	}

	private void initComponents() {
		setLayout(new BorderLayout(20, 20));
		setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));

		// Tiêu đề
		JLabel lblTitle = new JLabel("QUẢN LÝ CHECK-IN", SwingConstants.CENTER);
		lblTitle.setFont(new Font("Arial", Font.BOLD, 24));
		add(lblTitle, BorderLayout.NORTH);

		// Khu vực nhập thông tin
		JPanel centerPanel = new JPanel(new BorderLayout(10, 10));
		JPanel formPanel = new JPanel(new GridLayout(3, 2, 10, 20));

		formPanel.add(new JLabel("Biển Số Xe (VD: ABC-123): "));
		txtPlateNumber = new JTextField(15);
		txtPlateNumber.setFont(new Font("Arial", Font.PLAIN, 14));
		formPanel.add(txtPlateNumber);

		formPanel.add(new JLabel("Loại Xe: "));
		cmbVehicleType = new JComboBox<>(VehicleType.values());
		cmbVehicleType.setFont(new Font("Arial", Font.PLAIN, 14));
		formPanel.add(cmbVehicleType);

		formPanel.add(new JLabel("Thông Tin Chỗ Đỗ: "));
		lblSlotInfo = new JLabel(checkInController.getSlotInfo());
		lblSlotInfo.setFont(new Font("Arial", Font.BOLD, 14));
		lblSlotInfo.setForeground(new java.awt.Color(0, 100, 200));
		formPanel.add(lblSlotInfo);

		JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
		btnCheckIn = new JButton("Check-In");
		btnCheckIn.setFont(new Font("Arial", Font.BOLD, 14));
		btnClear = new JButton("Clear");
		btnClear.setFont(new Font("Arial", Font.BOLD, 14));
		buttonPanel.add(btnCheckIn);
		buttonPanel.add(btnClear);

		centerPanel.add(formPanel, BorderLayout.NORTH);
		centerPanel.add(buttonPanel, BorderLayout.CENTER);
		add(centerPanel, BorderLayout.CENTER);

		// Vùng hiển thị kết quả
		txtResult = new JTextArea(8, 40);
		txtResult.setFont(new Font("Monospaced", Font.PLAIN, 14));
		txtResult.setEditable(false);
		txtResult.setLineWrap(true);
		txtResult.setWrapStyleWord(true);
		JScrollPane scrollPane = new JScrollPane(txtResult);
		add(scrollPane, BorderLayout.SOUTH);

		// Xử lý sự kiện
		btnCheckIn.addActionListener(e -> performCheckIn());
		btnClear.addActionListener(e -> clearForm());
		cmbVehicleType.addActionListener(e -> updateSlotInfo());
	}

	/**
	 * Thực hiện check-in
	 */
	private void performCheckIn() {
		String plateNumber = txtPlateNumber.getText().trim();
		VehicleType vehicleType = (VehicleType) cmbVehicleType.getSelectedItem();

		if (plateNumber.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Vui lòng nhập biển số xe!", "Lỗi", JOptionPane.ERROR_MESSAGE);
			return;
		}

		String result = checkInController.handleCheckIn(plateNumber, vehicleType);
		txtResult.setText(result);

		// Nếu check-in thành công, xóa input và cập nhật info
		if (result.startsWith("✓")) {
			txtPlateNumber.setText("");
			txtPlateNumber.requestFocus();
			updateSlotInfo();
		}
	}
	
	/**
	 * Xóa form
	 */
	private void clearForm() {
		txtPlateNumber.setText("");
		txtResult.setText("");
		cmbVehicleType.setSelectedIndex(0);
		txtPlateNumber.requestFocus();
	}
	
	/**
	 * Cập nhật thông tin chỗ đỗ
	 */
	private void updateSlotInfo() {
		lblSlotInfo.setText(checkInController.getSlotInfo());
	}
}
 