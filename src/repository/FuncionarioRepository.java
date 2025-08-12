package repository;

import model.Funcionario;
import model.Pessoa;

import java.util.*;

public class FuncionarioRepository {
    private List<Funcionario> funcionarios = new ArrayList<>();

    public void adicionar(Funcionario funcionario) {
        funcionarios.add(funcionario);
    }

    public List<Funcionario> getFuncionarios() {
        return funcionarios;
    }

    public void removerPorNome(String nome) {
        funcionarios = funcionarios
                .stream()
                .filter(funcionario -> !funcionario.getNome().equals(nome))
                .toList();
    }

    public List<Funcionario> obterPorMesDeNascimento(int mes) {
        return funcionarios
                .stream()
                .filter(funcionario -> funcionario.getDataNascimento().getMonthValue() == mes)
                .toList();
    }

    public List<Funcionario> obterOrdenadosPorNome() {
        return funcionarios
                .stream()
                .sorted(Comparator.comparing(Pessoa::getNome))
                .toList();
    }

    public Optional<Funcionario> obterMaisExperiente() {
        if (funcionarios.isEmpty()) {
            return Optional.empty();
        }

        Funcionario funcionarioMaisExperiente = funcionarios.getFirst();

        for (Funcionario funcionario : funcionarios) {
            boolean oFuncionarioAtualEMaisExperienteQueOAnterior =
                    funcionario.getDataNascimento().toEpochDay() > funcionarioMaisExperiente.getDataNascimento().toEpochDay();

            if (oFuncionarioAtualEMaisExperienteQueOAnterior) {
                funcionarioMaisExperiente = funcionario;
            }
        }

        return Optional.of(funcionarioMaisExperiente);
    }

    public Map<String, List<Funcionario>> obterAgrupadosPorFuncao() {
        Map<String, List<Funcionario>> funcionariosAgrupadosPorFuncao = new HashMap<>();

        for (Funcionario funcionario : funcionarios) {
            String funcao = funcionario.getFuncao();

            if (!funcionariosAgrupadosPorFuncao.containsKey(funcao)) {
                funcionariosAgrupadosPorFuncao.put(funcao, new ArrayList<>(List.of(funcionario)));
            } else {
                funcionariosAgrupadosPorFuncao.get(funcao).add(funcionario);
            }
        }

        return funcionariosAgrupadosPorFuncao;
    }
}
