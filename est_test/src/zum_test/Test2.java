package zum_test;

public class Test2 {

	public static void main(String[] args) {
		
		int[][] data1 = {
						  { 1, 0, 5 }, 
						  { 2, 2, 2 }, 
						  { 3, 3, 1 }, 
						  { 4, 4, 1 },
						  { 5, 10, 2}
						};
		
		int[][] data2 = {
						  { 1, 0, 3 }, 
						  { 2, 1, 3 }, 
						  { 3, 3, 2 }, 
						  { 4, 9, 1 },
						  { 5, 10, 2}
						};
		
		int[][] data3 = { 
						  { 1, 2, 10 }, 
						  { 2, 5, 8 }, 
						  { 3, 6, 9 }, 
						  { 4, 20, 6 },
						  { 5, 25, 5}
						};
		
		int[][] data4 = {
						  { 1, 5, 5 }, 
						  { 2, 1, 3 }, 
						  { 3, 3, 2 }, 
						  { 4, 9, 1 },
						  { 5, 10, 5},
						  { 6, 10, 2 }, 
						  { 7, 17, 3 }, 
						  { 8, 20, 2 }, 
						  { 9, 21, 1 }
						};
		
		System.out.println( "result 1 : " + resultStr( solution(data1) ) );
		System.out.println( "result 2 : " + resultStr( solution(data2) ) );
		System.out.println( "result 3 : " + resultStr( solution(data3) ) );
		System.out.println( "result 4 : " + resultStr( solution(data4) ) );
		
	}

	public static int[] solution(int[][] data) {
		
		int[] answer = new int[data.length];
		
		// 다음 문서 인쇄를 시작하는 시간
		int times = 0;
		
		for ( int i = 0; i < answer.length; i++ ) {
			
			// 대기 중인 문서가 없을 때
			if ( answer[0] == 0 ) {
				
				// 해당 문서 번호를 첫번째 인덱스에 넣음
				answer[0] = data[i][0];
				
				// 첫 문서 인쇄 종료 시간 = 요청 시간 + 페이지 수
				times = data[i][1] + data[i][2];
				
				// 문서 인쇄가 완료되면 문서 번호를 0으로 변경
				data[i][0] = 0;
				continue;
				
			} else {
				
				// 다음 인쇄 문서 번호
				int next = 0;
				int bind = 0;
				
				for (int j = 0; j < data.length; j++ ) {

					
					// 문서번호가 0일 때
					if ( data[j][0] == 0 ) {
						
						// 조건에 맞는 index를 저장
						bind = j;
						
						// 다음 반복 진행
						continue;
					}
					
					// 인쇄 시작 시간보다 요청시간이 작거나 같을 때
					if ( data[j][1] <= times ) {
						
						// 조건에 맞는 인덱스 번호를 저장
						bind = j;
						
						// 다음 문서가 지정되지 않았을 때
						if ( next == 0 ) {
							
							// 해당 문서를 다음 인쇄 문서로 지정
							next = data[j][0] - 1;
							continue;
							
						}
						
						// 다음 인쇄 문서보다 해당 문서의 페이지 수가 적을 때
						if ( data[j][2] < data[next][2] ) {
							
							// 해당 문서를 다음 인쇄 문서로 지정
							next = data[j][0] - 1;
							continue;
							
						}
						
					}
					
				}
				
				// 문서번호가 0이 아니고, 인쇄 시작 시간보다 요청시간이 작거나 같은 문서가 없을 때
				if ( next == 0 ) {
					
					// 앞선 for문에서 마지막으로 조회한 index 이후부터 반복
					for ( int j = bind + 1; j < data.length; j++ ) {
						
						if ( data[j][0] == 0 ) {
							continue;
						}
						
						// 문서 번호가 가장 빠른 문서를 다음 인쇄 문서로 지정
						next = data[j][0] - 1;
						
						// 인쇄 시작 시간을 해당 문서의 요청시간으로 변경 
						times = data[j][1];
						break;
						
					}
					
				}
				
				// 다음 인쇄 문서 번호를 대기열에 저장
				answer[i] = data[next][0];
				
				// 대기열에 저장된 문서의 번호를 0으로 변경
				data[next][0] = 0;
				
				// 다음 문서의 인쇄 시작 시간
				times += data[next][2];
				
			}
			
		}
		
		return answer;
	}
	
	public static String resultStr(int[] answer) {
		
		String result = "";
		
		for (int i = 0; i < answer.length; i++ ) {
			if ( i == 0 ) {
				
				result += "[" + answer[i] ;
				
			} 
			
			else if ( i > 0 ) {
			
				result += answer[i];
				
			} 
			
			if ( i == answer.length - 1 ) {
				
				result += "]";
				
			} else {
				
				result += ", ";
				
			}
		}
		
		return result;
	}
}
