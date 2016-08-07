package allPart;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import com.google.common.base.CharMatcher;
import com.google.common.base.Splitter;
import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;


public class Part1 {

	static List<Multiset<String>> multi;

	public static void main(String[] args) throws IOException {

		File path = new File("repo");
		multi = getVectorList(path);

		Random rand = new Random();
		int random = rand.nextInt((multi.size() - 0) + 1) + 0;
		Multiset<String> single = multi.get(random);
		multi.remove(random);
		double angularDistance = SearchInSet.getAngularDistance(multi, single); // lowest angular distance
		 System.out.println(angularDistance);
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

}
