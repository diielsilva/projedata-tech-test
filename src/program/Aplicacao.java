package program;

import controller.FuncionarioController;
import controller.MenuController;
import repository.FuncionarioRepository;

public class Aplicacao {
    public static void main(String[] args) {
        FuncionarioRepository funcionarioRepository = new FuncionarioRepository();
        FuncionarioController funcionarioController = new FuncionarioController(funcionarioRepository);
        MenuController menuController = new MenuController(funcionarioController);

        menuController.iniciar();
    }
}
