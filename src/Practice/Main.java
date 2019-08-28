package Practice;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        while(sc.hasNext()){

            int N = sc.nextInt();
            int M = sc.nextInt();

            ArrayList<Integer> listGrade = new ArrayList<>();
            for (int i = 0; i < N; i++) {
                listGrade.add(sc.nextInt());//下标为i,ID是i+1
            }

            ArrayList<Integer> listMax = new ArrayList<>();
            for (int i = 0; i < M; i++) {

                String str = sc.next();
                char c = str.charAt(0);

                int A = sc.nextInt();
                int B = sc.nextInt();

                if ( c == 'Q' ){
                    if ( A > B ){
                        listMax.add(maxMethod(B, A, listGrade));
                    }else if ( A < B ){
                        listMax.add(maxMethod(A, B, listGrade));
                    }else{
                        listMax.add(listGrade.get(A-1));
                    }
                }

                if( c == 'U'){
                    listGrade.set( A-1, B);
                }
            }
            for (int i = 0; i < listMax.size(); i++) {
                System.out.println(listMax.get(i));
            }

        }
    }
    public static Integer maxMethod(int Min, int Max, ArrayList<Integer> listGrade ){
        Integer max = 0;
        for (int i = Min-1; i <= Max-1; i++){
            if (max < listGrade.get(i) ){
                max = listGrade.get(i);
            }
        }
        return max;

    }

}
