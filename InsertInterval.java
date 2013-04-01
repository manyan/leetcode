package leetcode;

import java.util.ArrayList;
import java.util.List;

import basicClass.Interval;


/*
 * Given a set of non-overlapping intervals, insert a new interval into the intervals (merge if necessary).

You may assume that the intervals were initially sorted according to their start times.

Example 1:
Given intervals [1,3],[6,9], insert and merge [2,5] in as [1,5],[6,9].

Example 2:
Given [1,2],[3,5],[6,7],[8,10],[12,16], insert and merge [4,9] in as [1,2],[3,10],[12,16].

This is because the new interval [4,9] overlaps with [3,5],[6,7],[8,10].
 * */

/*
 * idea: we have 4 situations to handle
 *     --- ---     --- (existing intervals)
 * ---   ---   ---     --- (4 situations)
 * 
 * first situation: the new interval is smaller than any of the existing intervals (we do not need to merge intervals, just need to add a new one infront of existing intervals)
 * second situation: the new interval is overlapped some (might more than 1) intervals (we need to merge intervals in this case)
 * third situation: the new interval is in between of 2 existing intervals (need to add new intervals in between the 2 existing ones)
 * forth situatation: the new intervals is greater than any of the existing intervals, (need to add it to the end of the existing ones)
 * 
 * so the sulution is that
 * 1) scan the whole existing intervals, divide them into 2 lists, one list is holding the existing intervals that has nothing to do with the new interval (no overlap)
 *    the other is holding the overlapped existing intervals
 * 2) merge new interval and overlapped existing intervals
 * 3) add the 2 lists back to one
 * 
 * */
public class InsertInterval {	
	/*
	 * Assume that the given list is alraedy sorted
	 * */
	public static List<Interval> insertInterval(List<Interval> list, Interval interval) {
		List<Interval> nonOverLappedIntervals = new ArrayList<Interval>();
		List<Interval> overlappedIntervals = new ArrayList<Interval>();
		
		int mergePoint = -1;
		for (int i = 0; i < list.size(); i++) {
			Interval exitingInterval = list.get(i);
			
			if (exitingInterval.end < interval.start) {
				// case 4,3
				nonOverLappedIntervals.add(exitingInterval);
			} else if (exitingInterval.start > interval.end ) {
				// case 1
				nonOverLappedIntervals.add(exitingInterval);
			} else if (exitingInterval.end > interval.start) {
				// case 2
				if (mergePoint == -1) {
					// only mark the first overlapped element
					mergePoint = i;
				}
				overlappedIntervals.add(exitingInterval);
			}
		}
		
		if (overlappedIntervals.size() > 0) {
			interval.start = overlappedIntervals.get(0).start > interval.start ? interval.start : overlappedIntervals.get(0).start;
			interval.end = overlappedIntervals.get(overlappedIntervals.size()-1).end > interval.end ? overlappedIntervals.get(overlappedIntervals.size()-1).end : interval.end;
		}
		
		nonOverLappedIntervals.add(mergePoint, interval);
		
		return nonOverLappedIntervals;
	}
	
	public static void printIntervals(List<Interval> list) {
		for (int i = 0; i < list.size(); i++) {
			Interval interval = list.get(i);
			System.out.print("[" + interval.start + " - " + interval.end + "]   ");
		}
	}
	
	public static void main(String[] args) {
		Interval int1 = new Interval(1,3);
		Interval int2 = new Interval(5,7);
		Interval int3 = new Interval(9,10);
		Interval int4 = new Interval(13,15);
		List<Interval> list = new ArrayList<Interval>();
		list.add(int1);
		list.add(int2);
		list.add(int3);
		list.add(int4);
		
		printIntervals(list);
		System.out.println();
		
		list = insertInterval(list, new Interval(6,12));
		printIntervals(list);
	}
}
