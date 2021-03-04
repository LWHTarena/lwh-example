package design_pattern.visitor.scene01.common2;
/**普通员工，也就是最小的小兵**/
public class CommonEmployee extends Employee {

	//工作内容，这个非常重要，以后的侄儿规划就是靠这个了
	private String job;

	protected String getOtherInfo(){
		return "工作："+this.job +"\t";
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	@Override
	public void accept(IVisitor visitor) {
		visitor.visit(this);

	}
}
