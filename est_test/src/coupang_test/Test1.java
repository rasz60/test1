package coupang_test;

public class Test1 {

	public static void main(String[] args) {
		int[][] delivery1 = { {1, 3, 1}, {3, 5, 0}, {5, 4, 0}, {2, 5, 0} };
		int[][] delivery2 = { {5, 6, 0}, {1, 3, 1}, {1, 5, 0}, {7, 6, 0}, {3, 7, 1}, {2, 5, 0} };

		System.out.println( solution(delivery1, 6) );
		System.out.println( solution(delivery2, 7) );

	}

	public static String solution(int[][] delivery, int n) {
		
		String answer = "";
		char[] tmp = initAnswer(n);
		
		int idx1 = 0, idx2 = 0, chk1 = 0, chk2 = 0;
		
		for ( int i = 0; i < delivery.length; i++ ) {
			
			idx1 = delivery[i][0];
			idx2 = delivery[i][1];
			
			// 배송 성공 시
			if ( delivery[i][2] == 1 ) {
				
				chk1 = 1;
				chk2 = 1;
				
			} 
			
			// 배송 실패 시
			else {
				
				char val1 = tmp[idx1];
				char val2 = tmp[idx2];
				
				// 두 개 물건 중 재고가 확실히 확인된 물건이 있으면, 다른 상품의 재고는 없는 것으로 확인
				if ( val1 == 'O' ) {
					chk2 = -1;
				} else if ( val2 == 'O' ) {
					chk1 = -1;
				} 
				
				// 둘 다 재고가 확인된 적 없는 상품일 때, 다음 반복 진행
				else {
					continue;
				}
				
			}
			
			// chk의 값이 바뀌었을 때, resetArr 메서드 실행
			if ( chk1 != 0 || chk2 != 0) {
				int[] params = {idx1, idx2, chk1, chk2};
				resetArr(delivery ,tmp, params);
			}
			
		}
		
		
		return answer;
	}
	
	// 상품의 개수만큼 ?로 채워진 char array를 반환하는 메서드
	public static char[] initAnswer(int length) {
		
		System.out.println("진입");
		
		String init = "";
		
		for ( int i = 0; i < length; i++ ) {
			init = init + "?";
		}
		
		return init.toCharArray();
	}
	
	public static void resetArr(int[][] arr1, char[] arr2, int[] arr3) {
		
		int idx1 = arr3[0];
		int idx2 = arr3[1];
		int chk1 = arr3[2];
		int chk2 = arr3[3];
		
		if ( chk1 == 1 && chk2 == 1 ) {
			
			arr2[idx1] = 'O';
			arr2[idx2] = 'O';
			
		}
		
		else if ( chk1 == -1 ) {
			
			arr2[idx1] = 'X';
			
		}
		
		else if ( chk2 == -1 ) {
			
			arr2[idx2] = 'X';
			
		}
		
		
		
	}
}
