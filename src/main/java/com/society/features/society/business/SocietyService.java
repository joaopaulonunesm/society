package com.society.features.society.business;

import java.util.List;

import com.society.features.society.domain.Society;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SocietyService {

    private final SocietyBusiness societyBusiness;

    public Society criar(Society society) {
        return societyBusiness.criar(society);
    }

    public Society alterar(String token, Society society) {
        return societyBusiness.alterar(token, society);
    }

    public Society ativarOuInativar(Long id, String confirmacao) {
        return societyBusiness.ativarOuInativar(id, confirmacao);
    }

    public List<Society> buscarTodos() {
        return societyBusiness.buscarTodos();
    }

    public Society buscarPorNomeUrl(String nomeUrl) {
        return societyBusiness.buscarPorNomeUrl(nomeUrl);
    }

    public Society buscarPorToken(String token) {
        return societyBusiness.buscarPorToken(token);
    }

    public Society buscarPorId(Long id) {
        return societyBusiness.buscarPorId(id);
    }

	public void addQntJogo(Society society) {
        societyBusiness.addQntJogo(society);
	}
}
