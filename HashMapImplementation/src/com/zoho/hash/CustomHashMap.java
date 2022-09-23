//$Id$
package com.zoho.hash;

import java.util.*;
import java.util.function.BiFunction;

public class CustomHashMap<KEY, VALUE> {
	private int capacity = 16; 

    private Entry<KEY, VALUE>[] table;

    @SuppressWarnings("unchecked")
	public CustomHashMap(){
        table = new Entry[capacity];
    }
    
    @SuppressWarnings("unchecked")
	public CustomHashMap(int capacity){
        this.capacity = capacity;
        table = new Entry[capacity];
    }
    
	public VALUE put(KEY key, VALUE value){
        int index = index(key);
        Entry<KEY, VALUE> newEntry = new Entry<KEY, VALUE>(key, value, null);
        if(table[index] == null){
            table[index] = newEntry;
        }
        else{
            Entry<KEY, VALUE> previousNode = null;
            Entry<KEY, VALUE> currentNode = table[index];
            while(currentNode != null){
            	if(currentNode.getKey().equals(key)){
            		VALUE val=currentNode.getValue();
                    currentNode.setValue(value);
                    return val;
                }
                previousNode = currentNode;
                currentNode = currentNode.getNext();
            }
            if(currentNode == null)
                previousNode.setNext(newEntry);
        }
        return null;
    }
    
	public VALUE get(KEY key){
        VALUE value = null;
        int index = index(key);
        Entry<KEY, VALUE> entry = table[index];
        while (entry != null){
            if(entry.getKey().equals(key)) {
                value = entry.getValue();
                break;
            }
            entry = entry.getNext();
        }
        return value;
    }
    
	public VALUE remove(KEY key){
        int index = index(key);
        Entry<KEY, VALUE> previous = null;
        Entry<KEY, VALUE> current = table[index];
        while (current != null){
            if(current.getKey().equals(key)){
                if(previous == null){
                	VALUE val=current.getValue();
                    current = current.getNext();
                    table[index] = current;
                    return val;
                }else {
                	VALUE val=current.getValue();
                    previous.setNext(current.getNext());
                    return val;
                }
            }
            previous = current;
            current= current.getNext();
        }
        return null;
    }
	
	public boolean isEmpty() {
		for(int i = 0; i < capacity; i++){
            if(table[i] != null){
            	return false;
            }
		}
		return true;
	}
	
	public void clear() {
		for(int i = 0; i < capacity; i++){
			if(table[i] != null)
				table[i] = null;
		}
	}
	
	public void display(){
		int flag=0;
		Set<Entry<KEY,VALUE>> hash_Set =this.entrySet();
		for(Entry<KEY, VALUE> curr:hash_Set) {
			flag=1;
            System.out.println(String.format("Key is %s and value is %s", curr.getKey(), curr.getValue()));
		}
        if(flag==0) {
        	System.out.println("No Data to Show...");
        }
    }
	
	public VALUE getOrDefault(KEY key, VALUE defaultstr) {
		VALUE value = null;
		if(this.containsKey(key)) {
			Set<Entry<KEY,VALUE>> hash_Set =this.entrySet();
			for(Entry<KEY, VALUE> curr:hash_Set) {
				 if(curr.getKey().equals(key)) {
		                value = curr.getValue();
		                return value;
		            }			
			}
		}
		return defaultstr;
	}
    
	public void putAll(CustomHashMap<KEY, VALUE> map1) {
		Set<Entry<KEY,VALUE>> hash_Set =map1.entrySet();
		for(Entry<KEY, VALUE> curr:hash_Set) {
            this.put(curr.getKey(),curr.getValue());
		}
	}
	
	public boolean containsKey(KEY key){
		Set<Entry<KEY,VALUE>> hash_Set =this.entrySet();
		for(Entry<KEY, VALUE> curr:hash_Set) {
			if(curr.getKey().equals(key)) {
                return true;
            }
		}
        return false;
    }
	
	public Set<Entry<KEY,VALUE>> entrySet() {
		Set<Entry<KEY,VALUE>> hash_Set = new HashSet<>();
		for(int i = 0; i < capacity; i++){
            if(this.table[i] != null){
                Entry<KEY, VALUE> currentNode = this.table[i];
                while (currentNode != null){
                    hash_Set.add(currentNode);
                    currentNode = currentNode.getNext();
                }
            }
        }
		return hash_Set;
	}
	
	public Set<KEY> keySet() {
		Set<KEY> hash_Set = new HashSet<KEY>();
		for(int i = 0; i < capacity; i++){
            if(table[i] != null){
                Entry<KEY, VALUE> currentNode = table[i];
                while (currentNode != null){
                    hash_Set.add(currentNode.getKey());
                    currentNode = currentNode.getNext();
                }
            }
        }
		return hash_Set;
	}
	
	@Override
	public String toString() {
		String str="{";
		int f=0;
		for(int i = 0; i < capacity; i++){
            if(table[i] != null){
                Entry<KEY, VALUE> currentNode = table[i];
                while (currentNode != null){
                	if(f==0) {
                		str+=currentNode.getKey()+"="+currentNode.getValue();
                		f=1;
                	}
                	else {
                		str+=", "+currentNode.getKey()+"="+currentNode.getValue();
                	}
                    currentNode = currentNode.getNext();
                }
            }
        }
		str+="}";
		return str;
	}
	
	public boolean containsValue(VALUE value){
		Set<Entry<KEY,VALUE>> hash_Set =this.entrySet();
		for(Entry<KEY, VALUE> curr:hash_Set) {
			if(curr.getValue().equals(value)) {
                return true;
            }
		}
        return false;
        
    }
	
	
	public int size() {
		Set<Entry<KEY,VALUE>> hash_Set =this.entrySet();
		return hash_Set.size();
	}
	
    private int index(KEY key){
        if(key == null){
            return 0;
        }
        return Math.abs(key.hashCode() % capacity);
        
    }


	public boolean replace(KEY key, VALUE val) {
		if(this.containsKey(key)) {
			this.put(key,val);
			return true;
		}
		return false;
	}
	
	public boolean replace(KEY key,VALUE oldval, VALUE val) {
		if(this.containsKey(key) && Objects.equals(this.get(key), oldval)) {
			this.put(key,val);
			return true;
		}
		return false;
	}
	
	public boolean equals(CustomHashMap<KEY, VALUE> map1) {
		
		for(int i = 0; i < capacity; i++){
            if(this.table[i] != null){
                Entry<KEY, VALUE> currentNode = this.table[i];
                while (currentNode != null){
                	if(map1.containsKey(currentNode.getKey())==false) {
                		return false;
                	}
                	else if(Objects.equals(map1.get(currentNode.getKey()),currentNode.getValue())==false){
                		return false;
                	}
                    currentNode = currentNode.getNext();
                }
            }
        }
		for(int i = 0; i < map1.capacity; i++){
            if(map1.table[i] != null){
                Entry<KEY, VALUE> currentNode = map1.table[i];
                while (currentNode != null){
                	if(this.containsKey(currentNode.getKey())==false) {
                		return false;
                	}
                	else if(Objects.equals(this.get(currentNode.getKey()),currentNode.getValue())==false){
                		return false;
                	}
                    currentNode = currentNode.getNext();
                }
            }
        }

		return true;
	}


	public VALUE compute(KEY key, BiFunction<KEY, VALUE, VALUE> object) {
		VALUE val=this.get(key);
		VALUE x=(VALUE) object.apply(key,val);
		this.replace(key, x);
		return x;
	}
	
	public Set<VALUE> values() {
		Set<VALUE> Result_Set = new HashSet<VALUE>();
		Set<KEY> hash_Set = this.keySet();
		for(KEY s:hash_Set) {
			Result_Set.add(this.get(s));
		}
		return Result_Set;
	}


	
	public VALUE merge(KEY str, VALUE num, BiFunction<VALUE, VALUE, VALUE> object) {
		VALUE x;
		if(this.containsKey(str)) {
			x=(VALUE) object.apply(num, this.get(str));
			this.replace(str, x);
		}
		else {
			this.put(str,num);
			x=num;
		}
		return x;
	}


	public void forEach(BiFunction<KEY, VALUE , VALUE> object) {
		Set<KEY> hash_Set =this.keySet();
		for(KEY s:hash_Set) {
			VALUE x=(VALUE) object.apply(s,this.get(s));
			this.replace(s, x);
		}
		
	}


	public void replaceAll(BiFunction<KEY, VALUE , VALUE> object) {
		Set<KEY> hash_Set = this.keySet();
		for(KEY s:hash_Set) {
			VALUE x=(VALUE) object.apply(s,this.get(s));
			this.replace(s, x);
		}
		
	}




}
