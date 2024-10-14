package br.com.fiap.dao;

import br.com.fiap.factory.ConnectionFactory;
import br.com.fiap.model.Receita;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReceitaDAO {

    public List<Receita> getAll() {
        List<Receita> receitas = new ArrayList<>();
        String sql = "SELECT * FROM Receita";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Receita receita = new Receita();
                receita.setId(rs.getInt("ID"));
                receita.setDescricao(rs.getString("Descricao"));
                receita.setValor(rs.getDouble("Valor"));
                receita.setDataRecebimento(rs.getDate("Data_Recebimento"));
                receitas.add(receita);
            }

        } catch (SQLException e) {
            System.err.println("Erro ao buscar receitas: " + e.getMessage());
            e.printStackTrace();
        }

        return receitas;
    }

    public void insert(Receita receita) {
        String sql = "INSERT INTO Receita (ID, Descricao, Valor, Data_Recebimento) VALUES (seq_receita_id.NEXTVAL, ?, ?, ?)";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, receita.getDescricao());
            stmt.setDouble(2, receita.getValor());
            stmt.setDate(3, new java.sql.Date(receita.getDataRecebimento().getTime()));

            stmt.executeUpdate();
            System.out.println("Receita inserida com sucesso!");

        } catch (SQLException e) {
            System.err.println("Erro ao inserir receita: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
