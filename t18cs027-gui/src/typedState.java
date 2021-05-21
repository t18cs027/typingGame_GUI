import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class typedState implements State {

	private State Restate;
	private View view; 

	public typedState(State state) {
		Restate=state;
	}


	public State processTimeElapsed(int msec) {
		return this;
	}


	public State processKeyTyped(String typed) {
		return this;
	}


	public State processMousePressed(int x, int y) {
		return this;
	}

	@Override
	public void paintComponent(Graphics g) {
		view.clear(g);
		Restate.paintComponent(g);
		g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
		g.setColor(Color.WHITE); 
		g.drawString("TYPED",200,300);

	}

}
