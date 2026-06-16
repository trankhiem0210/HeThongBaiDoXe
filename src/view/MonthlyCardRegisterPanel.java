package view;

import controller.ParkingSystemFacade;
import enums.VehicleType;

import javax.swing.*;
import java.awt.*;

public class MonthlyCardRegisterPanel extends JPanel {
   private JTextField txtplateNumber;
   private JTextField txtOwnerName;
   private JComboBox<VehicleType> cbVehicleType;
   private JComboBox<Integer> cbMonths;
   private JButton btnRegister;
   private JTextField txtMessage;

   private ParkingSystemFacade facade;

   public MonthlyCardRegisterPanel() {
       facade = ParkingSystemFacade.getInstance();
       initComponent();


   }
   private void initComponent() {
       setLayout(new BorderLayout(20, 20));
       setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));

       //tieu de
       JLabel lblTitle = new JLabel("ĐĂNG KÝ VÉ THÁNG", SwingConstants.CENTER);
       lblTitle.setFont(new Font("Ariel",Font.BOLD, 24));
       add(lblTitle, BorderLayout.NORTH);
       
       //khu vuc nhap thong tin
       JPanel formPanel = new JPanel(new GridLayout(5, 2, 10, 20));
       
       formPanel.add(new JLabel("Biển số xe (*): "));
       txtplateNumber = new JTextField();
       formPanel.add(txtplateNumber);
       
       formPanel.add(new JLabel("Tên chủ xe (8): "));
       txtOwnerName = new JTextField();
       formPanel.add(txtOwnerName);
       
       formPanel.add(new JLabel("Loại xe (*): "));
       cbVehicleType = new JComboBox<>(VehicleType.values());
       formPanel.add(cbVehicleType);
       
       formPanel.add(new JLabel("Số tháng đăng ký:"));
       Integer[] months = {1,2,3,6,12};
       cbMonths = new JComboBox<>(months);
       formPanel.add(cbMonths);
       
       formPanel.add(new JLabel("")); //place holder
       btnRegister = new JButton("Đăng ký thẻ");
       formPanel.add(btnRegister);
       
       add(formPanel, BorderLayout.CENTER);
       
       //hien thi thong bao
       txtMessage = new JTextField(" ");
       txtMessage.setHorizontalAlignment(JTextField.CENTER);
       txtMessage.setEditable(false);
       txtMessage.setBorder(null);
       txtMessage.setOpaque(false);
       txtMessage.setFont(new Font("Ariel" , Font.ITALIC, 14));
       add(txtMessage, BorderLayout.SOUTH);
       
       //xu ly su kien

       btnRegister.addActionListener(e -> performRegistration());
   }
   private void performRegistration() {
       String plateNumber = txtplateNumber.getText().trim();
       String ownerName = txtOwnerName.getText().trim();
       VehicleType vehicleType = (VehicleType) cbVehicleType.getSelectedItem();
       int months = (int) cbMonths.getSelectedItem();

       String result = facade.registerSubscriptionCard(plateNumber, ownerName, vehicleType, months);

       txtMessage.setText(result);
       if (result.startsWith("Đăng ký thành công")) {
           txtMessage.setForeground(Color.GREEN);
           txtplateNumber.setText("");
           txtOwnerName.setText("");
           cbMonths.setSelectedIndex(0);
       } else {
           txtMessage.setForeground(Color.RED);
       }
   }

}
