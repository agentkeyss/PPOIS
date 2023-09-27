import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class User {

    private final String userName;
    private final String userSurname;
    private final LocalDateTime registrationDateTime;
    public static ArrayList<User> listOfAllUsers = new ArrayList<>();

    public User(String userName, String userSurname) {
        this.userName = userName;
        this.userSurname = userSurname;
        this.registrationDateTime = LocalDateTime.now();
        listOfAllUsers.add(this);
    }

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", userSurname='" + userSurname + '\'' +
                ", birthDate=" + registrationDateTime.format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss")) +
                '}';
    }
}