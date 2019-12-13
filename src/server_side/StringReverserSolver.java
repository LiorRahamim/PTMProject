package server_side;

public class StringReverserSolver implements Solver<String, String> {

	@Override
	public String solve(String p) {
		// TODO Auto-generated method stub
		return new StringBuilder(p).reverse().toString();
	}

}
