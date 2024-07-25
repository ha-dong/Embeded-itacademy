package blog;

import java.util.List;

public class Blog {
	private int blogId;
	private String blogName;
	private String blogTitle;
	private Author author;
	private List<Comment>comms;
	private int blogAuthor;
	
	public Blog() {
	}
	public Blog(Integer blogId) {
		this.blogId = blogId;
	}

	public Blog(int blogId, String blogName, String blogTitle, Author author, List<Comment> comms, int blogAuthor) {
		super();
		this.blogId = blogId;
		this.blogName = blogName;
		this.blogTitle = blogTitle;
		this.author = author;
		this.comms = comms;
		this.blogAuthor = blogAuthor;
	}

	public int getBlogId() {
		return blogId;
	}

	public void setBlogId(int blogId) {
		this.blogId = blogId;
	}

	public String getBlogName() {
		return blogName;
	}

	public void setBlogName(String blogName) {
		this.blogName = blogName;
	}

	public String getBlogTitle() {
		return blogTitle;
	}

	public void setBlogTitle(String blogTitle) {
		this.blogTitle = blogTitle;
	}

	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}

	public List<Comment> getComms() {
		return comms;
	}

	public void setComms(List<Comment> comms) {
		this.comms = comms;
	}

	public int getBlogAuthor() {
		return blogAuthor;
	}

	public void setBlogAuthor(int blogAuthor) {
		this.blogAuthor = blogAuthor;
	}

	@Override
	public String toString() {
		return "Blog [blogId=" + blogId + ", blogName=" + blogName + ", blogTitle=" + blogTitle + ", author=" + author
				+ ", comms=" + comms + ", blogAuthor=" + blogAuthor + "]";
	}
	
	
}//class
