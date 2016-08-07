package BIG.BD3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class part3 {

	private static final int ELEMENTS_TO_PRODUCE = 1000000;

	public static void main(String[] args) throws IOException {

		// algorithm 1 --> Statistical
		double stat_error = 0;
		for (int i = 1; i <= 10; i++) {
			List<String> list = readInputSet(i);
			int k = 14;
			Estimator obj = new Estimator(list, k);
			double error = obj.estimateUniqueValues();
			stat_error += Math.pow(error,2);

		}
	double RMSE1 =  (Math.sqrt(stat_error)) / 10;
		System.out.println("from statistical RMSE = " + (int) RMSE1);

		// alogithm 2 --> bit pattern
		double bitP_tot = 0;
		for (int i = 1; i <= 10; i++) {
			int bit = 4;
			List<String> list = readInputSet(i);
			double error_bitPattern = bitPattern.getBitPatternEstimate(list, bit);
			bitP_tot += Math.pow(error_bitPattern , 2);

		}
		double RMSE2 =  (Math.sqrt(bitP_tot)) / 10;
		System.out.println("from hyperLogLog RMSE = " + (int) RMSE2);

	}

	public static List<String> readInputSet(int seed) throws IOException {
		Process proc = Runtime.getRuntime().exec(
				"java -jar jarFolder/generator2015.jar " + ELEMENTS_TO_PRODUCE
						+ " " + seed);
		List<String> list = new ArrayList<String>(ELEMENTS_TO_PRODUCE);

		BufferedReader is = new BufferedReader(new InputStreamReader(
				proc.getInputStream()));
		String line;
		while ((line = is.readLine()) != null) {
			list.add(line);
		}
		is.close();

		return list;
	}

}
