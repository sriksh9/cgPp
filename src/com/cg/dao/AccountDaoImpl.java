package com.cg.dao;

import java.util.HashMap;
import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime;   

import com.cg.bean.Account;
import com.cg.bean.Transaction;
import com.cg.exception.AccountException;
import com.cg.staticdb.StaticDB;

public class AccountDaoImpl implements AccountDao{
	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
	
	@Override
	public Account addAccount(Account acc) {
		// TODO Auto-generated method stub
		StaticDB.getAccount().put(acc.getAccNo(), acc);
		return acc;
	}
	@Override
	public void addTransaction(long transId, Transaction ts) {
		// TODO Auto-generated method stub
		StaticDB.getTransaction().put(transId,ts);
	}
	@Override
	public Account showAccount(long accNo) throws AccountException{
		// TODO Auto-generated method stub
		HashMap<Long,Account> acMap = StaticDB.getAccount();
		if(acMap.size()==0)
			throw new AccountException("No employee data found");
		else
			return StaticDB.getAccount().get(accNo); 
	}
	@Override
	public long[] showTransIDs(long accNo){
		return StaticDB.getAccount().get(accNo).getTransIds();
	}
	
	@Override
	public HashMap<Long,Transaction> showTransactions(){
		// TODO Auto-generated method stub
		HashMap<Long,Transaction> tsMap = StaticDB.getTransaction();
		if(tsMap.size()==0){
			System.out.println("No Transaction history found");
			return null;
		}
		else
			return tsMap;
	}

	@Override
	public double deposit(long accNo, double amt) {
		// TODO Auto-generated method stub
		
		//double balance = StaticDB.getAccount().get(accNo).getAccBal()+amt;
		//StaticDB.getAccount().get(accNo).setAccBal(balance);
		balInc(accNo,amt);
		StaticDB.getAccount().get(accNo).setTransId(((long)(Math.random()*1000+1)));
		LocalDateTime now = LocalDateTime.now();
		Transaction ts = new Transaction("Deposit",amt,StaticDB.getAccount().get(accNo).getAccBal(),dtf.format(now));
		addTransaction(StaticDB.getAccount().get(accNo).getTransId(0), ts);
		return StaticDB.getAccount().get(accNo).getAccBal();
	}
	
	private void balInc(long accNo, double amt){
		double balance = StaticDB.getAccount().get(accNo).getAccBal()+amt;
		StaticDB.getAccount().get(accNo).setAccBal(balance);
	}

	@Override
	public double withdraw(long accNo, double amt) {
		// TODO Auto-generated method stub
		//double balance = StaticDB.getAccount().get(accNo).getAccBal()-amt;
		//StaticDB.getAccount().get(accNo).setAccBal(balance);
		balDec(accNo,amt);
		StaticDB.getAccount().get(accNo).setTransId(((long)(Math.random()*1000+1)));
		LocalDateTime now = LocalDateTime.now();
		Transaction ts = new Transaction("Withdraw",amt,StaticDB.getAccount().get(accNo).getAccBal(),dtf.format(now));
		addTransaction(StaticDB.getAccount().get(accNo).getTransId(0), ts);
		return StaticDB.getAccount().get(accNo).getAccBal();
	}
	private void balDec(long accNo, double amt){
		double balance = StaticDB.getAccount().get(accNo).getAccBal()-amt;
		StaticDB.getAccount().get(accNo).setAccBal(balance);
	}

	@Override
	public boolean transfer(long accNo1, long accNo2, double amt) {
		// TODO Auto-generated method stub
		double bal1 = StaticDB.getAccount().get(accNo1).getAccBal();
		if(bal1>=amt){
			balDec(accNo1,amt);
			balInc(accNo2,amt);
			//amt = deposit(accNo2,(bal1-withdraw(accNo1,amt)));
			long tid = (long)(Math.random()*1000+1);
			StaticDB.getAccount().get(accNo1).setTransId(tid);
			LocalDateTime now = LocalDateTime.now();
			Transaction ts1 = new Transaction("Transfer",amt,StaticDB.getAccount().get(accNo1).getAccBal(),dtf.format(now));
			addTransaction(StaticDB.getAccount().get(accNo1).getTransId(0), ts1);
			StaticDB.getAccount().get(accNo2).setTransId(tid);
			Transaction ts2 = new Transaction("Transfer",amt,StaticDB.getAccount().get(accNo2).getAccBal(),dtf.format(now));
			addTransaction(StaticDB.getAccount().get(accNo1).getTransId(0), ts1);
			
			return true;
		}
		else
			return false;
			
	}
	
	
	

}
