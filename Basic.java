package com.letsVote;

public interface Basic {
	int  generate();
	boolean StoreId(int k);
	void signUp(String name, String voterId, String password, int age);
	boolean login(String voterId, String password);
}

