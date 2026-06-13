package controller;

public class ParkingSystemFacade {
	private static ParkingSystemFacade instance;
	
	private final SearchService searchService;

	private ParkingSystemFacade() {
		this.searchService = new SearchService();
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
}