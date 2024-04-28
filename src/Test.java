
public class Test {
	
	public static void main(String[] args) {
		int[] a = new int[8];
		int i = 0;
		int n = 11; 
		printArray(a); 
		while(n > 0) {
			a[i++]=n%2;
			printArray(a); 
			n/=2;
			
			
		}
		
		for(i = 7; i >= 0; i--) {
			System.out.printf("%d",a[i]);
			
		}
	}

  private static void printArray(int[] array) {
        for (int value : array) {
            System.out.print(value + " ");
        }
        System.out.println(); // 줄 바꿈을 추가하여 출력을 보기 좋게 합니다.
    }

}
