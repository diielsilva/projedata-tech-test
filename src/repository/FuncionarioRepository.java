package repository;

import model.Funcionario;
import model.Pessoa;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

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

    public List<Funcionario> filtrarPorMesDeNascimento(int mes) {
        List<Funcionario> funcionariosQueNasceramNoMesSelecionado = new ArrayList<>();

        for (Funcionario funcionario : funcionarios) {
            if (funcionario.getDataNascimento().getMonthValue() == mes) {
                funcionariosQueNasceramNoMesSelecionado.add(funcionario);
            }
        }

        return funcionariosQueNasceramNoMesSelecionado;
    }

    public List<Funcionario> obterFuncionariosOrdenadosPeloNome() {
        List<Funcionario> funcionariosOrdenadosPeloNome = new ArrayList<>(funcionarios);

        funcionariosOrdenadosPeloNome.sort(Comparator.comparing(Pessoa::getNome));

        return funcionariosOrdenadosPeloNome;
    }

    public Optional<Funcionario> obterFuncionarioMaisVelho() {
        if (funcionarios.isEmpty()) {
            return Optional.empty();
        }

        Funcionario funcionarioMaisVelho = funcionarios.getFirst();

        for (Funcionario funcionario : funcionarios) {
            boolean oFuncionarioAtualEMaisVelho = funcionario.getDataNascimento().toEpochDay() > funcionarioMaisVelho.getDataNascimento().toEpochDay();

            if (oFuncionarioAtualEMaisVelho) {
                funcionarioMaisVelho = funcionario;
            }
        }

        return Optional.of(funcionarioMaisVelho);
    }
}
