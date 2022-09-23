//$Id$
package com.zoho.hash;

//import java.util.HashMap;
import java.util.Scanner;

public class HashMainControl {
	

	public static void main(String[] args){
		
		@SuppressWarnings("resource")
		
		Scanner sc=new Scanner(System.in);
		
		CustomHashMap<String, String> map = new CustomHashMap<String, String>();
		CustomHashMap<String,CustomHashMap<String,Integer>> chm1 = new CustomHashMap<>();
		CustomHashMap<String,CustomHashMap<String,Integer>> chm2 = new CustomHashMap<>(10);
		CustomHashMap<String, String> map1 = new CustomHashMap<String, String>();
		CustomHashMap<String, Integer> map2 = new CustomHashMap<>();
		CustomHashMap<String, Integer> map3 = new CustomHashMap<>();
		
		map1.put("1", "One");
		map1.put("2", "Two");
		map1.put("3", "Three");
		
		map2.put("Shoes", 200);
	    map2.put("Bag", 300);
	    map2.put("Pant", 150);
	    
	    map3.put("Two", 200);
	    map3.put("Three", 300);
	    map3.put("Four", 450);
	    
	    chm2.put("1", map2);
	    chm2.put("2", map3);
	    
	    System.out.println(chm1.isEmpty());
	    chm1.put("1",map2);
	    chm1.put("2",map3);
	    System.out.println(chm1.size());
	    System.out.println(chm1.isEmpty());
	    chm1.display();
	    System.out.println("");
	    chm1.remove("2");
	    System.out.println(chm1.entrySet());
	    chm1.put("2",map3);
	    System.out.println(chm1.toString());
	    chm1.clear();
	    chm1.display();
	    chm1.put("1",map2);
	    chm1.put("2",map3);
	    System.out.println(chm1.get("1"));
	    System.out.println(chm1.keySet());
	    System.out.println(chm1.values());
	    System.out.println(chm1.containsKey("1"));
	    System.out.println(chm1.containsKey("4"));
	    System.out.println(chm1.containsValue(chm1.get("1")));
	    chm1.forEach((key, value) -> {
			System.out.println(key + "=" + value + " ");
			return value;
		});
	    System.out.println(chm1.equals(chm2));
	    chm2.put("3",map2);
	    System.out.println(chm1.equals(chm2));
	    chm1.putAll(chm2);
	    System.out.println(chm1.entrySet());
	    chm1.replace("1", chm1.get("1"),map3);
	    System.out.println(chm1.entrySet());
	    
	    
	    
		while(true){
			System.out.println("Enter 1  --> put()\nEnter 2  --> get()\nEnter 3  --> display()\nEnter 4  --> remove()\nEnter 5  --> empty()\nEnter 6  --> clear()\nEnter 7  --> putAll() \nEnter 8  --> containsKey()\nEnter 9  --> containsValue()\nEnter 10 --> getOrDefault()\nEnter 11 --> entrySet()\nEnter 12 --> keySet()\nEnter 13 --> size()\nEnter 14 --> replace()\nEnter 15 --> toString()\nEnter 16 --> equals()\nEnter 17 --> compute()\nEnter 18 --> values()\nEnter 19 --> merge()\nEnter 20 --> forEach()\nEnter 21 --> replaceAll()\nEnter 0  --> Exit..");
			int choice=sc.nextInt();
			
			if(choice==1) {
				System.out.println("Going to add entries in map");
				System.out.println("Enter Key :");
				String key=sc.next();
				System.out.println("Enter Value :");
				String value=sc.next();
				map.put(key,value);
				System.out.println("Data Added Successfully");
			}
			
			else if(choice==2) {
				System.out.println("Search Using key in Hash Table....");
				System.out.println("Enter Key :");
				String key=sc.next();
				String value=map.get(key);
				if(value==null) {
					System.out.println("Key not present in the table!!!");
				}
				else {
					System.out.println("Data Retrieved Successfully!!! The Value is... "+value);
				}
			}
			
			else if(choice==3) {
				System.out.println("Displaying all the entry in map......");
			    map.display();
			}
			
			else if(choice==4) {
				System.out.println("Remove Using key in Hash Table....");
				System.out.println("Enter Key :");
				String key=sc.next();
				map.remove(key);
				System.out.println("Data Removed Successfully");
			}
			
			else if(choice==5) {
				System.out.println(map.isEmpty());
			}
			
			else if(choice==6) {
				map.clear();
				System.out.println("Hash Table Data is Cleared");
			}
			
			else if(choice==7) {
				map.putAll(map1);
				System.out.println("Data Appended to Hash table");
			}
			
			else if(choice==8) {
				System.out.println("Enter Key :");
				String key=sc.next();
				System.out.println(map.containsKey(key));
			}
			
			else if(choice==9) {
				System.out.println("Enter Value :");
				String val=sc.next();
				System.out.println(map.containsValue(val));
			}
			
			else if(choice==10) {
				System.out.println("Get the Value Using key or Display Message....");
				System.out.println("Enter Key :");
				String key=sc.next();
				System.out.println("Enter Message :");
				String msg=sc.next();
				System.out.println(map.getOrDefault(key,msg));
			}
			
			else if(choice==11) {
				System.out.println(map.entrySet());
//				for(Entry<String,String> e:map.entrySet()) {
//					System.out.println(e.getKey()+" "+e.getValue());
//				}
			}
			
			else if(choice==12) {
				System.out.println(map.keySet());
			}
			
			else if(choice==13) {
				System.out.println("The Size : "+ map.size());
			}
			
			else if(choice==14) {
				boolean bool;
				System.out.println("Enter Key :");
				String key=sc.next();
				System.out.println("Enter Old Value:(Optional)");
				sc.nextLine();
				String old=sc.nextLine();
				System.out.println("Enter Value to Replace :");
				String msg=sc.next();
				if(old.isEmpty()==false) {
					bool=map.replace(key, old, msg);
				}
				else
					bool=map.replace(key,msg);
				if(bool)
					System.out.println("Value is Replaced!");
				else
					System.out.println("Value is Not Replaced!");
			}
			
			else if(choice==15) {
				System.out.println(map);
			}
			
			else if(choice==16) {
				System.out.println(map.equals(map1));
			}
			
			else if(choice==17) {
				System.out.println("Initial contents: "+map2);
				System.out.println("Enter the Key to Double..");
				String str=sc.next();
				int newPrice = map2.compute(str, (key, value) -> value * 2);
				System.out.println("The Updated Value is: "+newPrice);
				System.out.println("Changed contents: "+map2);
			}
			
			else if(choice==18) {
				System.out.println(map.values());
			}
			
			else if(choice==19) {
				System.out.println("Initial contents: "+map2);
				System.out.println("Enter the Key to Merge..");
				String str=sc.next();
				System.out.println("Enter the Value to Merge..");
				Integer num=sc.nextInt();
				int newPrice = map2.merge(str, num, (old, newv) -> old + newv);
				System.out.println("The Updated Value is: "+newPrice);
				System.out.println("Changed contents: "+map2);
				
			}
			
			else if(choice==20) {
				System.out.println("Initial contents: "+map2);
				map2.forEach((key, value) -> {
					value =value *2;
//					System.out.print(key + "=" + value + " "); 
					return value;
				});
				  
				System.out.println("Changed contents: "+map2);
			}
			
			else if(choice==21) {
				System.out.println("Initial contents: "+map);
				map.replaceAll((key, value) -> value.toUpperCase());
				System.out.println("Changed contents: "+map);
			}
			
			else if(choice==0) {
				break;
			}
			
			
		}
		
	    System.out.println("Process Completed!!!");
	    
	}
}
