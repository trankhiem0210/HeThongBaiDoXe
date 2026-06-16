package controller;

import model.ParkingTicket;
import repository.DataStorage;

import java.time.LocalDate;
import java.time.YearMonth;

public class ReportService {
    private final DataStorage dataStorage;

    public ReportService() {
        this.dataStorage = DataStorage.getInstance();
    }

    /**
     * Lấy số lượng xe hiện đang có trong bãi.
     */
    public int getCurrentlyParkedCount() {
        return dataStorage.getParkingTickets().size();
    }

    /**
     * Lấy tổng số lượt xe vào bãi trong ngày hôm nay.
     */
    public long getDailyCheckInCount() {
        LocalDate today = LocalDate.now();
        // Đếm xe vào trong ngày vẫn còn đang đỗ
        long currentEntries = dataStorage.getParkingTickets().stream()
                .filter(ticket -> ticket.getEntryTime().toLocalDate().isEqual(today))
                .count();
        // Đếm xe vào trong ngày nhưng đã rời bãi
        long historyEntries = dataStorage.getParkingTicketsHistory().stream()
                .filter(ticket -> ticket.getEntryTime().toLocalDate().isEqual(today))
                .count();
        return currentEntries + historyEntries;
    }

    /**
     * Lấy tổng số lượt xe ra khỏi bãi trong ngày hôm nay.
     */
    public long getDailyCheckOutCount() {
        LocalDate today = LocalDate.now();
        return dataStorage.getParkingTicketsHistory().stream()
                .filter(ticket -> ticket.getExitTime() != null && ticket.getExitTime().toLocalDate().isEqual(today))
                .count();
    }

    /**
     * Lấy tổng doanh thu trong tháng hiện tại từ các vé xe đã check-out.
     * Ghi chú: Doanh thu từ đăng ký vé tháng chưa được tính vì model SubscriptionCard thiếu thông tin ngày đăng ký.
     */
    public double getCurrentMonthRevenue() {
        YearMonth currentMonth = YearMonth.now();
        return dataStorage.getParkingTicketsHistory().stream()
                .filter(ticket -> ticket.getExitTime() != null && YearMonth.from(ticket.getExitTime()).equals(currentMonth))
                .mapToDouble(ParkingTicket::getTotalFee)
                .sum();
    }
}