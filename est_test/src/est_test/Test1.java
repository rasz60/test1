package est_test;

import java.util.Arrays;
import java.util.Collections;

public class Test1 {
	
	public static void main(String[] args) {
	
		String[] scores1 = {"AAFAFA", "FEECAA", "FABBCB", "CBEDEE", "CCCCCC"};
		String[] scores2 = {"BCD", "ABB", "FEE"};
		
		System.out.println("result 1 : " + solution(scores1));
		System.out.println("result 2 : " + solution(scores2));
		
		System.out.println("result 3 : " + solution2(scores1));
		System.out.println("result 4 : " + solution2(scores2));
	}
	
	/* stream 사용하지 않은 solution */
	public static int solution(String[] scores) {
        int answer = 0;
        
        // scores.length == 심사위원 수 & 점수의 개수
        for ( int i = 0; i < scores.length; i++ ) {
            // 각각의 점수를 배열로 만듦
            char[] score = scores[i].toCharArray();

            // 점수를 카운트할 배열 생성
            int[] count = new int[6];

            // 최소,최대값 init
            int min = -1;
            int max = -1;
            
            // 참가자 개개인이 받은 총점
            int total = 0;
            
            // 점수 하나하나를 카운트하여 배열 add
            for ( int j = 0; j < score.length; j++ ) {
                char scoreCheck = score[j];
                int tmpScore = 0;
                
                // score 에 알파벳 점수에 맞는 점수로 tmpScore에 넣어주고 scoreCheck배열에 카운트++
                switch(scoreCheck) {
                    case 'A':
                        count[0]++;
                        tmpScore = 5;
                        break;
                    case 'B':
                        count[1]++;
                        tmpScore = 4;
                        break;
                    case 'C':
                        count[2]++;
                        tmpScore = 3;
                        break;
                    case 'D':
                        count[3]++;
                        tmpScore = 2;
                        break;
                    case 'E':
                        count[4]++;
                        tmpScore = 1;
                        break;
                    case 'F':
                        count[5]++;
                        break;
                }
                
                // F가 2개 이상이면 불합격이므로 반복을 종료
                if ( count[5] > 2 ) {
                	break;
                }
                
                // A가 2개 이상이면 무조건 합격이므로 반복을 종료
                if ( count[0] > 2 ) {
                	answer++;
                	break;
                }
                
                total += tmpScore;
                
                // max 값이 초기 값이거나, 현재 점수가 max보다 클 때
                if ( max == -1 || max < tmpScore ) {
                	// 현재 점수를 max 값으로 지정
                	max = tmpScore;
                }
                
                // min 값이 초기 값이거나, 현재 점수가 min보다 작을 때
                if ( min == -1 || min > tmpScore ) {
                	// 현재 값을 min 값으로 지정
                	min = tmpScore;
                }
                
                // F와 A 개수 2개 이상 나오지 않고 마지막 점수까지 반복했을 때
                if ( j == score.length - 1 ) {
                	 // finalScore = 전체 점수 합에서 min+max값을 뺀 평균 값
                    int finalScore = (total - (min + max)) / (score.length - 2);

                    // finalScore가 3점 이상일때 합격
                    if ( finalScore >= 3) {
                        answer++;
                    }
                }
            }
           
            
        }
        
        return answer;
    }
	
	/* stream 사용한 solution */
	public static int solution2(String[] scores) {
		int answer = 0;
		
		for ( String str : scores ) {
			
			// String에 포함된 A와 F가 몇개인지 확인
			int count_a = (int)str.chars().filter(c -> c == 'A').count();
			int count_f = (int)str.chars().filter(c -> c == 'F').count();
			
			// A가 2개 이상이면 합격
			if ( count_a > 2 ) {
				answer++;
				// 다음 반복 실행
				continue;
			} 
			
			// F가 2개 이상이면 불합격
			else if ( count_f > 2 ) {
				// 다음 반복 실행
				continue;
			}
			
			// A나 F가 2개 이상이 아닐 때
			else {
				// 나머지 점수의 개수를 구함
				int count_b = (int)str.chars().filter(c -> c == 'B').count();
				int count_c = (int)str.chars().filter(c -> c == 'C').count();
				int count_d = (int)str.chars().filter(c -> c == 'D').count();
				int count_e = (int)str.chars().filter(c -> c == 'E').count();
				
				// str 포함된 문자 중에서 가장 큰 점수를 max로 지정
				int max = count_a > 0 ? 5: 
						  count_b > 0 ? 4: 
						  count_c > 0 ? 3: 
						  count_d > 0 ? 2: 
						  count_e > 0 ? 1: 0;
						  
				// str 포함된 문자 중에서 가장 작은 점수를 min으로 지정
				int min = count_f > 0 ? 0: 
						  count_e > 0 ? 1:
						  count_d > 0 ? 2:
						  count_c > 0 ? 3: 
						  count_b > 0 ? 4: 5;
				
				// 최종 점수 = total - min - max / 최고점과 최소점을 준 심사위원을 제외한 심사위원 수
				int finalScore = ( (count_a * 5) 
							   + (count_b * 4)
							   + (count_c * 3)
							   + (count_d * 2)
							   + (count_e)
							   - (min + max) )
							   / (str.length() - 2);
				
				// 최종 점수가 3점 이상이면 합격
				if ( finalScore >= 3 ) {
					answer++;
				}
				
			}
			
		}
		
		
		return answer;
	}
	
}


