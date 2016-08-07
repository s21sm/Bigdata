package allPart;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import com.google.common.base.CharMatcher;
import com.google.common.base.Splitter;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multimap;
import com.google.common.collect.Multiset;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;

public class part4 {

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

		List<Multiset<String>> allSpam = new ArrayList<Multiset<String>>();
		List<Multiset<String>> allHam = new ArrayList<Multiset<String>>();

		allSpam.addAll(multi1);
		allSpam.addAll(multi3);
		allSpam.addAll(multi5);
		allSpam.addAll(multi7);
		allSpam.addAll(multi9);

		allHam.addAll(multi2);
		allHam.addAll(multi4);
		allHam.addAll(multi6);
		allHam.addAll(multi8);
		allHam.addAll(multi10);

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

		Multimap<Multiset<String>, Multiset<String>> collection = ArrayListMultimap
				.create();

		for (int ind = 1; ind <= 4; ind++) {

			int numHyp = 16;

//			System.out.println("index number " + ind);

			Multimap<Collection<Integer>, Multiset<String>> multimapDataBase = getMultimap(
					finalDataSets, numHyp, ind);

			for (Multiset<String> message_set : messageVectors) {
				Collection<Integer> signature = getSignature(message_set,
						numHyp, ind);

				if (multimapDataBase.get(signature) != null
						&& !multimapDataBase.get(signature).isEmpty()) {

					List<Multiset<String>> match_list = new ArrayList<Multiset<String>>(
							multimapDataBase.get(signature));

					for (Multiset<String> set : match_list) {

						if (collection.get(message_set) != null && !collection.get(message_set).isEmpty()
								&& !collection.get(message_set).contains(set)) {
							collection.put(message_set, set);

						} else if(collection.get(message_set) == null || collection.get(message_set).isEmpty() ) {

							collection.put(message_set, set);
						}

					}

				}

			}
		}

		// Majority Approach

		int real_spam = 0;
		int real_jenuine = 0;
		int spam_spam = 0;
		int spam_jenuine = 0;
		int jenuine_jenuine = 0;
		int jenuine_spam = 0;
		int not_found = 0;

		for (int i = 0; i < messageVectors.size(); i++) {

			int s = 0;
			int j = 0;

			int found_jenuine = 0;
			int found_spam = 0;

			if (spamMessageVector.contains(messageVectors.get(i))) {

				s = 1;
				real_spam += 1;

			} else if (jenuineMessageVector.contains(messageVectors.get(i))) {

				j = 1;
				real_jenuine += 1;

			}

			for (Multiset<String> key : collection.keySet()) {

				if (messageVectors.get(i).equals(key)) {

					Collection<Multiset<String>> matchedSets = collection
							.get(key);

					for (Multiset<String> set : matchedSets) {

						if (allHam.contains(set)) {

							found_jenuine = found_jenuine + 1;

						} else if (allSpam.contains(set)) {

							found_spam = found_spam + 1;
						}
					}

				}

			}

			int j_1 = 0;
			int s_1 = 0;

			if (found_jenuine > found_spam) {

				j_1 = 1;

			} else if (found_jenuine < found_spam) {

				s_1 = 1;

			}

			if (found_jenuine == 0 && found_spam == 0) {

				not_found = not_found + 1;
			}

			if ((s * s_1) == 1) {

				spam_spam = spam_spam + 1;

			} else if ((s * j_1) == 1) {

				spam_jenuine = spam_jenuine + 1;

			} else if ((j * j_1) == 1) {

				jenuine_jenuine = jenuine_jenuine + 1;

			} else if ((j * s_1) == 1) {

				jenuine_spam = jenuine_spam + 1;
			}

		}
		
		int not_found_spam = real_spam-spam_spam-spam_jenuine;
		int not_found_ham = real_jenuine-jenuine_jenuine-jenuine_spam;
		
		// if a spam query email's potential candidate are empty I consider that as spam. 
		
		System.out.println("spam as spam = " + (spam_spam+not_found_spam) / (double) real_spam);

		System.out.println("spam as jenuine = " + spam_jenuine
				/ (double) real_spam);

		// if a ham query email's potential candidate are empty I consider that as ham.
		
		System.out.println("jenuine  as jenuine = " + (jenuine_jenuine+not_found_ham)
				/ (double) real_jenuine);

		System.out.println("jenuine as spam = " + jenuine_spam
				/ (double) real_jenuine);


	}

	private static Collection<Integer> getSignature(
			Multiset<String> messageVector, int numHyp, int ind) {

		Multimap<Multiset<String>, Integer> multimap = ArrayListMultimap
				.create();

		for (int num_plane = 0; num_plane < numHyp; num_plane++) {

			HashFunction hf = Hashing.murmur3_128(num_plane + ind);
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

	// private static double getAngularDistance(Multiset<String> a,
	// Multiset<String> key) {
	//
	// ImmutableMultiset<String> A = ImmutableMultiset.copyOf(a);
	// ImmutableMultiset<String> B = ImmutableMultiset.copyOf(key);
	// double normA = getNorm(A);
	// double normB = getNorm(B);
	//
	// int sum = 0;
	// for (Multiset.Entry<String> entryA : A.entrySet()) {
	//
	// for (Multiset.Entry<String> entryB : B.entrySet()) {
	//
	// if (entryA.getElement().equals(entryB.getElement())) {
	//
	// int x = entryA.getCount() * entryB.getCount();
	// sum = sum + x;
	//
	// }
	//
	// }
	// }
	//
	// double res;
	//
	// if (normB != 0 || normB != 0) {
	//
	// res = sum / (normA * normB);
	//
	// } else {
	// res = 0;
	// }
	//
	// if (res > 1.00 && res < 1.00001) {
	// res = Math.floor(res);
	// }
	//
	// double ans = Math.toDegrees(Math.acos(res));
	//
	// if (ans < 0.000001) {
	//
	// ans = Math.floor(ans);
	//
	// }
	//
	// return ans;
	// }

	// private static double getNorm(Multiset<String> multiset) {
	//
	// int A2sum = 0;
	// for (Multiset.Entry<String> entryA : multiset.entrySet()) {
	//
	// int A2 = (int) Math.pow(entryA.getCount(), 2);
	// A2sum = A2sum + A2;
	//
	// }
	//
	// double norm = Math.sqrt(A2sum);
	//
	// return norm;
	// }

	static Multimap<Collection<Integer>, Multiset<String>> getMultimap(
			List<Multiset<String>> finalDataSets, int hyperPlaneNumber, int ind) {

		Multimap<Multiset<String>, Integer> multimap = ArrayListMultimap
				.create();

		Multimap<Collection<Integer>, Multiset<String>> rev_multimap = ArrayListMultimap
				.create();

		for (int num_plane = 0; num_plane < hyperPlaneNumber; num_plane++) {

			HashFunction hf = Hashing.murmur3_128(num_plane + ind);

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
