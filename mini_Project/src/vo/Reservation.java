package vo;

import service.ReserveServiceImpl;

public class Reservation {
	private int resvId; // 예매번호 >> PK
	private	int movieId;//영화 id >> FK
	private int theaterId;// 상영관 id >> FK
	private String userId; // 사용자 id >> FK
	
	private int idx;  //좌석번호
	private String movieTime; //영화 시간대

	@Override
	public String toString() {
		return "[예매번호:" + resvId + ", 영화제목:" + ReserveServiceImpl.findMovieNameBy(movieId) + ", 상영관:" + theaterId +"관"+ ", ID:"
				+ userId + ", 좌석번호:" + idx + ", 시간:" + movieTime + "]";
	}
	/* [예매번호:1, 영화제목:타이타닉, 상영관:1관, ID:1, 좌석번호:1, 시간:07시] */
	public Reservation() {}

	public Reservation(int resvId, int movieId, int theaterId, String userId, int idx, String movieTime) {
		super();
		this.resvId = resvId;
		this.movieId = movieId;
		this.theaterId = theaterId;
		this.userId = userId;
		this.idx = idx;
		this.movieTime = movieTime;
	}

	public int getResvId() {
		return resvId;
	}

	public void setResvId(int resvId) {
		this.resvId = resvId;
	}

	public int getMovieId() {
		return movieId;
	}

	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}

	public int getTheaterId() {
		return theaterId;
	}

	public void setTheaterId(int theaterId) {
		this.theaterId = theaterId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public int getIdx() {
		return idx;
	}

	public void setIdx(int idx) {
		this.idx = idx;
	}

	public String getMovieTime() {
		return movieTime;
	}

	public void setMovieTime(String movieTime) {
		this.movieTime = movieTime;
	}

	


	
	
	
}
