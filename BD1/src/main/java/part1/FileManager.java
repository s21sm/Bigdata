package part1;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.*;

import java.util.List;
import java.util.Set;

public class FileManager {

	public FileManager() {

	}

	protected void writeContent(List<Set<String>> content, String writePath) {
		try {
			BufferedWriter writer = Files.newBufferedWriter(Paths
					.get(writePath));

			System.out.println("writing");

			for (Set<String> xyz : content) {
				for (String s : xyz) {
					writer.write(s + " ");
				}
				writer.newLine();
			}

			writer.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
