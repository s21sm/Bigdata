package BIG.BD3;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import com.google.common.base.Stopwatch;

public class part1 {

	public static void main(String[] args) throws IOException {

		FileWriter f = new FileWriter("output.txt");
		String newLine = System.getProperty("line.separator");

		Set<Long> set = new HashSet<Long>();


		long j = 0;
		while (true) {
			Stopwatch timer = Stopwatch.createUnstarted();
			timer.start();
			for (long i = 0; i < 1000000; i++) {
				try {
					set.add(j++);
				} catch (OutOfMemoryError e) {
					System.out.println(e);
					System.out.println(set.size());

				}

			}
			long t = timer.elapsed(TimeUnit.MILLISECONDS);
			f.write(t + newLine);
			f.flush();
		}

	}

}
