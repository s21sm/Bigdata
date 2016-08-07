//package part1;
//
//import java.io.BufferedReader;
//import java.io.FileNotFoundException;
//import java.io.FileReader;
//import java.io.IOException;
//import java.text.Normalizer;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Set;
//
//public class CopyOfDataset {
//
//	@SuppressWarnings({ "rawtypes", "unchecked" })
//	public static void main(String[] args) throws IOException,
//			ClassNotFoundException, InstantiationException,
//			IllegalAccessException {
//
//		final String csvFile = "D:\\BD\\dataset.csv";
//		final String txtFile = "D:\\BD\\words.txt";
//
//		List<Set> wikisets = readcsv(csvFile);
//
//		System.out.println("size check" + wikisets.size());
//
//		Set<String> wordList = readtxt(txtFile);
//
//		for (Set singleSet : wikisets) {
//			singleSet.removeAll(wordList);
//		}
//
//		StemmerInstanceENG obj = new StemmerInstanceENG();
//		List<Set> stemmedSet = obj.stemDocuments(wikisets);
//
//		FileManager f = new FileManager();
//		f.writeContent(stemmedSet, Config.stemmedFilepath);
//	}
//
//	@SuppressWarnings("rawtypes")
//	private static List<Set> readcsv(String csvFile) throws IOException {
//
//		BufferedReader br = null;
//		List<Set> wikisets = new ArrayList<Set>();
//		int lineNumber = 0;
//
//		try {
//
//			br = new BufferedReader(new FileReader(csvFile));
//
//			while (br.readLine() != null) {
//
//				String word = br.readLine();
//
//				System.out.println(lineNumber);
//
//				String s1 = word.substring(word.indexOf(",") + 3);
//				
//				System.out.println(s1);
//				String s2 = s1.replaceAll("[^a-zA-Z ]", "").toLowerCase();
//				
//				System.out.println(s2);
//				
//
//				String s3 = s2.replaceAll("\\s+", " ");
//				System.out.println(s3);
//				System.out.println("----------");
//
//				Set<String> items = new HashSet<String>(Arrays.asList(s3
//						.split(" ")));
//				wikisets.add(items);
//
//				if (lineNumber == 29)
//					break;
//
//				lineNumber++;
//			}
//
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		} finally {
//
//			br.close();
//
//		}
//
//		return wikisets;
//
//	}
//
//	private static Set<String> readtxt(String txtFile) throws IOException {
//
//		BufferedReader br = null;
//		String line = "";
//
//		Set<String> set2 = new HashSet<String>();
//
//		try {
//
//			br = new BufferedReader(new FileReader(txtFile));
//
//			int lines = 0;
//
//			while ((line = br.readLine()) != null) {
//
//				String z = line.replaceAll("\\s+", "");
//				String alphabetPart = z.replaceAll("\\d+", "");
//				String y = Normalizer.normalize(z, Normalizer.Form.NFD);
//				String x = y.replaceAll("\\W", "");
//				int numericPart = Integer
//						.valueOf(x.replaceAll("[a-zA-Z]+", ""));
//
//				if (lines >= 2908064) {
//
//					set2.add(alphabetPart);
//
//				}
//
//				if (numericPart <= 5) {
//
//					set2.add(alphabetPart);
//				}
//
//				lines++;
//
//			}
//			// System.out.println(lines);
//
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		} finally {
//
//			br.close();
//
//		}
//
//		return set2;
//
//	}
//
//}
