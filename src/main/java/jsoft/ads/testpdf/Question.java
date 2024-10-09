package jsoft.ads.testpdf;

public class Question {
	private String name;
	private boolean yesno;
	public String getName() {
		return name;
	}
	public boolean isYesno() {
		return yesno;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setYesno(boolean yesno) {
		this.yesno = yesno;
	}
	public Question(String name, boolean yesno) {
		this.name = name;
		this.yesno = yesno;
	}
	
	
}
