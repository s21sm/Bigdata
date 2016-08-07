package BIG.BD3;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;

public class test2 {
	
	public static void main(String[] args) {
		
		ArrayList<String> arr2 = new ArrayList<String>();
		Map<Double, Integer> map = new HashMap<Double, Integer>();		
		HashFunction hf = Hashing.murmur3_128(10);
		
		arr2.add("cow");
		arr2.add("dog");
		arr2.add("cat");
		arr2.add("rat");
		arr2.add("rat");
		arr2.add("rat");
		arr2.add("ratl");
		arr2.add("at");
//		arr2.add("ratad");
//		arr2.add("ratlsdf");
//		arr2.add("ratlsdf");
//		arr2.add("aplle");
//		arr2.add("banana");
//		arr2.add("cow");		
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
		
		long kmv =0 ;
		for (int i = 0; i < arr2.size(); i++) {
			long hashed_val = Math.abs(hf.hashString(arr2.get(i), StandardCharsets.UTF_8).asLong());
			
//			System.out.println(hashed_val);
			if(i==0){
				kmv=hashed_val;
			}else if(kmv>hashed_val){
			kmv=hashed_val;
			}
		}
//		System.out.println(kmv);
//		
		System.out.println( Math.pow(2, 16)/ (double)kmv);
		
		
		
		
	}

}
