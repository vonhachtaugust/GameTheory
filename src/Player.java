package homeproblem2;

public class Player {
	
	private int row;
	private int col;
	private double score;
	private int state;
	
	public Player(int row,int col, double score, int state) {
		this.setRow(row);
		this.setCol(col);
		this.setScore(score);
		this.setState(state);
			
	}

	public int getCol() {
		return col;
	}

	public void setCol(int col) {
		this.col = col;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}
	
	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}
	
    //@Override
    public String toString() {
        return "Player{" +
                "row=" + row +
                "," +
                "col=" + col +
                ", score=" + score +
                ", state=" + state +
                '}';
    }
	

}
