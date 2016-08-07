package part1;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.google.common.base.CharMatcher;
import com.google.common.base.Splitter;
import com.google.common.collect.Sets;

public class DatasetMain {

	public static void main(String[] args) throws IOException,
			ClassNotFoundException, InstantiationException,
			IllegalAccessException {

		final String csvFile = "D:\\BD\\dataset.csv"; //location of csv file in the disc
		final String txtFile = "D:\\BD\\words.txt";   //location of text file in the disc

		List<Set<String>> wikisets = readcsv(csvFile);

		Set<String> wordList = readtxt(txtFile);

		for (Set<String> singleSet : wikisets) {
			singleSet.removeAll(wordList);
		}

		StemmerInstanceENG obj = new StemmerInstanceENG();
		List<Set<String>> stemmedSet = obj.stemDocuments(wikisets);

		FileManager f = new FileManager();
		f.writeContent(stemmedSet, Config.stemmedFilepath);
	}

	private static final Splitter ws = Splitter.on(
			CharMatcher.JAVA_LETTER.negate()).omitEmptyStrings();

	private static List<Set<String>> readcsv(String csvFile) throws IOException {

		List<Set<String>> wikisets = new ArrayList<>();
		int lineNumber = 0;

		try (BufferedReader br = new BufferedReader(new InputStreamReader(
				new FileInputStream(csvFile), StandardCharsets.UTF_8))) {
			String line;
			while ((line = br.readLine()) != null) {

				String s1 = line.substring(line.indexOf(",") + 3);

				String lc = s1.toLowerCase();

				Iterable<String> words = ws.split(lc);

				Set<String> items = Sets.newHashSet(words);
				wikisets.add(items);

				if (lineNumber == 2000)
					break;

				lineNumber++;
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return wikisets;

	}

	private static Set<String> readtxt(String txtFile) throws IOException {

		BufferedReader br = null;
		String line = "";

		Set<String> set2 = new HashSet<String>();

		try {

			br = new BufferedReader(new FileReader(txtFile));

			int lines = 0;

			while ((line = br.readLine()) != null) {

				String z = line.replaceAll("\\s+", "");
				String alphabetPart = z.replaceAll("\\d+", "");
				String y = Normalizer.normalize(z, Normalizer.Form.NFD);
				String x = y.replaceAll("\\W", "");
				int numericPart = Integer
						.valueOf(x.replaceAll("[a-zA-Z]+", ""));

				if (lines >= 2908064) {

					set2.add(alphabetPart);

				}

				if (numericPart <= 5) {

					set2.add(alphabetPart);
				}

				lines++;

			}
	

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {

			br.close();

		}

		return set2;

	}

}
