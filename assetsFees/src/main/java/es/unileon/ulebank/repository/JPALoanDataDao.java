package es.unileon.ulebank.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import es.unileon.ulebank.assets.LoanData;

@Repository(value = "loanDataDao")
public class JPALoanDataDao implements LoanDataDao{
	
	private EntityManager em = null;

    /*
     * Sets the entity manager.
     */
    @PersistenceContext
    public void setEntityManager(EntityManager em) {
        this.em = em;
    }
	
    @Transactional(readOnly = true)
    @SuppressWarnings("unchecked")
	public List<LoanData> getLoanDataList() {
    	//Aqui hay q crear los loans y devolverlos
		return em.createQuery("select l from LoanData l order by l.id").getResultList();
	}

    @Transactional(readOnly = false)
	public void saveLoanData(LoanData loanData) {
    	em.merge(loanData);
	}
	
}
