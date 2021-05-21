
public class Score {
	PlayingState playingState; 
	private static int resultscore;
	
//スコアのコンストラクタ
	public Score(PlayingState playingState) {
		this.playingState=playingState;
		Score.resultscore=0;//スコアの保持
	}

	public Score() {
		resultscore=0;
	}

	//スコアの取得
	public int getresultScore() {
		return resultscore;
	}


	public void setresultScore(int score) {
		Score.resultscore = score;
	}

	//スコアの加算
	public static void Plus() {
		Score.resultscore	++;

	}


}
