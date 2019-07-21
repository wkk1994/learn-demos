package com.wkk.demo.javacontainer;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Description LRU缓存
 * @Author wkk
 * @Date 2019-03-22 21:52
 **/
public class LRUTest {

    static class LRUCache<K,V> extends LinkedHashMap<K,V>{
        private static final int MAX_ENTRIES = 3;

        @Override
        protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
            if(this.size() > MAX_ENTRIES){
                return true;
            }
            return false;
        }

        LRUCache(){
            super(MAX_ENTRIES, 0.75f, true);
        }
    }

    public static void main(String[] args) {
        LRUCache<String,String> lruCache = new LRUCache<>();
        lruCache.put("a","a");
        lruCache.put("b","b");
        lruCache.put("c","c");
        lruCache.put("g","g");
        lruCache.put("d","d");
        System.out.println(lruCache.keySet());
    }
}
