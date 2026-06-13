package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.*;

import controller.SearchService;

public class SearchPanel extends JPanel {
	private JTextField txtKeyword;
	private JButton btnSearch;
	private JTextArea txtResult;

	private SearchService searchService;

	public SearchPanel() {
		searchService = new SearchService();
		initComponents();
	}

	private void initComponents() {
		setLayout(new BorderLayout(20, 20));
		setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));

		// Tiêu đề
		JLabel lblTitle = new JLabel("TRA CỨU THÔNG TIN XE", SwingConstants.CENTER);
		lblTitle.setFont(new Font("Arial", Font.BOLD, 24));
		add(lblTitle, BorderLayout.NORTH);

		// Form nhập từ khóa tìm kiếm
		JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
		topPanel.add(new JLabel("Nhập biển số / Mã vé / Mã thẻ / Vị trí đỗ: "));
		txtKeyword = new JTextField(20);
		txtKeyword.setFont(new Font("Arial", Font.PLAIN, 14));
		topPanel.add(txtKeyword);

		btnSearch = new JButton("Tìm kiếm");
		topPanel.add(btnSearch);

		// Vùng hiển thị kết quả (có thanh cuộn scroll)
		txtResult = new JTextArea(10, 40);
		txtResult.setFont(new Font("Monospaced", Font.PLAIN, 14));
		txtResult.setEditable(false); // Ngăn không cho người dùng sửa text
		JScrollPane scrollPane = new JScrollPane(txtResult);

		add(topPanel, BorderLayout.CENTER);
		add(scrollPane, BorderLayout.SOUTH);

		// Xử lý sự kiện click tìm kiếm
		btnSearch.addActionListener(e -> performSearch());
	}

	private void performSearch() {
		String keyword = txtKeyword.getText();
		txtResult.setText(searchService.searchVehicle(keyword));
	}
}