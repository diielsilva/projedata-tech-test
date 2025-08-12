import controller.MenuController;

import java.util.Scanner;

public class Aplicacao {
    public static void main(String[] args) {
        MenuController menu = new MenuController(new Scanner(System.in));

        menu.exibirMenu();
    }
}
