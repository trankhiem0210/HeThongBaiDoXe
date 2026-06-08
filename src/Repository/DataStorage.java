package Repository;

import java.util.ArrayList;
import java.util.List;

import enums.Role;
import model.MonthlyCard;
import model.ParkingSession;
import model.Transaction;
import model.User;

public class DataStorage {
    private static DataStorage instance;
    
    private List<User> users;
    private List<ParkingSession> parkingSessions;
    private List<MonthlyCard> monthlyCards;
    private List<Transaction> transactions;

    // Sử dụng private constructor để áp dụng Singleton Pattern
    private DataStorage() {
        this.users = new ArrayList<>();
        this.parkingSessions = new ArrayList<>();
        this.monthlyCards = new ArrayList<>();
        this.transactions = new ArrayList<>();
        
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
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null; // Không tìm thấy người dùng
    }

    public List<ParkingSession> getParkingSessions() {
        return parkingSessions;
    }

    public void addParkingSession(ParkingSession session) {
        parkingSessions.add(session);
    }

    public List<MonthlyCard> getMonthlyCards() {
        return monthlyCards;
    }

    public void addMonthlyCard(MonthlyCard card) {
        monthlyCards.add(card);
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }
}