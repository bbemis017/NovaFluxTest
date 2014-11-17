package runtime;

public class Time extends Thread{
	
	private int wait;
	
	public Time(int wait){
		this.wait = wait;
	}
	
	@Override
	public void run() {
		super.run();
		try {
			Thread.sleep(wait);
		} catch(InterruptedException e){
			e.printStackTrace();
		}
	}

}
