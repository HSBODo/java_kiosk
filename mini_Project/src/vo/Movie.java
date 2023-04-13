package vo;

public class Movie {
	private int movieId; //  1
	private String movieName;//타이타닉
	private String author;// 감독
	private String cast;// 출연진
	private String grade;// 평점
	


	@Override
	public String toString() {
		return "[영화: " + movieName +"]\n"+"[감독:" + author+ "]\n"+"[출연진:" + cast+"]\n"
				+"[평점:" + grade + "]";   
		/*[영화: 타이타닉]
		  [감독:감독: 제임스 카메론]
		  [출연진:레오나르도 디카프리오, 케이트 원슬렛]
	   	  [평점:9.88]*/
	}

	public Movie(int movieId, String movieName, String author, String cast, String grade) {
		super();
		this.movieId = movieId;
		this.movieName = movieName;
		this.author = author;
		this.cast = cast;
		this.grade = grade;
	}

	public int getMovieId() {
		return movieId;
	}

	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}
	
	
	
	

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getCast() {
		return cast;
	}

	public void setCast(String cast) {
		this.cast = cast;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}
	
	public String getMovieName() {
		return movieName;
	}
}