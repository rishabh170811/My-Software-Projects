import java.io.*;
import java.util.*;

public class MxPerimeterTriangle {
    public static class Triangle{
        long side1;
        long side2;
        long side3;
        long perimeter = -1;
        public Triangle(long side1,long side2,long side3){
            this.side1 = side1;
            this.side2 = side2;
            this.side3 = side3;
            this.perimeter = this.side1 + this.side2 + this.side3;
        }
    }
    public static boolean checkIsTriangle(long a,long b,long c){
        if(a+b > c && b+c > a && a+c > b)
            return true;
        return false;
    }
    
    public static long smallest(long a,long b,long c){
        return a>b?(b>c?c:b):(a>c?c:a);
    }
    
    public static long smallest(Triangle maxPerimeter){
        return maxPerimeter.side1>maxPerimeter.side2?(maxPerimeter.side2>maxPerimeter.side3?maxPerimeter.side3:maxPerimeter.side2):(maxPerimeter.side1>maxPerimeter.side3?maxPerimeter.side3:maxPerimeter.side1);
    }
    
    public static long largest(long a,long b,long c){
        return a>b?(a>c?a:c):(b>c?b:c);
    }
    
    public static long largest(Triangle maxPerimeter){
            return maxPerimeter.side1>maxPerimeter.side2?(maxPerimeter.side1>maxPerimeter.side3?maxPerimeter.side1:maxPerimeter.side3):(maxPerimeter.side2>maxPerimeter.side3?maxPerimeter.side2:maxPerimeter.side3);
    }
    
    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner sc = new Scanner(System.in);
        int size = sc.nextInt();
        long sticks[] = new long[size];
        for(int i=0;i<size;i++){
            sticks[i] = sc.nextLong();
        }
        if(sticks.length < 3){
            System.out.println(-1);
            return;
        }
        Triangle maxPerimeterTriangle = new Triangle(0,0,0);  
        for(int i=0;i<size-2;i++){
            for(int j=i+1;j<size-1;j++){
                for(int k=j+1;k<size;k++){
                    if(checkIsTriangle(sticks[i],sticks[j],sticks[k])){
                        if(maxPerimeterTriangle.perimeter < sticks[i]+sticks[j]+sticks[k]){
                            maxPerimeterTriangle = new Triangle(sticks[i],sticks[j],sticks[k]);
                        }
                        else if(maxPerimeterTriangle.perimeter == sticks[i]+sticks[j]+sticks[k]){
                            if(largest(sticks[i],sticks[j],sticks[k]) > largest(maxPerimeterTriangle)){
                                 maxPerimeterTriangle = new Triangle(sticks[i],sticks[j],sticks[k]);
                            }
                            else if(largest(sticks[i],sticks[j],sticks[k]) == largest(maxPerimeterTriangle)){
                                if(smallest(sticks[i],sticks[j],sticks[k]) > smallest(maxPerimeterTriangle)){
                                    maxPerimeterTriangle = new Triangle(sticks[i],sticks[j],sticks[k]);
                                }
                            }
                        }
                    }
                }
            }
        }
        if(maxPerimeterTriangle.perimeter == 0){
            System.out.println(-1);    
            return;
        }
            long middle = maxPerimeterTriangle.perimeter - (smallest(maxPerimeterTriangle) + largest(maxPerimeterTriangle));
            System.out.println(smallest(maxPerimeterTriangle) +" "+middle+" "+largest(maxPerimeterTriangle)) ;
        return;
    }
}