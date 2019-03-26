package com.cg.service;

import java.util.HashMap;

import com.cg.bean.Account;
import com.cg.bean.Transaction;
import com.cg.exception.AccountException;

public interface AccountService {

	public boolean validateAccBal(double accBal);
	public Account addAccount(Account acc);
	//public Transaction addTransaction(long transId, Transaction ts);
	public Account showAccount(long accNo)throws AccountException;
	public long[] showTransIDs(long accNo);
	public HashMap<Long,Transaction> showTransaction();
	public double deposit(long accNo, double amt);
	public double withdraw(long accNo, double amt);
	public boolean transfer(long accNo1, long accNo2, double amt);
}
