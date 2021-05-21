import static org.junit.Assert.*;

import org.junit.Test;

public class TitleStateTest {

	@Test
	public void processKeyTypedで画面は遷移しない() {
		TitleState ts = new TitleState();
		assertEquals(ts, ts.processKeyTyped(" "));
		assertEquals(ts, ts.processKeyTyped("a"));
}
	
	@Test
	public void processMousePressed遊び方を押すとruleStateに移動する() {
		TitleState ts = new TitleState();
		
		assertEquals(ts, ts.processMousePressed(50,50));
}
	public void processTimeElapsedで画面は遷移しない() {
		TitleState ts = new TitleState();
		assertEquals(ts, ts.processTimeElapsed(100));
		assertEquals(ts, ts.processTimeElapsed(500));
}
	
	
	
}
