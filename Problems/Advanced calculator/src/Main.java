import java.util.Arrays;

/* Please, do not rename it */
class Problem {

    public static void main(String[] args) {
        String operator = args[0];
        long[] arr = Arrays.stream(Arrays.copyOfRange(args, 1, args.length)).mapToLong(Integer::parseInt).toArray();
        switch (operator) {
            case "MAX":
                long max = Arrays.stream(arr).max().getAsLong();
                System.out.println(max);
                break;
            case "MIN":
                long min = Arrays.stream(arr).min().getAsLong();
                System.out.println(min);
                break;
            case "SUM":
                long sum = Arrays.stream(arr).sum();
                System.out.println(sum);
                break;
            default:
                break;
        }
    }
}