import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class IntervalSchedulingMaximumJobs {
	static class JobScheduler{
		int s;
		int f;
	public JobScheduler(int s,int f){
		this.s = s;
		this.f = f;
	}
	
	public static int maxNumberOfJobs(ArrayList<JobScheduler> checkJob){
		Collections.sort(checkJob,new Comparator<JobScheduler>() {

			@Override
			public int compare(JobScheduler o1, JobScheduler o2) {
				return o1.f - o2.f;
			}
		});
		int maxJobs = 0;
		int tempFinish = Integer.MIN_VALUE;
		for(JobScheduler eachJob : checkJob){
			if(tempFinish < eachJob.s){
				tempFinish = eachJob.f;
				maxJobs = maxJobs + 1;
			}
		}
		return maxJobs;
	}
	}
	public static void main(String args[]){
		Scanner sc = new Scanner(System.in);
		ArrayList<JobScheduler> checkJob = new ArrayList<JobScheduler>();
		int N = sc.nextInt();
		for(int i=0;i<N;i++){
			checkJob.add(new JobScheduler(sc.nextInt(), sc.nextInt()));
		}
		System.out.println(JobScheduler.maxNumberOfJobs(checkJob));
	}
}
