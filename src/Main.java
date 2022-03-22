
public class Main {
    public static void main(String[] args){
        Menu menu = new Menu();

        System.out.println("Калькулятор");
        while (menu.isUserMenuWorking()){
            menu.printUserMenu();
        }
        while (menu.isCalcMenuWorking()){
            menu.printCalcMenu();
        }
    }
}
