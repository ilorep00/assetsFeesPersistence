package es.unileon.ulebank.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import es.unileon.ulebank.account.Account;
import es.unileon.ulebank.assets.Loan;
import es.unileon.ulebank.assets.LoanData;
import es.unileon.ulebank.assets.exceptions.LoanException;
import es.unileon.ulebank.assets.handler.FinancialProductHandler;
import es.unileon.ulebank.assets.strategy.loan.DefaultLoanStrategy;
import es.unileon.ulebank.assets.strategy.loan.ScheduledPayment;
import es.unileon.ulebank.assets.support.PaymentPeriod;
import es.unileon.ulebank.bank.Bank;
import es.unileon.ulebank.handler.GenericHandler;
import es.unileon.ulebank.handler.MalformedHandlerException;
import es.unileon.ulebank.office.Office;
import es.unileon.ulebank.repository.LoanDataDao;
import es.unileon.ulebank.transacionManager.TransactionManager;

@Controller
@Component
public class LoanController {
	protected final Log logger = LogFactory.getLog(getClass());
	
	@Autowired
	private LoanDataDao loanDataDao;
	
	@RequestMapping(value="/hello.htm")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		
		String now = (new Date()).toString();
        logger.info("Returning hello view with " + now);
        
        Map<String, Object> myModel = new HashMap<String, Object>();
        
        myModel.put("now", now);
        
        List<LoanData> lista = loanDataDao.getLoanDataList();
        
        try{
        	FinancialProductHandler fph = new FinancialProductHandler("LN", "ES");
            
            Bank bank = new Bank(new TransactionManager(), new GenericHandler("1234"));
            
            Office office = new Office(new GenericHandler("1234"), bank);
            
            Account account = new Account(office, bank, "0000000000");
            
            //Creamos ahora el loan con los datos de la tabla mas el handler mas el Account.
            Loan loan = new Loan(fph, lista.get(0).getInitialCapital(), lista.get(0).getInterest(), PaymentPeriod.MONTHLY, lista.get(0).getAmortizationTime(), account);
            
            DefaultLoanStrategy dls = new DefaultLoanStrategy(loan);
            
            ArrayList<ScheduledPayment> payments = dls.doCalculationOfPayments();
            
            myModel.put("products", payments);
        }
        catch(MalformedHandlerException e){
        	e.printStackTrace();
        }
        catch(LoanException e){
        	e.printStackTrace();
        }
        
        return new ModelAndView("hello", "model", myModel);
	}
	
	public void setLoanDataDao(LoanDataDao loanDataDao) {
        this.loanDataDao = loanDataDao;
    }
}
