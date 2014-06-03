package es.unileon.ulebank.repository;

import java.util.List;

import es.unileon.ulebank.assets.LoanData;

public interface LoanDataDao {
	public List<LoanData> getLoanDataList();

    public void saveLoanData(LoanData loanData);
}
