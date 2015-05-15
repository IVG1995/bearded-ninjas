
public class Table {
	//private fields: subject to change
	private double[][] table;
	private String name;
	
	
	//Main constructor used for overloading
	public Table(String name, double[][] table){
		this.name = name;
		this.table = table;
	}
	
	//Constructor to be made by IGGeorgiev, to contain as arguments the least amount
	//of information, needed for the creation of a table for observed categorical data.
	//Degree of freedom check already done - delete and make your own if you want.
	public Table(String name, int totalVar, int dimRow, int dimCol, Table oldTable){
		//TODO
		
		int degFree = oldTable.degreeOfFreedom();
		int taken = 0;
		for(double[] arr : oldTable.getTable()){
			for(double d : arr){
				if(d != 0) taken++;
			}
		}
		
		// check for enough information
		if(taken < degFree)throw new RuntimeException("Not enough data for construction!");		
		
		// Does it work??
	}
	
	//single-argument overloaded constructors, getters and setters
	public Table(double[][] table){
		this(null, table);
	}
	
	public Table(String name){
		this(name, null);
	}
	
	public double[][] getTable() {
		return table;
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
	
	
	//Check to see if the table is N x N by setting the length of the first array to an int
	//and then comparing it with every other array's length.
	public boolean isWellFormed(){
		boolean result = true;
		int expectedLen = table[0].length;
		for(int i = 1 ; i < this.table.length ; i++){
			result &= table[i].length == expectedLen;
		}
		return result;
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
		Table table = new Table("table1", new double[3][3]);
		System.out.print(table.toString());
	}
}
