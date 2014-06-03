package es.unileon.ulebank.assets;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="loan")
public class LoanData implements Serializable{
	
	private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    
    private double initialCapital;
    private double interest;
    private Integer amortizationTime;
    
    public Integer getId(){
        return id;
    }

    public void setId(Integer id){
        this.id = id;
    }
    
    public double getInitialCapital(){
    	return this.initialCapital;
    }
    
    public void setInitialCapital(double initialCapital){
    	this.initialCapital = initialCapital;
    }
    
    public double getInterest(){
    	return this.interest;
    }
    
    public void setInterest(double interest){
    	this.interest = interest;
    }
    
    public Integer getAmortizationTime(){
    	return this.amortizationTime;
    }
    
    public void setAmortizationTime(Integer amortizationTime){
    	this.amortizationTime = amortizationTime;
    }
}
