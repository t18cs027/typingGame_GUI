public class Model {

   
	private View view;
    private Controller controller;
    private State state=new TitleState();//初期はタイトルから
    private int time;//時間経過用
    private String typedChar = "";
    private int mx;
    private int my;//マウスクリック用
    
    
    

    public Model() {
        view = new View(this);
        controller = new Controller(this);
    }

    //各ステートに対してrepaintする
    public synchronized void processTimeElapsed(int msec) {
      
    state=	state.processTimeElapsed(msec);
        view.repaint();
    }

    public synchronized void processKeyTyped(String typed) {
        typedChar = typed;
        state=state.processKeyTyped(typed);
        view.repaint();
    }

    public synchronized void processMousePressed(int x, int y) {
        mx = x;
        my = y;
        state=state.processMousePressed(x,y);
        view.repaint();
    }

    public void start() {
        controller.start();
    }

    public View getView() {
        return view;
    }

    public Controller getController() {
        return controller;
    }

    public int getTime() {
        return time;
    }

    public String getTypedChar() {
        return typedChar;
    }

    public int getMX() {
        return mx;
    }

    public int getMY() {
        return my;
    }
    
  //ステートを返す
	public State getState() {return state;}
    

}
