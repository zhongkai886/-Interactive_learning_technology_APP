package com.alchemy.mindlibrary;

import wjk.java.table.BitTable;

public class MindColorThanTable {

	BitTable table;
	public MindColorThanTable(){
		this(TableString);
	}
	public MindColorThanTable(String s){
		table = new BitTable(SIZE, SIZE);
		read(s);
	}
	
	public boolean than(int c1, int c2){
		return table.getCell(c1, c2);
	}

	public boolean than(MindColorType t1, MindColorType t2){
		return table.getCell(indexOf(t1), indexOf(t2));
	}
	
	public void setThan(int c1, int c2, boolean b){
		table.setCell(c1, c2, b);
		table.setCell(c2, c1, !b);
	}
	
	public String export(){
		return table.toString();
	}
	
	public void read(String s){
		table.valueOf(s);
	}

	//TODO static
	public static final int SIZE = 4;
	public static final int INDEX_ORANGE = 0;
	public static final int INDEX_GREEN = 1;
	public static final int INDEX_BLUE = 2;
	public static final int INDEX_YELLOW = 3;

	static String TableString = "0111001000000110";

	public static int indexOf(MindColorType t){
		switch(t){
			case Orange:
				return INDEX_ORANGE;
			case Green:
				return INDEX_GREEN;
			case Blue:
				return INDEX_BLUE;
			case Yellow:
				return INDEX_YELLOW;
		}
		return 0;
	}

	public static void SetUp(String s){
		TableString = s;
	}
}
