import java.util.Scanner;

public class Merge2SortedArrays {
	public static int[] merge2SortedArrays(int a[],int b[]){
		int pointer1 = 0;
		int pointer2 = 0;
		if(a == null)
			return b;
		if(b == null)
			return a;
		int c[] = new int[a.length + b.length];
		int counter = 0;
		while(pointer1 < a.length && pointer2 < b.length){
			if(a[pointer1]  < b[pointer2]){
				c[counter++] = a[pointer1++];
				continue;
			}
			c[counter++] = b[pointer2++];
		}
		while(pointer1 < a.length){
			c[counter++] = a[pointer1++];
		}
		while(pointer2 < b.length){
			c[counter++] = b[pointer2++];
		}
		return c;
	}
	public static void main(String args[]){
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int M = sc.nextInt();
		int a[] = new int[N];
		int b[] = new int[M];
		for(int i=0;i<N;i++)
			a[i] = sc.nextInt();
		for(int i=0;i<M;i++)
			b[i] = sc.nextInt();
		int c[] = merge2SortedArrays(a,b);
		for(int eachInt : c){
			System.out.print(eachInt +" ");
		}
	}
}
