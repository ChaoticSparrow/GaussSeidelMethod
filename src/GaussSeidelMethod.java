import java.util.Stack;

public class GaussSeidelMethod {

    public static boolean isDiagonallyDominant(double[][] matrix){
        for(int i = 0; i < matrix.length; i++){
            double dia = Math.abs(matrix[i][i]);
            double nondia = 0;
            for(int j = 0; j < matrix[i].length; j++){
                if(j != i){
                    nondia += Math.abs(matrix[i][j]);
                }
            }
            if(nondia > dia){
                return false;
            }
        }

        return true;
    }

    public static Stack<double[][]> solveFor(double[][] matrix, double[][] b, double[][] x, int iterations) {
        Stack<double[][]> result = new Stack<>();
        int limit = matrix.length;
        double multipliers[] = new double[limit];
        double multiplied = 0;
        double[][] inverseOfD = new double[matrix.length][matrix.length];
        double[][] nonDiagonal = new double[matrix.length][matrix.length];

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                if (i == j) {
                    inverseOfD[i][j] = 1 / matrix[i][j];
                    nonDiagonal[i][j] = 0;
                } else {
                    inverseOfD[i][j] = 0;
                    nonDiagonal[i][j] = matrix[i][j];
                }
            }
        }
        result.push(x);

        for(int i = 0; i < limit; i++){
            multipliers[i] = 1/matrix[i][i];
        }

        for (int z = 0; z < iterations; z++) {

            double newX[][] = new double[limit][limit];
            for(int i = 0; i < limit; i++){
                multiplied = 0;
                for(int y = 0; y < limit; y++){
                    if(y != i){
                        if(y > i){
                            multiplied += -(matrix[i][y]*result.peek()[y][0]);
                        }else{
                            multiplied += -(matrix[i][y]*newX[y][0]);
                        }
                    }
                }

                newX[i][0] = multipliers[i]*(b[i][0] + multiplied);
            }
            result.push(newX);
        }

        return result;
    }
}
