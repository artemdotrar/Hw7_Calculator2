import java.util.Scanner;

public class Menu {
    private final static String ERROR_MESSAGE_1 = "Некорректное значение";
    private final static int MAX_USERS = 3;
    private boolean isCalcMenuWorking = true;
    private boolean isUserMenuWorking = true;
    public Calculator calc = new Calculator();
    public User[] users = new User[MAX_USERS];
    private int countOfUsers = 0;
    private int userLoggonId = -1;

    public void printUserMenu() {
        int point = inputValidInt("\n1)Регистрация\t2)Авторизация\t3)Список пользователей\n", ERROR_MESSAGE_1);
        switch (point) {
            case 1:
                if (countOfUsers < MAX_USERS) {
                    users[countOfUsers] = registration();
                } else {
                    System.out.println("Превышено максимальное число пользователей");
                }
                break;
            case 2:
                int userId = authorization();
                break;
            case 3:
                printUsersList();
                break;
            default:
                System.out.println(ERROR_MESSAGE_1);
        }
    }

    public void printCalcMenu() {
        int point = inputValidInt("\n1)Сложение\t2)Вычитание\t3)Умножение\t4)Деление\t5)Вывод истории операций\t6)Выход\n)", ERROR_MESSAGE_1);
        if (point == 6) {
            isCalcMenuWorking = false;
        } else if (point == 5) {
            System.out.println(calc.operationList.getHistory());
        } else if (point > 0 && point < 5) {
            calc.setFirstNumber(inputValidDouble("Введите число 1: ", ERROR_MESSAGE_1));
            calc.setSecondNumber(inputValidDouble("Введите число 2: ", ERROR_MESSAGE_1));
            System.out.println(calc.action(point));
        } else {
            System.out.println(ERROR_MESSAGE_1);
        }
    }

    private User registration() {
        Scanner scanner = new Scanner(System.in);
        String password = "";
        String name = "";
        String login = "";

        while (true) {
            System.out.println("Введите логин: ");
            boolean isFreeLogin = true;
            login = scanner.nextLine();
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
        password = scanner.nextLine();
        System.out.println("Введите имя пользователя: ");
        name = scanner.nextLine();
        countOfUsers++;
        return new User(login, name, password);
    }

    private int authorization() { //возвращает индекс юзера в массиве при успешной авторизации или -1
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
                System.out.printf("\nЗдравствуйте, %s, добро пожаловать в калькулятор", users[userId].getName());
                isUserMenuWorking = false;
                return userId;
            } else {
                System.out.println("Неверный пароль");
                return -1;
            }
        }
    }

    private void printUsersList() {
        for (int i = 0; i < countOfUsers; i++) {
            System.out.print("[" + users[i].getLogin() + " : " + users[i].getName() + "]\t");
        }
        System.out.println();
    }

    public boolean isCalcMenuWorking() {
        return isCalcMenuWorking;
    }

    public boolean isUserMenuWorking() {
        return isUserMenuWorking;
    }

    private static double inputValidDouble(String messageInput, String messageError) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print(messageInput);
            if (scanner.hasNextDouble()) {
                return scanner.nextDouble();
            } else {
                System.out.println(messageError);
                scanner.next();
            }
        }
    }

    private static int inputValidInt(String messageInput, String messageError) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print(messageInput);
            if (scanner.hasNextDouble()) {
                return scanner.nextInt();
            } else {
                System.out.println(messageError);
                scanner.next();
            }
        }
    }
}
