import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;


//メニュー画面のstate
public class RuleState implements State {
private State state;
	
//コンストラクタタイトルステイとの保持
	public RuleState(TitleState titleState) {
		this.state=titleState;
			}


	public State processTimeElapsed(int msec) {
		return this;
	}

	
	//何でもキーを押せばタイトルに戻る
	public State processKeyTyped(String typed) {
		return state;
	}

	
	public State processMousePressed(int x, int y) {
		return this;
	}

	
	//ルール説明の描画
	public void paintComponent(Graphics g) {
		g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 40));
		g.setColor(Color.WHITE); 
		g.drawString("ルール説明", 280, 80);
		g.drawString("Press any key!!",240,450);
		g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
		/*以下ルールの描写*/
		g.drawString("・タイピングにより爆弾を解体してください", 30, 160);
		g.drawString("・プレーヤーは w[上]　a[左]　s[下]　d[右] で移動できます", 30, 200);
		g.drawString("・プレーヤーを爆弾の元に移動させて単語を入力することで爆弾が解除されます", 30, 240);
		g.drawString("・爆弾内の数字は入力する単語の文字数を表しています", 30, 280);
		g.drawString("・導火線の短いものから爆発していきます", 30, 320);
		g.drawString("・入力を一度でも間違えるか,導火線が無くなり爆発するとゲームオーバーです", 30, 360);

	}

}
