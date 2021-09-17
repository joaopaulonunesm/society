package com.society.features.society.business;

import java.util.List;
import java.util.Optional;

import com.society.features.login.business.LoginBusiness;
import com.society.features.login.business.LoginService;
import com.society.features.society.domain.Society;
import com.society.features.society.persistence.SocietyRepository;
import com.society.features.society.domain.StatusSociety;
import com.society.features.login.domain.TipoLogin;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.society.exceptions.BusinessException;

@Component
@RequiredArgsConstructor
public class SocietyBusiness {

	private final SocietyRepository societyRepository;
	private final LoginService loginService;

	public Society criar(Society society) {
		
		society.setNomeUrl(society.getNome());
		society.setQuantidadeJogos(0l);
		society.setStatusSociety(StatusSociety.EM_ANALISE.getId());
		
		society.getLogin().setTipoLogin(TipoLogin.SOCIETY.getId());
		
		society.setLogin(loginService.completarLogin(society.getLogin()));

		return societyRepository.save(society);
	}

	public Society alterar(String token, Society society) {

		Society societyExistente = buscarPorToken(token);
		
		society.setId(societyExistente.getId());
		society.setStatusSociety(societyExistente.getStatusSociety());
		society.setNomeUrl(society.getNome());
		
		society.setLogin(loginService.manterDados(societyExistente.getLogin(), society.getLogin()));
		
		return societyRepository.save(society);
	}

	public List<Society> buscarTodos() {
		return societyRepository.findAll();
	}

	public Society buscarPorId(Long id) {

		Optional<Society> busca = societyRepository.findById(id);

		if (!busca.isPresent()) {
			throw new BusinessException("Não foi encontrado Society pelo ID", HttpStatus.NOT_FOUND);
		}

		return busca.get();
	}

	public Society buscarPorNomeUrl(String nomeUrl) {

		Optional<Society> busca = societyRepository.findByNomeUrl(nomeUrl);

		if (!busca.isPresent()) {
			throw new BusinessException("Não foi encontrado Society pelo Nome Url", HttpStatus.NOT_FOUND);
		}

		return busca.get();
	}
	
	public Society buscarPorToken(String token) {

		Long idLogin = loginService.buscarPorToken(token).getId();

		return societyRepository.findByLoginId(idLogin)
				.orElseThrow(() -> new BusinessException("Erro ao buscar Society por token",HttpStatus.BAD_REQUEST));
	}

	public void addQntJogo(Society society) {
		society = buscarPorId(society.getId());
		society.setQuantidadeJogos(society.getQuantidadeJogos() + 1);
		societyRepository.save(society);
	}

	public Society ativarOuInativar(Long id, String confirmacao) {

		if(!confirmacao.equalsIgnoreCase(StatusSociety.ATIVO.toString()) || !confirmacao.equalsIgnoreCase(StatusSociety.INATIVO.toString())) {
			throw new BusinessException("Parametro " + confirmacao + " é inválido para Ativação ou Inativação!", HttpStatus.BAD_REQUEST);
		}
		
		Society society = buscarPorId(id);
		
		society.setStatusSociety(StatusSociety.buscarPorString(confirmacao).getId());
		
		return societyRepository.save(society);
	}

}
