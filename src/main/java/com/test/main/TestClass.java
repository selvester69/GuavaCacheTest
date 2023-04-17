package com.test.main;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

public class TestClass {
	
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		TestClass obj = new TestClass();
		obj.whenLiveTimeEnd_thenRefresh();
	}

	
	public void whenPreloadCache_thenUsePutAll() {
	    CacheLoader<String, String> loader;
	    loader = new CacheLoader<String, String>() {
	        @Override
	        public String load(String key) {
	            return key.toUpperCase();
	        }
	    };

	    LoadingCache<String, String> cache;
	    cache = CacheBuilder.newBuilder().build(loader);

	    Map<String, String> map = new HashMap<String, String>();
	    map.put("first", "FIRST");
	    map.put("second", "SECOND");
	    cache.putAll(map);

	}
	
	public void whenLiveTimeEnd_thenRefresh() throws InterruptedException, ExecutionException {
	    CacheLoader<String, String> loader;
	    loader = new CacheLoader<String, String>() {
	        @Override
	        public String load(String key) {
	            return key.toUpperCase();
	        }
	    };

	    LoadingCache<String, String> cache;
	    cache = CacheBuilder.newBuilder()
	      .refreshAfterWrite(5,TimeUnit.SECONDS)
	      .build(loader);
	    
	    Map<String, String> map = new HashMap<String, String>();
	    map.put("first", "FIRST");
	    map.put("second", "SECOND");
	    cache.putAll(map);
	    
	    for(int i=0;i<100;i++) {
	    	Thread.sleep(5000);
	    	System.out.println(cache.get("FIRST"));
	    }
	}
}
