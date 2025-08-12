package controller;

import model.Funcionario;
import repository.FuncionarioRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
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

            System.out.print("Data de nascimento (dd/mm/aaaa): ");
            LocalDate dataDeNascimento = converterDataNascimentoParaUmFormatoValido(entrada.next());

            System.out.print("Salário (ex: 1512.50): ");
            double salario = entrada.nextDouble();

            System.out.print("Função: ");
            String funcao = entrada.next();

            Funcionario funcionario =
                    new Funcionario(nome, dataDeNascimento, BigDecimal.valueOf(salario), funcao);

            funcionarioRepository.adicionar(funcionario);

            return "Funcionário cadastrado com sucesso!";
        } catch (Exception ex) {
            return "Os campos recebidos foram inválidos, tente novamente!";
        }
    }

    public void listarFuncionarios() {
        List<Funcionario> funcionarios = funcionarioRepository.getFuncionarios();

        for (Funcionario funcionario : funcionarios) {
            System.out.println(funcionario);
            System.out.println("----------");
        }
    }

    private LocalDate converterDataNascimentoParaUmFormatoValido(String dataNascimento) {
        String[] dataNascimentoSeparadaEntreDiaMesEAno = dataNascimento.split("/");
        int dia = Integer.parseInt(dataNascimentoSeparadaEntreDiaMesEAno[0]);
        int mes = Integer.parseInt(dataNascimentoSeparadaEntreDiaMesEAno[1]);
        int ano = Integer.parseInt(dataNascimentoSeparadaEntreDiaMesEAno[2]);

        return LocalDate.of(ano, mes, dia);
    }
}
