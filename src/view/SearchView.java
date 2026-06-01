package view;

import javax.swing.*;

public class SearchView extends JPanel {
    // Nơi nhập từ khóa tìm kiếm (Biển số hoặc Ticket ID)
    private JTextField txtSearchKeyword;
    private JButton btnSearch;
    
    // Bảng hiển thị danh sách các phiên đỗ xe (ParkingSession) khớp với từ khóa
    private JTable tblSearchResults;

    public SearchView() {
        setLayout(new java.awt.BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        JPanel topPanel = new JPanel();
        topPanel.add(new JLabel("Từ khóa (Biển số / Mã vé):"));
        txtSearchKeyword = new JTextField(20);
        topPanel.add(txtSearchKeyword);
        btnSearch = new JButton("Tìm kiếm");
        topPanel.add(btnSearch);
        add(topPanel, java.awt.BorderLayout.NORTH);
        
        tblSearchResults = new JTable(new Object[][]{{"29A-12345", "T-001", "10:00 01/06", "Đang đỗ"}}, new Object[]{"Biển số", "Mã vé", "Giờ vào", "Trạng thái"});
        add(new JScrollPane(tblSearchResults), java.awt.BorderLayout.CENTER);
    }
}