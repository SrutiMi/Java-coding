import java.util.*;

// Class to represent a Job with id, deadline, and profit
class Job {
  int id, deadline, profit;

  Job(int id, int deadline, int profit) {
    this.id = id;
    this.deadline = deadline;
    this.profit = profit;
  }
}

public class JobSequencing {

  // Function to find the maximum profit and the number of jobs done
  public int[] JobScheduling(Job arr[], int n) {

    // 1. Sort jobs in descending order of profit
    // We use a custom comparator: if profit is higher, job comes first
    Arrays.sort(arr, (a, b) -> b.profit - a.profit);

    // 2. Find the maximum deadline among all jobs
    // This tells us how many total time slots we have available
    int maxDeadline = 0;
    for (int i = 0; i < n; i++) {
      if (arr[i].deadline > maxDeadline) {
        maxDeadline = arr[i].deadline;
      }
    }

    // 3. Create a 'calendar' array to track filled time slots
    // result[i] will store the job ID scheduled at time i
    // We size it maxDeadline + 1 to use 1-based indexing for time
    int[] result = new int[maxDeadline + 1];

    // Initialize all slots as -1 (empty)
    Arrays.fill(result, -1);

    int countJobs = 0; // To keep track of total jobs scheduled
    int totalProfit = 0; // To keep track of the sum of profits

    // 4. Iterate through the sorted jobs (starting with highest profit)
    for (int i = 0; i < n; i++) {

      // Try to schedule the job at its latest possible slot (its deadline)
      // If that's full, we move backwards (j--) to find the nearest free slot
      for (int j = arr[i].deadline; j > 0; j--) {

        // If a slot is found (it is still -1)
        if (result[j] == -1) {
          result[j] = arr[i].id; // Occupy the slot with this Job's ID
          countJobs++; // Increment the count of completed jobs
          totalProfit += arr[i].profit; // Add the profit to our total
          break; // Move to the next job in the sorted list
        }
      }
    }

    // Return the number of jobs and total profit as an array
    return new int[] { countJobs, totalProfit };
  }

  public static void main(String[] args) {
    JobSequencing solver = new JobSequencing();

    // Example Input: (id, deadline, profit)
    Job[] jobs = {
        new Job(1, 4, 20),
        new Job(2, 1, 10),
        new Job(3, 1, 40),
        new Job(4, 1, 30)
    };

    int[] finalResult = solver.JobScheduling(jobs, jobs.length);

    System.out.println("Total Jobs Done: " + finalResult[0]);
    System.out.println("Maximum Profit Earned: " + finalResult[1]);
  }
}