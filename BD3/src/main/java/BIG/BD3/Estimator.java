package BIG.BD3;

import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;

public class Estimator {

	private List<String> data;
	private int k ;
	Set<String> actuallyUniqueValues = null;

	public Estimator(List<String> data, int k) {
		this.data = data;
		this.k = k;
		actuallyUniqueValues = new HashSet<String>(data);

	}

	HashFunction hf = Hashing.murmur3_128(10);


	public double estimateUniqueValues() {

		int maxKmv = Math.abs(findKMinimum());

		 double d =  (k - 1) / (maxKmv /((double)Math.pow(2,31)-1));
		 		 		 		 
		 return ( Math.abs(actuallyUniqueValues.size()-d));

	}

	int findKMinimum() {
		Set<Integer> kmv = getInitialKMV();
		for (String str : data) {

			int hash = Math.abs(hf.hashString(str, StandardCharsets.UTF_8)
					.asInt());

			int maxKmv = Collections.max(kmv);
			if (hash < maxKmv && !kmv.contains(hash)) {
				kmv.add(hash);
				kmv.remove(maxKmv);
			}

		}
		return Collections.max(kmv);
	}

	private Set<Integer> getInitialKMV() {
		int i = 0;
		Set<Integer> kmv = new HashSet<Integer>();
		for (String element : data) {
			kmv.add(Math.abs(hf.hashString(element, StandardCharsets.UTF_8)
					.asInt())); 
			if (++i >= k) {
				break;
			}
		}
		return kmv;
	}
}
