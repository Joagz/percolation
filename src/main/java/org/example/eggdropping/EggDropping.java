package org.example.eggdropping;

public class EggDropping {

    int drop(int eggs, int floors) {

        if (eggs == 0) {
            return 0;
        }
        if (eggs == 1) {
            return floors;
        }
        if (floors == 0 || floors == 1) {
            return 0;
        }

        int min = Integer.MAX_VALUE;
        for (int drops = 1; drops < floors; drops++) {

            int res = Math.max(drop(eggs, floors - drops), drop(eggs - 1, drops - 1));

            if (res < min) {
                min = res;
            }

        }
        return min + 1;
    }

    public static void main(String[] args) {
        EggDropping eggDropping = new EggDropping();
//        Output = 4
        System.out.println(eggDropping.drop(2, 10));
    }


}
