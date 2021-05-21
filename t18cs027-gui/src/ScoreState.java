import java.awt.Color;
import java.util.Arrays;
import java.awt.Font;
import java.awt.Graphics;

public class ScoreState implements State {

	private int score;
	private Ranking rank=new Ranking();
	private State titleState;

	public ScoreState(int getresultScore, State titleState) {
		score=getresultScore;
		rank.setScore(getresultScore);
		this.titleState=titleState;

	}



	public State processTimeElapsed(int msec) {
		return this;
	}

	@Override
	public State processKeyTyped(String typed) {
		if(typed.equals(" ")) {
			Arrays.sort(rank.getRank());
			
			return new TitleState(rank.getRank(),this);
		}
		return this;
	}

	public State processMousePressed(int x, int y) {
		return this;
	}


	public void paintComponent(Graphics g) {
		g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 50));
		g.setColor(Color.WHITE); 
		g.drawString("SCORE: "+" "+getScore(), 200, 100);
		g.drawString("Press Space Key", 200, 350);
	}


	public int getScore() {
		return score;
	}

}
