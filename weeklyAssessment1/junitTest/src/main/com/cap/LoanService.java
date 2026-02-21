package main.com.cap;

public class LoanService {
	public boolean isEligible(int age,double salary) {
		if(age>=21 && age<=60 && salary>=25000) return true;
		return false;
	}
	
	public double calculateEMI(double loanAmount,int tenureYears) {
		if(loanAmount<=0) throw new IllegalArgumentException("Loan amount should be greater than 0");
		else if(tenureYears<=0) throw new IllegalArgumentException("tenure year should be greater than 0");
		
		double EMI = loanAmount/(tenureYears*12);
		return EMI;
	}
	
	public String getLoanCategory(int creditScore) {
		if(creditScore>=750) return "Premium";
		else if(creditScore>=600 && creditScore<750) return "Standard";
		else return "High Risk";
	}
}

