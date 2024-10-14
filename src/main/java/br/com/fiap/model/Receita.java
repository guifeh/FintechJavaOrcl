package br.com.fiap.model;

import java.util.Date;

public class Receita {

    private int id;
    private String descricao;
    private double valor;
    private Date dataRecebimento;

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public double getValor() { return valor; }
    public void setValor(double valor) { this.valor = valor; }

    public Date getDataRecebimento() { return dataRecebimento; }
    public void setDataRecebimento(Date dataRecebimento) { this.dataRecebimento = dataRecebimento; }
}
