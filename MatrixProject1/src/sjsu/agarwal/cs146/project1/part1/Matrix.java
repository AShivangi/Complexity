package sjsu.agarwal.cs146.project1.part1;
import java.util.Random;

public class Matrix {
	private double[][]array;
	
	public Matrix(double[][] array){ //converting array to Matrix
		this.array = array;
	}
	public Matrix(int n){ //empty matrix
		this.array = new double[n][n];
	}
	public int length(){
		return array.length;
	}
	public double get(int i, int j){
		return array[i][j];
	}
	
	public double[] data(int i){
		return array[i];
	}
	
	public static double[][] productRegular(double[][] a, double[][] b) {
	       int rowsInA = a.length;
	       double[][] c = new double[rowsInA][rowsInA];
	       for (int i = 0; i < rowsInA; i++) {
	           for (int j = 0; j < rowsInA; j++) {
	               for (int k = 0; k < rowsInA; k++) {
	                   c[i][j] = c[i][j] + a[i][k] * b[k][j];
	               }
	           }
	       }
	       return c;
	   }
	   
	   public double[][] productStrassen(double[][] a,double[][]b){
		   int rowsInA = a.length;
		   double[][] c = new double[rowsInA][rowsInA];
		   c = multiply(a,b);
		   return c;
	   }
	 
		public static double[][] makeMatrix(int size){
			  double[][] a =  new double[size][size];
			     for (int i = 0; i < size; i++) {
			         for (int j = 0; j < size; j++) {
			     		Random rand = new Random(); 
			    		double value = rand.nextDouble()*5;
			    		a[i][j] = value;
			         } 
			     }
			     return a;
			    
			 }
		
		public static void printMatrix(Matrix matrix){
		       for (int i = 0; i < matrix.length(); i++) {
		    	   for (int j = 0; j < matrix.length(); j++) {
	            System.out.print(matrix.get(i, j) + " ");
	        }
			System.out.println();
			}
		       System.out.println();
		}

	    /** Function to multiply matrices **/
	    public double[][] multiply(double[][] A, double[][] B)
	    {        
	        int n = A.length;
	        double[][] R = new double[n][n];
	        /** base case **/
	        if (n == 1)
	            R[0][0] = A[0][0] * B[0][0];
	        else
	        {
	            double[][] A11 = new double[n/2][n/2];
	            double[][] A12 = new double[n/2][n/2];
	            double[][] A21 = new double[n/2][n/2];
	            double[][] A22 = new double[n/2][n/2];
	            double[][] B11 = new double[n/2][n/2];
	            double[][] B12 = new double[n/2][n/2];
	            double[][] B21 = new double[n/2][n/2];
	            double[][] B22 = new double[n/2][n/2];
	 
	            /** Dividing matrix A into 4 halves **/
	            split(A, A11, 0 , 0);
	            split(A, A12, 0 , n/2);
	            split(A, A21, n/2, 0);
	            split(A, A22, n/2, n/2);
	            /** Dividing matrix B into 4 halves **/
	            split(B, B11, 0 , 0);
	            split(B, B12, 0 , n/2);
	            split(B, B21, n/2, 0);
	            split(B, B22, n/2, n/2);
	 
	            /** 
	              M1 = (A11 + A22)(B11 + B22)
	              M2 = (A21 + A22) B11
	              M3 = A11 (B12 - B22)
	              M4 = A22 (B21 - B11)
	              M5 = (A11 + A12) B22
	              M6 = (A21 - A11) (B11 + B12)
	              M7 = (A12 - A22) (B21 + B22)
	            **/
	 
	            double [][] M1 = multiply(add(A11, A22), add(B11, B22));
	            double [][] M2 = multiply(add(A21, A22), B11);
	            double [][] M3 = multiply(A11, sub(B12, B22));
	            double [][] M4 = multiply(A22, sub(B21, B11));
	            double [][] M5 = multiply(add(A11, A12), B22);
	            double [][] M6 = multiply(sub(A21, A11), add(B11, B12));
	            double [][] M7 = multiply(sub(A12, A22), add(B21, B22));
	 
	            /**
	              C11 = M1 + M4 - M5 + M7
	              C12 = M3 + M5
	              C21 = M2 + M4
	              C22 = M1 - M2 + M3 + M6
	            **/
	            double [][] C11 = add(sub(add(M1, M4), M5), M7);
	            double [][] C12 = add(M3, M5);
	            double [][] C21 = add(M2, M4);
	            double [][] C22 = add(sub(add(M1, M3), M2), M6);
	 
	            /** join 4 halves into one result matrix **/
	            join(C11, R, 0 , 0);
	            join(C12, R, 0 , n/2);
	            join(C21, R, n/2, 0);
	            join(C22, R, n/2, n/2);
	        }
	        /** return result **/    
	        return R;
	    }
	    /** Funtion to sub two matrices **/
	    public double[][] sub(double[][] A, double[][] B)
	    {
	        int n = A.length;
	        double[][] C = new double[n][n];
	        for (int i = 0; i < n; i++)
	            for (int j = 0; j < n; j++)
	                C[i][j] = A[i][j] - B[i][j];
	        return C;
	    }
	    /** Funtion to add two matrices **/
	    public double[][] add(double[][] A, double[][] B)
	    {
	        int n = A.length;
	        double[][] C = new double[n][n];
	        for (int i = 0; i < n; i++)
	            for (int j = 0; j < n; j++)
	                C[i][j] = A[i][j] + B[i][j];
	        return C;
	    }
	    /** Funtion to split parent matrix into child matrices **/
	    public void split(double[][] P, double[][] C, int iB, int jB) 
	    {
	        for(int i1 = 0, i2 = iB; i1 < C.length; i1++, i2++)
	            for(int j1 = 0, j2 = jB; j1 < C.length; j1++, j2++)
	                C[i1][j1] = P[i2][j2];
	    }
	    /** Funtion to join child matrices intp parent matrix **/
	    public void join(double[][] C, double[][] P, int iB, int jB) 
	    {
	        for(int i1 = 0, i2 = iB; i1 < C.length; i1++, i2++)
	            for(int j1 = 0, j2 = jB; j1 < C.length; j1++, j2++)
	                P[i2][j2] = C[i1][j1];
	    } 

}
