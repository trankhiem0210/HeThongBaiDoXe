package controller;

public class ParkingSystemFacade {
	private static ParkingSystemFacade instance;
	
	private final SearchService searchService;
	private final CheckInController checkInController;

	private ParkingSystemFacade() {
		this.searchService = new SearchService();
		this.checkInController = new CheckInController();
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
}


