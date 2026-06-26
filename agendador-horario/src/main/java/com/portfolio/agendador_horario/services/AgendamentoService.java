/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.portfolio.agendador_horario.services;

import com.portfolio.agendador_horario.infrastructure.entity.Agendamento;
import com.portfolio.agendador_horario.infrastructure.repository.AgendamentoRepository;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


/**
 *
 * @author Daniel
 */
@Service
@RequiredArgsConstructor
public class AgendamentoService {
    
    private final AgendamentoRepository agendamentoRepository;
    
    public Agendamento salvarAgendamento(Agendamento agendamento){
        
        LocalDateTime horaAgendamento = agendamento.getDataHoraAgendamento();
        LocalDateTime horaFim = agendamento.getDataHoraAgendamento().plusHours(1);
        
        Agendamento agendados = agendamentoRepository.findByServicoAndDataHoraAgendamentoBetween(agendamento.getServico(),
                                                        horaAgendamento, horaFim);
        if (Objects.nonNull(agendados)){
            throw new RuntimeException("Horário ja esta Preenchido");
        }
        return agendamentoRepository.save(agendamento);
    }
    
    public void deletarAgendamento (LocalDateTime dataHoraAgendamento, String cliente){
        agendamentoRepository.deleteByDataHoraAgendamentoAndCliente( dataHoraAgendamento, cliente);
        
}
   public Agendamento buscarAgendamentoDia(LocalDate data){
        LocalDateTime primeiraHoraDia = data.atStartOfDay();
        LocalDateTime horaFinalDia = data.atTime(23, 59, 59);
           
        return agendamentoRepository.findByDataHoraAgendamentoBetween(primeiraHoraDia, horaFinalDia);
        }
   
   public Agendamento alterarAgendamento(Agendamento agendamento, String cliente, 
        LocalDateTime dataAgendamento){
        Agendamento agenda = agendamentoRepository.findByDataHoraAgendamentoAndCliente(dataAgendamento, cliente);
        
        if (Objects.isNull(agenda)){
            throw new RuntimeException("Horário não esta agendado");
        }
        agendamento.setId(agenda.getId());
        return agendamentoRepository.save(agendamento);
    }
}
   