package br.com.fiap.dao;

import br.com.fiap.factory.ConnectionFactory;
import br.com.fiap.model.Despesa;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DespesaDAO {

    public List<Despesa> getAll() {
        List<Despesa> despesas = new ArrayList<>();
        String sql = "SELECT * FROM Despesa";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Despesa despesa = new Despesa();
                despesa.setId(rs.getInt("ID"));
                despesa.setDescricao(rs.getString("Descricao"));
                despesa.setValor(rs.getDouble("Valor"));
                despesa.setDataPagamento(rs.getDate("Data_Pagamento"));
                despesas.add(despesa);
            }

        } catch (SQLException e) {
            System.err.println("Erro ao buscar despesas: " + e.getMessage());
            e.printStackTrace();
        }

        return despesas;
    }

    public void insert(Despesa despesa) {
        String sql = "INSERT INTO Despesa (ID, Descricao, Valor, Data_Pagamento) VALUES (seq_receita_id.NEXTVAL, ?, ?, ?)";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, despesa.getDescricao());
            stmt.setDouble(2, despesa.getValor());
            stmt.setDate(3, new java.sql.Date(despesa.getDataPagamento().getTime()));

            stmt.executeUpdate();
            System.out.println("Despesa inserida com sucesso!");

        } catch (SQLException e) {
            System.err.println("Erro ao inserir despesa: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
