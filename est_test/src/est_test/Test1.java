package est_test;

public class Test1 {
	
	public static void main(String[] args) {
	
		String[] scores1 = {"AAFAFA", "FEECAA", "FABBCB", "CBEDEE", "CCCCCC"};
		String[] scores2 = {"BCD", "ABB", "FEE"};
		
		System.out.println(solution(scores1));
		System.out.println(solution(scores2));
	}
	
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
            
            // 점수 하나하나를 카운트하여 배열 add
            for ( int j = 0; j < score.length; j++ ) {
                char scoreCheck = score[j];

                switch(scoreCheck) {
                    case 'A':
                        count[0]++;
                        // A가 있으면 max = 5
                        max = 5;

                        // 최소값이 초기 설정 값 그대로 일 때 min = 5
                        if ( min < 0 ) {
                            min = 5;
                        }
                        break;
                    case 'B':
                        count[1]++;
                        // max가 4보다 작을 때 max = 4
                        if (max < 4) {
                            max = 4;
                        }

                        // 최소값이 초기 설정 값 그대로거나 4보다 클 때 min = 4
                        if (min < 0 || min > 4) {
                            min = 4;
                        }
                        break;
                    case 'C':
                        count[2]++;
                        // max가 3보다 작을 때 max = 3
                        if (max < 3) {
                            max = 3;
                        }
                        // 최소값이 초기 설정 값 그대로거나 3보다 클 때 min = 3
                        if (min < 0 || min > 3) {
                            min = 3;
                        }
                        break;
                    case 'D':
                        count[3]++;
                        // max가 2보다 작을 때 max = 2
                        if (max < 2) {
                            max = 2;
                        }

                        // 최소값이 초기 설정 값 그대로거나 2보다 클 때 min = 2
                        if (min < 0 || min > 2) {
                            min = 2;
                        }
                        break;
                    case 'E':
                        count[4]++;
                        // max가 1보다 작을 때 max = 1
                        if (max < 1) {
                            max = 1;
                        }

                        // 최소값이 초기 설정 값 그대로거나 1보다 클 때 min = 1
                        if (min < 0 || min > 1) {
                            min = 1;
                        }
                        break;
                    case 'F':
                        count[5]++;
                        // max가 초기 설정 값 그대로일 때 max = 0
                        if (max < 0) {
                            max = 0;
                        }

                        // F가 있으면 min = 0
                        min = 0;
                        break;
                }
            }
            // F가 2개보다 작고, A가 2개 이상일때는 무조건 합격
            if ( count[5] < 2 && count[0] >= 2 ) {
                answer++;

            // F가 2개보다 작을 때, 점수 확인
            } else if ( count[5] < 2 ) {
                
                // finalScore = 전체 점수 합에서 min+max값을 뺀 평균 값
                int finalScore = ((count[0] * 5) + 
                                (count[1] * 4) + 
                                (count[2] * 3) + 
                                (count[3] * 2) + 
                                (count[4] * 1) -
                                (min + max)) / (score.length - 2);

                // finalScore가 3점 이상일때 합격
                if ( finalScore >= 3) {
                    answer++;
                }
            }
        }
        return answer;
    }

}


