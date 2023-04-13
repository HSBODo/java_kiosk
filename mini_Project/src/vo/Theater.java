package vo;


import service.ReserveServiceImpl;

public class Theater {
	private int theaterId; // 상영관 ID 
    private String time;  // 7시              7시
    private int movieId;  // 영화ID
    private boolean[] seat = new boolean[25]; //총좌석25석

	public Theater() {}

	public Theater(int theaterId, String time, int movieId) {
		super();
		this.theaterId = theaterId;
		this.time = time;
		this.movieId = movieId;
	}

	public Theater(int theaterId, String time, int movieId, boolean[] seat) {
		this.theaterId = theaterId;
		this.time = time;
		this.movieId = movieId;
		this.seat = seat;
	}

	

	public int getTheaterId() {
		return theaterId;
	}

	public void setTheaterId(int theaterId) {
		this.theaterId = theaterId;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public int getMovieId() {
		return movieId;
	}

	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}

	public boolean[] getSeat() {
		return seat;
	}

	public void setSeat(boolean[] seat) {
		this.seat = seat;
	}
		public void switchSeat(int idx) {
		seat[idx] = !seat[idx];
	}

	@Override
	public String toString() {
		return "[    " + theaterId +"관"+ "      " + time + "     "+ ReserveServiceImpl.findMovieNameBy(movieId)+"   "+ getAvailSeats() + "]\n";
	}
	
	private int getAvailSeats() {
		int cnt = 0;
		for(boolean b : seat) {
			if(!b) cnt++;
		}
		return cnt;
	}

//	@Override
//	public String toString() {
//		String ret = Data.getMovies().get(getTheaterId()*3-1).getMovieName()+"[상영관:" + theaterId + ", 시간:" + time + ", 영화번호:" + movieId + "]"+"\n-------[screen]-------";
//		ret += "\n";
//		for(int i = 0 ; i < seat.length ; i++) {
//			ret += String.format("[" +  (seat[i] ? " X" : "%2d") + "]",i+1); 
//			if((i+1) % 5 == 0) {
//				ret += "\n";
//			}
//		}
//		return ret;
//	}
	
	
	
	
	
	
}