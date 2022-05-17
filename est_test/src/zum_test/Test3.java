package zum_test;

public class Test3 {

	public static void main(String[] args) {
		int[] histogram1 = {2, 2, 2, 3};
		int[] histogram2 = {6, 5, 7, 3, 4, 2};
		int[] histogram3 = {2, 2, 2, 3, 6, 5, 7, 3, 4, 2};
		
		System.out.println( "result 1 : " + solution(histogram1) );
		System.out.println( "result 2 : " + solution(histogram2) );
		System.out.println( "result 3 : " + solution(histogram3) );
		
	}

	public static int solution(int[] histogram) {
		int answer = 0;
		
		// 높이가 담긴 배열의 길이 만큼 반복
		for ( int i = 0; i < histogram.length; i++ ) {
			
			// 시작지점의 높이
			int start = histogram[i];
			
			// 시작지점에서 거리가 1이상인 index부터 반복
			for ( int j = i + 2; j < histogram.length; j++ ) {
				
				// 종료지점의 높이
				int end = histogram[j];
				
				// 시작지점과 종료지점의 높이 중 낮은 값을 최종 높이로 지정
				int height = start < end ? start : end ;

				// 시작 지점에서 종료지점 사이의 거리를 너비로 지정
				int width = j - i - 1;
				
				// 가장 큰 넓이 값이 없거나, 지정되어 있는 값보다 해당 넓이가 클 경우
				if ( answer == 0 || answer < width * height ) {
					
					// 해당 넓이 값을 가장 큰 넓이 값으로 지정
					answer = width * height;
					
				}
				
			}
			
		}
		
		return answer;
	}
}
