import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class LogParser {
    public static void main(String[] args)
            throws FileNotFoundException, IOException {
        String filename = "/Users/rishabhsharma/Downloads/AllCodingQuestionsWalkThrough/src/input_1.txt";
        if (args.length > 0) {
        	filename = args[0];
        }

        String answer = parseFile(filename);
        System.out.println(answer);
    }

    static String parseFile(String filename)
            throws FileNotFoundException, IOException {
        /*
    	 *  Don't modify this function
    	 */
        BufferedReader input = new BufferedReader(new FileReader(filename));
        List<String> allLines = new ArrayList<String>();
        String line;
        while ((line = input.readLine()) != null) {
            allLines.add(line);
        }
        input.close();

        return parseLines(allLines.toArray(new String[allLines.size()]));
    }
    
    static long getDateDiff(Date date1, Date date2, TimeUnit timeUnit) {
    long diffInMillies = date2.getTime() - date1.getTime();
    return timeUnit.convert(diffInMillies,TimeUnit.MINUTES);
    }
    
    static String parseLines(String[] lines) {
        /*
         *
         * Your code goes here
         *
         */
    	SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        long totalTimeConnected = 0L;
        Date start = null;
        Date connect = null;
        Date disconnect = null;
        Date shutdown = null;
        for(String line : lines){
            String tokens[] = line.split(" ");
            String token1[] = tokens[0].split("-");
            tokens[0] = token1[0] +  " " + token1[1];
            
            try {
            
            	tokens[0] = tokens[0].replaceAll("\\(", "");
                tokens[0] = tokens[0].replaceAll("\\)", "");
            	if(tokens[2].equals("START")){
            		start = simpleDateFormat.parse(tokens[0]);
            		continue;
            	}
            	if(tokens[2].equals("CONNECTED")){
            		connect = simpleDateFormat.parse(tokens[0]);
            		continue;
            	}
            	else if(tokens[2].equals("DISCONNECTED")){
            		disconnect = simpleDateFormat.parse(tokens[0]);
            		totalTimeConnected += getDateDiff(connect, disconnect,TimeUnit.MINUTES);
            		connect = null;
            		disconnect = null;
            	}
            	else if(tokens[2].equals("SHUTDOWN") && connect != null){
            		shutdown = simpleDateFormat.parse(tokens[0]);
            		totalTimeConnected += getDateDiff(connect, shutdown, TimeUnit.MINUTES);
            		break;
            	}
            	else{
            	shutdown = simpleDateFormat.parse(tokens[0]);
            	break;
            	}
            } catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        long den = 0L;
        double ans;
        den = getDateDiff(start, shutdown, TimeUnit.MINUTES);
        ans = ((double) totalTimeConnected/den);
        return String.valueOf(ans);
    }
}
