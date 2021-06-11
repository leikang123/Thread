package 并发容器;

public class MyJob implements Comparable<MyJob>{
	
	private int id;
	
	

	public MyJob() {
		super();
	}



	public MyJob(int id) {
		super();
		this.id = id;
	}



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	@Override
	public int compareTo(MyJob myJob) {
		
		return this.id >myJob.id?1:(this.id<myJob.id?-1:0);
	}



	@Override
	public String toString() {
		return "MyJob [id=" + id + "]";
	}

}
