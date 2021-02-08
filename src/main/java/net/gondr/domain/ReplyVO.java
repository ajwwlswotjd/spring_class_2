package net.gondr.domain;

public class ReplyVO {
	
	private Integer idx;
	private String writer;
	private String content;
	private int board_id;
	
	private String img; 
	
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public Integer getIdx() {
		return idx;
	}
	public void setIdx(Integer idx) {
		this.idx = idx;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}

	public int getBoard_id() {
		return board_id;
	}
	public void setBoard_id(int board_id) {
		this.board_id = board_id;
	}
	@Override
	public String toString() {
		return "ReplyVO [idx=" + idx + ", writer=" + writer + ", content=" + content + ", board_id=" + board_id + ", img=" + img + "]";
	}
	
	

}
