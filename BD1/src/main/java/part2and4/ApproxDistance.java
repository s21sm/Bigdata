package part2and4;

import java.util.Random;
import java.util.Set;
import part3.Minhasher;

public class ApproxDistance {

	public ApproxDistance() {

	}

	public static float approxDist(Set<String> Set1, Set<String> Set2, int N) {

		float approxJaccard = 0;

		int matchCounter = 0;

		Random rand = new Random();

		for (int i = 1; i <= N; i++) {

			int randSeed = rand.nextInt((10000 - 100) + 1) + 100;

			Minhasher obj = new Minhasher(randSeed);

			long lowestIndex1 = obj.hash(Set1);

			long lowestIndex2 = obj.hash(Set2);

			if (lowestIndex1 == lowestIndex2) {

				matchCounter = matchCounter + 1;

			}

			approxJaccard = 1 - (matchCounter / (float) N);

		}

		return approxJaccard;

	}

}
