package rent;

import java.util.Scanner;

public class Rent {
	public static void main(String[] args) {
		RentManager rentManager = new RentManager();

		// 데이터베이스 연결 초기화
		rentManager.initDBConnect();
		Scanner scanner = new Scanner(System.in);

		while (true) {
			// 메인 메뉴 출력
			System.out.println("*******************렌트 메뉴******************");
			System.out.println("1. 책 대출하기");
			System.out.println("0. 종료");
			System.out.print("선택하세요: ");
			int mainChoice = scanner.nextInt();

			if (mainChoice == 0) {
				System.out.println("프로그램을 종료합니다.");
				break;
			} else if (mainChoice == 1) {
				
				boolean end_flag = false;
				while (true) {

					Book[] books = rentManager.allFetch();

					System.out.println("대출 가능한 책 목록:");
					for (int i = 0; i < books.length; i++) {
						System.out.print("[" + (i + 1) + "] 제목: " + books[i].getTitle());
						System.out.println("저자: " + books[i].getWriter());
						System.out.println("출판사: " + books[i].getPublisher());

						System.out.print("대출할 책의 번호를 입력하세요 (뒤로 가려면 0 입력): ");
	                    int bookIndex = scanner.nextInt();
	                    scanner.nextLine();	
	                    
	                    if (bookIndex == 0) {
	                        // 뒤로 가기 선택 시 메인 메뉴로 돌아감
	                        System.out.println("메인 메뉴로 돌아갑니다.");
	                        break;
	                    }

	                    if (bookIndex > 0 && bookIndex <= books.length) {
	                        // 책 대출 날짜 입력 (추가적인 신원확인 정보 없이 진행)
	                        System.out.print("대출 시작 날짜(YYYY-MM-DD): ");
	                        String startDate = scanner.nextLine();
	                        System.out.print("반납 예정 날짜(YYYY-MM-DD): ");
	                        String endDate = scanner.nextLine();

	                        String selectedBookId = books[bookIndex - 1].getbookId();
	                        rentManager.insertRent(selectedBookId, null);

	                        System.out.println("책을 성공적으로 대출하였습니다.");
	                        end_flag = true;  // 대출 완료 상태로 변경
	                        break;  // 책을 빌린 후 메인 메뉴로 돌아감
	                    } else {
	                        System.out.println("잘못된 선택입니다. 다시 시도해주세요.");
	                    }
	                }
					
					
					
					scanner.close();
			        rentManager.releaseDB();
	            } 
			}
		     
	}
			
	}
}
