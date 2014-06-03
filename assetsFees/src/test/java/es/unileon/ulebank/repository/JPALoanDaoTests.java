package es.unileon.ulebank.repository;

import java.util.List;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import es.unileon.ulebank.assets.LoanData;

public class JPALoanDaoTests {
	
	private ApplicationContext context;
    private LoanDataDao loanDataDao;

    @Before
    public void setUp() throws Exception {
        context = new ClassPathXmlApplicationContext("classpath:test-context.xml");
        loanDataDao = (LoanDataDao) context.getBean("loanDataDao");
    }
    
    @Test
    public void testGetLoanList() {
        List<LoanData> loans = loanDataDao.getLoanDataList();
        assertEquals(loans.size(), 1, 0);	   
    }

    @Test
    public void testSaveLoan() {
        List<LoanData> loansData = loanDataDao.getLoanDataList();

        LoanData l = loansData.get(0);
        Double initialCapital = l.getInitialCapital();
        l.setInitialCapital(200000.12);
        loanDataDao.saveLoanData(l);

        List<LoanData> updatedLoansData = loanDataDao.getLoanDataList();
        LoanData l2 = updatedLoansData.get(0);
        assertEquals(l2.getInitialCapital(), 200000.12, 0);

        l2.setInitialCapital(initialCapital);
        loanDataDao.saveLoanData(l2);
    }
}
