
class BinarySearch {
    public static void main(String[] args)throws Exception {
        java.io.BufferedReader br = new java.io.BufferedReader(new java.io.InputStreamReader(System.in));
        int[] nums  = java.util.stream.Stream.of(br.readLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();
        int key = Integer.parseInt(br.readLine());
        System.out.println(callBinarySearch(nums,key));
    }
    public static int callBinarySearch(int[] nums, int key) {
        return  java.util.Arrays.binarySearch(nums,key);
    }
}