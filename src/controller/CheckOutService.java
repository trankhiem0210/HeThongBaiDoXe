package controllers;

import java.time.LocalDateTime;
import models.ParkingSlot;
import models.ParkingTicket;
import models.PricingPolicy;
import models.PricingStragety;
import models.SubscriptionCard;
import repository.DataStorage;

public class CheckOutService {
	private final DataStorage dataStorage;
	private final PricingPolicy pricingPolicy;

	public CheckOutService() {
		this.dataStorage = DataStorage.getInstance(); // Gọi Singleton DataStorage
		this.pricingPolicy = new PricingPolicy(new PricingStragety());
	}

	public String performCheckOut(String slotId, String ticketId) {
		// 1. Kiểm tra Vị trí đỗ
		ParkingSlot foundSlot = null;
		for (ParkingSlot slot : dataStorage.getParkingSlots()) {
			if (slot.getSlotId().equalsIgnoreCase(slotId)) {
				foundSlot = slot; break;
			}
		}
		if (foundSlot == null || !foundSlot.isOccupied()) {
			return "Lỗi: Không tìm thấy vị trí đỗ hoặc vị trí đang trống!";
		}

		// 2. Kiểm tra Vé xe
		ParkingTicket foundTicket = null;
		for (ParkingTicket ticket : dataStorage.getParkingTickets()) {
			if (ticket.getTicketId().equalsIgnoreCase(ticketId)) {
				foundTicket = ticket; break;
			}
		}
		if (foundTicket == null) {
			return "Lỗi: Không tìm thấy vé xe trên hệ thống!";
		}

		// 3. Kiểm tra Thẻ tháng (So khớp biển số và hạn dùng)
		boolean hasValidSubscription = false;
		for (SubscriptionCard card : dataStorage.getSubscriptionCards()) {
			if (card.getPlateNumber().equalsIgnoreCase(foundTicket.getPlateNumber()) && card.isValid()) {
				hasValidSubscription = true; break;
			}
		}

		// 4. Chốt giờ ra và tính tiền
		foundTicket.setExitTime(LocalDateTime.now());
		double totalFee = pricingPolicy.calculateFee(foundTicket, hasValidSubscription);
		
		foundTicket.setTotalFee(totalFee);
		// Chú ý: Cần chắc chắn trong file ParkingTicket của bạn có hàm setCheckout(boolean)
		foundTicket.setCheckout(true); 

		// 5. Cập nhật lại kho dữ liệu
		dataStorage.getParkingTickets().remove(foundTicket);
		dataStorage.getParkingTicketsHistory().add(foundTicket);
		foundSlot.setOccupied(false);

		return "Check-out thành công! Số tiền thanh toán: " + totalFee + " VNĐ";
	}
}