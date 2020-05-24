import java.util.*;

class Main {
    public static void main(String[] args) {
       Scanner sc = new Scanner(System.in);
       int n = sc.nextInt();
       int a[] = new int[n];
       for(int i=0;i<n;i++){
           a[i] = sc.nextInt();
       }
       int num = sc.nextInt();
       int freq = 0;
       for(int x : a){
           if(x == num){
               freq++;
           }
       }
        System.out.println(freq);
    }
}