import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class RightRotation {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int k = in.nextInt()%n;
        int q = in.nextInt();
        int[] a = new int[n];
        if(a.length == 1){
            
        	System.out.println(a[0]);
            return;
        }
        if(a.length == 0){
            return;
        }
        for(int a_i=0; a_i < n; a_i++){
            a[a_i] = in.nextInt();
        }
        
        int reverse2[] = new int[k];
        int reverse1[] = new int[n-k];
        int count = reverse2.length -1;
        for(int i = n-k;i<n;i++){
        	if(i == 213){
        		System.out.println("am here");
        	}
            reverse2[count] = a[i];
            count = count - 1;
        }
        count = reverse1.length - 1;
        for(int i = 0;i<n-k;i++){
            reverse1[count] = a[i];
            count = count - 1;
        }
        int combArr[] = new int[n];
       
        for(int i = 0;i<reverse1.length;i++){
        	combArr[i] = reverse1[i];
        }
        for(int i = 0,j=n-k; i < reverse2.length; i++,j++){
        	combArr[j] = reverse2[i];
        }
        for(int i = 0,j = n-1;i<j;i++,j--){
                int temp = combArr[i];
                combArr[i] = combArr[j];
                combArr[j] = temp;
        }
        
        for(int a0 = 0; a0 < q; a0++){
            int m = in.nextInt();
            System.out.println(combArr[m]);
        }
    }
}
