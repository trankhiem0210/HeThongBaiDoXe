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
	private DataStorage dataStorage;

	/**
	 * @param dataStorage
	 */
	public SearchService() {
		this.dataStorage = DataStorage.getInstance();
	}

	public String searchVehicle(String keyword) {
		if (keyword == null || keyword.trim().isEmpty()) {
			return "Vui long nhap tu khoa tim kiem (bien so, ma ve, hoac ma the thang)";
		}
		String searchKey = keyword.trim().toLowerCase();

		String plateFromCard = dataStorage.getSubscriptionCards().stream()
				.filter(c -> c.getCardId().toLowerCase().equals(searchKey)).map(c -> c.getPlateNumber().toLowerCase())
				.findFirst().orElse(null);

		// Ap dung tu khoa bien so xe tu the thang neu khong tim duoc dung nguyen goc
		String plateToSearch = (plateFromCard != null) ? plateFromCard : searchKey;

		StringBuilder resultBuilder = new StringBuilder();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
		boolean isFound = false;

		// Tim trong danh sach cac xe dang do trong bai
		for (ParkingTicket parkingTicket : dataStorage.getParkingTickets()) {
			String plate = parkingTicket.getPlateNumber().toLowerCase();
			String ticketId = parkingTicket.getTicketId().toLowerCase();

			if (plate.contains(plateToSearch) || ticketId.contains(searchKey)) {
				resultBuilder.append("Trạng thái: Đang đỗ | ").append("Biển số: ").append(parkingTicket.getPlateNumber())
						.append(" | Mã vé: ").append(parkingTicket.getTicketId()).append(" | Giờ vào: ")
						.append(parkingTicket.getEntryTime().format(formatter)).append("\n");
				isFound = true;
			}
		}
		// Tim trong danh sach cac xe da tung do trong bai
		for (ParkingTicket parkingTicket : dataStorage.getParkingTicketsHistory()) {
			String plate = parkingTicket.getPlateNumber().toLowerCase();
			String ticketId = parkingTicket.getTicketId().toLowerCase();

			if (plate.contains(plateToSearch) || ticketId.contains(searchKey)) {
				resultBuilder.append("Trạng thái: Đã rời bãi | ").append("Biển số: ").append(parkingTicket.getPlateNumber())
						.append(" | Mã vé: ").append(parkingTicket.getTicketId()).append(" | Giờ vào: ")
						.append(parkingTicket.getEntryTime().format(formatter)).append("\n");
				isFound = true;
			}
		}
		return isFound ? resultBuilder.toString() : "Khong tim thay thong tin xe nao phu hop voi: " + searchKey;
	}
}
