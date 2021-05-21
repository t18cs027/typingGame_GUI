import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class BossState implements State {
private State state;
private String s=" ";
	
//コンストラクタ、前回のstateを保持する
	public BossState(State state) {
	this.state = state;
}

	
	public State processTimeElapsed(int msec) {
				return this;
	}

	
	public State processKeyTyped(String typed) {
		//スペースキーが押されると前の画面へ遷移する
				if(typed.equals(s)) {
		return getState();
				}
				else
		return this;
	}

	
	public State processMousePressed(int x, int y) {
				return this;
	}

	
	public void paintComponent(Graphics g) {
				g.setColor(Color.WHITE);
		g.setFont(new Font(Font.SANS_SERIF, Font.BOLD,20));
		//ボスが来たと出るだけ
		g.drawString("Boss is coming!!", 300,300);
		g.drawString("Please wait...", 300,340);
	}
//stateを返すだけ
	public State getState() {
		return state;
	}

}
