package controller;

import model.Funcionario;
import repository.FuncionarioRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
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
            LocalDate dataDeNascimento = obterDataNascimento(entrada.next());

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

    public void obterAgrupadosPorFuncao() {
        Map<String, List<Funcionario>> funcionariosAgrupadosPorFuncao = funcionarioRepository.obterAgrupadosPorFuncao();

        for (String funcao : funcionariosAgrupadosPorFuncao.keySet()) {
            System.out.println(funcao.toUpperCase());
            listar(funcionariosAgrupadosPorFuncao.get(funcao));
        }
    }

    public void removerPorNome() {
        int numeroDeFuncionariosAntesDaRemocao = funcionarioRepository.getFuncionarios().size();

        System.out.print("Nome para remoção: ");
        String nome = entrada.next();

        funcionarioRepository.removerPorNome(nome);

        int numeroDeFuncionariosAposARemocao = funcionarioRepository.getFuncionarios().size();

        if (numeroDeFuncionariosAntesDaRemocao == numeroDeFuncionariosAposARemocao) {
            System.out.println("Não foram encontrados funcionários com este nome!");
        } else {
            System.out.println("Funcionários removidos com sucesso!");
        }
    }

    public void listar(List<Funcionario> funcionarios) {
        for (Funcionario funcionario : funcionarios) {
            System.out.println(funcionario);
        }
    }

    public void listar() {
        for (Funcionario funcionario : funcionarioRepository.getFuncionarios()) {
            System.out.println(funcionario);
        }
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

    public void obterOrdenadosPorNome() {
        listar(funcionarioRepository.obterOrdenadosPorNome());
    }

    public void obterTotalDosSalarios() {
        double total = 0.0;

        for (Funcionario funcionario : funcionarioRepository.getFuncionarios()) {
            total += funcionario.getSalario().doubleValue();
        }

        System.out.printf("Total: %.2f%n", total);
    }

    public void darAumentoATodos() {
        try {
            System.out.print("Porcentagem do aumento: ");
            int porcentagem = entrada.nextInt();

            if (porcentagem < 0) {
                System.out.println("O aumento tem que ser de ao menos 1%!");
            } else {
                for (Funcionario funcionario : funcionarioRepository.getFuncionarios()) {
                    funcionario.darAumento(porcentagem);
                }
            }
        } catch (Exception ignored) {

        }
    }

    private LocalDate obterDataNascimento(String dataNascimento) {
        String[] dataNascimentoSeparadaEmDiaMesEAno = dataNascimento.split("/");
        int dia = Integer.parseInt(dataNascimentoSeparadaEmDiaMesEAno[0]);
        int mes = Integer.parseInt(dataNascimentoSeparadaEmDiaMesEAno[1]);
        int ano = Integer.parseInt(dataNascimentoSeparadaEmDiaMesEAno[2]);

        return LocalDate.of(ano, mes, dia);
    }
}
