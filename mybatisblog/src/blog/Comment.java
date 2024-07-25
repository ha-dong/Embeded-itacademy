package blog;

public class Comment {
	private int commId;
	private String commTitile;
	private String commContent;
	private int blogId;
	
	public Comment() {
	}

	public Comment(int commId, String commTitile, String commContent, int blogId) {
		super();
		this.commId = commId;
		this.commTitile = commTitile;
		this.commContent = commContent;
		this.blogId = blogId;
	}

	public int getCommId() {
		return commId;
	}

	public void setCommId(int commId) {
		this.commId = commId;
	}

	public String getCommTitile() {
		return commTitile;
	}

	public void setCommTitile(String commTitile) {
		this.commTitile = commTitile;
	}

	public String getCommContent() {
		return commContent;
	}

	public void setCommContent(String commContent) {
		this.commContent = commContent;
	}

	public int getBlogId() {
		return blogId;
	}

	public void setBlogId(int blogId) {
		this.blogId = blogId;
	}

	@Override
	public String toString() {
		return "Comment [commId=" + commId + ", commTitile=" + commTitile + ", commContent=" + commContent + ", blogId="
				+ blogId + "]";
	}

}//class
