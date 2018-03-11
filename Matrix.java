
package moon.cs146.project1;

public class Matrix
{
	public double data[][];
	public Matrix(int size)
	{
		this.data = new double[size][size];
	}
	public Matrix(double r_matrix[][])
	{
		this.data = r_matrix;
	}
	
	public Matrix productRegular(Matrix r_matrix)
	{
		Matrix outcome = new Matrix(r_matrix.data.length);
		for(int i=0; i<data.length; i++)
		{
			for(int j=0; j<data.length;j++)
			{
				for(int k=0; k<data.length;k++)
				{
					outcome.data[i][j]+= data[i][k]*r_matrix.data[k][j];
				}
			}	
		}		
		return outcome;
	}
	public double[][] getData()
	{
		return data;
	}
    public void setData(double r_matrix[][])
    {
    	this.data = r_matrix;
    }
    public void setMatrix(Matrix r_matrix)
    {
    	this.data = r_matrix.data;
    }
    public void setMatrix(double r_matrix[][])
    {
    	this.data = r_matrix;
    }
	
    public Matrix productStrassen(Matrix r_matrix)
    {        
    	double outcome[][] = new double[r_matrix.data.length][r_matrix.data.length];
    	// base case
    	if(r_matrix.data.length==1)
		{
			outcome[0][0] = data[0][0]*r_matrix.data[0][0];
		}
        else
        {
        	//divide original matrix into 4 smaller matrices(n/2 size)
        	double A11[][] = new double[r_matrix.data.length/2][r_matrix.data.length/2];
			double A12[][] = new double[r_matrix.data.length/2][r_matrix.data.length/2];
			double A21[][] = new double[r_matrix.data.length/2][r_matrix.data.length/2];
			double A22[][] = new double[r_matrix.data.length/2][r_matrix.data.length/2];
			double B11[][] = new double[r_matrix.data.length/2][r_matrix.data.length/2];
			double B12[][] = new double[r_matrix.data.length/2][r_matrix.data.length/2];
			double B21[][] = new double[r_matrix.data.length/2][r_matrix.data.length/2];
			double B22[][] = new double[r_matrix.data.length/2][r_matrix.data.length/2];
			
			divide(A11, data, 0, 0);
			divide(A12, data, 0, data.length/2);
			divide(A21, data, data.length/2, 0);
			divide(A22, data, data.length/2, data.length/2);
			divide(B11, r_matrix.data, 0, 0);
			divide(B12, r_matrix.data, 0, r_matrix.data.length/2);
			divide(B21, r_matrix.data, r_matrix.data.length/2, 0);
			divide(B22, r_matrix.data, r_matrix.data.length/2, r_matrix.data.length/2);
 
			//Strassen multiplication with recursion
			Matrix P1 = new Matrix(A11);
			Matrix Temp = new Matrix(subt(B12, B22));
			P1.setMatrix(P1.productStrassen(Temp));
			Matrix P2 = new Matrix(add(A11,A12));
			Temp.setMatrix(B22);
			P2.setMatrix(P2.productStrassen(Temp));
			Matrix P3 = new Matrix(add(A21,A22));
			Temp.setMatrix(B11);
			P3.setMatrix(P3.productStrassen(Temp));
			Matrix P4 = new Matrix(A22); 
			Temp.setMatrix(subt(B21, B11));
			P4.setMatrix(P4.productStrassen(Temp));
			Matrix P5 = new Matrix(add(A11, A22));
			Temp.setMatrix(add(B11, B22));
			P5.setMatrix(P5.productStrassen(Temp));
			Matrix P6 = new Matrix(subt(A12, A22));
			Temp.setMatrix(add(B21, B22));
			P6.setMatrix(P6.productStrassen(Temp));
			Matrix P7 = new Matrix(subt(A21, A11));
			Temp.setMatrix(add(B11, B12));
			P7.setMatrix(P7.productStrassen(Temp));
		
		    // assigning correct values into each location of the matrix 
			double C11[][] = add(subt(add(P5.data, P4.data), P2.data), P6.data);
			double C12[][] = add(P1.data, P2.data);
			double C21[][] = add(P3.data, P4.data);
			double C22[][] = add(subt(add(P5.data, P1.data), P3.data),P7.data);

			//merge
            merge(C11, outcome, 0 , 0);
            merge(C12, outcome, 0 , data.length/2);
            merge(C21, outcome, data.length/2, 0);
            merge(C22, outcome, data.length/2, data.length/2);
        }
    	Matrix R = new Matrix(outcome);
        return R;
    }
	// matrices addition
    public double[][] add(double r_matrix1[][], double r_matrix2[][])
	{
		double outcome[][] = new double[r_matrix1.length][r_matrix1.length];
		for(int i=0; i<r_matrix1.length; i++)
		{
			for(int j=0; j<r_matrix1.length; j++)
			{
				outcome[i][j] = r_matrix1[i][j] + r_matrix2[i][j];
			}
		}
		return outcome;
	}
	// matrices subtraction
    public double[][] subt(double r_matrix1[][], double r_matrix2[][])
	{
		double outcome[][] = new double[r_matrix1.length][r_matrix1.length];
		for(int i=0; i<r_matrix1.length; i++)
		{
			for(int j=0; j<r_matrix1.length; j++)
			{
				outcome[i][j] = r_matrix1[i][j] - r_matrix2[i][j];
			}
		}
		return outcome;
	}
    // divde
    public void divide(double child[][], double parent[][], int iOffset, int jOffset)
	{
		for(int i=0; i<child.length; i++)
		{
			for(int j=0; j<child.length; j++)
			{
				child[i][j] = parent[i+iOffset][j+jOffset];
			}
		}
	}
    // merge
    public void merge(double child[][], double parent[][], int iOffset, int jOffset)
	{
		for(int i=0; i<child.length; i++)
		{
			for(int j=0; j<child.length; j++)
			{
				parent[i+iOffset][j+jOffset] = child[i][j];
			}
		}
	}
    // displaying matrix
    /*public void showMatrix()
	{
		for(int i=0; i<data.length; i++)
		{
			System.out.print("["+"\t");
			for(int j=0; j<data[i].length; j++)
			{
				System.out.print(data[i][j]+" ");
			}
			System.out.print("\t"+"]");
			System.out.println();
		}	
	}
	*/
    // assigning random values to matrix
    public Matrix random()
	{
    	Matrix R = new Matrix(data.length);
    	for(int i=0; i<data.length; i++)
    	{
    		for(int j=0; j<data.length; j++)
    		{
    			// up to 500.1, if this value becomes large or the decimal becomes smaller, 
    			// the result becomes in accurate
    			// probably due to double 
    			double rand = Math.random()*500.0+0.1;    	
    			R.data[i][j] = rand; 
    		}
    	}
    	return R;
	} 
}