
public class TypingWord {

	public TypingWord() {
		super();
	}

	//タイプする日本語の配列
	private String TypedWord1[][]= {{"あ","い","う","え","お"},
			{"星","花","貝","塩","富"},
			{"バナナ","プール","神父","時計","水面"},
			{"おにぎり","リモコン","イベント","大阪","大学"},
			{"こんにちは","海開き","オムライス","カブトムシ","映画館"},
			{"色鉛筆","美味しい水","オーケストラ","お正月","お化け屋敷"}
	};

//	タイプするローマ字
	private String TypedWord2[][]={{"a","i","u","e","o"},
			{"hoshi","hana","kai","shio","tomi"},
			{"banana","pu-ru","shinnpu","tokei","minamo"},
			{"onigiri","rimokonn","ibennto","oosaka","daigaku"},
			{"konnnitiha","umibiraki","omuraisu","kabutomushi","eigakann"},
			{"iroennpitsu","oishiimizu","o-kesutora","osyougatsu","obakeyahiki"}
	};

	//タイプする文字の取得
	public String[][] getTypedWord1() {
		return TypedWord1;
	}
	public String[][] getTypedWord2() {
		return TypedWord2;
	}



}
