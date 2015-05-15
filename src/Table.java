import java.util.Arrays;


public class Table {
	//private fields: subject to change
	private double[][] table;
	private String name;
	
	
	//New Table
	public Table(String name, int rows, int cols){
		this.name = name;
		this.table = new double[rows][cols];
		Arrays.fill(this.table, -1);
	}
	
	//Table communication within program
	public Table(String name, double[][] table){
		this.name = name;
		this.table = table;
	}
	
	//Automatic Table Filling/ Table Initialisation Method with given totals
	public void Init(Table oldTable, double[] colTotals, double[] rowTotals){
		//TODO
		
		int degFree = oldTable.degreeOfFreedom();
		int taken = 0;
		for(double[] arr : oldTable.getTable()){
			for(double d : arr){
				if(d != -1) taken++;
			}
		}
		
		// check for enough information
		if(taken >= degFree && oldTable.isWellFormed() && getTableRows() == rowTotals.length && getTableCols() == colTotals.length){
			//Work in Progress
			while(!isFilled()){
				for(int rcount = 0; rcount < getTableRows(); rcount++){
					int usedspace = 0;
					  for(int ccount = 0; ccount < getTableCols(); ccount++){
						  if(table[rcount][ccount] != -1) usedspace++;
					  }
					  if(usedspace == getTableCols() - 1){
						  
					  }
				}
				for(int rcount = 0; rcount < getTableRows(); rcount++){
					int usedspace = 0;
					  for(int ccount = 0; ccount < getTableCols(); ccount++){
						  if(table[rcount][ccount] != -1) usedspace++;
					  }
					  if(usedspace == getTableCols() - 1){
						  
					  }
				}
				 /* for(double[] row : table){
					  int usedspace = 0;
					  for(double d : row){
						  if(d != -1) usedspace++;
					  }
					  if(usedspace == getTableCols() - 1)
				  }*/
			}
		}
	}
	
	//Note: Remake Init with "double totalVariables" for ease of access and/or filling colTotals or rowTotals
	
	public double[][] getTable() {
		return table;
	}
	public int getTableRows(){
		return table.length;
	}
	public int getTableCols(){
		return table[0].length;
	}
	public void setTable(double[][] table) {
		this.table = table;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	//Check to see if the table is M x N by setting the length of the first array to an int
	//and then comparing it with every other array's length.
	public boolean isWellFormed(){
		boolean result = true;
		int expectedLen = table[0].length;
		for(int i = 1 ; i < this.table.length ; i++){
			result &= table[i].length == expectedLen;
		}
		return result;
	}
	
	//Check if table is filled
	//POSSIBLY REDUNDANT METHOD!
	public boolean isFilled(){
		for(double[] row : table){
			for(double d : row){
				if(d == -1) return false;
			}
		}
		return true;
	}
	
	//Calculates the degree of freedom of the table (M x N) using the formula
	//degree = (M-1) * (N-1), unless M = 1 or N = 1 
	public int degreeOfFreedom(){
		int result = -1;
		if(!this.isWellFormed())return -1;
		int dimRow = this.table.length;
		int dimCol = this.table[0].length;
		if(dimRow == 1 && dimCol != 1)result = dimCol-1;
		if(dimRow != 1 && dimCol == 1)result = dimRow-1;
		if(dimRow == 1 && dimCol == 1)result = 1;
		if(dimRow != 1 && dimCol != 1)result = (dimCol-1)*(dimRow-1);
		return result;
	}
	
	
	//Self-explanatory
	public String toString(){
		String result = this.name+":\n";
		for(double[] arr : this.table){
			result += arr[0]+" | ";
			for(int i = 1 ; i < arr.length-1 ; i++){
				result += arr[i]+" | ";
			}
			result += arr[arr.length-1]+"\n";
		}
		
		return result;
	}
	
	
	//Main method for testing:
	public static void main(String[] args){
		Table table = new Table("table1", 3, 3);
		System.out.print(table.toString());
	}
}
