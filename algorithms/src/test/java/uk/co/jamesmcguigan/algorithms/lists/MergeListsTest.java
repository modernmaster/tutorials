package Lists;

import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;

/**
 * Created by James McGuigan on 13/09/2015.
 */
public class MergeListsTest {
    @Test
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
