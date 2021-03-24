public class MatrixMerge {
    public MatrixMerge(int[][]C00, int[][]C01, int[][]C10, int[][]C11, int newRow, int newCol, int rowmod, int colmod){
        int m = C00.length;
        int n = C01.length;
        int p = C00[0].length;
        int q = C01[0].length;
        int[][]C0001 = new int[m][p+q];
        for (int i=0; i<m; i++){
            for(int j = 0; j<p+q; j++)
            {
                if (j<p){
                    C0001[i][j] = C00[i][j];
                }
                else
                {
                    C0001[i][j] = C01[i][j-p];
                }
            }
        }

        int a = C10.length;
        int b = C11.length;
        int c = C10[0].length;
        int d = C11[0].length;
        int[][]C1011 = new int[m][p+q];
        for (int i=0; i<a; i++){
            for(int j = 0; j<c+d; j++)
            {
                if (j<c){
                    C1011[i][j] = C10[i][j];
                }
                else
                {
                    C1011[i][j] = C11[i][j-c];
                }
            }
        }

        int[][] C = new int[m+n-rowmod][p+q-colmod];
        for (int i = 0; i<newRow; i++){
            for (int j = 0; j<newCol; j++){
                C[i][j] = C00[i][j];
                C[i][j+newCol-colmod] = C01[i][j];
                C[i+newRow-rowmod][j] = C10[i][j];
                C[i+newRow-rowmod][j+newCol-colmod] = C11[i][j];
                                    }
        }
        System.out.println("Matrix A + Matrix B");
        new MatrixPrint(C);
    }
}
