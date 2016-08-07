package allPart;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import com.google.common.base.CharMatcher;
import com.google.common.base.Splitter;
import com.google.common.base.Stopwatch;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multimap;
import com.google.common.collect.Multiset;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;

public class Part3 {

	public static void main(String[] args) throws IOException {

		File path1spam = new File("D:\\BD\\Enron\\enron1\\enron1\\spam");
		File path1ham = new File("D:\\BD\\Enron\\enron1\\enron1\\ham");

		File path2spam = new File("D:\\BD\\Enron\\enron2\\enron2\\spam");
		File path2ham = new File("D:\\BD\\Enron\\enron2\\enron2\\ham");

		File path3spam = new File("D:\\BD\\Enron\\enron3\\enron3\\spam");
		File path3ham = new File("D:\\BD\\Enron\\enron3\\enron3\\ham");

		File path4spam = new File("D:\\BD\\Enron\\enron4\\enron4\\spam");
		File path4ham = new File("D:\\BD\\Enron\\enron4\\enron4\\ham");

		File path5spam = new File("D:\\BD\\Enron\\enron5\\enron5\\spam");
		File path5ham = new File("D:\\BD\\Enron\\enron5\\enron5\\ham");

		File path6spam = new File("D:\\BD\\Enron\\enron6\\enron6\\spam");
		File path6ham = new File("D:\\BD\\Enron\\enron6\\enron6\\ham");

		List<Multiset<String>> multi1 = getVectorList(path1spam);
		List<Multiset<String>> multi2 = getVectorList(path1ham);
		List<Multiset<String>> multi3 = getVectorList(path2spam);
		List<Multiset<String>> multi4 = getVectorList(path2ham);
		List<Multiset<String>> multi5 = getVectorList(path3spam);
		List<Multiset<String>> multi6 = getVectorList(path3ham);
		List<Multiset<String>> multi7 = getVectorList(path4spam);
		List<Multiset<String>> multi8 = getVectorList(path4ham);
		List<Multiset<String>> multi9 = getVectorList(path5spam);
		List<Multiset<String>> multi10 = getVectorList(path5ham);

		List<Multiset<String>> finalDataSets = new ArrayList<Multiset<String>>();

		finalDataSets.addAll(multi1);
		finalDataSets.addAll(multi2);
		finalDataSets.addAll(multi3);
		finalDataSets.addAll(multi4);
		finalDataSets.addAll(multi5);
		finalDataSets.addAll(multi6);
		finalDataSets.addAll(multi7);
		finalDataSets.addAll(multi8);
		finalDataSets.addAll(multi9);
		finalDataSets.addAll(multi10);

		List<Multiset<String>> spamMessageVector = getVectorList(path6spam);
		List<Multiset<String>> jenuineMessageVector = getVectorList(path6ham);

		List<Multiset<String>> messageVectors = new ArrayList<Multiset<String>>();

		messageVectors.addAll(spamMessageVector);
		messageVectors.addAll(jenuineMessageVector);


		HashMap<Multiset<String>, Double> exact = new HashMap<Multiset<String>, Double>();

		ArrayList<Long> exactTime = new ArrayList<Long>();
		for (int q = 0; q < messageVectors.size(); q++) {

			Stopwatch timer2 = Stopwatch.createStarted();
			Multiset<String> A = messageVectors.get(q);

			double min_dist = SearchInSet.getAngularDistance(finalDataSets, A);
			exact.put(A, min_dist);
			exactTime.add(timer2.elapsed(TimeUnit.MILLISECONDS));

		}

		System.out.println("End of exact distance calculation");

		ArrayList<Long> time = new ArrayList<Long>();
		ArrayList<Double> error = new ArrayList<Double>();

		for (int numHyp = 1; numHyp <= 32; numHyp *= 2) {

			System.out.println("hyper plan number = " + numHyp);

			HashMap<Multiset<String>, Double> approx = new HashMap<Multiset<String>, Double>();

			Multimap<Collection<Integer>, Multiset<String>> multimapDataBase = getMultimap(
					finalDataSets, numHyp);

			System.out.println("database caluclated");
			Stopwatch timer = Stopwatch.createStarted();

			for (int r = 0; r < messageVectors.size(); r++) {

				double min_dist;

				Collection<Integer> signature = getSignature(
						messageVectors.get(r), numHyp);

				if (multimapDataBase.get(signature) != null
						&& !multimapDataBase.get(signature).isEmpty()) {

					List<Multiset<String>> match_list = new ArrayList<Multiset<String>>(
							multimapDataBase.get(signature));

					min_dist = SearchInSet.getAngularDistance(match_list,
							messageVectors.get(r));
					approx.put(messageVectors.get(r), min_dist);

				} else {

					min_dist = 90.00;
					approx.put(messageVectors.get(r), min_dist);
				}
			}

			time.add(timer.elapsed(TimeUnit.MILLISECONDS));

			double tot_diff = 0;
			for (Multiset<String> key : approx.keySet()) {

				double app = approx.get(key);

				double exa = exact.get(key);

				double diff = (app - exa); 

				tot_diff += diff;

			}

			double avg = tot_diff / approx.size();

			error.add(avg);

		}

		for (int b = 0; b < error.size(); b++) {
			System.out.println(error.get(b));

		}

		System.out.println("-----------");

		for (int b = 0; b < time.size(); b++) {
			System.out.println(time.get(b));

		}

	}

	private static Collection<Integer> getSignature(
			Multiset<String> messageVector, int numHyp) {

		Multimap<Multiset<String>, Integer> multimap = ArrayListMultimap
				.create();

		for (int num_plane = 0; num_plane < numHyp; num_plane++) {

			HashFunction hf = Hashing.murmur3_128(num_plane);
			Multiset<String> multiset2 = messageVector;
			long sum = 0;
			for (Multiset.Entry<String> entry : multiset2.entrySet()) {
				long first = hf.hashString(entry.getElement(),
						StandardCharsets.UTF_8).asLong();
				long second = hf.hashLong(first).asLong();
				sum += entry.getCount() * second;

			}

			if (sum >= 0) {

				multimap.put(multiset2, 1);

			} else if (sum < 0) {

				multimap.put(multiset2, 0);

			}

		}

		return multimap.get(messageVector);
	}



	private static List<Multiset<String>> getVectorList(File path)
			throws FileNotFoundException, IOException {

		List<Multiset<String>> some = new ArrayList<Multiset<String>>();
		File[] listOfFiles = path.listFiles();
		for (int i = 0; i < listOfFiles.length; i++) {

			StringBuffer stringBuffer = new StringBuffer();

			try (BufferedReader br = new BufferedReader(new FileReader(
					listOfFiles[i]))) {
				String line = null;
				while ((line = br.readLine()) != null) {

					stringBuffer.append(line);

				}

				String email = stringBuffer.toString().substring(8)
						.toLowerCase();
				Multiset<String> multiset = HashMultiset.create(Splitter
						.on(CharMatcher.WHITESPACE).omitEmptyStrings()
						.split(email));
				some.add(multiset);

			}

		}

		return some;
	}



	static Multimap<Collection<Integer>, Multiset<String>> getMultimap(
			List<Multiset<String>> finalDataSets, int hyperPlaneNumber) {

		Multimap<Multiset<String>, Integer> multimap = ArrayListMultimap
				.create();

		Multimap<Collection<Integer>, Multiset<String>> rev_multimap = ArrayListMultimap
				.create();

		for (int num_plane = 0; num_plane < hyperPlaneNumber; num_plane++) {

			HashFunction hf = Hashing.murmur3_128(num_plane);

			for (int p = 0; p < finalDataSets.size(); p++) {

				Multiset<String> multiset2 = finalDataSets.get(p);
				long sum = 0;
				for (Multiset.Entry<String> entry : multiset2.entrySet()) {
					long first = hf.hashString(entry.getElement(),
							StandardCharsets.UTF_8).asLong();
					long second = hf.hashLong(first).asLong();
					sum += entry.getCount() * second;

				}

				if (sum >= 0) {

					multimap.put(multiset2, 1);

				} else if (sum < 0) {

					multimap.put(multiset2, 0);

				}
			}

			for (Multiset<String> key : multimap.keySet()) {

				rev_multimap.put(multimap.get(key), key);

			}

		}
		return rev_multimap;

	}
}
