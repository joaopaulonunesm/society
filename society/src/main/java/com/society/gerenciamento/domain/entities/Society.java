package com.society.gerenciamento.domain.entities;

import com.society.gerenciamento.domain.enums.StatusSociety;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Builder
@Getter
public class Society {

    private Long id;
    private StatusSociety status;
    private String nome;
    private String nomeUrl;
    private String descricao;
    private BigDecimal valorHora;
    private String nomeResponsavel;
    private String celular;
    private String telefone;
    private String endereco;
    private String cep;
    private String observacao;
    private String urlFoto;
    private Long quantidadeCampos;

    public void defineId(Long id) {
        this.id = id;
    }

    public void defineStatus(StatusSociety status) {
        this.status = status;
    }

    public void atualizarDados(Society society) {
        this.nome = society.getNome();
        this.nomeUrl = society.getNomeUrl();
        this.descricao = society.getDescricao();
        this.valorHora = society.getValorHora();
        this.nomeResponsavel = society.getNomeResponsavel();
        this.celular = society.getCelular();
        this.telefone = society.getTelefone();
        this.endereco = society.getEndereco();
        this.cep = society.getCep();
        this.observacao = society.getObservacao();
        this.urlFoto = society.getUrlFoto();
        this.quantidadeCampos = society.getQuantidadeCampos();
    }

    public void defineNomeUrl() {
        this.nomeUrl = this.nome.replaceAll(" ", "-").replaceAll("[ãâàáä]", "a").replaceAll("[êèéë]", "e")
                .replaceAll("[îìíï]", "i").replaceAll("[õôòóö]", "o").replaceAll("[ûúùü]", "u")
                .replaceAll("[ÃÂÀÁÄ]", "A").replaceAll("[ÊÈÉË]", "E").replaceAll("[ÎÌÍÏ]", "I")
                .replaceAll("[ÕÔÒÓÖ]", "O").replaceAll("[ÛÙÚÜ]", "U").replace('ç', 'c').replace('Ç', 'C')
                .replace('ñ', 'n').replace('Ñ', 'N').toLowerCase();
    }
}
