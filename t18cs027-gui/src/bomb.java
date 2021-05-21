public class bomb {
	
	private int x;//爆弾の初期位置x,y
	private int y;
	
	private int fuse;//導火線の長さ
	
	private int limit;//爆弾の文字数
	
	private int bombSize;//爆弾の大きさ
	private int time;//導火線の爆発まで
	private boolean Explotion;//爆弾が爆発したかの判定用
	

	//初期値は0
	public bomb(PlayingState Pstate){
		this(Pstate,0,0,0,0,0);
	}
	
//入力ありのコンストラクタ
	public bomb(PlayingState Pstate,int X,int Y,int Fuse,int Limit,int BombSize ) {// モデルを保持
    
        this.x=X;
        this.y=Y;
        this.fuse=Fuse;
        this.limit=Limit;
        this.bombSize=BombSize;
     this.Explotion=false;//最初はfalse   
	}


	public int getX() {
		return x;
	}


	public int getY() {
		return y;
	}

	public int getFuse() {
		return fuse;
	}


	public int getLimit() {
		return limit;
	}


	public int getBombSize() {
		return bombSize;
	}

//爆弾の導火線の更新
	public void updata(int msec) {
		
		time++;//時間経過
		if(time%100==0) {
			if(fuse==0)//導火線が0になるとchangeで爆発したことを示す
				change();
			if(fuse>0) {//時間経過で導火線は短くなっていく
			fuse--;
			}
		}
		
	}

	//fuse導火線が0になると呼ばれtrueとなる
public void change() {
	Explotion=true;
}
	//爆発したかの確認用
public boolean isExplotion() {
	return Explotion;
}

	
}
