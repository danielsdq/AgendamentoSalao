/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.portfolio.agendador_horario.controller;

import com.portfolio.agendador_horario.infrastructure.entity.Agendamento;
import com.portfolio.agendador_horario.services.AgendamentoService;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Daniel
 */
@RestController
@RequestMapping("/agendamento")
@RequiredArgsConstructor
public class AgendamentoController {
    private final AgendamentoService agendamentoService;
    
    
    @PostMapping
    public ResponseEntity<Agendamento> salvarAgendamento(@RequestBody Agendamento agendamento){
        return ResponseEntity.accepted().body(agendamentoService.salvarAgendamento(agendamento));
    }
    @DeleteMapping
    public ResponseEntity<Void> deletarAgendamento(@RequestParam String cliente,
                                                   @RequestParam LocalDateTime dataHoraAgendamento){
        
        agendamentoService.deletarAgendamento(dataHoraAgendamento, cliente);
        return ResponseEntity.noContent().build();
        
    }
    @GetMapping
    public ResponseEntity<Agendamento> buscarAgendamentosDia(@RequestParam LocalDate data){
        return ResponseEntity.ok().body(agendamentoService.buscarAgendamentoDia(data));
    }
    @PutMapping
    public ResponseEntity<Agendamento> alterarAgendamentos(@RequestParam Agendamento agendamento,
                                                           @RequestParam String cliente,
                                                           @RequestParam LocalDateTime dataHoraAgendamento  ){
        return ResponseEntity.accepted().body(agendamentoService.alterarAgendamento(agendamento, cliente, dataHoraAgendamento));
    }
}
