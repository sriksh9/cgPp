package com.cg.dao;

import java.util.HashMap;

import com.cg.bean.Account;
import com.cg.bean.Transaction;
import com.cg.exception.AccountException;

public interface AccountDao {
	long transactionID=0;
	public Account addAccount(Account acc);
	public void addTransaction(long transId, Transaction ts);
	public Account showAccount(long accNo)throws AccountException;
	public long[] showTransIDs(long accNo);
	public HashMap<Long,Transaction> showTransactions();
	public double deposit(long accNo, double amt);
	public double withdraw(long accNo, double amt);
	public boolean transfer(long accNo1, long accNo2, double amt);
}
