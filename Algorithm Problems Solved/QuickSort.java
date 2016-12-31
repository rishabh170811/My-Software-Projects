import java.util.*;
public class QuickSort {
       // 0 len-1
  public static int[] quickSort(int[] ar,int start,int last) {
                    if(last-start == 0){
                    	int temp_Left[] = new int[1];
                    	temp_Left[0] = ar[start];
                    	return temp_Left;
                    }
                    else if(last-start < 0)
                    	return null;
                    else{
                        int pivotArray[] = pivotPartition(ar,start,last);
                        int pivot_position = findStart(pivotArray,ar[start]);
                        int Left[] = quickSort(pivotArray,0,pivot_position-1);
                        int Right[]= quickSort(pivotArray,pivot_position+1,pivotArray.length-1);
                        return merge(Left,Right,pivotArray[pivot_position]);
                    }
       }
  public static void display(int arr[]){
	  for(int each : arr){
		  System.out.print(each+" ");
	  }
	  System.out.println();
	  return;
  }
  public static int[] merge(int Left[],int Right[],int pivot){
	  if(Right == null){
		  int combinationArr[] = new int[Left.length+1];
		  for(int i=0;i<Left.length;i++){
          	combinationArr[i] = Left[i];
          }
		  combinationArr[Left.length] = pivot;
		  display(combinationArr);
		  return combinationArr;
	  }
	  if(Left == null){
		  int combinationArr[] = new int[Right.length+1];
		  combinationArr[0] = pivot;
		  for(int i=1;i<=Right.length;i++){
          	combinationArr[i] = Right[i-1];
          }
		  display(combinationArr);
		  return combinationArr;
	  }
	  int combinationArr[] = new int[Left.length+1+Right.length];
	  for(int i=0;i<Left.length;i++){
      	combinationArr[i] = Left[i];
      }
	  combinationArr[Left.length] = pivot;
	  for(int i=0;i<Right.length;i++){
	      	combinationArr[i+Left.length+1] = Right[i];
	      }
	  display(combinationArr);
	  return combinationArr;
  }
  
  public static int findStart(int ar[], int pivot){
	  int index = ar.length/2;
	  for(int i = 0; i < ar.length ; i++){
		  if(pivot == ar[i])
			  return i;
	  }
	  return index;
  }
  public static int[] pivotPartition(int ar[],int start,int last){
            int pivotValue = ar[start];
            ArrayList<Integer> Left = new ArrayList<>();
            ArrayList<Integer> Equal = new ArrayList<>();
            ArrayList<Integer> Right = new ArrayList<>();
            for(int i=start;i<=last;i++){
            	if(pivotValue == ar[i])
            		Equal.add(ar[i]);
            	else if(pivotValue > ar[i])
            		Left.add(ar[i]);
            	else
            		Right.add(ar[i]);		
            }
            int finalArr[] = new int[Equal.size()+Left.size()+Right.size()];
            
            for(int i=0;i<Left.size();i++){
            	finalArr[i] = Left.get(i);
            }
            for(int i=0;i<Equal.size();i++){
            	finalArr[Left.size() + i] = Equal.get(i);
            }
            for(int i=0;i<Right.size();i++){
            	finalArr[Left.size()+Equal.size()+i] = Right.get(i);
            }
            return finalArr;
        }
 static void printArray(int[] ar) {
         for(int n: ar){
            System.out.print(n+" ");
         }
           System.out.println("");
      }
       
      public static void main(String[] args) {
           Scanner in = new Scanner(System.in);
           int n = in.nextInt();
           int[] ar = new int[n];
           for(int i=0;i<n;i++){
              ar[i]=in.nextInt(); 
           }
           quickSort(ar,0,ar.length-1);
       }    
   }

