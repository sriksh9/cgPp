package com.cg.service;

import java.util.HashMap;
import java.util.regex.Pattern;

import com.cg.bean.Account;
import com.cg.bean.Transaction;
import com.cg.dao.AccountDao;
import com.cg.dao.AccountDaoImpl;
import com.cg.exception.AccountException;
import com.cg.staticdb.StaticDB;

public class AccountServiceImpl implements AccountService{

	AccountDao dao = new AccountDaoImpl();
	
	@Override
	public boolean validateAccBal(double accBal) {
		// TODO Auto-generated method stub
		double accBalThresh = 1000;
		if(accBal<accBalThresh)
			return true;
		else
			return false;
	}

	@Override
	public Account addAccount(Account acc) {
		// TODO Auto-generated method stub
		return dao.addAccount(acc);
	}
	/*@Override
	public Transaction addTransaction(long transId, Transaction ts) {
		// TODO Auto-generated method stub
		return dao.addTransaction(transId, ts);
	}*/
	@Override
	public long[] showTransIDs(long accNo){
		return dao.showTransIDs(accNo);
	}

	@Override
	public Account showAccount(long accNo)throws AccountException{
		// TODO Auto-generated method stub
		return dao.showAccount(accNo);	
	}
	
	@Override
	public HashMap<Long,Transaction> showTransaction() {
		// TODO Auto-generated method stub
		return dao.showTransactions();
	}

	@Override
	public double deposit(long accNo, double amt) {
		// TODO Auto-generated method stub
		return dao.deposit(accNo, amt);
	}

	@Override
	public double withdraw(long accNo, double amt){
		// TODO Auto-generated method stub
		if(amt<StaticDB.getAccount().get(accNo).getAccBal())
			return dao.withdraw(accNo, amt);
		else{
			System.out.println("Withdrawl declined due to insufficient funds");
			return dao.withdraw(accNo, 0);
		}
	}

	@Override
	public boolean transfer(long accNo1, long accNo2, double amt) {
		// TODO Auto-generated method stub
		if((amt<StaticDB.getAccount().get(accNo1).getAccBal())&&(dao.transfer(accNo1, accNo2, amt)))
			return true;
		else{
			System.out.println("Transaction declined due to insufficient funds");
			return false;
		}
				
	}

	
	
	

}
