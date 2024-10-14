package br.com.fiap.model;

import java.util.Date;

public class Investimento {

    private int id;
    private String descricao;
    private double valor;
    private Date dataAplicacao;

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public double getValor() { return valor; }
    public void setValor(double valor) { this.valor = valor; }

    public Date getDataAplicacao() { return dataAplicacao; }
    public void setDataAplicacao(Date dataAplicacao) { this.dataAplicacao = dataAplicacao; }
}
