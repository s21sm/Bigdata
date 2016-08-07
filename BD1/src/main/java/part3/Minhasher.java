package part3;

import java.nio.charset.StandardCharsets;
import java.util.Set;

import com.google.common.base.Preconditions;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;

public class Minhasher {

	/**
	 * The hash function which defines the permutation
	 */
	private final HashFunction hf;

	/**
	 * Create a new minhasher. The seed determines the permutation of the
	 * universe.
	 * 
	 * @param seed
	 */
	public Minhasher(int seed) {
		this.hf = Hashing.murmur3_128(seed);
	}

	/**
	 * Perform min-hash on a set of Strings.
	 * 
	 * @param doc
	 *            A set with at least one string.
	 * @return the outcome of the min-hash.
	 */
	public long hash(Set<String> doc) {
		Preconditions.checkArgument(doc.size() > 1);
		long lowestIndex = Long.MAX_VALUE;
		for (String word : doc) {
			//in principle there is no need to take the absolute value. However, it looks a bit strange if the hash outcomes are negative numbers.
			long permutedWordIndex = Math.abs(this.hf.hashString(word, StandardCharsets.UTF_8).asLong());
			if (permutedWordIndex < lowestIndex) {
				lowestIndex = permutedWordIndex;
			}
		}
		return lowestIndex;
	}

}