package repository;

import model.Funcionario;

import java.util.ArrayList;
import java.util.List;

public class FuncionarioRepository {
    private final List<Funcionario> funcionarios = new ArrayList<>();

    public void adicionar(Funcionario funcionario) {
        funcionarios.add(funcionario);
    }

    public void removerPeloNome(String nome) {
        List<Integer> indicesParaRemocao = new ArrayList<>();

        for (int i = 0; i < funcionarios.size(); i++) {
            Funcionario funcionario = funcionarios.get(i);

            if (funcionario.getNome().equals(nome)) {
                indicesParaRemocao.add(i);
            }
        }

        for (Integer indice : indicesParaRemocao) {
            funcionarios.remove(indice);
        }
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
}
