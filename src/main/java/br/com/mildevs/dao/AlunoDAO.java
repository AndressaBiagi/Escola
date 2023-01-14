package br.com.mildevs.dao;

import java.util.List;

import br.com.mildevs.entity.Aluno;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

public class AlunoDAO {

	/*EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("escola");
		EntityManager manager = entityManagerFactory.createEntityManager();
	*/
	//Esse modo é substituido por esse que está abaixo, pois ja criamos as querys e as tabelas.
	
	private EntityManager manager;

	public AlunoDAO() {
		this.manager = Persistence.createEntityManagerFactory("escola").createEntityManager();
	}
	
	public boolean criaAluno(Aluno aluno) {
		//aqui precisaria de uma tratativa para possiveis exceptions
		manager.getTransaction().begin();
		manager.persist(aluno);
		manager.getTransaction().commit();
		return true;
	}
	
	public Aluno consultaAluno(int matricula) {
		return manager.find(Aluno.class, matricula);
	}
	
	public List<Aluno> listaAlunos(){
		Query query = manager.createQuery("select a from Aluno as a");
		return query.getResultList();
		
	}
	
	public boolean removeAluno(int matricula) {
		 Aluno alunoASerRemovido = manager.find(Aluno.class, matricula);
		 
		 //metodo de fail first, onde voce trata o possivel erro antes de colocar o bloco que daria certo
	
		 if(alunoASerRemovido == null) 
			 return false;
			 
		manager.getTransaction().begin();
		manager.remove(alunoASerRemovido);
		manager.getTransaction().commit();
		return true;
	}
}
