package server_side;

public class GraphPack {
	
	int[][] matrix;
	Point start;
	Point finish;
	
	public GraphPack(int[][] matrix, Point start, Point finish) {
		
		this.matrix = matrix;
		this.start = start;
		this.finish = finish;
	}
	
	public int[][] getMatrix() {
		return matrix;
	}
	
	public Point getStart() {
		return start;
	}
	
	public Point getFinish() {
		return finish;
	}	
}