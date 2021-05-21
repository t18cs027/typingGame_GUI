
public class Ranking {
	//現在のランキングの中身
	private int [] rank=new int [] {0,0,0,0};
	
	public int [] getRank() {
		return rank;
	}

	//現在終わったスコアをセットする
	public void setScore(int getresultScore) {
				rank[3]=getresultScore;
		
	} 
}
