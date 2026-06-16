package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.*;
import controllers.ParkingSystemFacade;

public class CheckOutPanel extends JPanel {
	private JTextField txtSlotId;
	private JTextField txtTicketId;
	private JButton btnCheckOut;
	private JTextArea txtResult;

	private ParkingSystemFacade facade;

	public CheckOutPanel() {
		this.facade = ParkingSystemFacade.getInstance(); // Lay Facade chung
		initComponents();
	}

	private void initComponents() {
		setLayout(new BorderLayout(20, 20));
		setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));

		JLabel lblTitle = new JLabel("XỬ LÝ CHECK-OUT", SwingConstants.CENTER);
		lblTitle.setFont(new Font("Arial", Font.BOLD, 24));
		add(lblTitle, BorderLayout.NORTH);

		JPanel centerPanel = new JPanel(new BorderLayout(10, 10));
		JPanel formPanel = new JPanel(new GridLayout(2, 2, 10, 10));

		formPanel.add(new JLabel("Nhập mã vị trí đỗ (Slot ID): "));
		txtSlotId = new JTextField(15);
		txtSlotId.setFont(new Font("Arial", Font.PLAIN, 14));
		formPanel.add(txtSlotId);

		formPanel.add(new JLabel("Nhập mã vé xe (Ticket ID): "));
		txtTicketId = new JTextField(15);
		txtTicketId.setFont(new Font("Arial", Font.PLAIN, 14));
		formPanel.add(txtTicketId);

		JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		btnCheckOut = new JButton("Thanh Toán & Cho Xe Ra");
		btnCheckOut.setFont(new Font("Arial", Font.BOLD, 14));
		buttonPanel.add(btnCheckOut);

		centerPanel.add(formPanel, BorderLayout.NORTH);
		centerPanel.add(buttonPanel, BorderLayout.CENTER);
		add(centerPanel, BorderLayout.CENTER);

		txtResult = new JTextArea(6, 40);
		txtResult.setFont(new Font("Monospaced", Font.PLAIN, 14));
		txtResult.setEditable(false);
		JScrollPane scrollPane = new JScrollPane(txtResult);
		add(scrollPane, BorderLayout.SOUTH);

		btnCheckOut.addActionListener(e -> processCheckOut());
	}

	private void processCheckOut() {
		String slotId = txtSlotId.getText().trim();
		String ticketId = txtTicketId.getText().trim();

		if (slotId.isEmpty() || ticketId.isEmpty()) {
			txtResult.setText("Hệ thống: Vui lòng cung cấp đầy đủ thông tin Slot ID và Ticket ID!");
			return;
		}

		// Ham xu ly tu Facade
		String finalResponse = facade.performCheckOut(slotId, ticketId);
		txtResult.setText(finalResponse);

		if (finalResponse.contains("thành công")) {
			JOptionPane.showMessageDialog(this, finalResponse, "Thành Công", JOptionPane.INFORMATION_MESSAGE);
			// Dua ve rong
			txtSlotId.setText("");
			txtTicketId.setText("");
		} else {
			JOptionPane.showMessageDialog(this, finalResponse, "Thất Bại", JOptionPane.ERROR_MESSAGE);
		}
	}
}
