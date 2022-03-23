import java.util.Scanner;

public class Menu {
    private final static String ERROR_MESSAGE_1 = "Некорректное значение";
    private boolean isCalcMenuWorking = true;
    private boolean isUserMenuWorking = true;
    private Calculator calc = new Calculator();
    private int currentUserId = -1;
    private UsersDataBase usersDB = new UsersDataBase();

    public void printUserMenu() {
        int point = inputValidInt("\n1)Регистрация\t2)Авторизация\t3)Список пользователей\n", ERROR_MESSAGE_1);
        switch (point) {
            case 1:
                if (usersDB.getCountOfUsers() < usersDB.getMaxUsers()) {
                    usersDB.registration();
                } else {
                    System.out.println("Превышено максимальное число пользователей");
                }
                break;
            case 2:
                currentUserId = usersDB.authorization();
                if (currentUserId != -1) {
                    System.out.printf("\nЗдравствуйте, %s, добро пожаловать в калькулятор", usersDB.getUserNameById(currentUserId));
                    isUserMenuWorking = false;
                }
                break;
            case 3:
                usersDB.printUsersList();
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
