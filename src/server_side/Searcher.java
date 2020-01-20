package server_side;

public interface Searcher<T> {
	
	public SearchSolution<T> search(Searchable<T> s);
	
	// for comparisons between algorithms
	public int getNumberOfNodesEvaluated();
}
