import java.io.*;
import java.util.Scanner;

public class UsersDataBase {
    private final String pathDB = "UserDB.txt";
    private final int maxUsers = 10;
    private int countOfUsers = 0;
    private User[] users = new User[maxUsers];


    public int getMaxUsers() {
        return maxUsers;
    }

    public int getCountOfUsers() {
        return countOfUsers;
    }

    public String getUserNameById(int id) {
        return users[id].getName();
    }

    public UsersDataBase() {
        loadDB();
    }

    private void loadDB() {
        try (BufferedReader br = new BufferedReader(new FileReader(pathDB))) {
            String line;
            countOfUsers = 0;
            while ((line = br.readLine()) != null){
                String[] userLine = line.split(" ");
                users[countOfUsers] = new User(userLine[0], userLine[1], userLine[2]);
                countOfUsers++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void saveUser(String login, String name, String password) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(pathDB, true))) {
            bw.write(login + " " + name + " " + password);
            bw.newLine();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void registration() {
        Scanner scanner = new Scanner(System.in);
        String password = "";
        String name = "";
        String login = "";

        while (true) {
            System.out.println("Введите логин: ");
            boolean isFreeLogin = true;
            login = scanner.nextLine().trim();
            for (int i = 0; i < countOfUsers; i++) {
                if (login.equals(users[i].getLogin())) {
                    isFreeLogin = false;
                    break;
                }
            }
            if (isFreeLogin) {
                break;
            } else {
                System.out.println("Этот логин уже занят");
            }
        }
        System.out.println("Введите пароль: ");
        password = scanner.nextLine().trim();
        System.out.println("Введите имя пользователя: ");
        name = scanner.nextLine().trim();
        users[countOfUsers] = new User(login, name, password);
        saveUser(login, name, password);
        countOfUsers++;
    }

    //возвращает индекс юзера в массиве при успешной авторизации или -1
    public int authorization() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите логин: ");
        String login = scanner.nextLine();
        int userId = -1;
        for (int i = 0; i < countOfUsers; i++) {
            if (users[i].getLogin().equals(login)) {
                userId = i;
                break;
            }
        }
        if (userId == -1) {
            System.out.println("Нет такой учетной записи");
            return userId;
        } else {

            System.out.println("Введите пароль: ");
            String password = scanner.nextLine();
            if (users[userId].isTruePassword(password)) {
                return userId;
            } else {
                System.out.println("Неверный пароль");
                return -1;
            }
        }
    }

    public void printUsersList() {
        for (int i = 0; i < countOfUsers; i++) {
            System.out.print("[" + users[i].getLogin() + " : " + users[i].getName() + "]\t");
        }
        System.out.println();
    }
}
