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

    public String getFuncao() {
        return funcao;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public void darAumento(int porcentagem) {
        if (porcentagem > 0) {
            double aumento = porcentagem / 100.0;

            aumento += 1;

            salario = salario.multiply(BigDecimal.valueOf(aumento));
        }
    }

    public double quantidadeDeSalariosMinimos() {
        double salarioMinimo = 1212.0;

        return salario.doubleValue() / salarioMinimo;
    }

    @Override
    public String toString() {
        return "Funcionario {" +
                " Nome = '" + nome + '\'' +
                ", Data de Nascimento = " + dataNascimento.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) +
                ", Salário = " + salario +
                ", Funcao = '" + funcao + '\'' +
                ", QTD de Salários Mínimos = " + String.format("%.2f", quantidadeDeSalariosMinimos()) +
                " }";
    }
}
