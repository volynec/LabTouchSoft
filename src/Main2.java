import java.util.Arrays;

/**
 * Created by user on 14.01.2018.
 */
public class Main2 {
    public static void main(String[] args) {
        int[] num = {1, 2, 3, 4, 5, 3, 2, 1, 2, 6, 5, 4, 3, 3, 2, 1, 1, 3, 3, 3, 4, 2};
        int[] num2 = new int[num.length];
        int sum = 0;
        for (int i = 0; i < num2.length; i++) {
            num2[i] = 1;
        }
        for (int i = 1; i < num.length; i++) {
            if (num[i] > num[i - 1]) {
                num2[i] = num2[i - 1] + 1;
            }
        }
        System.out.println(Arrays.toString(num2));
        for (int i = num.length - 2; i >= 0; i--) {
            if (num[i] > num[i + 1] && num2[i] <= num2[i + 1]) {
                num2[i] = num2[i + 1] + 1;
            }
        }
        for (int i = 0; i < num2.length; i++) {
            sum += num2[i];
        }
        System.out.println(Arrays.toString(num2));
        System.out.println(sum);
    }
}
