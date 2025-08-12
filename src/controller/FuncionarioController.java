package controller;

import model.Funcionario;
import repository.FuncionarioRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Scanner;

public class FuncionarioController {
    private final FuncionarioRepository funcionarioRepository;

    public FuncionarioController(FuncionarioRepository funcionarioRepository) {
        this.funcionarioRepository = funcionarioRepository;
    }

    public String inserirFuncionario() {
        try {
            Scanner entrada = new Scanner(System.in);
            System.out.print("Nome: ");
            String nome = entrada.next();

            System.out.println();

            System.out.print("Data de nascimento (dd/mm/aaaa): ");
            String dataDeNascimento = entrada.next();

            System.out.println();

            System.out.print("Salário (ex: 1512.50): ");
            double salario = entrada.nextDouble();

            System.out.println();

            System.out.print("Função: ");
            String funcao = entrada.next();

            Funcionario funcionario =
                    new Funcionario(nome, LocalDate.parse(dataDeNascimento), BigDecimal.valueOf(salario), funcao);

            funcionarioRepository.adicionar(funcionario);

            return "Funcionário cadastrado com sucesso!";
        } catch (Exception ex) {
            return "Os campos recebidos foram inválidos, tente novamente!";
        }
    }
}
