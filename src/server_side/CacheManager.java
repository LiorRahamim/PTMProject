package server_side;

public interface CacheManager <Problem, Solution> {
	boolean isSolved(Problem p);
	Solution getSolution(Problem p);
	void SaveSolution(Problem p, Solution s);
}
