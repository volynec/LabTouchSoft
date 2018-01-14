import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by user on 14.01.2018.
 *
 * N студентов стоят в очереди за стипендией. Каждый студент имеет учебный рейтинг. Деканат должен выдать стипендию таким образом, чтобы:
 * каждый студент получил хотя бы 1 рубль,
 * студенты с более высоким рейтингом получили больше рублей, чем их соседи в очереди.
 Копеек в деканате нет. Какое минимальное количество рублей должно быть у деканата?

 На вход подаётся массив из N элементов, содержащий рейтинги для каждого студента.
 На выходе ожидается число, обозначающее минимальное количество рублей, которых должно хватить студентам.

 Пример.
 Вход: [1, 2, 3, 4, 5, 3, 2, 1, 2, 6, 5, 4, 3, 3, 2, 1, 1, 3, 3, 3, 4, 2]
 Выход: 47


 */

    public class Main {

        static int overjump = 0;
        static long cost = 0;
        static boolean check = false;

        public static int recur(ArrayList<Integer> arr, int ind, Integer prev) {
            if (ind == arr.size() - 1) {
                if (check) {
                    return 0;
                }
                check = true;
                if (arr.get(ind) > arr.get(ind - 1)) {
                    cost += prev + 1;
                    return 0;
                }
                if (arr.get(ind) == arr.get(ind - 1)) {
                    cost += prev;
                    return 0;
                }
                cost += 1;
                return 1;
            }
            int jump = 0;
            int grant = 0;
            if (arr.get(ind) > arr.get(ind - 1)) {
                if (arr.get(ind) > arr.get(ind + 1)) {
                    grant = Math.max(prev, recur(arr, ind + 1, prev)) + 1;
                    cost += grant;
                    jump = overjump + 1;
                    overjump = 0;
                    recur(arr, ind + jump, 1);
                    return 0;
                }
                if (arr.get(ind) == arr.get(ind + 1)) {
                    grant = Math.max(prev + 1, recur(arr, ind + 1, prev + 1));
                    cost += prev + 1;
                    jump = overjump + 1;
                    overjump = 0;
                    recur(arr, ind + jump, 1);
                    return 0;
                }
                cost += prev + 1;
                recur(arr, ind + 1, prev + 1);
                return 0;
            }
            if (arr.get(ind) == arr.get(ind - 1)) {
                overjump++;
                if (arr.get(ind) > arr.get(ind + 1)) {
                    grant = Math.max(prev, recur(arr, ind + 1, prev) + 1);
                    cost += grant;
                    return grant;
                }
                if (arr.get(ind) == arr.get(ind + 1)) {
                    grant = Math.max(prev, recur(arr, ind + 1, prev));
                    cost += 1;
                    return 1;
                }
                cost += 1;
                return 1;
            }
            if (arr.get(ind) < arr.get(ind - 1)) {
                overjump++;
                if (arr.get(ind) > arr.get(ind + 1)) {
                    grant = recur(arr, ind + 1, 0) + 1;
                    cost += grant;
                    return grant;
                }
                if (arr.get(ind) == arr.get(ind + 1)) {
                    grant = recur(arr, ind + 1, 0);
                    cost += 1;
                    return 1;
                }
                cost += 1;
                return 1;
            }
            return 0;
        }

        public static void main(String[] args) {
            try {
                ArrayList<Integer> arr = new ArrayList<>();
                Scanner sc = new Scanner(new File("input.txt"));
                arr.add(Integer.MIN_VALUE);
                while (sc.hasNextInt()) {
                    arr.add(sc.nextInt());
                }
                recur(arr, 1, 0);
                System.out.println(cost);
            } catch (RuntimeException | IOException e) {
                System.out.println("Runtime/IOException ");
                e.printStackTrace();
            }
        }
    }


