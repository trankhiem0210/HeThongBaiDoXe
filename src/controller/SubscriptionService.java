package controller;

import enums.VehicleType;
import model.SubscriptionCard;
import repository.DataStorage;

import java.time.LocalDate;
import java.util.List;

public class SubscriptionService {
    private static final int MOTORBIKE_MONTHLY_FEE = 50000;
    private static final int CAR_MONTHLY_FEE = 200000;

    private DataStorage storage = new DataStorage().getInstance();

    public int calculateMonthlyFee(VehicleType vehicleType) {
        if (vehicleType == VehicleType.MOTORBIKE) {
            return MOTORBIKE_MONTHLY_FEE;
        }
        if (vehicleType == VehicleType.CAR) {
            return CAR_MONTHLY_FEE;
        }
        return 0;
    }

    public int calculateTotalFee(VehicleType vehicleType, int months) {
        if (months <= 0) {
            return 0;
        }
        return calculateMonthlyFee(vehicleType) * months;
    }

    public String registerSubscriptionCard(String plateNumber, String ownerName, VehicleType vehicleType, int months) {
    if (plateNumber == null || plateNumber.trim().isEmpty()) {
        return "Biển số xe không được để trống!";
    }
		if (ownerName == null || ownerName.trim().isEmpty()) {
        return "Tên chủ xe không được để trống!";
    }
		if (months <= 0) {
        return "Số tháng đăng ký phải lớn hơn 0!";
    }

    List<SubscriptionCard> cards = storage.getSubscriptionCards();

    // Kiểm tra xem biển số xe đã có thẻ tháng còn hạn chưa
    boolean hasActiveCard = cards.stream()
            .anyMatch(c -> c.getPlateNumber().equalsIgnoreCase(plateNumber) && c.isValid());

		if (hasActiveCard) {
        return "Biển số xe này đã có thẻ tháng đang còn hiệu lực!";
    }

    // Tạo mã thẻ mới (Ví dụ: C-00X)
    String newCardId = String.format("C-03", cards.size() + 1);
    LocalDate expiryDate = LocalDate.now().plusMonths(months);
    int totalFee = calculateTotalFee(vehicleType, months);

    SubscriptionCard newCard = new SubscriptionCard(newCardId, plateNumber.toUpperCase(), ownerName, vehicleType, expiryDate, totalFee);
		cards.add(newCard);

		return "Đăng ký thành công! Mã thẻ: " + newCardId + " (Hạn: " + expiryDate + ", Tổng tiền: " + totalFee + " VND)";
}



}
