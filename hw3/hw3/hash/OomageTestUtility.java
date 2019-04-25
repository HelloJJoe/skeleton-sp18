package hw3.hash;

import java.util.HashSet;
import java.util.List;

public class OomageTestUtility {
    public static boolean haveNiceHashCodeSpread(List<Oomage> oomages, int M) {
        /* TODO:
         * Write a utility function that returns true if the given oomages
         * have hashCodes that would distribute them fairly evenly across
         * M buckets. To do this, convert each oomage's hashcode in the
         * same way as in the visualizer, i.e. (& 0x7FFFFFFF) % M.
         * and ensure that no bucket has fewer than N / 50
         * Oomages and no bucket has more than N / 2.5 Oomages.
         */
        int[] bucketNums = new int[M];
        for (Oomage oo : oomages) {
            int bucketNum = (oo.hashCode() & 0x7FFFFFFF) % M;
            bucketNums[bucketNum]++;
        }

        int min = oomages.size() / 50;
        int max = (int) (oomages.size() / 2.5);

        for (int num : bucketNums) {
            if (num < min || num > max) {
                return false;
            }
        }
        return true;
    }
}
