package blog;

public class CommentSearcher {
	
	private int blogId;
	private String searchKey;
	private String searchValue;
	
	public CommentSearcher() {
		}

	public CommentSearcher(int blogId, String searchKey, String searchValue) {
		super();
		this.blogId = blogId;
		this.searchKey = searchKey;
		this.searchValue = searchValue;
	}

	public int getBlogId() {
		return blogId;
	}

	public void setBlogId(int blogId) {
		this.blogId = blogId;
	}

	public String getSearchKey() {
		return searchKey;
	}

	public void setSearchKey(String searchKey) {
		this.searchKey = searchKey;
	}

	public String getSearchValue() {
		return searchValue;
	}

	public void setSearchValue(String searchValue) {
		this.searchValue = searchValue;
	}

	@Override
	public String toString() {
		return "CommnetSearcher [blogId=" + blogId + ", searchKey=" + searchKey + ", searchValue=" + searchValue + "]";
	}
	
	
	
}//class
