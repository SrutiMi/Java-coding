/**
 * QUESTION: Shortest Job First (SJF) - Average Waiting Time
 * * LOGIC:
 * 1. SORT: Since all jobs arrive at T=0, we sort them by Burst Time (bt) 
 * to serve the shortest jobs first. This minimizes the total wait time.
 * 2. RUNNING SUM: We keep a 'currentTime' clock. For each job, its 
 * wait time is the current time on the clock.
 * 3. TOTAL: We add each wait time to a 'totalWaitTime' sum.
 * * COMPLEXITY:
 * - Time: O(N log N) for sorting the burst times.
 * - Space: O(1) if we sort the input array in place.
 */

import java.util.Arrays;

public class SJFScheduling {
    public int solve(int[] bt) {
        int n = bt.length;

        // 1. Sort burst times to follow SJF policy
        Arrays.sort(bt);

        // 2. Use 'long' for the sum to prevent overflow (very important!)
        long totalWaitTime = 0;
        long currentTime = 0;

        // 3. Calculate waiting time for each process
        for (int i = 0; i < n; i++) {
            // The wait time for the current process is the time 
            // the previous processes took to finish.
            totalWaitTime += currentTime;

            // Now, update the clock by adding the current process's burst time
            currentTime += bt[i];
        }

        // 4. Return average (Java integer division automatically "floors" the result)
        // We cast to int at the very end to match the required return type.
        return (int) (totalWaitTime / n);
    }

    public static void main(String[] args) {
        SJFScheduling scheduler = new SJFScheduling();
        int[] burstTimes = {4, 1, 3, 9, 2};
        
        System.out.println("Average Waiting Time: " + scheduler.solve(burstTimes));
    }
}