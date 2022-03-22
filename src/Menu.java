import java.util.Scanner;

public class Menu {
    private final static String ERROR_MESSAGE_1 = "Некорректное значение";

    private boolean isCalcMenuWorking = true;
    private boolean isUserMenuWorking = true;
    public Calculator calc = new Calculator();
    public User[] users = new User[3];
    private int countOfUsers = 0;

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

    private User registration() {
        Scanner scanner = new Scanner(System.in);
        String password = "";
        String name = "";
        String login = "";
        System.out.println("Введите логин: ");
        login = scanner.nextLine();
        for (int i = 0; i < users.length; i++) {
            if (users[i].getLogin() == login) {
                System.out.println("Этот логин уже занят");
                return null;
            } else {
                System.out.println("Введите пароль: ");
                password = scanner.nextLine();
                System.out.println("Введите имя пользователя: ");
                name = scanner.nextLine();
            }
        }
        return new User(login, name, password);
    }

    private boolean authorization(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите логин: ");
        String login = scanner.nextLine();
        int userId = -1;
        for(int i = 0; i < users.length; i++){
            if(users[i].getLogin() == login){
                userId = i;
            }
        }
        if(userId == -1){
            System.out.println("Нет такой учетной записи");
            return false;
        }

        System.out.println("Введите пароль: ");
        String password = scanner.nextLine();
        if(users[userId].chekPassowrd(password)){
            return true;
        }

    }

    public boolean isCalcMenuWorking() {
        return isCalcMenuWorking;
    }

    public boolean isUserMenuWorking() {
        return isUserMenuWorking;
    }

    public void printUserMenu() {
        int point = inputValidInt("\n1)Регистрация\t2)Авторизация\t3)Список пользователей\n", ERROR_MESSAGE_1);
        switch (point) {
            case 1:
                users[countOfUsers] = registration();
                break;
            case 2:
                break;
            case 3:
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
            calc.action(point);
            System.out.println(calc.operationList.getHistory());
        } else {
            System.out.println(ERROR_MESSAGE_1);
        }
    }


}
