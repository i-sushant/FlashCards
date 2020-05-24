import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int a = scanner.nextInt();
        int b = scanner.nextInt();
        int n = scanner.nextInt();
        int k = scanner.nextInt();

        int min = Integer.MAX_VALUE;
        int seed = a;
        for(int i = a;i<=b;i++){
            Random random = new Random(i);
            ArrayList<Integer> arrayList = new ArrayList<>(4);
            for(int j = 0; j<n;j++){
                 arrayList.add(random.nextInt(k));
            }
            int max = Collections.max(arrayList);
            if(max < min) {
                min = max;
                seed = i;
            }
        }
        System.out.println(seed+"\n" + min);
    }
}