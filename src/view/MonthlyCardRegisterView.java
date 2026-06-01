package view;

import javax.swing.*;

public class MonthlyCardRegisterView extends JPanel {
    private JTextField txtOwnerName;
    private JTextField txtPlateNumber;
    private JComboBox<String> cbVehicleType;
    private JComboBox<String> cbDurationMonth; // Lựa chọn số tháng đăng ký (1, 3, 6, 12...)
    
    private JButton btnRegister;

    public MonthlyCardRegisterView() {
        setLayout(new java.awt.GridLayout(5, 2, 15, 15));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        add(new JLabel("Tên chủ xe:"));
        txtOwnerName = new JTextField();
        add(txtOwnerName);
        
        add(new JLabel("Biển số xe đăng ký:"));
        txtPlateNumber = new JTextField();
        add(txtPlateNumber);
        
        add(new JLabel("Loại phương tiện:"));
        cbVehicleType = new JComboBox<>(new String[]{"Ô tô", "Xe máy"});
        add(cbVehicleType);
        
        add(new JLabel("Gói thời hạn (Tháng):"));
        cbDurationMonth = new JComboBox<>(new String[]{"1", "3", "6", "12"});
        add(cbDurationMonth);
        
        btnRegister = new JButton("Đăng ký vé tháng");
        add(btnRegister);
    }
}