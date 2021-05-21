import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class TitleState implements State {
//遊び方の座標
	private final int ruleX=300;
	private final int ruleY=380;
	//ゲームスタートの座標
	private final int gameX=240;
	private final int gameY=440;
	//初期のランキングの中身
	private int rank[]=new int[] {0,0,0,0};
	private State state;



	public TitleState(int[] rank2, ScoreState scoreState) {
		rank=rank2;
		

	}
//コンストラクタで前回のstateを保持
	public TitleState(State state) {
		this.state=state;
	}

	public TitleState() {
			}

	public State processTimeElapsed(int msec) {

		return this;
	}

//何も起きない
	public State processKeyTyped(String typed) {
		return this;

	}


	public State processMousePressed(int x, int y) {
		//ルールを押せばルール画面に移行する
		if(x>ruleX-10&&x<ruleX+120&&y>ruleY-40&&y<ruleY+10) 
			return new RuleState(this);
		//GAME STARTを押すとゲームスタートstateに移行する
		else if(x>gameX-10&&x<gameX+290&&y>gameY-40&&y<gameY+10)
			return new TimeCountState(this);

		return this;
	}


	//以下タイトルの描写
	public void paintComponent(Graphics g) {
		g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 40));
		g.setColor(Color.RED); 
		g.drawString("爆弾解体ゲーム", 240, 50);
		g.setColor(Color.WHITE);
		g.drawString("TOP SCORE", 270, 130);
		g.drawString("1:"+rank[3], 270, 190);
		g.drawString("2:"+rank[2], 270, 240);
		g.drawString("3:"+rank[1], 270, 290);
		g.drawString("遊び方",ruleX,ruleY);
		g.drawString("GAME START!!",gameX,gameY);

	}




}
