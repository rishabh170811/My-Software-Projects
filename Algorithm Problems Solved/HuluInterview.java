import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Queue;
import java.util.Stack;
import java.util.LinkedList;
class Employee{
	String name;
	String boss;
	String year;
	String position;
	int level = 0;
	public Employee(String name,String boss, String position,String year){
		this.name = name;
		this.boss = boss;
		this.year = year;
		this.position = position;
	}
}

public class HuluInterview {
	public static void main(String args[]) throws IOException{
		String input = "/Users/rishabhsharma/Downloads/AllCodingQuestionsWalkThrough/src/huluInput.txt";
		String output = "/Users/rishabhsharma/Downloads/AllCodingQuestionsWalkThrough/src/huluOutput.txt";
		FileReader fr = new FileReader(input);
		FileWriter fw = new FileWriter(output);
		BufferedReader br = new BufferedReader(fr);
		String line = br.readLine();
		int count = 1;
		HashMap<String,ArrayList<Employee>> mapEmployees;
		while((line = br.readLine()) != null){
			fw.write("Case #"+count+"\n");
			mapEmployees = new HashMap<String,ArrayList<Employee>>();
			String empTokens[] = line.split("--");
			//fw.write("Case #"+counter+"\n");

			for(String eachEmployee : empTokens){
				String empInfo[] = eachEmployee.split(",");
				mapEmployees.computeIfAbsent(empInfo[1], k -> new ArrayList<>()).add(new Employee(empInfo[0],empInfo[1],empInfo[2],empInfo[3]));
			}
		Stack<Employee> empGraph = new Stack<Employee>();
		empGraph.add(mapEmployees.get("NULL").get(0));
	
		while(!empGraph.isEmpty()){
			Employee tempEmp = empGraph.pop();
			
			String desh = "";
			for(int i = 0; i < tempEmp.level;i++){
				desh = desh + "-";
			}
			fw.write(desh +tempEmp.name+" ("+tempEmp.position+") "+tempEmp.year+"\n");
			if(mapEmployees.get(tempEmp.name) !=null){
				ArrayList<Employee> empList = (mapEmployees.get(tempEmp.name));
				Collections.sort(empList,new Comparator<Employee>(){

					@Override
					public int compare(Employee o1, Employee o2) {
						// TODO Auto-generated method stub
						return o2.name.compareTo(o1.name);
					}
					
				});
				for(Employee eachEmp : empList){
					eachEmp.level = tempEmp.level + 1;
					empGraph.push(eachEmp);
				}
			}
				
			
		}
		count = count + 1;
		}
		fw.close();
		br.close();
	}
}

