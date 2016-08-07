package BIG.BD3;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;

public class bitPattern {

	static double getBitPatternEstimate(List<String> list, int b) {

		Set<String> actuallyUniqueValues = new HashSet<String>(list);
		// Map<Double, Integer> map = new HashMap<Double, Integer>();
		Map<String, Integer> map = new HashMap<String, Integer>();
		HashFunction hf = Hashing.murmur3_128(10);

		double alpha;
		int m = (int) Math.pow(2, b);

		if (m == 16)
			alpha = 0.673;
		else if (m == 32)
			alpha = 0.697;
		else if (m == 64)
			alpha = 0.709;
		else
			alpha = 0.7213 / (1 + 1.079 / m);

		for (int i = 0; i < list.size(); i++) {

			long hValue = hf.hashString(list.get(i), StandardCharsets.UTF_8)
					.asLong();
			String word = Long.toBinaryString(hValue);

			String k = word.substring(word.length() - b);

			// double index = Math.log(Integer.parseInt(k));
			String remaining_bits = word.substring(0, word.length() - b);

			int a = remaining_bits.lastIndexOf("0");

			String x = remaining_bits.substring(0,
					remaining_bits.lastIndexOf("0") + 1);

			int a1 = x.lastIndexOf("1");

			int num_right_zeros = a - a1;

			if (map.get(k) != null && (map.get(k) < num_right_zeros)) {

				map.put(k, num_right_zeros);

			} else if (map.get(k) == null) {

				map.put(k, num_right_zeros);
			}

		}

		double add = 0;
		for (String key : map.keySet()) {
			add += Math.pow(2, -map.get(key));

		}

		double hyperloglog = map.size() * map.size() * alpha * (1 / add);

		return (Math.abs((Math.floor(hyperloglog) - Math
				.abs(actuallyUniqueValues.size()))));
	}

}
