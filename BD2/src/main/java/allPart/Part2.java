package allPart;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;
import com.google.common.collect.Multiset.Entry;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;

public class Part2 {

	public static void main(String[] args) throws IOException {

		Part1.main(args);

		List<Multiset<String>> Listmultiset = Part1.multi;
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();

		Multiset<String> A = Listmultiset.get(0);
		Multiset<Integer> N1 = getHashed(A);
		Random rand = new Random();

		for (Entry<Integer> entry : N1.entrySet()) {
			int index = rand.nextInt((2 ^ 64 - 0) + 1) + 0;
			map.put(entry.getElement(), index);

		}
//two bucket
		List<Multiset<String>> above = new ArrayList<Multiset<String>>();
		List<Multiset<String>> below = new ArrayList<Multiset<String>>();

// putting into bucket
		for (int i = 0; i < Listmultiset.size(); i++) {
			Multiset<String> B = Listmultiset.get(i);
			Multiset<Integer> N2 = getHashed(B);
			int sum = 0;
			for (Entry<Integer> entry : N2.entrySet()) {

				boolean checker = map.containsKey(entry.getElement());

				if (checker == true) {

					sum += entry.getElement();

				}

			}

			if (sum > 1 || sum == 0) {

				above.add(B);

			} else {
				below.add(B);
			}
		}

		// extra message vector to check the position of that
		int k = rand.nextInt((Listmultiset.size() - 0) + 1) + 0;
		Multiset<String> testset = Listmultiset.get(k);

		if (above.contains(testset)) {

			System.out.println("Found in above");
		} else if (below.contains(testset)) {

			System.out.println("Found in below");

		} else {
			System.out.println("Not Found!");
		}
		
		
		double angluarDistance1 = SearchInSet.getAngularDistance(above,testset);	
		System.out.println( "testing above = " + angluarDistance1);
		
		double angluarDistance2 = SearchInSet.getAngularDistance(below,testset);
		System.out.println("testing below= " + angluarDistance2);
		
		
	}

	static Multiset<Integer> getHashed(Multiset<String> A) {

		Multiset<Integer> mul = HashMultiset.create();
		HashFunction hf = Hashing.murmur3_128(10);

		for (Multiset.Entry<String> entryA : A.entrySet()) {

			int index1 = hf.hashString(entryA.getElement(),
					StandardCharsets.UTF_8).asInt();

			mul.add(index1);

		}
		return mul;
	}
}