/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.portfolio.agendador_horario.infrastructure.repository;

import com.portfolio.agendador_horario.infrastructure.entity.Agendamento;
import jakarta.transaction.Transactional;
import java.time.LocalDateTime;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Daniel
 */
public interface AgendamentoRepository extends JpaRepository<Agendamento,Long > {
    
    Agendamento findByServicoAndDataHoraAgendamentoBetween(String servico, 
            LocalDateTime dataHoraInicio, LocalDateTime dataHoraFinal);
    
    @Transactional
    void deleteByDataHoraAgendamentoAndCliente(LocalDateTime dataHoraAgendamento, 
            String cliente);
    Agendamento findByDataHoraAgendamentoBetween(LocalDateTime dataHoraInicial, 
            LocalDateTime dataHoraFinal);
    Agendamento findByDataHoraAgendamentoAndCliente(LocalDateTime dataHoraAgendamento, 
            String cliente);
            


            
}

