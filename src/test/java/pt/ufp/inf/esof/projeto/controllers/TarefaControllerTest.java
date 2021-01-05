package pt.ufp.inf.esof.projeto.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import pt.ufp.inf.esof.projeto.dtos.EmpregadoCreateDTO;
import pt.ufp.inf.esof.projeto.modelos.TarefaPrevista;
import pt.ufp.inf.esof.projeto.repositories.EmpregadoRepository;
import pt.ufp.inf.esof.projeto.services.TarefaService;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;


@WebMvcTest(TarefaController.class)
class TarefaControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private TarefaService tarefaService;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void criarTarefa() throws Exception {
        TarefaPrevista tarefaPrevista = new TarefaPrevista();
        tarefaPrevista.setNome("TarefaPrevistaController");
        tarefaPrevista.setTempoPrevistoConlusao(10);

        when(this.tarefaService.criarTarefa(tarefaPrevista)).thenReturn(Optional.of(tarefaPrevista));

        String tarefaJson = new ObjectMapper().writeValueAsString(tarefaPrevista);

        mockMvc.perform(post("/tarefa").content(tarefaJson).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());

        TarefaPrevista tarefaPrevista1 = new TarefaPrevista();
        tarefaPrevista1.setNome("TarefaPrevista");

        String tarefaJson1 = new ObjectMapper().writeValueAsString(tarefaPrevista1);

        mockMvc.perform(post("/tarefa").content(tarefaJson1).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest());
    }

    @Test
    void adicionaEmpregado() throws Exception {
        TarefaPrevista tarefaPrevista = new TarefaPrevista();
        tarefaPrevista.setNome("TarefaPrevistaController");
        tarefaPrevista.setTempoPrevistoConlusao(10);

        EmpregadoCreateDTO empregadoCreateDTO = new EmpregadoCreateDTO();
        empregadoCreateDTO.setEmail("teste@teste.pt");

        String empregadoJson = objectMapper.writeValueAsString(empregadoCreateDTO);
        when(tarefaService.adicionaEmpregado(1L,empregadoCreateDTO.converter().getEmail())).thenReturn(Optional.of(tarefaPrevista));
        mockMvc.perform(patch("/tarefa/empregado/1").content(empregadoJson).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());




    }
}