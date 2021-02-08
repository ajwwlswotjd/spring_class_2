package net.gondr.domain;

public class Criteria {

	private Integer page;
	private Integer perPageNum;
	private Integer perChapterNum;
	private String keyword;
	
	private boolean prev;
	private boolean next;
	private Integer start;
	private Integer end;
	private Integer totalPage;
	
	public Criteria() {
		this.page = 1;
		this.perPageNum = 10;
		this.perChapterNum = 5;
		this.keyword = null;
		this.prev = true;
		this.next = true;
	}
	
	// 전체 글의 갯수를 받아서 페이지네이션 제작에 필요한 변수를 채운다.
	public void Calculate( Integer total ) {
		this.totalPage = (int) Math.ceil( (double)total / this.perPageNum );
		if( this.totalPage == 0 ) this.totalPage = 1;
		this.end = (int)Math.ceil( (double)this.page / this.perChapterNum ) * this.perChapterNum;
		this.start = this.end - this.perChapterNum + 1;
		
		if( this.end >= this.totalPage ) {
			this.end = this.totalPage;
			this.next = false;
		}
		if( this.start == 1 ) {
			this.prev = false;
		}
			
	}
	
	public Integer getPageStart() {
		return (this.page - 1) * this.perPageNum; 
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getPerPageNum() {
		return perPageNum;
	}

	public void setPerPageNum(Integer perPageNum) {
		this.perPageNum = perPageNum;
	}

	public Integer getPerChapterNum() {
		return perChapterNum;
	}

	public void setPerChapterNum(Integer perChapterNum) {
		this.perChapterNum = perChapterNum;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public boolean isPrev() {
		return prev;
	}

	public void setPrev(boolean prev) {
		this.prev = prev;
	}

	public boolean isNext() {
		return next;
	}

	public void setNext(boolean next) {
		this.next = next;
	}

	public Integer getStart() {
		return start;
	}

	public void setStart(Integer start) {
		this.start = start;
	}

	public Integer getEnd() {
		return end;
	}

	public void setEnd(Integer end) {
		this.end = end;
	}

	public Integer getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}
	
}
