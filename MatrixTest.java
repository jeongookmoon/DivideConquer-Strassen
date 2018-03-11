
package moon.cs146.project1;
import static org.junit.Assert.assertArrayEquals;
import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;


public class MatrixTest extends TestCase 
{
	private Matrix A, B; //input matrices
	private Matrix productRegularResult, productStrassenResult; // Matrices for storing the results
	private int N; // size of the NXN matrix
	public static void main(String[] args) 
	{
		
	}
	@Before
	public void setUp() throws Exception
	{
	   N = 4; 
	   double[][] matrix1 = new double[N][N];
	   double[][] matrix2 = new double[N][N];
	   A = new Matrix(matrix1);
	   B = new Matrix(matrix2);
	   productRegularResult = new Matrix(N);
	   productStrassenResult = new Matrix(N);
	} // setUp()
	
	
	@Test
	public void testProductCompare1() 
	{   
		 A = A.random();
	     B = B.random();
	     
	     // run productRegular()
	     double time = System.currentTimeMillis();
	     productRegularResult = A.productRegular(B);
	     double endtime = System.currentTimeMillis();
	     System.out.print("Running time of productRegular for size "+N+": ");
	     System.out.print(endtime-time+" ms");
	     System.out.println();
	     
	     // run productStrassen()
	     time = System.currentTimeMillis();
		 productStrassenResult = A.productStrassen(B);
	     endtime = System.currentTimeMillis();
	     System.out.print("Running time of productStrassen for size "+N+": ");
	     System.out.print(endtime-time+" ms");
	     System.out.println();
	     System.out.println();
		 for (int i = 0; i < N; i++) 
		 {
	    	assertArrayEquals(productRegularResult.data[i], productStrassenResult.data[i], 0.0001 ); 
	     }
	}
	
	// multiplying a 2D array using the regular method:
	@Test
	public void testProductRegular1() 
	{
		 //expected output
		double[][] expected = {{96.0,94.0,81.0,128.0},{144.0,117.0,112.0,162.0},{132.0,112.0,101.0,152.0},{112.0,86.0,87.0,130.0}};
		// input 2D array
		double[][] matrix1 = {{2.0,4.0,5.0,7.0},{6.0,7.0,2.0,8.0},{4.0,6.0,3.0,9.0},{8.0,4.0,1.0,5.0}};
		double[][] matrix2 = {{6.0,4.0,5.0,8.0},{8.0,7.0,8.0,8.0},{2.0,6.0,5.0,9.0},{6.0,4.0,2.0,5.0}}; 		
	    Matrix m1 = new Matrix(matrix1);
		Matrix m2 = new Matrix(matrix2);
	    
		productRegularResult = m1.productRegular(m2);
	    for (int i = 0; i < N; i++) 
	    {
			assertArrayEquals(expected[i],productRegularResult.data[i], 0.0); 
			// data[][] is a data member for storing matrix values in class Matrix.
		}
	}
	//test for handling small decimal numbers
	@Test
	public void testProductRegular2() 
	{
		N = 2; 
		productRegularResult = new Matrix(N);
		
		double[][] expected = {{40.110, 40.200},{5.100, 6.000}};
		double[][] matrix1 = {{0.100, 20.000},{ 1.000, 2.000}};
		double[][] matrix2 = {{1.100, 2.00},{ 2.000, 2.000}}; 		
	    Matrix m1 = new Matrix(matrix1);
		Matrix m2 = new Matrix(matrix2);
		
		productRegularResult = m1.productRegular(m2);
	    for (int i = 0; i < N; i++) 
	    {
			assertArrayEquals(expected[i], productRegularResult.data[i], 0.000); 
		}
	}
	
	// multiplying a 2D array using the Strassen method:
	@Test
	public void testProductStrassen1() 
	{
	    //expected output
		double[][] expected = {{96.0,94.0,81.0,128.0},{144.0,117.0,112.0,162.0},{132.0,112.0,101.0,152.0},{112.0,86.0,87.0,130.0}};
	    // input 2D array
		double[][] array1 = {{2.0,4.0,5.0,7.0},{6.0,7.0,2.0,8.0},{4.0,6.0,3.0,9.0},{8.0,4.0,1.0,5.0}};
		double[][] array2 = {{6.0,4.0,5.0,8.0},{8.0,7.0,8.0,8.0},{2.0,6.0,5.0,9.0},{6.0,4.0,2.0,5.0}}; 		
	    Matrix m1 = new Matrix(array1);
		Matrix m2 = new Matrix(array2);
	    // run productRegular()
		productStrassenResult= m1.productStrassen(m2);
	    for (int i = 0; i < N; i++) 
	    {
			assertArrayEquals(expected[i],productStrassenResult.data[i], 0.0); 
			// data[][] is a data member for storing matrix values in class Matrix.
		}
	}
	//test for handling small decimal numbers
	@Test
	public void testProductStrassen2() 
	{
		N = 2; 
		productStrassenResult = new Matrix(N);
		
		double[][] expected = {{40.110, 40.200},{5.100, 6.000}};
		double[][] matrix1 = {{0.100, 20.000},{ 1.000, 2.000}};
		double[][] matrix2 = {{1.100, 2.00},{ 2.000, 2.000}}; 		
	    Matrix m1 = new Matrix(matrix1);
		Matrix m2 = new Matrix(matrix2);
	    
		productStrassenResult = m1.productStrassen(m2);

	    for (int i = 0; i < N; i++) 
	    {
			assertArrayEquals(expected[i], productStrassenResult.data[i], 0.000); 
		}
	}
	// for 16X16 matrices 
	@Test
	public void testProductCompare2()
	{
		 N = 16; 
		 double[][] matrix1 = new double[N][N];
		 double[][] matrix2 = new double[N][N];
		 A = new Matrix(matrix1);
		 B = new Matrix(matrix2);
		 productRegularResult = new Matrix(N);
		 productStrassenResult = new Matrix(N);
		 
		 A = A.random();
	     B = B.random();
	     
	     // run productRegular()
	     double time = System.currentTimeMillis();
	     productRegularResult = A.productRegular(B);
	     double endtime = System.currentTimeMillis();
	     System.out.print("Running time of productRegular for size "+N+": ");
	     System.out.print(endtime-time+" ms");
	     System.out.println();
	     
	     // run productStrassen()
	     time = System.currentTimeMillis();
		 productStrassenResult = A.productStrassen(B);
	     endtime = System.currentTimeMillis();
	     System.out.print("Running time of productStrassen for size "+N+": ");
	     System.out.print(endtime-time+" ms");
	     System.out.println();
	     System.out.println();
		 for (int i = 0; i < N; i++) 
		 {
	    	assertArrayEquals(productRegularResult.data[i], productStrassenResult.data[i], 0.0001 ); 
	     }
	}
	// for 512X512 matrices 
	@Test
	public void testProductCompare3()
	{
		 N = 512; 
		 double[][] matrix1 = new double[N][N];
		 double[][] matrix2 = new double[N][N];
		 A = new Matrix(matrix1);
		 B = new Matrix(matrix2);
		 productRegularResult = new Matrix(N);
		 productStrassenResult = new Matrix(N);
		 
		 A = A.random();
	     B = B.random();
	     
	     // run productRegular()
	     double time = System.currentTimeMillis();
	     productRegularResult = A.productRegular(B);
	     double endtime = System.currentTimeMillis();
	     System.out.print("Running time of productRegular for size "+N+": ");
	     System.out.print(endtime-time+" ms");
	     System.out.println();
	     
	     // run productStrassen()
	     time = System.currentTimeMillis();
		 productStrassenResult = A.productStrassen(B);
	     endtime = System.currentTimeMillis();
	     System.out.print("Running time of productStrassen for size "+N+": ");
	     System.out.print(endtime-time+" ms");
	     System.out.println();
	     System.out.println();
		 for (int i = 0; i < N; i++) 
		 {
	    	assertArrayEquals(productRegularResult.data[i], productStrassenResult.data[i], 0.0001 ); 
	     }
	}
	// for 1024X1024 matrices 
	@Test
	public void testProductCompare4()
	{
		 N = 1024; 
		 double[][] matrix1 = new double[N][N];
		 double[][] matrix2 = new double[N][N];
		 A = new Matrix(matrix1);
		 B = new Matrix(matrix2);
		 productRegularResult = new Matrix(N);
		 productStrassenResult = new Matrix(N);
		 
		 A = A.random();
	     B = B.random();
	     
	     // run productRegular()
	     double time = System.currentTimeMillis();
	     productRegularResult = A.productRegular(B);
	     double endtime = System.currentTimeMillis();
	     System.out.print("Running time of productRegular for size "+N+": ");
	     System.out.print(endtime-time+" ms");
	     System.out.println();
	     
	     // run productStrassen()
	     time = System.currentTimeMillis();
		 productStrassenResult = A.productStrassen(B);
	     endtime = System.currentTimeMillis();
	     System.out.print("Running time of productStrassen for size "+N+": ");
	     System.out.print(endtime-time+" ms");
	     System.out.println();
		 for (int i = 0; i < N; i++) 
		 {
	    	assertArrayEquals(productRegularResult.data[i], productStrassenResult.data[i], 0.0001 ); 
	     }
	}
}
