package com.cg.pl;

//import java.util.HashMap;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;

import com.cg.bean.Account;
import com.cg.bean.Transaction;
import com.cg.exception.AccountException;
import com.cg.service.AccountService;
import com.cg.service.AccountServiceImpl;

public class Client {

	public static void main(String[] args) throws AccountException {
		// TODO Auto-generated method stub
		AccountService ser = new AccountServiceImpl();
		int choice = 0;
		long acNumBase=1000000000;
		long acNum = acNumBase;
		try(Scanner sc = new Scanner(System.in))
		{
			do
			{
				System.out.print("1->Create Account\n2->Display Details\n3->Deposit\n"
						+ "4->Withdraw\n5->Transfer\n6->MiniStatement\n7->Exit");
				choice=sc.nextInt();
				switch(choice){
				case 1:{
					acNum+=1;
					System.out.print("\nEnter the Name : ");
					String name = sc.next();
					System.out.print("\nEnter the IFSC : ");
					String ifsc = sc.next();
					double bal=0;
					do{
						System.out.print("\nEnter the opening balance : ");
						bal = sc.nextDouble();
					}while(ser.validateAccBal(bal));
					Account draftAc = new Account(acNum,name,ifsc,bal);
					Account ac = ser.addAccount(draftAc);
					System.out.print("\nAccount created Successfully, your A/c number is : "+ac.getAccNo());
					break;
				}
				case 2:{
					System.out.print("\nEnter the Account number : ");
					long acn  = sc.nextLong();
					System.out.print("\nYour account details;\n");
					System.out.println(ser.showAccount(acn));
					break;
				}
				case 3:{
					System.out.println("Enter your account number : ");
					long acn  = sc.nextLong();
					System.out.print("\nPlease enter the amount to be deposited : ");
					double dep = sc.nextDouble();
					System.out.println(ser.deposit(acn, dep));
					System.out.println(ser.showAccount(acn));
					break;
				}
				case 4:{
					System.out.print("\nEnter your account number : ");
					long acn  = sc.nextLong();
					System.out.print("\nPlease enter the amount to be withdrawn : ");
					double dep = sc.nextDouble();
					System.out.println(ser.withdraw(acn, dep));
					System.out.println(ser.showAccount(acn));
					break;
				}
				case 5:{
					System.out.print("\nEnter your account number : ");
					long acn1 = sc.nextLong();
					System.out.print("\nEnter the account number to be credited : ");
					long acn2 = sc.nextLong();
					System.out.print("\nEnter the amount to be transferred : ");
					double amt = sc.nextDouble();
					if(ser.transfer(acn1,acn2,amt)){
						System.out.println("Transaction successful");
						System.out.println(ser.showAccount(acn1));
					}
					break;
				}
				case 6:{
					System.out.print("\n Enter your Account number : ");
					long acn  = sc.nextLong();
					System.out.print("\nYour last 10 transactions are;\n");
					HashMap<Long,Transaction> tsMap = ser.showTransaction();
					if(tsMap.size()!=0){
						//Set<Long>keys = tsMap.keySet();
						long[] keys = ser.showTransIDs(acn);
						for(long key:keys){
							System.out.println(tsMap.get(key));
						}
					}
					break;
					
				}
				case 7: System.exit(0);
				}
				
				System.out.print("\nDo you want to continue 1->yes, 0->no : ");
				choice = sc.nextInt();
			}while(choice!=0);
			
		}
	}

}
