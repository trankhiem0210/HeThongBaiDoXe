package repository;

import java.util.ArrayList;
import java.util.List;

import enums.Role;
import model.ParkingSlot;
import model.SubscriptionCard;
import model.User;

public class DataStorage {
    private static DataStorage instance;
    
    private List<User> users;
    private List<ParkingSlot> parkingSlots;
    private List<SubscriptionCard> subscriptionCards;

    // Sử dụng private constructor để áp dụng Singleton Pattern
    private DataStorage() {
        this.users = new ArrayList<>();
        this.parkingSlots = new ArrayList<ParkingSlot>();
        this.subscriptionCards = new ArrayList<SubscriptionCard>();
        
        initializeMockData();
    }

    private void initializeMockData() {
        // Thêm một số người dùng mẫu
        users.add(new User("admin", "admin123", Role.ADMIN));
        users.add(new User("user1", "password1", Role.STAFF));
        users.add(new User("user2", "password2", Role.STAFF));
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