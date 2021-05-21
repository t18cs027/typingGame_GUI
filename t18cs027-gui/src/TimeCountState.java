import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;



public class TimeCountState implements State {
	int time=2900; 

	private State titleState;
	public TimeCountState(TitleState titleState) {
		this.titleState=titleState;
	}


	public State processTimeElapsed(int msec) {
		time-=msec;
		if(time==-1000)
			return new PlayingState(titleState);

		return this;
	}


	public State processKeyTyped(String typed) {
		return this;
	}


	public State processMousePressed(int x, int y) {
		return this;
	}


	public void paintComponent(Graphics g) {
		g.setColor(Color.WHITE);
		g.setFont(new Font(Font.SANS_SERIF, Font.BOLD,50));
		g.drawString(""+(time/1000+1), 400, 250);
	}





}
