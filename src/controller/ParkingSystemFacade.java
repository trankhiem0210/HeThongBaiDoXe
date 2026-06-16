package controller;

import enums.VehicleType;

public class ParkingSystemFacade {
    private static ParkingSystemFacade instance;

    private final SearchService searchService;
    private final SubscriptionService subscriptionService;

    private ParkingSystemFacade() {
        this.searchService = new SearchService();
        this.subscriptionService = new SubscriptionService();
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

    public int calculateMonthlySubscriptionFee(VehicleType vehicleType) {
        return subscriptionService.calculateMonthlyFee(vehicleType);
    }

    public int calculateTotalSubscriptionFee(VehicleType vehicleType, int months) {
        return subscriptionService.calculateTotalFee(vehicleType, months);
    }

    public String registerSubscriptionCard(String plateNumber, String ownerName, VehicleType vehicleType, int months) {
        return subscriptionService.registerSubscriptionCard(plateNumber, ownerName, vehicleType, months);
    }


}
