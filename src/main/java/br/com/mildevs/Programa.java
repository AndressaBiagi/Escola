package br.com.mildevs;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import br.com.mildevs.dao.AlunoDAO;
import br.com.mildevs.dao.ProfessorDAO;
import br.com.mildevs.dao.TurmaDAO;
import br.com.mildevs.entity.Aluno;
import br.com.mildevs.entity.Professor;
import br.com.mildevs.entity.Sala;
import br.com.mildevs.entity.Turma;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Programa {

	public static void main(String[] args) {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("escola");
		EntityManager manager = entityManagerFactory.createEntityManager();

		ProfessorDAO professorDao = new ProfessorDAO();
		TurmaDAO turmaDao = new TurmaDAO();
		AlunoDAO alunoDao = new AlunoDAO();

		Professor professor = new Professor();
		professor.setDisciplina("Lógica de Programação");
		professor.setNivelGraduacao("BACHAREL");
		professor.setNome("jose");
		professor.setSalario(2000);
		professor.setTelefone("56497894984");
		
		professorDao.criaProfessor(professor);

		Professor professorDb = professorDao.consultaProfessor(0);
		System.out.println("Professor encontrado -> " + professorDb);

		List<Professor> professoresNoDb = professorDao.listaProfessores();
		for (Professor professorEncontradoNaListagem : professoresNoDb) {
			System.out.println(professorEncontradoNaListagem);
		}

		Sala sala = new Sala();
		sala.setAltura(10);
		sala.setComprimento(15);
		sala.setLargura(345);

		Turma turmaCriada = turmaDao.criaTurma(sala);
		turmaDao.adicionaProfessor(professorDb, turmaCriada.getCodTurma());

		Aluno aluno = new Aluno();
		aluno.setDataNascimento(LocalDate.now());
		aluno.setNome("José");
		aluno.setSerie("1 serie");

		Aluno aluno2 = new Aluno();
		aluno2.setDataNascimento(LocalDate.now());
		aluno2.setNome("Victor");
		aluno2.setSerie("2 serie");

		Aluno aluno3 = new Aluno();
		aluno3.setDataNascimento(LocalDate.now());
		aluno3.setNome("Vitor");
		aluno3.setSerie("2 serie");

		turmaDao.adicionaAluno(aluno, turmaCriada.getCodTurma());
		turmaDao.adicionaAluno(aluno2, turmaCriada.getCodTurma());
		turmaDao.adicionaAluno(aluno3, turmaCriada.getCodTurma());

		List<Turma> turmas = turmaDao.listarTurmas();

		for (Turma turmaNoDb : turmas) {
			System.out.println(turmaNoDb);
		}
		manager.close();
		entityManagerFactory.close();
	}
	/*
	 * Professor professor = new Professor(); professor.setCodFuncionario(6);
	 * professor.setDisciplina("logica de programação");
	 * professor.setNivelGraduacao("MESTRADO"); professor.setNome("Jose");
	 * professor.setSalario(1000); professor.setTelefone("132131312");
	 * 
	 * Turma turma = new Turma(); turma.setCodTurma(3);
	 * 
	 * List<Turma> turmasDoJose = new ArrayList<Turma>();
	 * turma.setProfessor(professor); turmasDoJose.add(turma);
	 * professor.setTurmas(turmasDoJose);
	 * 
	 * manager.getTransaction().begin(); manager.persist(professor);
	 * manager.getTransaction().commit();
	 */

}
