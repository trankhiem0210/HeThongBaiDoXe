/**
 * 
 */
package controller;

import java.time.format.DateTimeFormatter;

import model.ParkingTicket;
import repository.DataStorage;

/**
 * 
 */
public class SearchService {
	private final DataStorage dataStorage;
	
	private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

	/**
	 * @param dataStorage
	 */
	public SearchService() {
		this.dataStorage = DataStorage.getInstance();
	}

	public String searchVehicle(String keyword) {
		if (keyword == null || keyword.trim().isEmpty()) {
			return "Vui lòng nhập từ khóa tìm kiếm (biển số, mã vé, hoặc mã thẻ tháng).";
		}
		String searchKey = keyword.trim().toLowerCase();

		String plateFromCard = dataStorage.getSubscriptionCards().stream()
				.filter(c -> c.getCardId() != null && searchKey.equals(c.getCardId().toLowerCase()))
				.map(c -> c.getPlateNumber() != null ? c.getPlateNumber().toLowerCase() : null)
				.findFirst().orElse(null);

		// Ap dung tu khoa bien so xe tu the thang neu khong tim duoc dung nguyen goc
		String plateToSearch = (plateFromCard != null) ? plateFromCard : searchKey;

		StringBuilder resultBuilder = new StringBuilder();
		boolean isFound = false;

		// Tim trong danh sach cac xe dang do trong bai
		for (ParkingTicket parkingTicket : dataStorage.getParkingTickets()) {
			if (appendTicketInfo(resultBuilder, parkingTicket, plateToSearch, searchKey, "Đang đỗ")) {
				isFound = true;
			}
		}
		// Tim trong danh sach cac xe da tung do trong bai
		for (ParkingTicket parkingTicket : dataStorage.getParkingTicketsHistory()) {
			if (appendTicketInfo(resultBuilder, parkingTicket, plateToSearch, searchKey, "Đã rời bãi")) {
				isFound = true;
			}
		}
		return isFound ? resultBuilder.toString() : "Không tìm thấy thông tin xe nào phù hợp với: " + keyword;
	}

	private boolean appendTicketInfo(StringBuilder builder, ParkingTicket ticket, String plateToSearch, String searchKey, String status) {
		String plate = ticket.getPlateNumber() != null ? ticket.getPlateNumber().toLowerCase() : "";
		String ticketId = ticket.getTicketId() != null ? ticket.getTicketId().toLowerCase() : "";

		if (plate.contains(plateToSearch) || ticketId.contains(searchKey)) {
			builder.append("Trạng thái: ").append(status).append(" | Biển số: ").append(ticket.getPlateNumber())
					.append(" | Mã vé: ").append(ticket.getTicketId()).append(" | Giờ vào: ")
					.append(ticket.getEntryTime().format(FORMATTER)).append("\n");
			return true;
		}
		return false;
	}
}
