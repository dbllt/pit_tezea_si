package tezea.si.dao;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;

import tezea.si.model.Client;

public class ClientDAO {
	
	public ClientDAO() {
		
	}
	
	public void persist(Client client) {
		EntityManager manager = EntityManagerHelper.getEntityManager();
		EntityTransaction tx = manager.getTransaction();
		tx.begin();


		try {
			manager.persist(client);
		} catch (Exception e) {
			e.printStackTrace();
		}
		tx.commit();
	}
	
	
}
