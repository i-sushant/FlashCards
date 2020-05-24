import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int a[] = new int[n];
        for(int i=0;i<n;i++){
            a[i] = sc.nextInt();
        }
        int count = 0;
        for(int i = 0; i<n-2;i++){
            int triple = 0;
            for(int j = i; j < i+2;j++){
                if(a[j+1] - a[j] == 1){
                    triple++;
                } else {
                    break;
                }
            }
            if(triple == 2){
                count++;
            }
        }
        System.out.println(count);
    }
}