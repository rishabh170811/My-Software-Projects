import java.util.Scanner;

public class MaximizeXOR {
/*
 * Complete the function below.
 */

    static int maxXor(int l, int r) {
        int maxValue = 0;
        for(int i=l;i<r;i++){
            for(int j =i+1;j<=r;j++){
                if((j^i)>maxValue)
                    maxValue = j^i;
            }
        }
        return maxValue;

    }

    public static void main(String[] args) {
        @SuppressWarnings("resource")
		Scanner in = new Scanner(System.in);
        int res;
        int _l;
        _l = Integer.parseInt(in.nextLine());
        
        int _r;
        _r = Integer.parseInt(in.nextLine());
        
        res = maxXor(_l, _r);
        System.out.println(res);
        
    }
}

