package com.society.features.agendamento.business;

import com.society.features.society.business.SocietyService;
import com.society.features.agendamento.domain.StatusAgendamento;
import com.society.exceptions.BusinessException;
import com.society.features.agendamento.domain.Agendamento;
import com.society.features.society.domain.Society;
import com.society.features.usuario.business.UsuarioService;
import com.society.features.usuario.domain.Usuario;
import com.society.features.agendamento.persistence.AgendamentoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class AgendamentoBusiness {

    private final AgendamentoRepository agendamentoRepository;
    private final SocietyService societyService;
    private final UsuarioService usuarioService;

    public Agendamento criar(Agendamento agendamento) {

        Society society = societyService.buscarPorId(agendamento.getSociety().getId());

        agendamento.setSociety(society);
        agendamento.setDataFim(agendamento.getDataInicio().plusHours(1));

        agendamento.setStatusAgendamento(StatusAgendamento.AGUARDANDO_SOCIETY.getId());

        Usuario usuario = usuarioService.buscarPorId(agendamento.getUsuario().getId());

        agendamento.setUsuario(usuario);

        if (agendamento.getDataInicio().isBefore(LocalDateTime.now())) {
            throw new BusinessException("A data do jogo deve ser maior que a data atual.", HttpStatus.BAD_REQUEST);
        }

        if (!horarioDisponivel(agendamento)) {
            throw new BusinessException("Os campos já estão reservados no " + agendamento.getSociety().getNome()
                    + " para o horário informado.", HttpStatus.BAD_REQUEST);
        }

        if (existeReservaParaUsuario(usuario, society, agendamento.getDataInicio())) {
            throw new BusinessException("Você já tem uma reserva nesse horário para esse Society.", HttpStatus.BAD_REQUEST);
        }

        Agendamento agendamentoSalvo = agendamentoRepository.save(agendamento);

        societyService.addQntJogo(society);

        return agendamentoSalvo;
    }

    public Agendamento buscarPorId(Long id) {

        Optional<Agendamento> agendamento = agendamentoRepository.findById(id);

        if (!agendamento.isPresent()) {
            throw new BusinessException("Não foi encontrado Agendamento pelo ID", HttpStatus.NOT_FOUND);
        }

        return agendamento.get();
    }

    public Agendamento confirmarOuCancelar(Long id, String confirmacao) {

        if (!confirmacao.equalsIgnoreCase(StatusAgendamento.CONFIRMADO.toString()) &&
                !confirmacao.equalsIgnoreCase(StatusAgendamento.CANCELADO.toString())) {
            throw new BusinessException("Parametro " + confirmacao + " é inválido para Confirmação ou Cancelamento!",
                    HttpStatus.BAD_REQUEST);
        }

        Agendamento agendamento = buscarPorId(id);

        if (confirmacao.equalsIgnoreCase(StatusAgendamento.CONFIRMADO.toString()) &&
                !horarioDisponivel(agendamento) &&
                !StatusAgendamento.CONFIRMADO.equals(StatusAgendamento.buscarPorId(agendamento.getStatusAgendamento()))) {
            agendamento.setStatusAgendamento(StatusAgendamento.CANCELADO.getId());
            agendamentoRepository.save(agendamento);
            throw new BusinessException("Não existe mais campos disponíveis para esse horário.", HttpStatus.BAD_REQUEST);
        }

        if (LocalDateTime.now().isAfter(agendamento.getDataInicio())) {
            agendamento.setStatusAgendamento(StatusAgendamento.CONFIRMADO.equals(StatusAgendamento.buscarPorId(agendamento.getStatusAgendamento())) ? StatusAgendamento.CONFIRMADO.getId() : StatusAgendamento.CANCELADO.getId());
            agendamentoRepository.save(agendamento);
            throw new BusinessException("O jogo já iniciou! Caso estava em analise, o jogo será cancelado automaticamente.", HttpStatus.BAD_REQUEST);
        }

        agendamento.setStatusAgendamento(StatusAgendamento.valueOf(confirmacao).getId());
        return agendamentoRepository.save(agendamento);
    }

    public List<Agendamento> buscaComTokenPorSociety(String token) {
        Society society = societyService.buscarPorToken(token);
        return agendamentoRepository.findBySocietyIdOrderByDataInicio(society.getId());
    }

    public List<Agendamento> buscaComTokenPorUsuario(String token) {
        Usuario usuario = usuarioService.buscarPorToken(token);
        return agendamentoRepository.findByUsuarioIdOrderByDataInicio(usuario.getId());
    }

    private boolean horarioDisponivel(Agendamento agendamento) {
        List<Agendamento> agendamentoExistente = agendamentoRepository.findByDataInicioAndDataFimAndSocietyAndStatusAgendamento(agendamento.getDataInicio(), agendamento.getDataFim(), agendamento.getSociety().getId(), StatusAgendamento.CONFIRMADO.getId());
        return agendamentoExistente.size() < agendamento.getSociety().getQuantidadeCampos();
    }

    private boolean existeReservaParaUsuario(Usuario usuario, Society society, LocalDateTime dataAgendamento) {
        Optional<Agendamento> agendamento = agendamentoRepository.findByUsuarioIdAndDataInicioAndSocietyId(usuario.getId(), dataAgendamento, society.getId());
        return agendamento.isPresent();
    }
}
