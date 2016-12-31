import java.util.HashMap;
import java.util.Scanner;

public class MorganAndString {
	public static void main(String args[]){
		Scanner sc = new Scanner(System.in);
		int T = Integer.parseInt(sc.nextLine());
		for(int i=0;i<T;i++){
			StringBuilder result = new StringBuilder();
			String jack = sc.nextLine();
			String daniel = sc.nextLine();
			int pointer1 = 0,pointer2 = 0;
			while(pointer1 < jack.length() && pointer2 < daniel.length()){
				if(jack.charAt(pointer1) == daniel.charAt(pointer2)){
					int pointerX = pointer1;
					int pointerY = pointer2;
					char temp = jack.charAt(pointer1);
					while(pointerX < jack.length() && pointerY < daniel.length()){
						if(jack.charAt(pointerX) == daniel.charAt(pointerY)){
							if(jack.charAt(pointerX) > temp){
								result.append(jack.substring(pointer1,pointerX)).append(daniel.substring(pointer2, pointerY));
								pointer1 = pointerX;
								pointer2 = pointerY;
							//	temp = jack.charAt(pointer1);
							}
							pointerX++;pointerY++;
							continue;
						}
						break;
					}
					if(pointerX == jack.length()){
						result.append(String.valueOf(daniel.charAt(pointer2++)));
					}
					else if(pointerY == daniel.length()){
						result.append(String.valueOf(jack.charAt(pointer1++)));
					}
					else{
						if(jack.charAt(pointerX) < daniel.charAt(pointerY)){
							result.append(String.valueOf(jack.charAt(pointer1++)));
						}
						else{
							result.append(String.valueOf(daniel.charAt(pointer2++)));
						}
					}
						
				}
				else if(jack.charAt(pointer1) < daniel.charAt(pointer2)){
					result.append(String.valueOf(jack.charAt(pointer1++)));
				}
				else{
					result.append(String.valueOf(daniel.charAt(pointer2++)));
				}
			}
			while(pointer1 < jack.length()){
				result.append(String.valueOf(jack.charAt(pointer1++)));
			}
			while(pointer2 < daniel.length()){
				result.append(String.valueOf(daniel.charAt(pointer2++)));			
			}
			System.out.println(result);
		}
	}
}