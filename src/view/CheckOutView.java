package view;

import javax.swing.*;

public class CheckOutView extends JPanel {
    // Đầu vào: Quét mã vé hoặc nhập biển số
    private JTextField txtTicketIdOrPlate;
    private JButton btnCheckOut;
    
    // Đầu ra: Hiển thị thông tin sau khi xử lý
    private JLabel lblFeeAmount; // Hiển thị số tiền cần thu
    private JLabel lblBarrierStatus; // Hiển thị trạng thái mở/đóng Barie
    private JButton btnPrintReceipt; // Nút in biên lai

    public CheckOutView() {
        setLayout(new java.awt.GridLayout(4, 2, 15, 15));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        add(new JLabel("Quét mã vé / Biển số:"));
        txtTicketIdOrPlate = new JTextField();
        add(txtTicketIdOrPlate);
        
        btnCheckOut = new JButton("Xử lý Check-out");
        add(btnCheckOut);
        
        lblFeeAmount = new JLabel("Số tiền cần thu: 0 VNĐ");
        add(lblFeeAmount);
        
        lblBarrierStatus = new JLabel("Trạng thái Barie: ĐÓNG");
        add(lblBarrierStatus);
        
        btnPrintReceipt = new JButton("In biên lai");
        add(btnPrintReceipt);
    }
}