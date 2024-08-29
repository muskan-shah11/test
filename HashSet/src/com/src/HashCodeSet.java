package com.src;

import java.util.HashSet;

public class HashCodeSet {

	public static void main(String[] args) {

		HashSet<ECommerce> hashSet = new  HashSet<>();
		
		ECommerce ecom = new ECommerce(101,"amazon","2d0d4809e6bdb6f4db3e547f27b1873c");
		ECommerce ecom1 = new ECommerce(102,"flipkart","5972793cc1718423517639938aae9100");
		ECommerce ecom2 = new ECommerce(101,"amazon","2d0d4809e6bdb6f4db3e547f27b1873c");
		ECommerce ecom3 = new ECommerce(101,"amazon","2d0d4809e6bdb6f4db3e547f27b1873c");

		hashSet.add(ecom);
		hashSet.add(ecom1);
		hashSet.add(ecom2);
		hashSet.add(ecom3);
		
		for(ECommerce ec : hashSet) {
			
			System.out.println(ec);
		}
	}

}
