package controller;

import java.util.Scanner;

public class MenuController {
    private final Scanner entrada = new Scanner(System.in);
    private boolean estaExecutando = true;
    private final FuncionarioController funcionarioController;


    public MenuController(FuncionarioController funcionarioController) {
        this.funcionarioController = funcionarioController;
    }

    public void iniciar() {
        do {
            try {
                System.out.print("""
                        ----------------------------------
                        Escolha uma das opções abaixo
                        0 - Encerrar Programa
                        1 - Inserir Funcionário
                        2 - Listar Funcionários
                        3 - Listar Aniversariantes do Mês
                        4 - Listar Funcionário Mais Experiente
                        5 - Listar Funcionários Agrupados Por Função
                        6 - Listar Funcionários em Ordem Alfabética
                        7 - Listar Total dos Salários
                        8 - Remover Funcionários Pelo Nome
                        9 - Dar Aumento aos Funcionários
                        -----------------------------------
                        Opcão selecionada:  """);

                int opcao = entrada.nextInt();

                executarOpcaoEscolhida(opcao);

            } catch (Exception exception) {
                System.out.println("Opção inválida!");
                entrada.nextLine();
            }

        } while (estaExecutando);
    }

    private void executarOpcaoEscolhida(int opcao) {
        switch (opcao) {
            case 0:
                estaExecutando = false;
                break;
            case 1:
                funcionarioController.adicionar();
                break;
            case 2:
                funcionarioController.listar();
                break;
            case 3:
                funcionarioController.aniversariantesDoMes();
                break;
            case 4:
                funcionarioController.obterMaisExperiente();
                break;
            case 5:
                funcionarioController.obterAgrupadosPorFuncao();
                break;
            case 6:
                funcionarioController.obterOrdenadosPorNome();
                break;
            case 7:
                funcionarioController.obterTotalDosSalarios();
                break;
            case 8:
                funcionarioController.removerPorNome();
                break;
            case 9:
                funcionarioController.darAumentoATodos();
                break;
            default:
                System.out.println("Opção inválida!");
                break;
        }
    }
}
