package service;

import java.util.List;

import vo.Movie;
import vo.Reservation;
import vo.Theater;
import vo.User;

public interface ReserveService {
	
	
	
	/**
	 * 영화 조회
	 */
	public void listMovies();
	
	/**
	 * 영화 예매
	 */
	public void reserveMovie();
	
	/**
	 * 영화 예매 시 영화 선택 메서드 생성
	 * @author 조호연<br>
	 * 
	 */
	public int chooseMovie();
	
	/**
	 * 영화 예매 시 좌석 선택 메서드 생성
	 * @author 조호연<br>
	 */
	public int chooseTheater(int no);
	
	/**
	 * 영화 예매 시 좌석 선택
	 * @author 조호연<br>
	 * @return
	 */
	public int chooseSeat(int no);
	
	/**
	 * 예매한 영화 조회
	 * @author 전지용<br>
	 */
	public void iquiry();
	
	/**
	 * 예매 취소
	 * @author 조호연<br>
	 */
	public void cancelReservation();
	
	/**
	 * 회원 가입
	 * @author 김선민
	 */
	public void input();
	
	/**
	 * 관리자모드 회원 조회
	 *  @author 한수빈
	 */
	public void list();
	
	/**
	 * 아이디로 회원 존재 여부 확인
	 * @author 조호연
	 * @return
	 */
	public User findById(String id);
	
	/**
	 * 아이디, 비밀번호로 회원 존재 여부 확인
	 * @author 한수빈
	 * @param id
	 * @param pw
	 * @return
	 */
	public User findByIdPw(String id, String pw);
	
	/**
	 * 로그인
	 * @author 한수빈
	 */
	public void login();
	
	/**
	 * 영화ID를 영화제목으로 조회
	 * @author 한수빈
	 * @param movieId
	 * @return
	 */
	public Movie findMovieBy(int movieId);
	
	/**
	 * 예매 번호로 예매 조회
	 * @author 조호연
	 * @param reservId
	 * @return
	 */
	public Reservation findReservationBy(int reservId);
	
	/**
	 * 유저 ID로 예매내역 찾기
	 * @author 전지용
	 * @param userId
	 * @return
	 */
	public List<Reservation> findReservationsByUserId(String userId);
	 
	
	/**
	 * 상영관 번호로 상영관 조회 
	 * @author 한수빈
	 * @param theaterId
	 * @return
	 */
	public Theater findTheaterBy(int theaterId);
	
	/**
	 * 영화 번호로 상영관 찾기
	 * @author 한수빈 
	 * @param movieId
	 * @return
	 */
	public List<Theater> findTheatersByMovieId(int movieId);
	
	/**
	 * id로 유저 조회
	 * @author 한수빈
	 * @param id
	 * @return
	 */
	public User findUserBy(String id);
	
	/**
	 * 예매 내역 출력 
	 * @author 전지용
	 * @param list
	 */
	public void printList(List<?> list);
	
	
	// 예약수정 (X)
	// 예약취소
	
	// CRUD
}
