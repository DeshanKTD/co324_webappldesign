//package bank;

import java.util.HashMap;
import java.util.Map;

public class Account {

	private static int count = 0;
	/** Hash table of all accounts that have been created */
	private static Map<Integer,Account> accounts = new HashMap<Integer,Account>();
	
	/** Find account by ID */
	public static Account find(int id) {
		return accounts.get(id);	
	}

	final int id;
	private int balance;
	private int accntNumber;
	
	/** Create new account with unique ID and add it to global hash table */
	public Account() {
		synchronized(accounts) {
			id = count++;
			this.accntNumber=id;
			accounts.put(id, this);
		}
	}
	
	/** Negative amount indicates withdrawal */
	public void deposit(int amount) {
		//synchronized(this){
		        balance += amount;
		//}
	}
	
	/** Transfer amount from this account to another. */
	public void transfer (Account to, int amount) {
		if(this.accntNumber>to.accntNumber){
			synchronized(this){
			 	synchronized(to){
			 		this.balance-=amount;
			        to.balance+=amount;
			 	}
			}
		}
		else{
			synchronized(to){
			 	synchronized(this){
			 		this.balance-=amount;
			        to.balance+=amount;
			 	}
			}
		}
		 //deposit(-amount);
		//to.deposit(amount);
	}
	
	public int balance() {
		return balance;
	}

	@Override
	public String toString() {
		return "ID:"+id+", balance:"+balance;
	}
}
