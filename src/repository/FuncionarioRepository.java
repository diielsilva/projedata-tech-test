package repository;

import model.Funcionario;

import java.util.ArrayList;
import java.util.List;

public class FuncionarioRepository {
    private final List<Funcionario> funcionarios = new ArrayList<>();

    public void adicionar(Funcionario funcionario) {
        funcionarios.add(funcionario);
    }
}
