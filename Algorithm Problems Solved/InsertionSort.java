import java.util.Scanner;

public class InsertionSort {
	public static void main(String args[]){
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int sortIt[] = new int[N];
		int no_of_operations = 0;
		for(int i=0;i<N;i++){
			sortIt[i] = sc.nextInt();
		}
		for(int i=1;i<N;i++){
			int temp = sortIt[i];
			for(int j=i-1;j>=0;j--){
				if(temp >= sortIt[j]){
					sortIt[j+1] = temp;
					break;
				}
				sortIt[j+1] = sortIt[j];
				if(j==0)
					sortIt[0] = temp;
				no_of_operations += 1;
			}
		}
		System.out.println(no_of_operations);
	}
}
