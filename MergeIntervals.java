package leetcode;

import java.util.ArrayList;
import java.util.List;

import basicClass.Interval;

/*
 * Given a collection of intervals, merge all overlapping intervals.

For example,
Given [1,3],[2,6],[8,10],[15,18],
return [1,6],[8,10],[15,18].
 * 
 * */
public class MergeIntervals {
	public static ArrayList<Interval> mergeIntervals(ArrayList<Interval> list) {
		if (list.size() == 0)
			return null;
		
		if (list.size() == 1) {
			return list;
		}
		
		Interval lastInterval = list.get(0);
		ArrayList<Interval> mergedList = new ArrayList<Interval>();
		for (int i = 1; i < list.size(); i++) {
			Interval currentInterval = list.get(i);
			if (lastInterval.end >= currentInterval.start) {
				// merge
				currentInterval.start = lastInterval.start;
				currentInterval.end = currentInterval.end > lastInterval.end ? currentInterval.end : lastInterval.end;
				lastInterval = currentInterval;
			} else {
				if (!mergedList.contains(lastInterval)) {
					mergedList.add(lastInterval);
				}
			}
			
			mergedList.add(currentInterval);
		}
		
		return mergedList;
	}
	
	public static void main(String[] args) {
		Interval int1 = new Interval(1,3);
		Interval int2 = new Interval(2,6);
		Interval int3 = new Interval(8,10);
		Interval int4 = new Interval(15,18);
		List<Interval> list = new ArrayList<Interval>();
		list.add(int1);
		list.add(int2);
		list.add(int3);
		list.add(int4);
		
		InsertInterval.printIntervals(list);
		System.out.println();
		//list = mergeIntervals(list);
		InsertInterval.printIntervals(list);
	}
}
