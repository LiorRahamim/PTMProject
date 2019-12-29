package server_side;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// This class is about handling the actual problem in this project - finding shortest path
public class MyClientHandler implements ClientHandler {

	// returns the matrix the client sends
	private int[][] readMatrix(BufferedReader in) {
		String line;
		ArrayList<ArrayList<Integer>> matrix = new ArrayList<ArrayList<Integer>>();
		int[][] primitiveMatrix;
		try {
			line = in.readLine();

			while (!line.equals("end")) {
				List<String> stringLine = new ArrayList<String>();
				ArrayList<Integer> integerLine = new ArrayList<Integer>();
				stringLine = Arrays.asList(line.split(","));
				for (String str : stringLine)
					integerLine.add(Integer.valueOf(str));
				matrix.add(integerLine);
				line = in.readLine();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO
			e.printStackTrace();
		}

		primitiveMatrix = new int[matrix.size()][matrix.get(0).size()];

		for (int i = 0; i < matrix.size(); i++) {
			for(int j = 0; j < matrix.get(i).size(); j++) {
				primitiveMatrix[i][j] = matrix.get(i).get(j);
			}
		}

		return primitiveMatrix;
	}

	// returns the point the client sends
	private Point readPoint(BufferedReader in) {
		String line;
		Point point = null;

		try {
			List<String> stringLine = new ArrayList<String>();
			
			line = in.readLine();
			stringLine = Arrays.asList(line.split(","));
			point = new Point(Integer.parseInt(stringLine.get(0)), Integer.parseInt(stringLine.get(1)));
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return point;
	}
	
	@Override
	public void handleClient(InputStream inputStream, OutputStream outputStream) {
		BufferedReader in = new BufferedReader(new InputStreamReader(inputStream));
		PrintWriter out = new PrintWriter(outputStream);
		Solver<String, String> solver = new StringReverserSolver();
		CacheManager<String, String> cacheManager = new HashCacheManager<>();

		String line;
		String solution;
		int[][] matrix;
		Point start;
		Point finish;
		
		try {
			matrix = readMatrix(in);
			start = readPoint(in);
			finish = readPoint(in);
			
			line = "something";
			//line = in.readLine();
			while (!line.equals("end")) {

				if (cacheManager.isSolved(line)) {
					solution = cacheManager.getSolution(line);
				} else {
					solution = solver.solve(line);
					cacheManager.SaveSolution(line, solution);
				}

				out.println(solution);
				out.flush();

				line = "end";
				// line = in.readLine();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
