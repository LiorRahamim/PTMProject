package server_side;

import java.util.Hashtable;

public class HashCacheManager<Problem extends Comparable<Problem>, Solution>
			implements CacheManager<Problem, Solution> {
	
	private Hashtable<Problem, Solution> cache;
	
	public HashCacheManager() {
		this.cache = new Hashtable<Problem, Solution>();
	}
	
	public boolean isSolved(Problem p) {
		if (this.cache.containsKey(p)) {
			return true;
		}
		
		return false;
	}
	
	
	public Solution getSolution(Problem p) {
		return this.cache.get(p);
	}
	
	
	public void SaveSolution(Problem p, Solution s) {
		this.cache.put(p, s);
	}
}
