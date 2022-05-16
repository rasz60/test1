package coupang_test;

public class Test1 {

	public static void main(String[] args) {
		int[][] delivery1 = { {1, 3, 1}, {3, 5, 0}, {5, 4, 0}, {2, 5, 0} };
		int[][] delivery2 = { {5, 6, 0}, {1, 3, 1}, {1, 5, 0}, {7, 6, 0}, {3, 7, 1}, {2, 5, 0} };

		System.out.println( "result 1 : " + solution(delivery1, 6) );
		System.out.println( "result 2 : " +solution(delivery2, 7) );

	}

	public static String solution(int[][] delivery, int n) {
		
		String answer = "";
		
		// ?로만 채워진 char 배열 생성
		char[] tmp = new char[n];
		for ( int i = 0; i < n; i++ ) {
			tmp[i] = '?';
		}
		
		for ( int i = 0; i < delivery.length; i++ ) {
			// 주문한 두 개 상품의 재고 배열 인덱스 생성
			int idx1 = delivery[i][0] - 1;
			int idx2 = delivery[i][1] - 1;
			
			// 배송 성공 시
			if ( delivery[i][2] == 1 ) {
				
				// 해당 상품 모두 재고가 있는 것으로 확인
				tmp[idx1] = 'O';
				tmp[idx2] = 'O';
				
				// 재고 여부를 다시 세팅
				tmp = resetArr(delivery, tmp, idx1, idx2);
				
			} 
			
			// 배송 실패 시
			else {
				
				// 두 개 상품의 재고 여부 확인
				char val1 = tmp[idx1];
				char val2 = tmp[idx2];
				
				// 두 개 상품 중 재고가 확실히 확인된 상품이 있으면, 다른 상품의 재고는 없는 것으로 확인
				if ( val1 == 'O' ) {
					
					tmp[idx2] = 'X';
					
				} 
				
				else if ( val2 == 'O' ) {
					
					tmp[idx1] = 'X';
					
				}
				
				// 둘 다 재고가 확인된 적 없는 상품일 때
				else {
					
					// 다음 반복 실행
					continue;
					
				}
				
			}
			
		}
		
		answer = new String(tmp);
		
		return answer;
		
	}
	
	// 배송에 성공한 경우, delivery 배열을 처음부터 확인해서 재고 여부를 다시 체크하는 메서드
	public static char[] resetArr(int[][] arr1, char[] arr2, int idx1, int idx2) {
		
		for ( int i = 0; i < arr1.length; i++ ) {
			
			// 배송이 실패한 경우
			if ( arr1[i][2] == 0 ) {
				
				// 재고가 확실히 있는 것으로 확인된 상품과 함께 주문된 상품은 재고가 없는 것으로 확인
				if ( arr1[i][0] == idx1 + 1 || arr1[i][0] == idx2 + 1 ) {

					arr2[arr1[i][1] - 1] = 'X';
					
				}
				
				else if ( arr1[i][1] == idx1 + 1 || arr1[i][1] == idx2 + 1 ) {
					
					arr2[arr1[i][0] - 1] = 'X';
					
				} 
				
				// 상품 번호가 재고가 확인된 상품 번호와 일치하는 것이 없는 경우, 다음 반복 진행
				else {
					
					// 다음 반복 실행
					continue;
					
				}
				
			} 
			// 배송이 성공한 경우
			else {
				
				// 다음 반복 실행
				continue;
			
			}
		
		}
		
		return arr2;
		
	}
}
