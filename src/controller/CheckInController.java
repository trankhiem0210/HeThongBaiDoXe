package controller;

import java.time.LocalDateTime;
import enums.VehicleType;
import model.ParkingSlot;
import model.ParkingTicket;
import repository.DataStorage;

public class CheckInController {
	private final CheckInService checkInService;
	private final DataStorage dataStorage;
	
	public CheckInController() {
		this.checkInService = new CheckInService();
		this.dataStorage = DataStorage.getInstance();
	}
	
	/**
	 * Xử lý check-in xe
	 */
	public String handleCheckIn(String plateNumber, VehicleType vehicleType) {
		// Kiểm tra biển số hợp lệ
		if (!checkInService.isValidPlateNumber(plateNumber)) {
			return "Lỗi: Biển số xe không hợp lệ! Định dạng: ABC-123";
		}
		
		// Kiểm tra xe đã check-in chưa
		if (checkInService.isVehicleAlreadyParked(plateNumber)) {
			return "Lỗi: Xe này đã check-in rồi! Vui lòng check-out trước.";
		}
		
		// Kiểm tra còn chỗ đỗ không
		if (!checkInService.hasAvailableSlot(vehicleType)) {
			return "Lỗi: Bãi đỗ " + vehicleType.name() + " đã đầy! Vui lòng quay lại sau.";
		}
		
		// Lấy vị trí đỗ trống
		ParkingSlot slot = checkInService.getAvailableSlot(vehicleType);
		
		// Tạo mã vé
		String ticketId = generateTicketId();
		
		// Tạo vé đỗ
		ParkingTicket ticket = new ParkingTicket(
				ticketId,
				plateNumber.toUpperCase(),
				vehicleType,
				LocalDateTime.now(),
				null,
				0.0,
				false
		);
		
		// Cập nhật vị trí đỗ
		slot.setOccupied(true);
		
		// Lưu vé vào danh sách
		dataStorage.getParkingTickets().add(ticket);
		
		// Trả về thông báo thành công
		return "✓ CHECK-IN THÀNH CÔNG!\n"
				+ "═══════════════════════════════\n"
				+ "Mã Vé: " + ticketId + "\n"
				+ "Biển Số: " + plateNumber.toUpperCase() + "\n"
				+ "Loại Xe: " + vehicleType.name() + "\n"
				+ "Vị Trí Đỗ: " + slot.getSlotId() + "\n"
				+ "Giờ Vào: " + ticket.getEntryTime() + "\n"
				+ "═══════════════════════════════";
	}
	
	/**
	 * Tạo mã vé tự động
	 */
	private String generateTicketId() {
		long timestamp = System.currentTimeMillis();
		return "TK" + timestamp;
	}
	
	/**
	 * Lấy thông tin chỗ đỗ
	 */
	public String getSlotInfo() {
		int carFree = checkInService.getAvailableSlotsCount(VehicleType.CAR);
		int carTotal = checkInService.getTotalSlotsCount(VehicleType.CAR);
		int bikeFree = checkInService.getAvailableSlotsCount(VehicleType.MOTORBIKE);
		int bikeTotal = checkInService.getTotalSlotsCount(VehicleType.MOTORBIKE);
		
		return String.format("Ô tô: %d/%d | Xe máy: %d/%d", carFree, carTotal, bikeFree, bikeTotal);
	}
}

