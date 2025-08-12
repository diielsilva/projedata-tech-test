package model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Funcionario extends Pessoa {
    private BigDecimal salario;
    private String funcao;

    public Funcionario(String nome, LocalDate dataNascimento, BigDecimal salario, String funcao) {
        super(nome, dataNascimento);
        this.salario = salario;
        this.funcao = funcao;
    }

    public void darAumento(int porcentagem) {
        if (porcentagem > 0) {
            double aumento = porcentagem / 100.0;

            aumento += 1;

            salario = salario.multiply(BigDecimal.valueOf(aumento));
        }
    }

    @Override
    public String toString() {
        return "Funcionario {" +
                " Nome = '" + nome + '\'' +
                ", Data de Nascimento = " + dataNascimento.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) +
                ", Salário = " + salario +
                ", Funcao = '" + funcao + '\'' +
                " }";
    }
}
