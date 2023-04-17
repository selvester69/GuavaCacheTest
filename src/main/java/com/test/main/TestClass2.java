package com.test.main;

import java.util.concurrent.TimeUnit;

public class TestClass2 {
	CacheStore<String> cache = new CacheStore<>(5, TimeUnit.HOURS);
	int counter = 0;
	public TestClass2() {
		// TODO Auto-generated constructor stub
		cache = new CacheStore<>(3, TimeUnit.SECONDS);
	}
	
	public static void main(String[] args) throws InterruptedException {
		TestClass2 obj = new TestClass2();
		for(int i=0;i<10;i++) {
			Thread.sleep(2000);
			obj.testCache();
		}
		
	}
	public void testCache() {
		String secret = cache.get("SECRET");
		if(secret==null || "".equals(secret)) {
			cache.add("SECRET", "Hello "+counter++);
		}
		System.out.println(secret);
		
	}

}
