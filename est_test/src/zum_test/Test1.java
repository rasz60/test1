package zum_test;

public class Test1 {

	public static void main(String[] args) {
		System.out.println("/* n으로 나누었을 때 몫과 나머지가 같은 값을 모두 더한 값 구하기 */");
		System.out.println( "result 1 : " + solution(2) );
		System.out.println( "result 2 : " + solution(3) );
		System.out.println( "result 3 : " + solution(5023945) );
		

	}
	
	public static long solution(int n) {
		/* n으로 나누었을 때 몫과 나머지가 같은 값을 모두 더한 값 구하기 */
		
		long answer = 0;
		
		// n으로 나누면 몫과 나머지가 1이 나오는 숫자부터 반복 시작
		long i = n + 1;
		
		// i를 n으로 나눈 몫이 n보다 작을 때까지 반복 ( 나머지가 n과 같을 수 없으므로 )
		while ( (i/n) < n ) {
			
			// 몫과 나머지가 같은 값을 누적
			answer += i;

			// i를 n으로 나눈 몫과 나머지가 현재의 +1된 값이 나오는 값으로 i를 변경
			i = ( n * (i/n + 1) ) + (i%n + 1);
			
		}

		return answer;
		
	}

}
