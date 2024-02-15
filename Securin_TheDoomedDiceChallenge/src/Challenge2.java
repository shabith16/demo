import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Challenge2 {
    public static void main(String[] args) {
        int [] diceA={1,2,3,4,5,6};
        int [] diceB={1,2,3,4,5,6};
        undoom_dice(diceA,diceB);
    }
    public static void undoom_dice(int []diceA,int[]diceB){
        System.out.println("\nDice A : "+ Arrays.toString(diceA));
        System.out.println("Dice B : "+ Arrays.toString(diceB)+"\n");

        HashMap<Integer,Integer> Probability_Map=ProbabilityMap(diceA,diceB);

        System.out.println("Probability Distribution of Original dice:\n");
        Probability(diceA,diceB);
        for(int i=0;i<diceA.length;i++){
            diceB[i]=0;
            diceA[i]=0;
        }
        System.out.println("\nDoomed dice A : "+ Arrays.toString(diceA));
        System.out.println("Doomed dice B : "+ Arrays.toString(diceB)+"\n");
        int [] Dice1={1,2,3,4};
        int [] Dice2={1,2,3,4,5,6,7,8};

        List<List<Integer>>DiceA= Generate_DiceA(Dice1);
        // System.out.println(DiceA.toString());
        List<List<Integer>>DiceB=Generate_DiceB(Dice2);
        //System.out.println(DiceB.toString());
        int breaker=0;
        for(List<Integer> dice1:DiceA){
            for(List<Integer>dice2:DiceB){
                int flag=0;
                for(int p=2;p<=12;p++) {
                    int count =0;
                    for (Integer A : dice1) {
                        for (Integer B : dice2){
                            if(A+B==p){
                                count++;
                            }
                        }
                    }
                    if(count==Probability_Map.get(p)){
                        flag++;
                    }
                }
                if(flag==11){
                    System.out.println("The faces of the Undoomed dice are:");
                    diceA=convertor(diceA,dice1);
                    diceB=convertor(diceB,dice2);
                    System.out.println("New_Dice A : "+Arrays.toString(diceA));
                    System.out.println("New_Dice B : "+Arrays.toString(diceB));
                    breaker=1;
                    break;
                }
            }
            if(breaker==1){
                break;
            }
        }
        System.out.println("\nProbability Distribution of the transformed dice:\n");
        Probability(diceA,diceB);
    }
    public static int[] convertor(int []arr,List<Integer>list){
        for(int i=0;i<arr.length;i++){
            arr[i]= list.get(i);
        }
        return arr;
    }
    public static List<List<Integer>> Generate_DiceB(int[]diceB){
        List<List<Integer>> dice=new ArrayList<>();
        DiceB(dice,new ArrayList<>(),diceB);
        return dice;
    }
    public static void DiceB(List<List<Integer>>dice,List<Integer>list,int[]diceB){
        if(list.size()==6){
            dice.add(list);
            return;
        }
        for(int i=0;i<diceB.length;i++){
            if(!list.contains(diceB[i]))
            { list.add(diceB[i]);
                DiceB(dice,new ArrayList<>(list),diceB);
                list.remove(list.size()-1);
            }


        }
    }
    public static List<List<Integer>> Generate_DiceA(int[]diceA){
        List<List<Integer>>dice=new ArrayList<>();
        DiceA(new ArrayList<>(),diceA,dice);
        return dice;

    }
    public static void DiceA(List<Integer>list,int[]diceA, List<List<Integer>>gen){

        if(list.size()==6){
            List<List<Integer>>in2=new ArrayList<>();
            gen.add(list);
            return;
        }
        List<List<Integer>>out=new ArrayList<>();
        List<Integer>in=new ArrayList<>();
        for(int i=0;i< diceA.length;i++){
            list.add(diceA[i]);
            DiceA(new ArrayList<>(list),diceA,gen);
            list.remove(list.size()-1);
        }
        return;
    }
    public static  HashMap<Integer,Integer> ProbabilityMap(int []arr1,int[]arr2){
        HashMap<Integer,Integer> map=new HashMap<>();

        int   count =0;
        for(int i=0;i<arr1.length;i++){
            for(int j=0;j<arr2.length;j++){
                int sum=arr1[i]+arr2[j];
                map.put(sum,map.getOrDefault(sum,0)+1);
            }
        }
        return map;
    }
    public static void Probability(int[] arr1, int[] arr2) {
        // System.out.println("Probability distribution for all possible sums:");
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
}