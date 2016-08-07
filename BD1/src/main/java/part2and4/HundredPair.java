package part2and4;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class HundredPair {

	public List<Set<String>> setList1 = new ArrayList<>();
	public List<Set<String>> setList2 = new ArrayList<>();

	public void readStemmed(String stemmedFilepath) throws IOException {

		BufferedReader br = null;

		
		try {

			br = new BufferedReader(new FileReader(stemmedFilepath));

			int lineNumber = 0;

	
			int setLimit = 999;


			String line;
			while ((line = br.readLine()) != null) {

				if (lineNumber < setLimit) {

					Set<String> set1 = new HashSet<String>(Arrays.asList(line
							.split(" ")));

					setList1.add(set1);
				}

				if (lineNumber > setLimit) {

					Set<String> set2 = new HashSet<String>(Arrays.asList(line
							.split(" ")));

					setList2.add(set2);
				}

				lineNumber++;

				if (lineNumber >= 2000) {

					break;
				}

			}
			// }

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {

			br.close();

		}

		

		
		
	}

}
