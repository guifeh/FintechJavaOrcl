package br.com.fiap.dao;

import br.com.fiap.factory.ConnectionFactory;
import br.com.fiap.model.Investimento;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InvestimentoDAO {

    public List<Investimento> getAll() {
        List<Investimento> investimentos = new ArrayList<>();
        String sql = "SELECT * FROM Investimento";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Investimento investimento = new Investimento();
                investimento.setId(rs.getInt("ID"));
                investimento.setDescricao(rs.getString("Descricao"));
                investimento.setValor(rs.getDouble("Valor"));
                investimento.setDataAplicacao(rs.getDate("Data_Aplicacao"));
                investimentos.add(investimento);
            }

        } catch (SQLException e) {
            System.err.println("Erro ao buscar investimentos: " + e.getMessage());
            e.printStackTrace();
        }

        return investimentos;
    }

    public void insert(Investimento investimento) {
        String sql = "INSERT INTO Investimento (ID, Descricao, Valor, Data_Aplicacao) VALUES (seq_receita_id.NEXTVAL, ?, ?, ?)";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, investimento.getDescricao());
            stmt.setDouble(2, investimento.getValor());
            stmt.setDate(3, new java.sql.Date(investimento.getDataAplicacao().getTime()));

            stmt.executeUpdate();
            System.out.println("Investimento inserido com sucesso!");

        } catch (SQLException e) {
            System.err.println("Erro ao inserir investimento: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
