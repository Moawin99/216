import java.util.ArrayList;
import java.util.List;

public class Demo {
    public static void main(String[] args){
        int[] s = {1, 3, 0, 5, 3, 5, 6, 8, 8, 2, 12};
        int[] f = {4, 5, 6, 7, 9, 9, 10, 11, 12, 14, 16};
        System.out.println(actSort(s, f));
    }

    public static int actSort(int[] s, int[] f){
        List<Integer> ans = new ArrayList<>();
        int[] comps = new int[s.length];
        int maxHours = 0;
        comps[0] = f[0] - s[0];
        for(int i = 1; i < s.length; i++){
            for(int j = i; j >= 0; j--){
                if(f[j] <= s[i]){
                    maxHours = (f[i] - s[i]) + comps[i - 1];
                    if(comps[i-1] < maxHours){
                        comps[i] = maxHours;
                        break;
                    }
                }
            }
            if(f[i] - s[i] > comps[i - 1]){
                comps[i] = f[i] - s[i];
            }
            else{
                comps[i] = comps[i-1];
            }

        }
        return comps[comps.length-1];
    }
}
