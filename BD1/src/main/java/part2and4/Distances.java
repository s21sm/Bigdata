package part2and4;

import java.util.Set;

public class Distances {

	public static double Jaccard(Set<String> set1, Set<String> set2) {

		Set<String> union = Utility.union(set1, set2);
		Set<String> intersection = Utility.intersection(set1, set2);

		double distance = 1 - ((double) intersection.size() / union.size());

		return distance;
	}

}
