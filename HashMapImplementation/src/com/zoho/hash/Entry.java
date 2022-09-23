//$Id$
package com.zoho.hash;

public class Entry<KEY, VALUE> {

	    private KEY key;
	    private VALUE value;
	    private Entry<KEY, VALUE> next;

	    public Entry(KEY key, VALUE value, Entry<KEY, VALUE> next){
	        this.key = key;
	        this.value = value;
	        this.next = next;
	    }

	    public KEY getKey() {
	        return key;
	    }

	    public void setKey(KEY key) {
	        this.key = key;
	    }

	    public VALUE getValue() {
	        return value;
	    }

	    public void setValue(VALUE value) {
	        this.value = value;
	    }

	    public Entry<KEY, VALUE> getNext() {
	        return next;
	    }

	    public void setNext(Entry<KEY, VALUE> next) {
	        this.next = next;
	    }

		@Override
		public String toString() {
			return key + " = " + value;
		}
	    

	    
	    
}
