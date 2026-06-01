package view;

import javax.swing.*;

public class CheckInView extends JPanel {
    // Các thành phần giao diện nhập liệu và thao tác
    private JTextField txtPlateNumber;
    private JComboBox<String> cbVehicleType; // Lựa chọn loại xe (Car/Motorbike)
    private JButton btnCheckIn;
    
    // Thành phần hiển thị kết quả (VD: Thành công, Vé lượt/tháng, Cảnh báo)
    private JLabel lblStatusMessage;

    public CheckInView() {
        setLayout(new java.awt.GridLayout(4, 2, 15, 15));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        add(new JLabel("Biển số xe:"));
        txtPlateNumber = new JTextField();
        add(txtPlateNumber);
        
        add(new JLabel("Loại phương tiện:"));
        cbVehicleType = new JComboBox<>(new String[]{"Ô tô", "Xe máy"});
        add(cbVehicleType);
        
        btnCheckIn = new JButton("Ghi nhận Check-in");
        add(btnCheckIn);
        
        lblStatusMessage = new JLabel("Trạng thái: Sẵn sàng");
        add(lblStatusMessage);
    }
}