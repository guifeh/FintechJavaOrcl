package br.com.fiap;

import br.com.fiap.dao.ReceitaDAO;
import br.com.fiap.dao.DespesaDAO;
import br.com.fiap.dao.InvestimentoDAO;
import br.com.fiap.model.Receita;
import br.com.fiap.model.Despesa;
import br.com.fiap.model.Investimento;

import br.com.fiap.factory.ConnectionFactory;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.DriverManager;

import java.util.Date;
import java.util.List;

public class App {

    public static void main(String[] args) {
        try {
            Connection conexao = ConnectionFactory.getConnection();
            System.out.println("Conexão realizada!");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        ReceitaDAO receitaDAO = new ReceitaDAO();
        DespesaDAO despesaDAO = new DespesaDAO();
        InvestimentoDAO investimentoDAO = new InvestimentoDAO();

        // Inserir registros de receitas
        for (int i = 1; i <= 5; i++) {
            Receita receita = new Receita();
            receita.setDescricao("Receita " + i);
            receita.setValor(1000.0 * i);
            receita.setDataRecebimento(new Date());
            receitaDAO.insert(receita);
        }

        // Consultar todas as receitas
        List<Receita> receitas = receitaDAO.getAll();
        receitas.forEach(r -> System.out.println("Receita: " + r.getDescricao()));

        // Fazer o mesmo para Despesas e Investimentos
        // Inserir despesas
        for (int i = 1; i <= 5; i++) {
            Despesa despesa = new Despesa();
            despesa.setDescricao("Despesa " + i);
            despesa.setValor(500.0 * i);
            despesa.setDataPagamento(new Date());
            despesaDAO.insert(despesa);
        }

        // Consultar todas as despesas
        List<Despesa> despesas = despesaDAO.getAll();
        despesas.forEach(d -> System.out.println("Despesa: " + d.getDescricao()));

        // Inserir investimentos
        for (int i = 1; i <= 5; i++) {
            Investimento investimento = new Investimento();
            investimento.setDescricao("Investimento " + i);
            investimento.setValor(2000.0 * i);
            investimento.setDataAplicacao(new Date());
            investimentoDAO.insert(investimento);
        }

        // Consultar todos os investimentos
        List<Investimento> investimentos = investimentoDAO.getAll();
        investimentos.forEach(i -> System.out.println("Investimento: " + i.getDescricao()));
    }
}
