import java.util.Scanner;

public class Menu {
    private boolean isWorking = true;
    public Calculator calc = new Calculator();

    public boolean isWorking() {
        return isWorking;
    }

    public void printMenu() {
        int point = 0;
        System.out.println("\n1)Сложение\t2)Вычитание\t3)Умножение\t4)Деление\t5)Вывод истории операций\t6)Выход)");

        Scanner scanner = new Scanner(System.in);
        if (scanner.hasNextInt()) {
            point = scanner.nextInt();
            if (point == 6) {
                isWorking = false;
            } else if (point == 5) {
                System.out.println(calc.operationList.getHistory());
            } else if (point > 0 && point < 5) {
                System.out.print("Введите число 1: ");
                if (scanner.hasNextDouble()) {
                    calc.setFirstNumber(scanner.nextDouble());
                } else {
                    printError(2);
                    return;
                }
                System.out.print("Введите число 2: ");
                if (scanner.hasNextDouble()) {
                    calc.setSecondNumber(scanner.nextDouble());
                } else {
                    printError(2);
                    return;
                }
                calc.action(point);
                System.out.println(calc.operationList.getHistory());
            } else {
                printError(1);
            }
        } else {
            printError(1);
        }
    }

    private void printError(int id) {
        switch (id) {
            case 1:
                System.out.println("Некорректное значение. Пункт меню должен быть цифрой от 1 до 5");
                break;
            default:
                System.out.println("Некорректное значение");
        }
    }

}
