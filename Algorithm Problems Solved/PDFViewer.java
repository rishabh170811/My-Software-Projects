import java.util.Scanner;
public class PDFViewer {

    public static void main(String[] args) {
        @SuppressWarnings("resource")
		Scanner in = new Scanner(System.in);
        int n = 26;
        int h[] = new int[n];
        for(int h_i=0; h_i < n; h_i++){
            h[h_i] = in.nextInt();
        }
        String word = in.next();
        int width = word.length();
        int maxHeight = 0;
        for(int i=0;i < word.length();i++){
            if(h[word.charAt(i) - 97] > maxHeight){
                maxHeight = h[word.charAt(i) - 97];
            }
        }
        System.out.println(maxHeight*width);
    }
}
