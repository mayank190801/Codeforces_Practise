import java.util.List;

public class one {
    
    public static int findEarliestMonth(List<Integer> stockPrice){
        //now 
        //net price change is min
        long total = 0;
        for(int price : stockPrice){
            total += price;
        }

        long ans = Integer.MAX_VALUE;
        long idx = -1;
        long current = 0;

        for(long i= 0; i < stockPrice.size() - 1; i++){
            current += stockPrice.get((int)i);
            long left = current/(i + 1);
            long right = (total - current)/(stockPrice.size() - (i + 1));

            long cans = Math.abs(left - right);
            if(cans < ans){
                ans = cans;
                idx = i;
            }
        }

        return (int)(long)(idx + 1);
    }

}
