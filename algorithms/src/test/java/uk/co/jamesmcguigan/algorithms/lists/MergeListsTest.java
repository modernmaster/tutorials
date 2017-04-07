package uk.co.jamesmcguigan.algorithms.lists;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;

public class MergeListsTest {
    @Test
    @Ignore
    public void MergeTwoSortedListsTest(){
        int[] m = {1,5,7,9};
        int[] n = {2,4,6,8,10,12,14};
        MergeLists mergeList = new MergeLists();
        mergeList.mergeTwoSortedLists(m,m.length,n,n.length);
    }

    @Test
    public void MaxProfitTest() {
        int[] maxProfit = {5, 1, 2, 3, 4};
        MergeLists mergeList = new MergeLists();
        int result = mergeList.maxProfit(maxProfit);
        Assert.assertThat(result,equalTo(3));
    }

    @Test
    public void MaxProfit2Test() {
        int[] maxProfit = {5, 1, 2, 3, 4};
        MergeLists mergeList = new MergeLists();
        int result = mergeList.maxProfit2(maxProfit);
        Assert.assertThat(result,equalTo(3));
    }
}
