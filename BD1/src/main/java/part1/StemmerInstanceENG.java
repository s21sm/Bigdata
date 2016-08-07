package part1;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.tartarus.snowball.ext.englishStemmer;

public class StemmerInstanceENG {

private englishStemmer stemmer;
	
	public StemmerInstanceENG() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		@SuppressWarnings("rawtypes")
		Class stemClass = Class.forName("org.tartarus.snowball.ext.englishStemmer");
		stemmer = (englishStemmer) stemClass.newInstance();
	}
	public String stemWord(String w){
		  stemmer.setCurrent(w);
		  stemmer.stem();
		  String stemmed = stemmer.getCurrent();  
		  return stemmed;
	}
	public Set<String> stemWords(Set<String> words){
		Set<String> ws = new HashSet<String>();
		for(String w : words){
			ws.add(stemWord(w));
		}
		return ws;
	}

	public List<Set<String>> stemDocuments(List<Set<String>> documents){
		List<Set<String>> stemmedSetList= new ArrayList<Set<String>>();
		for(Set<String> singleSet : documents){
			stemmedSetList.add(stemWords(singleSet));
		}
		return stemmedSetList;
	}
}
