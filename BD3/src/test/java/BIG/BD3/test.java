package BIG.BD3;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;

public class test {

	public static void main(String[] args) {

		ArrayList<String> arr2 = new ArrayList<String>();
		Map<Double, Integer> map = new HashMap<Double, Integer>();

		arr2.add("cow");
		arr2.add("dog");
		arr2.add("cat");
//		arr2.add("rat");
//		arr2.add("rat");
//		arr2.add("rat");
//		arr2.add("ratl");
//		arr2.add("at");
//		arr2.add("ratad");
		arr2.add("ratlsdf");
//		arr2.add("ratlsdf");
//		arr2.add("aplle");
//		arr2.add("banana");
//		arr2.add("cow");
//		
//		arr2.add("cokw");
//		arr2.add("dogk");
//		arr2.add("catk");
//		arr2.add("ratk");
//		arr2.add("ratk");
//		arr2.add("ratk");
//		arr2.add("ratkl");
//		arr2.add("atk");
//		arr2.add("ratkad");
//		arr2.add("ratlksdf");
//		arr2.add("ratlsdf");
//		arr2.add("aplle");
//		arr2.add("banana");
//		arr2.add("cow");
//		
//		
//		arr2.add("rpatk");
//		arr2.add("ra5tk");
//		arr2.add("rat6k");
//		arr2.add("ratk1l");
//		arr2.add("atk1");
//		arr2.add("ratk8ad");
//		arr2.add("ratlk9sdf");
//		arr2.add("ratlsdf");
//		arr2.add("aplle");
//		arr2.add("banana");
//		arr2.add("cow");
//		
//		
//		
//		arr2.add("rat6k");
//		arr2.add("ra1tk1l");
//		arr2.add("atk61");
//		arr2.add("ratk28ad");
//		arr2.add("ratlk29sdf");
//		arr2.add("ratl85sdf");
//		arr2.add("apll22e");
//		arr2.add("bana96na");
//		arr2.add("cow2");
//		
//		
//		
//		
//
//		arr2.add("czow");
//		arr2.add("dozg");
//		arr2.add("cazt");
//		arr2.add("razt");
//		arr2.add("razt");
//		arr2.add("razt");
//		arr2.add("raztl");
//		arr2.add("azt");
//		arr2.add("raztad");
//		arr2.add("ratzlsdf");
//		arr2.add("ratlzsdf");
//		arr2.add("apllez");
//		arr2.add("banzana");
//		arr2.add("cowz");
//		
//		arr2.add("cokwz");
//		arr2.add("zdogk");
//		arr2.add("czatk");
//		arr2.add("raztk");
//		arr2.add("ratzk");
//		arr2.add("ratk");
//		arr2.add("ratkzl");
//		arr2.add("atkz");
//		arr2.add("ratkad");
//		arr2.add("ratlzksdf");
//		arr2.add("ratlsdf");
//		arr2.add("apllez");
//		arr2.add("banana");
//		arr2.add("cow");
//		
//		
//		arr2.add("rpatk");
//		arr2.add("ra5tk");
//		arr2.add("rat6k");
//		arr2.add("ratk1l");
//		arr2.add("atk1");
//		arr2.add("ratk8ad");
//		arr2.add("ratlk9sdf");
//		arr2.add("ratlsdf");
//		arr2.add("aplle");
//		arr2.add("banana");
//		arr2.add("cow");
		
		

		HashFunction hf = Hashing.murmur3_128(10);

		int b = 6;
		int m = 2 ^ b;
		double alpha;
		if (m == 16)
			alpha = 0.673;
		else if (m == 32)
			alpha = 0.697;
		else if (m == 64)
			alpha = 0.709;
		else {
			alpha = 0.7213 / (1 + 1.079 / m);
		}
		for (int i = 0; i < arr2.size(); i++) {
			long hValue = hf.hashString(arr2.get(i), StandardCharsets.UTF_8).asLong();
			String word = Long.toBinaryString(hValue);
			String k = word.substring(word.length() - b);
			double index = Math.log(Integer.parseInt(k));
			String remaining_bits = word.substring(0, word.length() - b);
			int a = remaining_bits.lastIndexOf("0");
			String x = remaining_bits.substring(0,
					remaining_bits.lastIndexOf("0") + 1);
			int a1 = x.lastIndexOf("1");
			int num_right_zeros = a - a1;
			
			System.out.println(remaining_bits); 
			System.out.println(x);
			System.out.println(a);
			System.out.println(a1);
			System.out.println(num_right_zeros);
			System.out.println("---------"); 
			
//			if (map.get(index) != null && !(map.get(index) < num_right_zeros)) {
//
//				map.put(index, num_right_zeros);
//
//			} else if (map.get(index) != null
//					&& !(map.get(index) > num_right_zeros)) {
//				
//				// do nothing
//
//			} else {
//
//				map.put(index, num_right_zeros);
//			}

		



			// LinkedList<String> list = new
			// LinkedList<String>(Arrays.asList(remaining_bits.split("1+")));
			// Collections.sort(list);
			// int maxLength = list.getLast().length();
			// // System.out.println(arr2.get(i) + " = " + maxLength);
			// sum += maxLength;

			// System.out.println(k);
			// System.out.println(index);

		}
		
		
//	int sum=0;	
//	double add=0;
//		for (double key : map.keySet()){
//			
//			sum+=map.get(key);
//			
//			add+= Math.pow(2, -map.get(key));
//			
//		}
//		
//		double avg = (double)sum/ map.size();
//		
//		System.out.println(sum);
//		System.out.println(map.size());
//		System.out.println(avg);
//		System.out.println(Math.pow(add, -1));
//		
//		double c = map.size()*map.size()*alpha*Math.pow(add, -1);
//		
//		double cardinality = alpha*map.size()* Math.pow(2, avg);
//		
//		
//		
//		System.out.println("estimated cardinality = " + cardinality);
//		
//		
////		System.out.println(map);
//
//		 Set<String> uniqueSet = new HashSet<String>(arr2);
//		 System.out.println("real cardinality = " + uniqueSet.size() );
//		 
//		 System.out.println(c);
		// System.out.println("estimated cardinality = " +
		// (double)sum/arr2.size() );
		//
		//
		// System.out.println(arr2.size());

	}

}
