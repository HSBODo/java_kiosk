package service;



import static service.ReserveServiceImpl.*;
import static util.Common.*;

import sun.reflect.ReflectionFactory.GetReflectionFactoryAction;
import vo.Theater;
import vo.Movie;

public class TheaterManagerServiceImpl {
	//영화 등록
	public void addMovie() {
		
		while (true) {

			try {
				int movieId = nextInt("영화 식별코드를 입력하세요>>");
				Movie movieIds = findMovieBy(movieId);

				if (movieIds != null) {
					System.out.println();
					System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
					System.out.println("xxx 중복된 영화 식별코드가 존재합니다. xxx");
					System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
					System.out.println();
					continue;
				} else if (movieIds == null) {

					while (true) {
						String movieName = nextLine("영화 제목을 입력하세요>>");
						String movieNames = findMovieNameBy(movieName);

						if (movieName.equals(movieNames)) {
							System.out.println();
							System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxx");
							System.out.println("xxx 중복된 영화입니다. xxx");
							System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxx");
							System.out.println();
							continue;
						} else {
							getMovies().add(new Movie(movieId, movieName, nextLine("감독 이름을 입력하세요>>"),
									nextLine("출연진 이름을 입력하세요"), nextLine("영화 평점을 입력하세요>>")));
							break;
						}
					}
					break;
				}
			} catch (IllegalArgumentException e) {
				System.out.println();
				System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
				System.out.println("xxx 올바른 식별코드를 입력하세요. xxx");
				System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
				System.out.println();
			}
			
			
			
			
		}
	}
	
	//영화 삭제
	public void deleteMovie() {
		while (true) {
			try {
				int movieId = nextInt("삭제할 영화 식별코드를 입력하세요>>");
				if (findMovieBy(movieId) == null) {
					System.out.println();
					System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
					System.out.println("xxx 영화가 존재하지 않습니다. xxx");
					System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
					System.out.println();
					continue;
				} else {
					getMovies().remove(findMovieBy(movieId));
					System.out.println();
					System.out.println("ooooooooooooooooooooooo");
					System.out.println("ooo 삭제되었습니다. ooo");
					System.out.println("ooooooooooooooooooooooo");
					System.out.println();
					break;
				}
			} catch (IllegalArgumentException e) {
				System.out.println();
				System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
				System.out.println("xxx 올바른 식별코드를 입력하세요. xxx");
				System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
				System.out.println();
			}
		}
	}
	
	//영화아이디 검색
	public Movie findMovieBy(int movieId) {
		for(Movie movie : getMovies()) {
			if(movie.getMovieId() == movieId) {
				return movie;
			}
		}
		return null;
	}
	
	//영화 이름 검색
	public String findMovieNameBy(String movieName) {
		for(Movie movie : getMovies() ) {
			if(movie.getMovieName().equals(movieName)) {
				return movie.getMovieName();
			}
		}
		return null;
	}
	
	//영화 출력
	public void MovieList() {
		for(Movie movie : getMovies()) {
			System.out.println(movie.getMovieId());
			System.out.println(movie);
		}
	}
	
	public void theaterList() {// 상영관조회
		System.out.println("     상영관    시간    영화제목    좌석");
		System.out.println("-----------------------------------------------");
		System.out.println(getTheaters());
		
	}
	
	
	public void addTheater() { // 상영관추가
			System.out.println("=======================================");
		try {
			for (int i = 0; i < getMovies().size(); i++) {
				System.out.println("ID." + getMovies().get(i).getMovieId() + ":" + getMovies().get(i).getMovieName());
			}
			
			System.out.println("===============영화ID목록==============");
			System.out.println();
			int theaterId = nextInt("추가할 상영관 번호를 입력하세요");
			if (theaterFindBy(theaterId) == 0) {

				getTheaters().add(new Theater(theaterId, nextLine("영화 상영 시간을 입력하세요.>>"), nextInt("영화 ID를 입력하세요.>>")));
			} else if (theaterFindBy(theaterId) != 0) {
				System.out.println();
				System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
				System.out.println("xxx 이미 상영관이 존재합니다. xxx");
				System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
				System.out.println();
			}
		} catch (IllegalArgumentException e) {
			System.out.println();
			System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
			System.out.println("xxx 올바른 상영관 번호를 입력하세요 xxx");
			System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
			System.out.println();
		}
		
		
		
	}
	
	public void deleteTheater() {// 상영관삭제
		while (true) {
			
			try {
				int theaterId = nextInt("삭제할 상영관 번호를 입력하세요");
			if (theaterFindBy(theaterId) == 0) {
				System.out.println();
				System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
				System.out.println("xxx 상영관이 존제하지 않습니다. xxx");
				System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
				System.out.println();
				continue;
			} else {
				getTheaters().remove(theaterId - 1);
				System.out.println();
				System.out.println("oooooooooooooooooooooooooooooooo");
				System.out.println("ooo 상영관이 삭제되었습니다. ooo");
				System.out.println("oooooooooooooooooooooooooooooooo");
				System.out.println();
				break;
			}
			} catch(IllegalArgumentException e) {
				System.out.println();
				System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
				System.out.println("xxx 올바른 상영관 번호를 입력하세요 xxx");
				System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
				System.out.println();
			}
			

		}
		
		
		
	}
	
	
	public int theaterFindBy(int theaterId) {
		for(int i = 0 ; i <getTheaters().size();i++) {
			if(theaterId == getTheaters().get(i).getTheaterId()) {
				return theaterId;
			}
		}
		return 0 ;
		
	}
}


	
	
