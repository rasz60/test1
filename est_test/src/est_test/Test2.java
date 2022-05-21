package est_test;

import java.util.ArrayList;

public class Test2 {
	
	// 가장 많은 완제품을 생산할 수 있는 부품 조합을 저장할 배열
	static int[] maxItems;
	
	public static void main(String[] args) {

		int[][] needs1 = { { 1, 0, 0 }, 
						  { 1, 1, 0 }, 
						  { 1, 1, 0 }, 
						  { 1, 0, 1 }, 
						  { 1, 1, 0 }, 
						  { 0, 1, 1 } };
		
		int r1 = 2;
		
		
		int[][] needs2 = { { 1, 0, 0, 1, 0, 1, 1 },
						   { 0, 0, 0, 1, 1, 1, 1 }, 
				  		   { 1, 1, 0, 1, 1, 0, 0 }, 
				  		   { 1, 0, 1, 0, 1, 1, 0 }, 
				  		   { 1, 1, 0, 0, 0, 1, 1 },
				  		   { 1, 1, 0, 1, 1, 0, 1 }, 
				  		   { 1, 1, 0, 0, 1, 0, 0 }, 
				  		   { 1, 0, 0, 0, 0, 1, 0 }, 
				  		   { 1, 1, 0, 0, 0, 1, 0 }, 
				  		   { 0, 1, 1, 0, 0, 1, 1 } };

		int r2 = 4;
		
		
		
		System.out.println("result 1 : " + solution(needs1, r1));
		System.out.println("result 1 - maxItems : " + intToString(maxItems));
		
		System.out.println("result 2 : " + solution(needs2, r2));
		System.out.println("result 2 - maxItems : " + intToString(maxItems));
		
	}
	
	public static int solution(int[][] needs, int r) {
        int answer = 0;
        // 부품 개수만큼의 길이를 갖는 배열로 선언
        maxItems = new int[needs[0].length];
        
        // 부품 조합의 모든 경우의 수를 담을 배열 선언
        ArrayList<ArrayList<Integer>> combines = new ArrayList<ArrayList<Integer>>();
        
        // r만큼의 개수만 담을 배열 선언 (getCombine 메서드에서 사용)
		int[] now = new int[r];
		
		// 전체 부품을 r개의 조합으로 만들 수 있는 모든 경우의 수를 구하는 메서드
		getCombine(combines, now, needs[0].length, r, 0, 0, 0);
		
		// 모든 경우의 수에 주어진 배열을 비교하여 가장 많은 완제품을 만들 수 있는 조합과 개수를 구함
		for ( int j = 0; j < combines.size(); j++ ) {
			int count = 0;
			
			// 하나하나의 부품 조합을 담을 배열
			int[] arr = new int[needs[0].length];
			
			for ( int k = 0; k < combines.get(j).size(); k++ ) {
				// arr에 각각의 부품 조합을 대입
				arr[k] = combines.get(j).get(k);
				
			}
			
			// 완제품을 만드는 데 필요한 부품 조합을 arr 부품 조합으로 생산 가능한지 확인
			for ( int i = 0; i < needs.length; i++ ) {
	            
	        	// 필요한 부품의 개수를 확인
	        	int itemCount = 0;
	        	
	        	for ( int tmp = 0; tmp < needs[i].length; tmp++ ) {
	        		if ( needs[i][tmp] == 1 ) {
	        			itemCount++;
	        		}
	        	}
	        	
	        	// 필요한 부품의 개수가 r보다 작거나 같고, arr부품 조합으로 생산이 가능할 때 count++
	        	if ( itemCount <= r && differ(arr, needs[i]) ) {
	        		count++;
	        	}
	        	
	        }
			
			// count가 최대값이거나 answer가 초기 값일 때, count 값 대입
			if ( count > answer ) {
				answer = count;
				// 추가 : 가장 많은 완제품을 만드는 부품 조합을 저장
				for ( int p = 0; p < arr.length; p++ ) {
					maxItems[p] = arr[p];
				}
				
			} 
			
			
		}

        return answer;
    }
	
	// [공부 필요..] 모든 부품 조합을 구하는 메서드
	public static void getCombine(ArrayList<ArrayList<Integer>> result, int[] now, int total, int r, int depth, int index, int target) {
		
		if ( depth == r ) {
			ArrayList<Integer> tmp = new ArrayList<Integer>();
			
			for ( int i = 0; i < total; i++ ) {
				tmp.add(0);
			}
			
			for ( int i = 0; i < now.length; i++ ) {
				tmp.set(now[i], 1);
			}
			
			result.add(tmp);
			return;
		}
		
		if (target == total) {
			return; 
		}
		
		now[index] = target;
		
		// depth가 r과 같아 질때 까지 재귀호출.....
		getCombine(result, now, total, r, depth + 1, index + 1, target + 1);
		getCombine(result, now, total, r, depth, index, target + 1);
		
	}
	
	// 생성이 가능한지 확인하는 메서드
	public static boolean differ(int[] items1, int[] items2) {
		boolean chk = true;
		
		// items1[i] : 0 or 1, items2[i] : 0 or 1
		// 두 값의 차이가 -1 : items1이 items2를 만들 수 없는 부품 조합
		for ( int i = 0; i < items1.length; i++ ) {
			
			int result = items1[i] - items2[i];
			
			if ( result == -1 ) {
				
				chk = false;
				break;
				
			}
			
		}
		
		return chk;
	}
	
	public static String intToString(int[] items) {
		String result = "";
		
		for ( int i : items ) {
			result += i;
		}
		
		return result;
	}
	
}
