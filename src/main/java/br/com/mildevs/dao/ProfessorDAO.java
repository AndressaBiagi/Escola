package br.com.mildevs.dao;

import java.util.List;

import br.com.mildevs.entity.Professor;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

public class ProfessorDAO {

	private EntityManager manager;

	public ProfessorDAO() {
		this.manager = Persistence.createEntityManagerFactory("escola").createEntityManager();
	}
	
	//criar professor
	public boolean criaProfessor(Professor professor) {
		this.manager.getTransaction().begin();
		this.manager.persist(professor);
		this.manager.getTransaction().commit();
		return true;
	}
	
	public Professor consultaProfessor(int codFuncionario) {
		return manager.find(Professor.class, codFuncionario);
	}
	
	public List<Professor> listaProfessores(){
		Query query = manager.createQuery("select p from Professor as p");
		
		return query.getResultList();
	}
	
	public boolean removeProfessor(int codFuncionario) {
		
		Professor professor = this.manager.find(Professor.class, codFuncionario);
		
		if (professor == null) {
			return false;
		}
		this.manager.getTransaction().begin();
		this.manager.remove(professor);
		this.manager.getTransaction().commit();
		return true;
	}

	
	

}
