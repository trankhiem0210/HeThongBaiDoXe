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

		// Panel nhập liệu
		JPanel inputPanel = createInputPanel();
		add(inputPanel, BorderLayout.CENTER);

		// Panel kết quả
		JPanel resultPanel = createResultPanel();
		add(resultPanel, BorderLayout.SOUTH);
	}

	/**
	 * Tạo panel nhập liệu
	 */
	private JPanel createInputPanel() {
		JPanel panel = new JPanel(new GridLayout(5, 1, 10, 15));
		panel.setBorder(BorderFactory.createTitledBorder("Thông Tin Check-In"));

		// Row 1: Biển số xe
		JPanel row1 = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
		JLabel lblPlate = new JLabel("Biển Số Xe (ABC-123):");
		lblPlate.setFont(new Font("Arial", Font.PLAIN, 14));
		lblPlate.setPreferredSize(new java.awt.Dimension(200, 25));
		txtPlateNumber = new JTextField(15);
		txtPlateNumber.setFont(new Font("Arial", Font.PLAIN, 14));
		row1.add(lblPlate);
		row1.add(txtPlateNumber);
		panel.add(row1);

		// Row 2: Loại xe
		JPanel row2 = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
		JLabel lblVehicleType = new JLabel("Loại Xe:");
		lblVehicleType.setFont(new Font("Arial", Font.PLAIN, 14));
		lblVehicleType.setPreferredSize(new java.awt.Dimension(200, 25));
		cmbVehicleType = new JComboBox<>(VehicleType.values());
		cmbVehicleType.setFont(new Font("Arial", Font.PLAIN, 14));
		cmbVehicleType.setPreferredSize(new java.awt.Dimension(150, 30));
		row2.add(lblVehicleType);
		row2.add(cmbVehicleType);
		panel.add(row2);

		// Row 3: Thông tin chỗ đỗ
		JPanel row3 = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
		JLabel lblInfo = new JLabel("Thông Tin Chỗ Đỗ:");
		lblInfo.setFont(new Font("Arial", Font.PLAIN, 14));
		row3.add(lblInfo);
		lblSlotInfo = new JLabel(checkInController.getSlotInfo());
		lblSlotInfo.setFont(new Font("Arial", Font.BOLD, 12));
		lblSlotInfo.setForeground(new java.awt.Color(0, 100, 200));
		row3.add(lblSlotInfo);
		panel.add(row3);

		// Row 4: Buttons
		JPanel row4 = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 5));
		btnCheckIn = new JButton("CHECK-IN");
		btnCheckIn.setFont(new Font("Arial", Font.BOLD, 14));
		btnCheckIn.setPreferredSize(new java.awt.Dimension(120, 40));
		btnCheckIn.setBackground(new java.awt.Color(0, 150, 0));
		btnCheckIn.setForeground(java.awt.Color.WHITE);
		
		btnClear = new JButton("CLEAR");
		btnClear.setFont(new Font("Arial", Font.BOLD, 14));
		btnClear.setPreferredSize(new java.awt.Dimension(120, 40));
		
		row4.add(btnCheckIn);
		row4.add(btnClear);
		panel.add(row4);

		// Row 5: Mô tả
		JPanel row5 = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
		JLabel lblDesc = new JLabel("Định dạng biển số: 3 chữ cái - 3 số (VD: ABC-123)");
		lblDesc.setFont(new Font("Arial", Font.ITALIC, 11));
		lblDesc.setForeground(java.awt.Color.GRAY);
		row5.add(lblDesc);
		panel.add(row5);

		// Xử lý sự kiện
		btnCheckIn.addActionListener(e -> performCheckIn());
		btnClear.addActionListener(e -> clearForm());
		cmbVehicleType.addActionListener(e -> updateSlotInfo());

		return panel;
	}

	/**
	 * Tạo panel hiển thị kết quả
	 */
	private JPanel createResultPanel() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.setBorder(BorderFactory.createTitledBorder("Kết Quả"));

		txtResult = new JTextArea(8, 40);
		txtResult.setFont(new Font("Monospaced", Font.PLAIN, 13));
		txtResult.setEditable(false);
		txtResult.setLineWrap(true);
		txtResult.setWrapStyleWord(true);
		txtResult.setBackground(new java.awt.Color(240, 240, 240));

		JScrollPane scrollPane = new JScrollPane(txtResult);
		panel.add(scrollPane, BorderLayout.CENTER);

		return panel;
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
 