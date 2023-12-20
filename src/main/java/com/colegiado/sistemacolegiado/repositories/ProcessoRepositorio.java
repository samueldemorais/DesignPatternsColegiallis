package com.colegiado.sistemacolegiado.repositories;

import com.colegiado.sistemacolegiado.models.Aluno;
import com.colegiado.sistemacolegiado.models.Assunto;
import com.colegiado.sistemacolegiado.models.Processo;
import com.colegiado.sistemacolegiado.models.Professor;
import com.colegiado.sistemacolegiado.models.enums.StatusProcesso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ProcessoRepositorio extends JpaRepository<Processo, Integer> {
    @Query("select processo from Processo processo where processo.aluno = :aluno " +
            "and (:status is null or processo.status = :status) " +
            "and (:assunto is null or processo.assunto = :assunto)")
    List<Processo> findByStatusAndAssuntoAndAlunoOrderByDataRecepcao(StatusProcesso status, Assunto assunto, Aluno aluno);

    @Query("select processo from Processo processo where processo.professor = :professor " +
            "and (:status is null or processo.status = :status) " +
            "and (:assunto is null or processo.assunto = :assunto)")
    List<Processo> findByStatusAndAssuntoAndProfessorOrderByDataRecepcao(StatusProcesso status, Assunto assunto, Professor professor);

    List<Processo> findAllByOrderByDataRecepcao();

    @Query("select processo from Processo processo where 1=1 " +
            "and (:professor is null or processo.professor = :professor) " +
            "and (:aluno is null or processo.aluno = :aluno) " +
            "and (:status is null or processo.status = :status)")
    List<Processo> findAllByAlunoAndProfessorAndStatusOrderByDataRecepcao(Aluno aluno, Professor professor, StatusProcesso status);

    @Query ("select p from Processo p where p.requerimento = :nome")
    List<Processo> filtrarRequerimento (@Param("nome") String nome);

    @Query("select p from Processo p where p.dataRecepcao = :datarecepcao")
    List<Processo> filtrarData (@Param("datarecepcao")LocalDate data);
    @Query("select p from Processo p WHERE p.aluno.id = :idaluno ORDER BY p.dataRecepcao DESC ")
    List<Processo> filtrarDataRecente(@Param("idaluno") int idaluno);

    @Query("select p from Processo p where p.status = :status and p.aluno.id = :alunoId")
    List<Processo> filtarStatus (@Param("status") StatusProcesso status, @Param("alunoId") int id);

    @Query("select p from Processo p WHERE p.aluno.id = :idaluno AND p.requerimento = :requerimento AND p.status = :status ORDER BY p.dataRecepcao DESC, p.requerimento DESC, p.status DESC")
    List<Processo> filtrarRequerimentoDataStatus(@Param("idaluno") int idaluno, @Param("requerimento") String requerimento, @Param("status") StatusProcesso status);

    @Query ("select p from Processo p where p.aluno.nome LIKE %:nome% AND p.dataRecepcao = :datarecepcao")
    List<Processo> filtrarNomeEData (@Param("nome") String nome, @Param("datarecepcao")LocalDate data);

}
