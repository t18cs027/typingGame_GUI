

public class Player {
	//playerのx座標y座標
	private int x;
	private int y;

	//playerの移動距離
	private int dx;
	private int dy;

	//爆弾の半径（大きさ固定）
	private int R=50;

	//コンストラクタ
	public Player(int x,int y,int dx,int dy) {
		this.x =x;
		this.y = y;
		this.dx =dx;
		this.dy =dy;
	}


	//プレーヤーのx座標を返す
	public int getX() {
		return x;
	}

	//プレーヤーのｙ座標を返す
	public int getY() {
		return y;
	}

	//Playerの更新
	public void update(String typed) {
		//wが押されるｙ座標を小さくして上に更新
		if(typed.equals("w")) 
			if(y>1)//範囲を超えていなければ更新
				y-=dy;
		
		//ｓを押すと下に移動
		if(typed.equals("s"))
			if(y<300)//範囲を超えていないか確認
				y+=dy;
		
		//dを押すと左へ移動x座標を大きくする
		if(typed.equals("d"))
			if(x<750)//範囲を超えていないか
				x+=dx;
		
		//aを押すと左へ更新
		if(typed.equals("a"))
			if(x>1)
				x-=dx;
	}


	//爆弾の大きさ　半径を返す
	public int getR() {
		return R;
	}

}
