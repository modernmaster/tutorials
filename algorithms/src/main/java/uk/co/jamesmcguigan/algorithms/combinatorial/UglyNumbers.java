package Combinatorial;

import java.util.ArrayList;
import java.util.List;

public class UglyNumbers {

    public boolean isUgly(int num) {
        int div = 2*3*5;
        while (num > 0 && div > 1) {
            if (num%div==0) num /= div;
            else if (div%2==0 && num%2!=0) div/=2;
            else if (div%3==0 && num%3!=0) div/=3;
            else if (div%5==0 && num%5!=0) div/=5;
        }
        return num == 1;
    }

    public int nthUglyNumber(int n) {
        if(n<=0) return 0;
        int a=0,b=0,c=0;
        List<Integer> table = new ArrayList<Integer>();
        table.add(1);
        while(table.size()<n)
        {
            int next_val = Math.min(table.get(a)*2,Math.min(table.get(b)*3,table.get(c)*5));
            table.add(next_val);
            if(table.get(a)*2==next_val) a++;
            if(table.get(b)*3==next_val) b++;
            if(table.get(c)*5==next_val) c++;
        }
        return table.get(table.size()-1);
    }
}
