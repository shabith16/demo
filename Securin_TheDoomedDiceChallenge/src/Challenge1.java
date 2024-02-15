public class Challenge1 {
    public static void main(String[] args) {
        int [] diceA={1,2,3,4,5,6};
        int [] diceB={1,2,3,4,5,6};
        combinations_possible(6,2);
        display(diceA,diceB);
        Probability(diceA,diceB);
    }

    public static void Probability(int[] arr1, int[] arr2) {
        System.out.println("Probability distribution for all possible sums:");
        int[] sumCount = new int[13];
        for (int num1 : arr1) {
            for (int num2 : arr2) {
                int sum = num1 + num2;
                sumCount[sum]++;
            }
        }
        double totalCombinations = arr1.length * arr2.length;
        for (int p = 2; p <= 12; p++) {
            double probability = (double) sumCount[p] / totalCombinations;
            System.out.println("P(Sum = " + p + ") : " + probability);
        }
    }

    public static void display(int []arr1,int[]arr2){
        System.out.println();
        System.out.println("Displaying all possible combinations when 2 die are rolled together:");
        for(int k=0;k<arr1.length*arr2.length;k++){
            System.out.print("_");
        }
        System.out.println();
        for(int i=0;i<arr1.length;i++){
            System.out.print("| ");
            for(int j=0;j<arr2.length;j++){
                System.out.print(arr1[i]+","+arr2[j]+" | ");
            }
            System.out.println();
            for(int k=0;k<arr1.length*arr2.length;k++){
                System.out.print("_");
            }
            System.out.println();

        }
        System.out.println();
        System.out.println("Sum Distribution Matrix of 2 dice:");
        for(int k=0;k<arr1.length*5-3;k++){
            System.out.print("_");
        }
        System.out.println();
        for(int i=0;i<arr1.length;i++){
            System.out.print("| ");
            for(int j=0;j<arr2.length;j++){
                System.out.print(arr1[i]+arr2[j]+" | ");
            }
            System.out.println();
            for(int k=0;k<arr1.length*5-3;k++){
                System.out.print("_");
            }
            System.out.println();

        }
        System.out.println();
    }
    public static void combinations_possible(int dice,int n){
        //n represents the number of dice
        int x= (int) Math.pow(dice,n);
        System.out.println();
        System.out.println("Total combinations possible with "+n+" dice = "+x);
    }
}