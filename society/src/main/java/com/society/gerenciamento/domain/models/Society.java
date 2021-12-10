package com.society.gerenciamento.domain.models;

import com.society.gerenciamento.domain.enums.StatusSociety;
import com.society.gerenciamento.domain.exceptions.DomainException;
import com.society.gerenciamento.domain.exceptions.DomainMensagem;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;

@ToString
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
    private String email;
    private String endereco;
    private String cep;
    private String observacao;
    private String urlFoto;
    private Long quantidadeCampos;

    public void defineId(Long id) {
        this.id = id;
    }

    public void criar() {
        this.status = StatusSociety.ATIVO;
        defineNomeUrl();
    }

    public void atualizar(Society society) {
        this.nome = society.getNome();
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

        defineNomeUrl();
    }

    public void inativar() {
        if (this.status.equals(StatusSociety.INATIVO)) {
            throw new DomainException(DomainMensagem.SITUACAO_SOCIETY_ATUAL);
        }

        this.status = StatusSociety.INATIVO;
    }

    public void ativar() {
        if (this.status.equals(StatusSociety.ATIVO)) {
            throw new DomainException(DomainMensagem.SITUACAO_SOCIETY_ATUAL);
        }

        this.status = StatusSociety.ATIVO;
    }

    private void defineNomeUrl() {
        this.nomeUrl = this.nome.replaceAll(" ", "-").replaceAll("[ãâàáä]", "a").replaceAll("[êèéë]", "e")
                .replaceAll("[îìíï]", "i").replaceAll("[õôòóö]", "o").replaceAll("[ûúùü]", "u")
                .replaceAll("[ÃÂÀÁÄ]", "A").replaceAll("[ÊÈÉË]", "E").replaceAll("[ÎÌÍÏ]", "I")
                .replaceAll("[ÕÔÒÓÖ]", "O").replaceAll("[ÛÙÚÜ]", "U").replace('ç', 'c').replace('Ç', 'C')
                .replace('ñ', 'n').replace('Ñ', 'N').toLowerCase();
    }
}
