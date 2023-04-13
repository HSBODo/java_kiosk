
package service;

import vo.Movie;
import vo.Reservation;
import vo.Theater;
import vo.User;

import java.util.ArrayList;
import java.util.List;

import javax.management.RuntimeErrorException;

import util.Common;

public class ReserveServiceImpl implements ReserveService {
	User user;
	private static List<Movie> movies = new ArrayList<Movie>(); // 0
	private static List<Theater> theaters = new ArrayList<Theater>();// 0
	TheaterManagerServiceImpl manager = new TheaterManagerServiceImpl();
	private List<Reservation> reservations = new ArrayList<Reservation>();// 0
	List<User> users = new ArrayList<>();
	{
		users.add(new User("superman", "1234", true));
	}
//
	{

		// (극장id, 시간대, 영화id,카운트)
		movies.add(new Movie(1, "타이타닉", "감독: 제임스 카메론", // 0
				"레오나르도 디카프리오, 케이트 원슬렛", "9.88"));

		movies.add(new Movie(2, "어벤져스:엔드게임", "감독: 안소니 루소, 조 루소", // 1
				"로버트 다우니 주니어, 크리스 에반스, 크리스 헴스워스", "9.49"));

		movies.add(new Movie(3, "기생충", "감독: 봉준호", // 2
				"송강호, 이선균, 박소담, 조여정, 최우식", "9.07"));

		movies.add(new Movie(4, "범죄와의 전쟁", "감독: 윤종빈", // 3
				"하정우, 최민식, 마동석, 조진웅, 곽도원", "8.64"));

		String[] times = { "07시", "12시", "19시" };

		for (int i = 0; i < 12; i++) {
			theaters.add(new Theater(i + 1, times[i % 3], i / 3 + 1));
		}

	}

	// 상영중 영화 목록 조회
	public void listMovies() {
		for (Movie movie : movies) {
			System.out.println();
			System.out.println("-" + movie.getMovieId() + "번영화 -");
			System.out.println(movie);
		}
	}

	@Override
	public void reserveMovie() {

		int movieNumber = chooseMovie();
		int theaterNumber = chooseTheater(movieNumber);
		Theater t = findTheaterBy(theaterNumber);
		int seat = chooseSeat(theaterNumber);

		int resvNo = reservations.size() == 0 ? 1 : reservations.get(reservations.size() - 1).getResvId() + 1;
		reservations.add(new Reservation(resvNo, t.getMovieId(), t.getTheaterId(), user.getId(), seat, t.getTime()));
		t.switchSeat(seat - 1);
		
		System.out.println();
		System.out.println("oooooooooooooooooooooooooooooo");
		System.out.println("ooo 예매가 완료되었습니다. ooo");
		System.out.println("oooooooooooooooooooooooooooooo");
		System.out.println();
	}

	public int chooseMovie() {
		listMovies();
		while (true) {
			int no = 0;
			try {
				no = Common.nextInt("예매할 영화의 번호를 입력하세요>");
				if (no <= 0 || no > getMovies().size()) {
					throw new RuntimeException();
				} else {
					return no;
				}
			} catch (NumberFormatException e) {
				System.out.println();
				System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
				System.out.println("xxx 올바른 영화 번호를 입력하세요. xxx");
				System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
				System.out.println();
			} catch (RuntimeException e) {
				System.out.println();
				System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
				System.out.println("xxx 없는 영화 번호입니다. xxx");
				System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
				System.out.println();
			}
		}
	}

	public int chooseTheater(int no) {
		List<Theater> tmp = findTheatersByMovieId(no);
		System.out.println("┌　　　　　　         　　　　　　　　　 　　┐");
		System.out.println("　　상영관　　상영시간　　영화제목  남은좌석");
		printList(tmp);
		System.out.println("└　　　　　　　　　　　　　　　　          　┘");
		List<Integer> tmp2 = new ArrayList<Integer>();
		for (Theater t : tmp)
			tmp2.add(t.getTheaterId());
		while (true) {
			try {
				no = Common.nextInt("상영관을 선택하세요>");
				if (!tmp2.contains(no)) {
				} else {
					return no;
				}
			} catch (IllegalArgumentException e) {
				
				System.out.println();
				System.out.println("ooooooooooooooooooooooooooooooooo");
				System.out.println("ooo 올바른 값을 입력해주세요. ooo");
				System.out.println("ooooooooooooooooooooooooooooooooo");
				System.out.println();
			}
		}
	}

	public int chooseSeat(int no) {
		Theater t = findTheaterBy(no);
		// 좌석표 보여주는 메서드
		seatImg(t);
		while (true) {
			try {
				int seat = Common.nextInt("좌석을 선택하세요>");
				if (seat <= 0 || seat > 25) {
					throw new IllegalArgumentException();
				} else if (t.getSeat()[seat - 1] == true) {
					System.out.println();
					System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
					System.out.println("xxx 이미 예약된 자리입니다. xxx");
					System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
					System.out.println();
				} else {
					return seat;
				}
			} catch (NumberFormatException e) {
				System.out.println();
				System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
				System.out.println("xxx 올바른 좌석 번호를 입력하세요. xxx");
				System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
				System.out.println();
			} catch (IllegalArgumentException e) {
				System.out.println();
				System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
				System.out.println("xxx 없는 좌석 번호입니다. xxx");
				System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
				System.out.println();
			}
		}

	}

	@Override
	public void iquiry() { // ?
		System.out.println();
		System.out.println("- " + user.getId() + "님의 예매내역 -");
		System.out.println("┌　　　　　　         　　　　　　　　　 　　┐");
		printList(findReservationsByUserId(user.getId()));
		System.out.println("└　　　　　　　　　　　　　　　　          　┘");
	}

	@Override
	public void cancelReservation() {
		try {
			int no = Common.nextInt("취소할 티켓의 예매번호를 입력하세요");
			Reservation r = findReservationBy(no);
			findTheaterBy(r.getTheaterId()).switchSeat(r.getIdx() - 1);
			;
			reservations.remove(findReservationBy(no));
		} catch (Exception e) {
			System.out.println();
			System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
			System.out.println("xxx 올바른 예매번호를 입력하세요.xxx");
			System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
			System.out.println();
		}

	}

	public void input() {
		System.out.println("--------회원가입--------");
		String id = Common.nextLine("ID를 입력해주세요.>");
		String pw = Common.nextLine("비밀번호를 입력해주세요>");
		
		if (findById(id) != null) {
			System.out.println();
			System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
			System.out.println("xxx 중복된 아이디 입니다. xxx");
			System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
			System.out.println();

		} else if (id.matches("") || id.matches(" ") || pw.matches("") || pw.matches(" ")) {
			System.out.println();
			System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxx");
			System.out.println("xxx 잘못 입력하셨습니다. xxx");
			System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxx");
			System.out.println();

		}

		else {
			users.add(new User(id, pw));

		}

	}

	public void list() {
		System.out.println("=============================");
		for (int i = 0; i < users.size(); i++) {
			System.out.println(i + 1 + "." + users.get(i).toString());
		}
	}

	public User findById(String id) {
		for (int i = 0; i < users.size(); i++) {
			if (users.get(i).getId().equals(id)) {
				return users.get(i);
			}
		}
		return null;
	}

	public User findByIdPw(String id, String pw) {
		for (int i = 0; i < users.size(); i++) {
			if (users.get(i).getId().equals(id) && users.get(i).getPwd().equals(pw)) { // 로그인 실패
				return users.get(i);
			}
		}
		return null;
	}

	public void login() {
		System.out.println("--------로그인--------");
		String id = Common.nextLine("ID를 입력해주세요.>").trim();
		String pw = Common.nextLine("비밀번호를 입력해주세요>").trim();
		user = findByIdPw(id, pw);
		if (user == null) { // 로그인 실패
			System.out.println("==========================================");
			System.out.println("올바른 ID/PW가 아닙니다. 다시 입력해주세요");
			System.out.println("==========================================");
			return;
		}
		if (user.isManager()) {
			System.out.println("             .=.,\r\n" + 
					"            ;c =\\\r\n" + 
					"          __|  _/\r\n" + 
					"        .'-'-._/-'-._\r\n" + 
					"       /..   ____    \\\r\n" + 
					"      /' _  [<_->] )  \\\r\n" + 
					"     (  / \\--\\_>/-/'._ )\r\n" + 
					"      \\-;_/\\__;__/ _/ _/\r\n" + 
					"       '._}|==o==\\{_\\/\r\n" + 
					"        /  /-._.--\\  \\_\r\n" + 
					"       // /   /|   \\ \\ \\\r\n" + 
					"      / | |   | \\;  |  \\ \\\r\n" + 
					"     / /  | :/   \\: \\   \\_\\\r\n" + 
					"    /  |  /.'|   /: |    \\ \\\r\n" + 
					"    |  |  |--| . |--|     \\_\\\r\n" + 
					"    / _/   \\ | : | /___--._) \\\r\n" + 
					"   |_(---'-| >-'-| |       '-'\r\n" + 
					"          /_/     \\_\\");
			System.out.println();
			System.out.println("관리자로 로그인 되었습니다.");
			System.out.println();
			boolean m = true;
			while (m) {
				System.out.println("-------------------------------");
				System.out.println("-------관리자모드입니다.-------");
				System.out.println("-------------------------------");
				int a = 0;
				try {
					a = Common.nextInt(" ①영화조회  ②영화등록  ③영화삭제  ④상영관조회  ⑤상영관등록  ⑥상영관삭제  ⑦회원조회  ⑧로그아웃");
					if (a <= 0 || a > 8) {
						System.out.println();
						System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
						System.out.println("xxx 올바른 메뉴 번호를 입력해주세요. xxx");
						System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
						System.out.println();
					}
				} catch (IllegalArgumentException e) {
					System.out.println();
					System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
					System.out.println("xxx 올바른 메뉴 번호를 입력해주세요. xxx");
					System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
					System.out.println();
				}
				switch (a) {
				case 1:
					manager.MovieList();
					break;
				case 2:
					manager.addMovie();
					break;
				case 3:
					manager.deleteMovie();
					break;
				case 4:
					manager.theaterList();
					break;
				case 5:
					manager.addTheater();
					break;
				case 6:
					manager.deleteTheater();
					break;
				case 7:
					list();
					;
					break;
				case 8:
					m = false;
					break;
				}
			}
		} else {
			boolean t = true;
			
			System.out.println();
			System.out.println("                로그인 되었습니다.");
			System.out.println();
			while (t) {
				
				System.out.println("                       .-.\r\n" + 
						"                      |_:_|\r\n" + 
						"                     /(_Y_)\\\r\n" + 
						".                   ( \\/M\\/ )\r\n" + 
						" '.               _.'-/'-'\\-'._\r\n" + 
						"   ':           _/.--'[[[[]'--.\\_\r\n" + 
						"     ':        /_'  : |::\"| :  '.\\\r\n" + 
						"       ':     //   ./ |oUU| \\.'  :\\\r\n" + 
						"         ':  _:'..' \\_|___|_/ :   :|\r\n" + 
						"           ':.  .'  |_[___]_|  :.':\\\r\n" + 
						"            [::\\ |  :  | |  :   ; : \\\r\n" + 
						"             '-'   \\/'.| |.' \\  .;.' |\r\n" + 
						"             |\\_    \\  '-'   :       |\r\n" + 
						"             |  \\    \\ .:    :   |   |\r\n" + 
						"             |   \\    | '.   :    \\  |\r\n" + 
						"             /       \\   :. .;       |\r\n" + 
						"            /     |   |  :__/     :  \\\\\r\n" + 
						"           |  |   |    \\:   | \\   |   ||\r\n" + 
						"          /    \\  : :  |:   /  |__|   /|\r\n" + 
						"          |     : : :_/_|  /'._\\  '--|_\\\r\n" + 
						"          /___.-/_|-'   \\  \\\r\n" + 
						"                         '-'");
				System.out.println("=========================================");
				System.out.println("       휴먼영화관 예약시스템입니다.       ");
				System.out.println("=========================================");
				System.out.println("    원하는 서비스의 번호를 입력하세요.    ");
				System.out.println();
				int a = 0;
				try {
					a = Common.nextInt(" ①상영중 영화목록   ② 영화예매     ③예매조회     ④예매취소      ⑤로그아웃");
				} catch (NumberFormatException e) {
					System.out.println();
					System.out.println("숫자를 입력하세요.");
				}
				switch (a) {
				case 1:
					listMovies();
					break;
				case 2:
					reserveMovie();
					break;
				case 3:
					iquiry();
					break;
				case 4:
					cancelReservation();
					break;
				case 5:
					System.out.println("시스템이 종료었습니다.");
					t = false;
					break;
				}
			}
		}
	}

	public static String findMovieNameBy(int movieId) {
		for (Movie m : movies) {
			if (m.getMovieId() == movieId) {
				return m.getMovieName();   // int movieId를 MovieName으로 바꿔주는 메서드 
			} 
		}
		return null;

	}

	public Movie findMovieBy(int movieId) {
		for (Movie movie : movies) {
			if (movie.getMovieId() == movieId) {
				return movie;
			}
		}
		return null;
	}

	public Reservation findReservationBy(int reservId) {
		for (Reservation reservation : reservations) {
			if (reservation.getResvId() == reservId) {
				return reservation;
			}
		}
		return null;
	}

	public List<Reservation> findReservationsByUserId(String userId) {
		List<Reservation> ret = new ArrayList<Reservation>();
		for (Reservation reservation : reservations) {
			if (reservation.getUserId().equals(userId)) {
				ret.add(reservation);
			}
		}
		return ret;
	}

	public Theater findTheaterBy(int theaterId) {
		for (Theater theater : theaters) {
			if (theater.getTheaterId() == theaterId) {
				return theater;
			}
		}
		return null;
	}

	public List<Theater> findTheatersByMovieId(int movieId) {
		List<Theater> ret = new ArrayList<Theater>();
		for (Theater t : theaters) {
			if (t.getMovieId() == movieId) {
				ret.add(t);
			}
		}
		return ret;
	}

	public User findUserBy(String id) {
		for (User user : users) {
			if (user.getId().equals(id)) {
				return user;
			}
		}
		return null;
	}

	public void printList(List<?> list) {
		for (Object o : list) {
			System.out.println(o);
		}
	}

	public static List<Movie> getMovies() {
		return movies;
	}

	public static void setMovies(List<Movie> movies) {
		ReserveServiceImpl.movies = movies;
	}

	public static List<Theater> getTheaters() {
		return theaters;
	}

	public static void setTheaters(List<Theater> theaters) {
		ReserveServiceImpl.theaters = theaters;
	}

	public static void seatImg(Theater t) {
		System.out.println();
		System.out.println("=============================");
		System.out.println("           스크린            ");
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				if (t.getSeat()[(i * 5) + (j)] == false) {
					System.out.printf("[%3d] ", ((i * 5) + (j + 1)));
				} else {
					System.out.print("[ X ] ");
				}
			}
			System.out.println();
		}
		System.out.println("=============================="); // 좌석 display
	}
}
