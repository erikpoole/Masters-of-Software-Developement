package assignment01;

public class Matrix {
	int numRows;
	int numColumns;
	int data[][];

	// Constructor with data for new matrix (automatically determines
	// dimensions)
	public Matrix(int data[][]) {
		numRows = data.length; // d.length is the number of 1D arrays in the 2D
								// array
		if (numRows == 0) {
			numColumns = 0;
		} else {
			numColumns = data[0].length; // d[0] is the first 1D array
		}
		this.data = new int[numRows][numColumns]; // create a new matrix to hold
													// the data
		// copy the data over
		for (int i = 0; i < numRows; i++) {
			for (int j = 0; j < numColumns; j++) {
				this.data[i][j] = data[i][j];
			}
		}
	}
	
//***************************************************************************
//***************************************************************************	

	@Override // instruct the compiler that we do indeed intend for this method
				// to override
				// the superclass' (Object) version
	public boolean equals(Object other) {
		if (!(other instanceof Matrix)) { // make sure the Object we're
											// comparing to is a Matrix
			return false;
		}
		Matrix matrix = (Matrix) other; // if the above was not true, we know
										// it's safe to treat 'o' as a Matrix

		if (this.numRows != matrix.numRows) {
			return false;
		}
		if (this.numColumns != matrix.numColumns) {
			return false;
		}

		for (int i = 0; i < this.numRows; i++) {
			for (int j = 0; j < this.numColumns; j++) {
				if (this.data[i][j] != matrix.data[i][j]) {
					return false;
				}
			}
		}
		return true;
	}

	@Override // instruct the compiler that we do indeed intend for this method
				// to override
				// the superclass' (Object) version
	public String toString() {
		String output = "";

		for (int i = 0; i < this.numRows; i++) {
			for (int j = 0; j < this.numColumns; j++) {
				output += this.data[i][j];
				output += " ";
			}
			output += "\n";
		}

		return output;
	}
	
//***************************************************************************
//***************************************************************************
	
	// TODO This is concise but miserable to read - refactor?
	public Matrix times(Matrix matrix) {
		if (this.numColumns != matrix.numRows) {
			return null;
		}

		Matrix output = new Matrix(new int[this.numRows][matrix.numColumns]);
		for (int i = 0; i < output.numRows; i++) {
			for (int j = 0; j < output.numColumns; j++) {
				for (int k = 0; k < this.numColumns; k++) {
					output.data[i][j] += this.data[i][k] * matrix.data[k][j];
					// System.out.println(output.data[i][j]);
				}
				// System.out.println("Total: " + output.data[i][j]);
			}
		}
		return output;
	}

	public Matrix plus(Matrix matrix) {
		if (this.numRows != matrix.numRows) {
			return null;
		}
		if (this.numColumns != matrix.numColumns) {
			return null;
		}

		Matrix output = new Matrix(new int[this.numRows][this.numColumns]);
		for (int i = 0; i < output.numRows; i++) {
			for (int j = 0; j < output.numColumns; j++) {
				output.data[i][j] = this.data[i][j] + matrix.data[i][j];
			}
		}
		return output;
	}
}
