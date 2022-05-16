package coupang_test;

public class Test2 {

	public static void main(String[] args) {
		
		System.out.println( "result 1 : " + solution(6L) );
		System.out.println( "result 2 : " + solution(12L) );
		System.out.println( "result 3 : " + solution(19L) );
		System.out.println( "result 4 : " + solution(2047L) );
		System.out.println( "result 5 : " + solution(988027L) );
	}

	public static String solution(long n) {
		long[] answer = { -1 , -1 };
		
		// 2부터 n/2까지 반복
		for ( long i = 2L; i <= n/2; i++ ) {
			
			// n이 i로 나누어 떨어질 때
			if ( n%i == 0 ) {
				
				// 나눈 값과 몫 모두 소수가 맞는지 체크
				boolean rst = isPrime(i) == true && isPrime(n/i) == true ? true : false;
				
				
				// 두 값이 소수가 맞을 경우
				if ( rst ) {
					
					// 배열에 두 값을 담음
					answer[0] = i;
					answer[1] = n/i;
					
					// 반복을 종료
					break;
					
				} 
				
				// 두 값이 소수가 아닐 경우
				else {
					
					// 다음 반복 실행
					continue;
				
				}
			
			} 
			
		}
		
		String rst = "[" + answer[0] + ", "  + answer[1] + "]";
		
		return rst;
		
	}
	
	// 소수인지 체크하는 메서드
	public static boolean isPrime(long n) {
		// 리턴 값
		boolean rst = true;
		
		// 2 ~ n/2까지 반복 ( parameter 값이 2인 경우는 소수이기 때문에 반복을 실행하지 않고 true를 return )
		for ( long i = 2L; i <= n/2; i++ ) {
			
			// 나누어 떨어지는 수가 있을 경우
			if ( n%i == 0 ) {
				
				// 소수가 아닌 것으로 확인
				rst = false;
				// 반복 종료
				break;
			
			}
			
		}
		
		return rst;
	}
}
