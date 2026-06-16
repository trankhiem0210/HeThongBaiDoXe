package view;

import controller.ParkingSystemFacade;

import javax.swing.*;
import java.awt.*;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class ReportPanel extends JPanel {

    private final ParkingSystemFacade facade;

    private JLabel lblVehiclesInLotValue;
    private JLabel lblDailyTurnoverValue;
    private JLabel lblMonthlyRevenueValue;
    private JLabel lblReportDateValue;

    public ReportPanel() {
        this.facade = ParkingSystemFacade.getInstance();
        initComponents();
        updateReport(); // Tải dữ liệu khi khởi tạo
    }

    private void initComponents() {
        setLayout(new BorderLayout(20, 20));
        setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));

        // Tiêu đề
        JLabel lblTitle = new JLabel("BÁO CÁO DOANH THU VÀ HOẠT ĐỘNG", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 24));
        add(lblTitle, BorderLayout.NORTH);

        // Panel nội dung báo cáo
        JPanel reportContentPanel = new JPanel(new GridLayout(0, 2, 20, 20));
        reportContentPanel.setBorder(BorderFactory.createTitledBorder("Thống Kê Hôm Nay"));
        add(reportContentPanel, BorderLayout.CENTER);

        // --- Hàng 1: Ngày báo cáo ---
        JLabel lblReportDate = new JLabel("Ngày báo cáo:");
        lblReportDate.setFont(new Font("Arial", Font.BOLD, 16));
        reportContentPanel.add(lblReportDate);

        lblReportDateValue = new JLabel();
        lblReportDateValue.setFont(new Font("Arial", Font.PLAIN, 16));
        reportContentPanel.add(lblReportDateValue);

        // --- Hàng 2: Số xe trong bãi ---
        JLabel lblVehiclesInLot = new JLabel("Số xe hiện tại trong bãi:");
        lblVehiclesInLot.setFont(new Font("Arial", Font.BOLD, 16));
        reportContentPanel.add(lblVehiclesInLot);

        lblVehiclesInLotValue = new JLabel();
        lblVehiclesInLotValue.setFont(new Font("Arial", Font.PLAIN, 16));
        reportContentPanel.add(lblVehiclesInLotValue);

        // --- Hàng 3: Lượt xe ra/vào ---
        JLabel lblDailyTurnover = new JLabel("Lượt xe ra/vào trong ngày:");
        lblDailyTurnover.setFont(new Font("Arial", Font.BOLD, 16));
        reportContentPanel.add(lblDailyTurnover);

        lblDailyTurnoverValue = new JLabel();
        lblDailyTurnoverValue.setFont(new Font("Arial", Font.PLAIN, 16));
        reportContentPanel.add(lblDailyTurnoverValue);

        // --- Hàng 4: Doanh thu tháng ---
        JLabel lblMonthlyRevenue = new JLabel("Tổng doanh thu tháng này:");
        lblMonthlyRevenue.setFont(new Font("Arial", Font.BOLD, 16));
        reportContentPanel.add(lblMonthlyRevenue);

        lblMonthlyRevenueValue = new JLabel();
        lblMonthlyRevenueValue.setFont(new Font("Arial", Font.BOLD, 16));
        lblMonthlyRevenueValue.setForeground(new Color(0, 100, 0));
        reportContentPanel.add(lblMonthlyRevenueValue);

        // Panel dưới cùng cho nút làm mới
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton btnRefresh = new JButton("Làm Mới Báo Cáo");
        btnRefresh.setFont(new Font("Arial", Font.BOLD, 14));
        btnRefresh.addActionListener(e -> updateReport());
        bottomPanel.add(btnRefresh);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    private void updateReport() {
        int vehiclesInLot = facade.getCurrentlyParkedCount();
        long dailyCheckIns = facade.getDailyCheckInCount();
        long dailyCheckOuts = facade.getDailyCheckOutCount();
        double monthlyRevenue = facade.getCurrentMonthRevenue();

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));

        lblReportDateValue.setText(LocalDate.now().format(dateFormatter));
        lblVehiclesInLotValue.setText(vehiclesInLot + " xe");
        lblDailyTurnoverValue.setText(dailyCheckIns + " lượt vào / " + dailyCheckOuts + " lượt ra");
        lblMonthlyRevenueValue.setText(currencyFormatter.format(monthlyRevenue));
    }
}