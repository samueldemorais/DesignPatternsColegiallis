package com.colegiado.sistemacolegiado.services;


import com.colegiado.sistemacolegiado.interfaces.Mediator.Coordenador;
import com.colegiado.sistemacolegiado.interfaces.MediatorProcesso;
import com.colegiado.sistemacolegiado.interfaces.Proxy.ProxyConsultaProcessosProfessor;
import com.colegiado.sistemacolegiado.models.Aluno;
import com.colegiado.sistemacolegiado.models.Assunto;
import com.colegiado.sistemacolegiado.models.dto.CriarProcessoDTO;
import com.colegiado.sistemacolegiado.models.Processo;
import com.colegiado.sistemacolegiado.models.Professor;
import com.colegiado.sistemacolegiado.models.Voto.Voto;
import com.colegiado.sistemacolegiado.models.Voto.VotoId;
import com.colegiado.sistemacolegiado.models.dto.FiltrarProcessoDTO;
import com.colegiado.sistemacolegiado.models.dto.VotoDTO;
import com.colegiado.sistemacolegiado.models.enums.StatusProcesso;
import com.colegiado.sistemacolegiado.models.enums.TipoDecisao;
import com.colegiado.sistemacolegiado.models.enums.TipoVoto;
import com.colegiado.sistemacolegiado.repositories.ProcessoRepositorio;
import com.colegiado.sistemacolegiado.repositories.VotoRepositorio;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProcessoService {
    final ProcessoRepositorio processoRepositorio;
    final ProfessorService professorService;
    final VotoRepositorio votoRepositorio;
    final AlunoService alunoService;
    final AssuntoService assuntoService;

    public Processo criarProcesso(CriarProcessoDTO processoDTO){
        var assunto = assuntoService.encontrarPorId(processoDTO.getIdAssunto());
        var aluno = alunoService.encontrarPorId(processoDTO.getIdAluno());
        return this.processoRepositorio.save(new Processo(processoDTO, aluno, assunto));
    }

    @Transactional
    public Aluno setProcessoNoAluno(Aluno aluno, Processo processo){
        Aluno alunoX  = alunoService.encontrarPorId(aluno.getId());
        alunoX.setProcessoDoAluno(processo);
        return alunoX;
    }


    public Optional<Processo> encontrarPorId(int id){
        return this.processoRepositorio.findById(id);
    }

    public List<Processo> listarProcessos(){
        return this.processoRepositorio.findAll();
    }

    public void deletarProcesso(Processo processo){
        if (processo.getId() != null && this.processoRepositorio.existsById(processo.getId())) {
            processo.setAssunto(null);
            processo.setAluno(null);
            processo.setReuniao(null);
            processo.setProfessor(null);
            this.processoRepositorio.delete(processo);
        } else {
            throw new RuntimeException("O processo não existe");
        }
    }

    public Processo mudarDecisao(int id, TipoDecisao novaDecisao){
        Optional<Processo> processoOptional = this.processoRepositorio.findById(id);
        if (processoOptional.isPresent()) {
            Processo processo = processoOptional.get();
            processo.setParecer(novaDecisao);
            return this.processoRepositorio.save(processo);
        } else {
            throw new RuntimeException("Processo não encontrado");
        }
    };

    public void votarProfessor(VotoDTO voto){
        Professor professor = this.professorService.encontrarPorId(voto.getIdProfessor());
        Processo processo = this.processoRepositorio.findById(voto.getIdProcesso())
                .orElseThrow(() -> new RuntimeException("Processo não encontrado"));
        VotoId votoId = new VotoId(voto.getIdProfessor(), voto.getIdProcesso());
        Voto votoFinal = new Voto(votoId, professor, processo, voto.getVoto(), voto.getTexto());
        processo.avancarParaProximoEstado();
        this.votoRepositorio.save(votoFinal);
    }

    public Processo votarRelator(Integer idProcesso, TipoDecisao decisaoRelator, String texto){
        System.out.println(idProcesso);
        Processo processo = this.processoRepositorio.findById(idProcesso)
                .orElseThrow(() -> new RuntimeException("Processo não encontrado"));
        processo.setParecer(decisaoRelator);
        processo.setJustificativa(texto);
        processo.avancarParaProximoEstado();
        return this.processoRepositorio.save(processo);
    }

    public void votarColegiado(List<VotoDTO> votos){
        votos.forEach(this::votarProfessor);
        int votoComRelator = votos.stream().filter(voto -> voto.getVoto().equals(TipoVoto.COM_RELATOR)).toList().size();
        int votoDivergente = votos.stream().filter(voto -> voto.getVoto().equals(TipoVoto.DIVERGENTE)).toList().size();
        if(votoDivergente > votoComRelator){
            Processo processo = this.processoRepositorio.findById(votos.get(0).getIdProcesso())
                    .orElseThrow(() -> new RuntimeException("Processo não encontrado"));
            processo.setParecer(processo.getParecer().equals(TipoDecisao.DEFERIDO) ? TipoDecisao.INDEFERIDO : TipoDecisao.DEFERIDO);
            processo.avancarParaProximoEstado();
        }

    }

    public List<Processo> listarProcessosProfessor(FiltrarProcessoDTO filtro, Long idProfessor) {
        ProxyConsultaProcessosProfessor proxy = new ProxyConsultaProcessosProfessor(this, filtro, idProfessor);
        return proxy.consultarProcessos();
    }



    public List<Processo> listarProcessos(FiltrarProcessoDTO filtro) {
        Assunto assunto = null;
        if (filtro.getIdAssunto() != null){
            assunto = assuntoService.encontrarPorId(filtro.getIdAssunto());
        }
        if (filtro.getIdAluno() != null){
            var aluno = alunoService.encontrarPorId(filtro.getIdAluno());
            return processoRepositorio.findByStatusAndAssuntoAndAlunoOrderByDataRecepcao(filtro.getStatus(), assunto, aluno);
        } else if (filtro.getIdProfessor() != null){
            var professor = professorService.encontrarPorId(filtro.getIdProfessor());
            return processoRepositorio.findByStatusAndAssuntoAndProfessorOrderByDataRecepcao(filtro.getStatus(), assunto, professor);
        } else {
            throw new RuntimeException("Aluno ou professor não informados.");
        }
    }

    public List<Processo> listarProcessosCoordenador(FiltrarProcessoDTO filtro) {
        Aluno aluno = null;
        Professor professor = null;
        if (filtro.getIdAluno() != null){
            aluno = alunoService.encontrarPorId(filtro.getIdAluno());
        }
        if (filtro.getIdProfessor() != null){
            professor = professorService.encontrarPorId(filtro.getIdProfessor());
        }
        return processoRepositorio.findAllByAlunoAndProfessorAndStatusOrderByDataRecepcao(aluno, professor, filtro.getStatus());
    }

    public Processo atribuirProcesso(Integer idProcesso, Integer idProfessor) {
        var processo = processoRepositorio.findById(idProcesso)
                .orElseThrow(() -> new RuntimeException("Processo não encontrado"));

        MediatorProcesso coordenador = new Coordenador(this, professorService);
        coordenador.atribuirProcesso(idProcesso, idProfessor);

        return processoRepositorio.save(processo);
    }

    public Optional<Processo> listprocessoscomoassunto (Assunto assunto){
        return processoRepositorio.findById(assunto.getId());

    }

    public List<Processo> filtrarprocesso (Aluno aluno, String nome, String data, StatusProcesso status){
        System.out.println(data);
        System.out.println(aluno);
        int idAluno = aluno.getId();

        if (StringUtils.hasText(nome) && StringUtils.hasText(data)  && status != null) {
            return processoRepositorio.filtrarRequerimentoDataStatus(idAluno, nome, status);
        } else if(StringUtils.hasText(nome)){
            return processoRepositorio.filtrarRequerimento(nome);
        } else if (StringUtils.hasText(data)) {
            return processoRepositorio.filtrarDataRecente(idAluno);
        } else if (status != null){
            return processoRepositorio.filtarStatus(status, aluno.getId());
        }

        return aluno.getProcessos();
    }
}
