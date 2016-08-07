package allPart;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import com.google.common.collect.Multiset;

public class SearchInSet {

	public static double getAngularDistance(List<Multiset<String>> multi,
			Multiset<String> singleset) {

		Multiset<String> A = singleset;

		double normA = getNorm(A);

		ArrayList<Double> listOfDegree = new ArrayList<Double>();

		for (int i = 0; i < multi.size(); i++) {

			Multiset<String> B = multi.get(i);

			double normB = getNorm(B);

			int sum = 0;

			for (Multiset.Entry<String> entryA : A.entrySet()) {

				for (Multiset.Entry<String> entryB : B.entrySet()) {

					if (entryA.getElement().equals(entryB.getElement())) {

						int x = entryA.getCount() * entryB.getCount();
						sum = sum + x;

					}

				}

			}

			double res;

			if (normA != 0 || normB != 0) {

				res = sum / (normA * normB);

			} else {
				res = 0;
			}

			if (res > 1.00 && res < 1.00001) {
				res = Math.floor(res);
			}

			double ans = Math.toDegrees(Math.acos(res));

			if (ans < 0.000001) {

				ans = Math.floor(ans);

			}

			listOfDegree.add(ans);
		}

		double min_angle = Collections.min(listOfDegree);

		return min_angle;

	}

	private static double getNorm(Multiset<String> multiset) {

		int A2sum = 0;
		for (Multiset.Entry<String> entryA : multiset.entrySet()) {

			int A2 = (int) Math.pow(entryA.getCount(), 2);
			A2sum = A2sum + A2;

		}

		double norm = Math.sqrt(A2sum);

		return norm;
	}

}
