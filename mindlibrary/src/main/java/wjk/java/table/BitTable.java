package wjk.java.table;

public class BitTable {

	boolean[][] table; 
	final int row;
	final int column;
	
	public BitTable(int c, int r){
		table = new boolean[c][r];
		row = r;
		column = c;
	}
	
	public void setCell(int r, int c, boolean b){
		table[c][r] = b;
	}
	
	public boolean getCell(int r, int c){
		return table[c][r];
	}
	
	public String toString(){
		String s = "";
		for(int i = 0;i<column;i++)
			for(int j = 0;j<row;j++){
				s += table[i][j]?"1":"0";
			}
		return s;
	}
	
	public void valueOf(String s){
		char[] c = s.toCharArray();
		for(int i = 0;i<column;i++)
			for(int j = 0;j<row;j++){
				table[i][j] = (c[i*row+j]=='1'?true:false);
			}
	}
}
