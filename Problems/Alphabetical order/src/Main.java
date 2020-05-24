import java.util.*;
class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
      String[] a = sc.nextLine().split(" ");
      boolean flag = false;
      for(int i = 0;i< a.length-1;i++){
          if(a[i].compareTo(a[i+1]) > 0){
              flag = true;
              break;
          }
      }
      if(!flag){
          System.out.println(true);
          return;
      }
        System.out.println(false);
    }
}