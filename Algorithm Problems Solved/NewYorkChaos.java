import java.util.*;

public class NewYorkChaos {
    public static void swap(int a[],int x, int y){
        int temp = a[x];
            a[x] = a[y];
            a[y] = temp;
        return;
    }
    
    public static boolean isSorted(int a[]){
        for(int i =1;i<a.length;i++){
            if(a[i-1]>a[i])
                return false;
        }
        return true;
    }
    public static boolean tooChaoticCheck(int a[]){
        for(int i = 0; i < a.length; i++){
            if((a[i]-i) > 2){
                System.out.println("Too chaotic");
                return true;
            }
                     }
        return false;
        
    }
    public static void main(String[] args) {
        @SuppressWarnings("resource")
		Scanner in = new Scanner(System.in);
        int T = in.nextInt();
        for(int a0 = 0; a0 < T; a0++){
            int n = in.nextInt();
            int q[] = new int[n];
            for(int q_i=0; q_i < n; q_i++){
                q[q_i] = in.nextInt()-1;
            }
            // your code goes here

                 int min_swaps = 0;
            //1st iteration . travel 1 hop closer to the destination
            //2nd iteration . perform the same operation a heuristic that keeps a track of how far each number is from it's desired location should do the trick !!!
            // should not be that complex..yup should work
            if (!tooChaoticCheck(q)){
                 while(!isSorted(q)){    
            for(int i = 1;i < n; i ++){
                    if(q[i] < q[i-1]){
                        swap(q,i,i-1);
                        min_swaps++;
                    }                    
                }              
            }
                System.out.println(min_swaps);             
            }
        }
        }
    }

