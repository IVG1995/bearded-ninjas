
public class Statistics {
	public static double findMean(double[] a){
		double sum = 0;
		for(double i : a){
			sum+=i;
		}
		return sum/(a.length);
	}
	
	public static double findStandardDeviation(double[] a){
		double mean = findMean(a);
		double sum = 0;
		for(double i : a){
			sum += (i-mean)*(i-mean);
		};
		sum = sum / (a.length-1);
		return Math.sqrt(sum);
	}
	
	public static double findCorrCoeff(double[] a, double[] b){
		double meanA = findMean(a);
		double meanB = findMean(b);
		double stDevA = findStandardDeviation(a);
		double stDevB = findStandardDeviation(b);
		double sum = 0;
		if(a.length == b.length){
			for (int i = 0 ; i < a.length ; i++){
				sum+= (a[i]-meanA)*(b[i]-meanB);
			}
		};
		return sum/(a.length*stDevA*stDevB);
	}
	
	public static Table createXVT(Table data){
		double[][] result = new double[data.getTable().length][data.getTable()[0].length];
		int[] rowSums = new int[data.getTable()[0].length];
		int[] colSums = new int[data.getTable().length];
		for(int i = 0 ; i < data.getTable().length ; i++){
			colSums[i] = 0;
		}
		int totalSum  = 0;
		for(int i = 0 ; i < data.getTable().length ; i ++){
			rowSums[i] = 0;
			for(int j = 0 ; j < data.getTable()[0].length ; j++){
				rowSums[i]+= data.getTable()[i][j];
				colSums[j]+= data.getTable()[i][j];
				totalSum+=   data.getTable()[i][j];
			}
		}
		for(int i = 0 ; i < data.getTable().length ; i++){
			for(int j = 0 ; j < data.getTable()[0].length ; j++){
				result[i][j] =(double) rowSums[i]*colSums[j]/totalSum;
			}
		}
		Table result1 = new Table(data.getName(), result);
		return result1;
	}
	/*This method returns true if the chi^2 test is relevant to the variance */
	public static boolean chiRelevance(Table xvm){
		double entriesCrit = (double)xvm.getTable().length * xvm.getTable()[0].length / 5; //the 20% critical entry value for the partic matrix
		double critValues = 0 ;
		for(int i = 0 ; i < xvm.getTable().length ; i++){
			for(int j = 0 ; j < xvm.getTable()[0].length ; j++){
				if(xvm.getTable()[i][j] < 5)critValues++;
			}
		}
		return critValues < entriesCrit ;
	}
	public static double findChiSquared(Table data){
		double chiSq = 0;
		boolean lengthCheck = true;
		int expectedLength = data.getTable()[0].length;
		for(double[] i : data.getTable()){
			lengthCheck &= i.length == expectedLength;
		};
		if(lengthCheck){
			Table xvm = createXVT(data);
			for(int i = 0 ; i < data.getTable().length ; i++){
				for(int j = 0 ; j < expectedLength ; j++){
					chiSq+= (data.getTable()[i][j] - xvm.getTable()[i][j])*(data.getTable()[i][j] - xvm.getTable()[i][j])/xvm.getTable()[i][j];
				}
			}
		}else{
			throw(new RuntimeException("table is not well-formed"));
		};
		return chiSq;
	}
	
	public static void main(String[] args){
		double[] a = new double[]{8,8,10,8,8,9,6.5,4};
		double[] b = new double[]{1,4,1,5,10,2,6,12};
		Table data = new Table(new double[][]{new double[]{12,18},new double[]{17,13}});
		System.out.printf("Mean of A: %.3f"+"\n"+"Standard Deviation of A: %.3f"+"\n"+"Mean of B: %.3f"+"\n"+
						  "Standard Deviation of B: %.3f"+"\n"+"Coefficient Of Correlation: %.3f"+"\nChi Squared value"+
						  " to three decimals is :%.3f"+"\nChi^2 test is viable for this variance: %s"+"\n"
						  ,findMean(a),findStandardDeviation(a),findMean(b),findStandardDeviation(b)
						  ,findCorrCoeff(a,b),findChiSquared(data), chiRelevance(createXVT(data)));
		Table xvm = createXVT(data);
		for(int i = 0 ; i < xvm.getTable().length ; i++){
			for(int j = 0 ; j < xvm.getTable()[0].length ; j++){
				System.out.print("|"+ xvm.getTable()[i][j] +"|");
			}
			System.out.println("");
		}
	}	
}
