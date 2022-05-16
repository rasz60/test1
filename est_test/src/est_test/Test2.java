package est_test;

import java.util.Arrays;
import java.util.Collections;

public class Test2 {
	public static void main(String[] args) {
		int[][] needs = { { 1, 0, 0 }, {1, 1, 0}, {1, 1, 0}, {1, 0, 1}, {1, 1, 0}, {0, 1, 1} };
		int r = 2;
		
		System.out.println(solution(needs, r));
	}
	
	public static int solution(int[][] needs, int r) {
        int answer = 0;
        int[] count = new int[15];

        // 완제품을 만드는데 많이 쓰이는 부품 번호 구하기
        for ( int i = 0; i < needs.length; i++ ) {
            // 완제품(각 행)을 만드는데 필요한 부품의 개수
            int item = Collections.frequency(Arrays.asList(needs[i]), 1);

            // 부품의 개수가 로봇의 개수보다 작을 때만 부품을 카운트
            if ( item <= r) {
                for ( int j = 0; j < needs[i].length; j++ ) {
                    // 필요한 부품일 경우 해당하는 인덱스 카운트++
                    if ( needs[i][j] == 1 ) {
                        count[j]++;
                    }
                }
            }
        }

        // 가장 많이 필요한 부품 번호를 r 개수만큼 구하기
        for( int i = 0; i < count.length; i++ ) {
            System.out.println(count[i]);
        }

        return answer;
    }
}
