package controller;

import enums.VehicleType;

public class ParkingSystemFacade {
	private static ParkingSystemFacade instance;
	
	private final SearchService searchService;
	private final CheckInController checkInController;
	private final CheckOutService checkOutService;
	private final SubscriptionService subscriptionService;
	private final ReportService reportService;

	private ParkingSystemFacade() {
		this.searchService = new SearchService();
		this.checkInController = new CheckInController();
		this.checkOutService = new CheckOutService();
		this.subscriptionService = new SubscriptionService();
		this.reportService = new ReportService();
	}

	public static ParkingSystemFacade getInstance() {
		if (instance == null) {
			synchronized (ParkingSystemFacade.class) {
				if (instance == null) {
					instance = new ParkingSystemFacade();
				}
			}
		}
		return instance;
	}

	public String searchVehicle(String keyword) {
		return searchService.searchVehicle(keyword);
	}
	
	public CheckInController getCheckInController() {
		return checkInController;
	}
	
	public SearchService getSearchService() {
		return searchService;
	}
	
	public String performCheckOut(String slotId, String ticketId) {
		return checkOutService.performCheckOut(slotId, ticketId);
	}

	public String registerSubscriptionCard(String plateNumber, String ownerName, VehicleType vehicleType, int months) {
		return subscriptionService.registerSubscriptionCard(plateNumber, ownerName, vehicleType, months);
	}

	public int getCurrentlyParkedCount() {
		return reportService.getCurrentlyParkedCount();
	}

	public long getDailyCheckInCount() {
		return reportService.getDailyCheckInCount();
	}

	public long getDailyCheckOutCount() {
		return reportService.getDailyCheckOutCount();
	}

	public double getCurrentMonthRevenue() {
		return reportService.getCurrentMonthRevenue();
	}
}
