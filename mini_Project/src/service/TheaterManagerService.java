package service;

import vo.Movie;

public interface TheaterManagerService {

	/**
	 * 영화 추가
	 * @author 조호연
	 */
	void addMovie();
	
	/**
	 * 영화 삭제
	 * @author 조호연
	 */
	void deleteMovie();
	
	/**
	 * 영화ID로 영화 찾기
	 * @author 조호연
	 * @param movieId
	 * @return
	 */
	Movie findMovieBy(int movieId);
	
	/**
	 * 영화 이름으로 영화 찾기
	 * @author 조호연
	 * @param movieName
	 * @return
	 */
	String findMovieNameBy(String movieName);
	
	/**
	 * 영화 목록 출력
	 * @author 조호연
	 */
	void MovieList();
		
	/**
	 * 상영관 목록 출력
	 * @author 한수빈
	 */
	void theaterList();
	
	/**
	 * 상영관 추가
	 * @author 한수빈
	 */
	void addTheater();
	
	/**
	 * 상영관 삭제
	 * @author 한수빈
	 */
	void deleteTheater();
	
	/**
	 * 상영관 ID로 상영관 찾기
	 * @author 한수빈
	 * @param theaterId
	 * @return
	 */
	int theaterFindBy(int theaterId);
}
