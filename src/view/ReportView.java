package view;

import javax.swing.*;

public class ReportView extends JPanel {
    // Bộ lọc thời gian
    private JSpinner spinnerDateFrom;
    private JSpinner spinnerDateTo;
    private JButton btnGenerateReport;
    private JButton btnExportExcel;
    
    // Vùng hiển thị dữ liệu
    private JTable tblRevenueSummary; // Bảng thống kê chi tiết
    private JPanel pnlChartContainer; // Panel để chứa biểu đồ (Chart) doanh thu

    public ReportView() {
        setLayout(new java.awt.BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        JPanel topPanel = new JPanel();
        topPanel.add(new JLabel("Từ ngày:"));
        spinnerDateFrom = new JSpinner(new SpinnerDateModel());
        topPanel.add(spinnerDateFrom);
        topPanel.add(new JLabel("Đến ngày:"));
        spinnerDateTo = new JSpinner(new SpinnerDateModel());
        topPanel.add(spinnerDateTo);
        
        btnGenerateReport = new JButton("Tạo báo cáo");
        topPanel.add(btnGenerateReport);
        btnExportExcel = new JButton("Xuất Excel");
        topPanel.add(btnExportExcel);
        
        add(topPanel, java.awt.BorderLayout.NORTH);
        
        tblRevenueSummary = new JTable(new Object[][]{{"Vé lượt Ô tô", "5,000,000"}, {"Vé tháng Xe máy", "12,000,000"}}, new Object[]{"Loại doanh thu", "Số tiền (VNĐ)"});
        add(new JScrollPane(tblRevenueSummary), java.awt.BorderLayout.CENTER);
    }
}