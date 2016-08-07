package part2and4;

import java.io.IOException;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class ReadStemmedDocMain {

	public static void main(String[] args) throws IOException,
			ClassNotFoundException, InstantiationException,
			IllegalAccessException {

		float[][] resultArray = new float[100][16];
		final String stemmedFilepath = "stemmed_Document.txt"; // location of stemmed Document

		HundredPair obj = new HundredPair();
		obj.readStemmed(stemmedFilepath);
		List<Set<String>> SETList1 = obj.setList1;
		List<Set<String>> SETList2 = obj.setList2;

		Random rn = new Random();
		
		for (int k = 0; k < 100; k++) {

			
			int rand = rn.nextInt((990 - 0) + 1) + 0;

			Set<String> set1 = SETList1.get(rand);
			Set<String> set2 = SETList2.get(rand);

			double Jaccard_distance = Distances.Jaccard(set1, set2);

			int columnNumber = 0;

			for (int i = 5; i <= 80; i += 5) {

				float approxDist = ApproxDistance.approxDist(set1, set2, i);

				float a = Math.abs((float) (Jaccard_distance - approxDist));

				resultArray[k][columnNumber] = a;

				columnNumber = columnNumber + 1;

			}

		}

		for (int column = 0; column < 16; column++) {

			float sum = 0;

			for (int row = 0; row < 100; row++) {

				sum += (float) resultArray[row][column];

			}

			float average = sum / 100;

	
			System.out.println(average);
		}

	}
}
