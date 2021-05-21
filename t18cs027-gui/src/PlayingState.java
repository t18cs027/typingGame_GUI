import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
//import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.Random;

//import javax.swing.Timer; 

public class PlayingState implements State {

	private Model model;

	//タイトルstateの保持用
	private State titleState;

	//プレーヤーの初期値は固定
	private Player player=new Player(20,20,10,10);

	//前回のスコアの保持用
	private Score score=new Score(this);

	//ランキングの保持
	private Ranking rank=new Ranking();

	//爆弾に接触すると文字が出できているか確認用
	private boolean typedflag=false;

	//爆弾の出現個数の制限今回は7
	private static final int LIMITBOMB =15;

	//タイプする文字数の範囲の定義
	private static final int LIMITTYPED =5;

	//タイプされた文字の保持用
	private String typedString="";

	//入力用の文字のクラス
	private TypingWord typingword=new TypingWord();

	//爆弾と当たっているかのflag
	private int collisionBomblimit;

	//入力している文字の番号
	private int StringNumber=0;
	private int r;//爆弾にあたった時に出力する文字のためのrandom用
	Random random = new Random(System.currentTimeMillis());
	
	//爆弾を解除したときの削除する爆弾の番号
	private int removeBombNumber;

	//bombクラスのリスト
	LinkedList<bomb> bombs = new LinkedList<bomb>();


//playingStateではtitleStateの保持している
	public PlayingState(State titleState) {
		this.titleState=titleState;
	}

//時間経過によって爆弾の更新
	public State processTimeElapsed(int msec) {
		//爆弾の制限個数以下なら追加する
		while(bombs.size()<LIMITBOMB) {
			bomb B=makebomb();
			//ここで存在する爆弾とプレーヤーと重なっているか調べる
			if(OverlapBumb(B)==false)//重なっていないなら追加
				bombs.add(B);
		}

//爆弾の導火線や爆発していないかの確認
		LinkedList<bomb> bomb = this.getBombs();
		for(bomb b:bomb) {
			//爆弾の更新
			b.updata(msec);
			if( b.isExplotion()==true)//爆弾が爆発しているか確認
				return new ScoreState(score.getresultScore(),titleState);//爆破していたのでスコアに遷移
		}
		return this;
	}


	public State processKeyTyped(String typed) {
		if(typed.equals(" "))//スペースキーが押されるとボス画面へ遷移する
			return new BossState(this);

		if(isTypedflag()==false) {//爆弾に当たって解除できているかの確認
			player.update(typed);//爆弾に当たっていないので更新
			if(CollisionBomb()) {//爆弾に当たっているかの確認
				changeTypedflag();//当たっていたのでタイプフラグを変える
				r=random.nextInt(LIMITTYPED);//ランダムに文字数に適した文字列を出す
				return this;		
			}
		}

		else if(isTypedflag()==true) {//爆弾に当たっている　タイプ中を表す

		//入力された文字と一致しているかの確認
			if(typed.equals((typingword.getTypedWord2()[collisionBomblimit-1][r]).charAt(StringNumber)+"")) {
				setTyedString(typed);//正しい入力なのでセットする
				StringNumber++;//入力する文字の番号の変化
				
				//入力する文字を入力し終えたかの確認
				if(typedString.equals((typingword.getTypedWord2()[collisionBomblimit-1][r]))) {
					Score.Plus();//入力終えたので爆弾解除したのでスコアをプラス
					RemoveBomb(removeBombNumber);//爆弾の解除
					resetTyped();//タイプした文字の破棄
				}
			}
			else //入力を一度でも間違えたのでスコアへ遷移
				return new ScoreState(score.getresultScore(),titleState);
		}
		return this;
	}


	public void paintComponent(Graphics g) {

		//Playerの描画
		drawPlayer(g);

		//爆弾の描写
		LinkedList<bomb> bomb = this.getBombs();
		for(bomb b:bomb) {
			//爆弾本体の描写
			Graphics2D g2 = (Graphics2D)g;
			//爆弾の線を太くする
			BasicStroke BStroke = new BasicStroke(6.0f);
			g2.setStroke(BStroke);
			g2.setColor(Color.WHITE);
			//導火線の短い爆弾は黄色そして赤色に変化させる
			if(b.getFuse()==1)
				g2.setColor(Color.YELLOW);

			if(b.getFuse()==0)
				g2.setColor(Color.RED);

			g2.drawOval(b.getX(), b.getY(),b.getBombSize(),b.getBombSize());


			g.setFont(new Font(Font.SANS_SERIF, Font.BOLD,b.getBombSize()/2));
			g.drawString(" " +b.getLimit(),b.getX()+b.getBombSize()/4,b.getY()+b.getBombSize()/4+b.getBombSize()/4+b.getBombSize()/4);

			//導火線の描写
			g2.drawLine(b.getX()+b.getBombSize()/4+12,(b.getY()+b.getBombSize()/4+b.getBombSize()/4+b.getBombSize()/4)-38,
					b.getX()+b.getBombSize()/4+12, (((b.getY()+b.getBombSize()/4+b.getBombSize()/4+b.getBombSize()/4))-40)-b.getFuse()*5);

			//導火線の赤の描写
			g2.setColor(Color.RED);
			g2.drawLine(b.getX()+b.getBombSize()/4+12,((b.getY()+b.getBombSize()/4+b.getBombSize()/4+b.getBombSize()/4)-38)-b.getFuse()*5,
					b.getX()+b.getBombSize()/4+12,((b.getY()+b.getBombSize()/4+b.getBombSize()/4+b.getBombSize()/4)-40)-(b.getFuse()*5));



			//タイプする文字列の描画
			if(isTypedflag()) {
				g2.setColor(Color.WHITE);
				g2.drawRect(150, 300, 500, 100);
				g.setColor(Color.WHITE);
				g.setFont(new Font(Font.SANS_SERIF, Font.BOLD,20));
				g.drawString(getTypedString1(),200,350);
				g.drawString(getTypedString2(),200,370);
				g.drawString(typedString,200,390);
			}			
		}
	}

	
	//タイプした文字の保持
	private void setTyedString(String typedString2) {
		typedString+=typedString2;		
	}

	//プレーヤの描画
	private void drawPlayer(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillOval(player.getX(), player.getY(), player.getR(),player.getR() );
		g.setFont(new Font(Font.SANS_SERIF, Font.BOLD,20));	
	}

	//爆弾を作る関数
	public bomb makebomb() {
		int X = random.nextInt(600);
		int x=X+100;
		int Y = random.nextInt(200);
		int y=Y+50;
		int LIMIT = random.nextInt(60);
		int limit=LIMIT%6+1;
		int fuse=limit;


		int bombSize=50;

		return new bomb(this,x,y,fuse,limit,bombSize);
	}

	//ボムクラスのリスト
	public LinkedList<bomb> getBombs() {
		return bombs;
	}

	public Model getModel() {
		return model;
	}
	
	
	public boolean OverlapBumb(bomb B) {//爆弾を作る際に今の爆弾,プレーヤーと重ならないか判定
		LinkedList<bomb> bomb = this.getBombs();
		for(bomb b:bomb) {
			if(((B.getX()<b.getX()+50&&B.getX()>b.getX()-50)&&//既存の爆弾との重なり判定
					(B.getY()<b.getY()+50&&B.getY()>b.getY()-50))||
					((B.getX()<player.getX()+50&&B.getX()>player.getX()-50)&&//既存のプレーヤーとの重なり判定
							(B.getY()<player.getY()+50&&B.getY()>player.getY()-50)))
				return true;
		}
		return false	;
	}


	//爆弾解除中かの判定
	public boolean isTypedflag() {
		return typedflag;
	}

	//プレーヤーの取得
	public Player getPlayer() {
		return player;
	}

	//爆弾の導火線の取得
	public static int getLimitbomb() {
		return LIMITBOMB;
	}

	
	//入力する文字の取得
	public String getTypedString1() {	
		return typingword.getTypedWord1()[collisionBomblimit-1][r];
		
	}

	//入力する文字のローマ字取得
	public String getTypedString2() {
		return typingword.getTypedWord2()[collisionBomblimit-1][r];
		
	}

//解除中を示すフラグの変化
	public void changeTypedflag() {
		typedflag=true;		
	}

	
	//爆弾とプレーヤーが重なっているかの判定
	public boolean CollisionBomb() {
		int i=0;
		LinkedList<bomb> bomb = this.getBombs();
		for(bomb b:bomb) {

			//プレーヤーの位置と比較している
			if(player.getX()<b.getX()+45&&player.getX()>b.getX()-45&&
					player.getY()<b.getY()+45&&player.getY()>b.getY()-45) {
				setCollisionBombFuse(b.getLimit());
				setRemoveBombNumber(i);//解除中の爆弾の番号
				return true;
			}
			i++;
		}
		return false;
	}

	//解除する爆弾の番号の保持
	private void setRemoveBombNumber(int i) {
		removeBombNumber=i;	
	}

	//爆弾の導火線の取得
	public int getCollisionBombFuse() {
		return collisionBomblimit;
	}

	public void setCollisionBombFuse(int collisionBumbFuse) {
		this.collisionBomblimit = collisionBumbFuse;
	}

	//タイプした文字の消去
	private void resetTyped() {
		typedString="";	
		StringNumber=0;
		typedflag=false;
	}

	//ここで爆弾の消去を行う
	private void RemoveBomb(int removeBombNumber2) {
		LinkedList<bomb> bomb = this.getBombs();
		bomb.remove(removeBombNumber2);
	}


	public State processMousePressed(int x, int y) {
		return this;
	}

	public Ranking getRank() {
		return rank;
	}


}
