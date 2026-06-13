package repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import enums.Role;
import enums.VehicleType;
import model.ParkingArea;
import model.ParkingSlot;
import model.ParkingTicket;
import model.SubscriptionCard;
import model.User;

public class DataStorage {
    private static DataStorage instance;
    
    private List<User> users;
    private List<ParkingSlot> parkingSlots;
    private List<SubscriptionCard> subscriptionCards;
    private List<ParkingTicket> parkingTickets;
    private List<ParkingTicket> parkingTicketsHistory;
    private List<ParkingArea> parkingAreas;

    // Sử dụng private constructor để áp dụng Singleton Pattern
    private DataStorage() {
        this.users = new ArrayList<>();
        this.parkingSlots = new ArrayList<ParkingSlot>();
        this.subscriptionCards = new ArrayList<SubscriptionCard>();
        this.parkingTickets = new ArrayList<ParkingTicket>();
        this.parkingTicketsHistory = new ArrayList<ParkingTicket>();
        this.parkingAreas = new ArrayList<ParkingArea>();

        initDefaultData();
    }

    private void initDefaultData() {
        // Thêm một số người dùng mẫu
        users.add(new User("admin", "admin123", Role.ADMIN));
        users.add(new User("user1", "password1", Role.STAFF));
        users.add(new User("user2", "password2", Role.STAFF));
        
        subscriptionCards.add(new SubscriptionCard("C-001", "ABC-123", "Khách Hàng A", VehicleType.CAR, LocalDate.now().plusMonths(1))); 
		subscriptionCards.add(new SubscriptionCard("C-002", "DEF-456", "Khách Hàng B", VehicleType.CAR, LocalDate.now().minusDays(5)));
   
		parkingAreas.add(new ParkingArea("A", "Khu A", VehicleType.CAR));
		parkingAreas.add(new ParkingArea("B", "Khu B", VehicleType.MOTORBIKE));
		
		for (int i = 1; i < 10; i++) {
			parkingSlots.add(new ParkingSlot("A" + i, VehicleType.CAR, false));
		}
		for (int i = 1; i < 10; i++) {
			parkingSlots.add(new ParkingSlot("B-"+ i, VehicleType.MOTORBIKE, false));
		}
		
    }

    // Hàm lấy instance duy nhất của DataStorage
    public static DataStorage getInstance() {
        if (instance == null) {
            instance = new DataStorage();
        }
        return instance;
    }

    public List<User> getUsers() {
        return users;
    }

    public void addUser(User user) {
        users.add(user);
    }

    public User findUserByUsername(String username) {
        for (User user : users) {
            if (user.getUserName().equals(username)) {
                return user;
            }
        }
        return null; // Không tìm thấy người dùng
    }

   
}