import static org.junit.Assert.*;

import org.junit.Test;

public class BossStateTest {

	@Test
	public void processMousePressed遊び方を押すとplayingStateに移動する() {
		PlayingState ps = new PlayingState(null);
		BossState bs=new BossState(ps);
		
		assertEquals(bs, bs.processMousePressed(50,50));
}
	
	
	public void processTimeElapsedで画面は遷移しない() {
		PlayingState ps = new PlayingState(null);
		BossState bs=new BossState(ps);
		assertEquals(bs, bs.processTimeElapsed(100));
		assertEquals(bs, bs.processTimeElapsed(500));
}
	
	@Test
	public void processKeyTypedでスペースキーで画面は遷移する() {
		PlayingState ps = new PlayingState(null);
		BossState bs=new BossState(ps);
		assertEquals(ps, bs.processKeyTyped(" "));
		assertEquals(bs, bs.processKeyTyped("a"));
}

}
