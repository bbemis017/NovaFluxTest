package runtime;

public class Time implements Runnable{
	
	private int wait;
	
	public Time(int wait){
		this.wait = wait;
	}

	@Override
	public void run() {
			try {
				Thread.sleep(wait);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		
	}
	
	

}
