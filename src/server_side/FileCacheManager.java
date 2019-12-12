package server_side;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class FileCacheManager<Problem extends Comparable<Problem>, Solution>
		implements CacheManager<Problem, Solution> {

	@Override
	public boolean isSolved(Problem p) {
		File file = new File("solutions.dat");
		FileInputStream in;

		try {
			in = new FileInputStream(file);
			ObjectInputStream o = new ObjectInputStream(in);

			Problem problem = (Problem) o.readObject();
			Solution solution = (Solution) o.readObject();
			while (problem != null) {
				if (problem.equals(p)) {
					o.close();
					return true;
				}
				problem = (Problem) o.readObject();
				solution = (Solution) o.readObject();
			}
			
			o.close();
			return false;

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public Solution getSolution(Problem p) {
		File file = new File("solutions.dat");
		FileInputStream in;

		try {
			in = new FileInputStream(file);
			ObjectInputStream o = new ObjectInputStream(in);

			Problem problem = (Problem) o.readObject();
			Solution solution = (Solution) o.readObject();
			while (problem != null) {
				if (problem.equals(p)) {
					o.close();
					return solution;
				}
				problem = (Problem) o.readObject();
				solution = (Solution) o.readObject();
			}
			
			o.close();
			return null;

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public void SaveSolution(Problem p, Solution s) {
		File file = new File("solutions.dat");
		FileOutputStream out;

		try {
			out = new FileOutputStream(file);
			ObjectOutputStream o = new ObjectOutputStream(out);
			o.writeObject(p);
			o.writeObject(s);
			o.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
