package 线程池;

import java.util.concurrent.Callable;

public class FindMaxTask implements Callable<Integer> {
    private int[] mData;
    private int start;
    private int end;

    public FindMaxTask(int[] mData, int start, int end) {
        this.mData = mData;
        this.start = start;
        this.end = end;
    }


    @Override
    public Integer call() throws Exception {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < mData.length; i++) {
            if (mData[i] > max) max = mData[i];
        }

        return max;
    }


}
