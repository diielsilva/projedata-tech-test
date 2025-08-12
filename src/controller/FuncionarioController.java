package controller;

import model.Funcionario;
import repository.FuncionarioRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class FuncionarioController {
    private final Scanner entrada = new Scanner(System.in);
    private final FuncionarioRepository funcionarioRepository;

    public FuncionarioController(FuncionarioRepository funcionarioRepository) {
        this.funcionarioRepository = funcionarioRepository;
    }

    public void adicionar() {
        try {
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

            System.out.println("Funcionário inserido com sucesso!");
        } catch (Exception ex) {
            System.out.println("Os campos recebidos foram inválidos, tente novamente!");
        }
    }

    public void listarFuncionarios() {
        List<Funcionario> funcionarios = funcionarioRepository.getFuncionarios();

        for (Funcionario funcionario : funcionarios) {
            System.out.println(funcionario);
            System.out.println("----------");
        }
    }

    public void removerPeloNome() {
        List<Funcionario> funcionarios = funcionarioRepository.getFuncionarios();

        System.out.print("Nome para remoção: ");
        String nome = entrada.next();

        funcionarioRepository.removerPorNome(nome);

        List<Funcionario> listaDeFuncionariosAposRemocao = funcionarioRepository.getFuncionarios();

        boolean houveRemocoes = funcionarios.size() != listaDeFuncionariosAposRemocao.size();
    }

    public void aniversariantesDoMes() {
        try {
            System.out.print("Valor do mês (1 - Jan, 2 - Fev...): ");
            int mes = entrada.nextInt();

            List<Funcionario> aniversariantes = funcionarioRepository.obterPorMesDeNascimento(mes);

            for (Funcionario funcionario : aniversariantes) {
                System.out.println(funcionario);
                System.out.println("----------");
            }
        } catch (Exception ignored) {

        }
    }

    public void obterMaisExperiente() {
        Optional<Funcionario> funcionarioMaisExperiente = funcionarioRepository.obterMaisExperiente();

        if (funcionarioMaisExperiente.isPresent()) {
            System.out.println(funcionarioMaisExperiente.get());
        } else {
            System.out.println("Não há funcionários cadastrados!");
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
