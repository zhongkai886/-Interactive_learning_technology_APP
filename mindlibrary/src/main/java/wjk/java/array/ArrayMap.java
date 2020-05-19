package wjk.java.array;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ArrayMap {

	ArrayList<HashMap<String, Object>> list = new ArrayList();
	HashMap<String, Object> map = new HashMap();

	public int size(){
		return list.size();
	}

	public void store(){
		list.add(map);
		map = new HashMap();
	}

	public void put(String e, Object v, int i){
		list.get(i).put(e, v);
	}

	public void put(String e, Object v){
		map.put(e, v);
	}

	public Object get(String e, int i){
		return list.get(i).get(e);
	}

	public int getInt(String e, int i){
		Object obj = list.get(i).get(e);
		if(obj == null)return 0;
		return Integer.valueOf(list.get(i).get(e).toString());
	}

	public float getFloat(String e, int i){
		Object obj = list.get(i).get(e);
		if(obj == null)return 0;
		return Float.valueOf(list.get(i).get(e).toString());
	}

	public String getString(String e, int i){
		Object obj = list.get(i).get(e);
		if(obj == null)return "";
		return list.get(i).get(e).toString();
	}

	public int[] getIntArray(String e){
		int[] array = new int[list.size()];
		for(int i = 0; i<array.length; i++){
			array[i] = getInt(e, i);
		}
		return array;
	}

	public float[] getFloatArray(String e){
		float[] array = new float[list.size()];
		for(int i = 0; i<array.length; i++){
			array[i] = getFloat(e, i);
		}
		return array;
	}

	public String[] getStringArray(String e){
		String[] array = new String[list.size()];
		for(int i = 0; i<array.length; i++){
			array[i] = getString(e, i);
		}
		return array;
	}

	//TODO enum

	public void put(Enum e, Object v, int i){
		put(e.name(), v, i);
	}

	public void put(Enum e, Object v){
		put(e.name(), v);
	}

	public Object get(Enum e, int i){
		return get(e.name(), i);
	}

	public int getInt(Enum e, int i){
		return getInt(e.name(), i);
	}

	public float getFloat(Enum e, int i){
		return getFloat(e.name(), i);
	}

	public String getString(Enum e, int i){
		return getString(e.name(), i);
	}

	public int[] getIntArray(Enum e){
		return getIntArray(e.name());
	}

	public float[] getFloatArray(Enum e){
		return getFloatArray(e.name());
	}

	public String[] getStringArray(Enum e){
		return getStringArray(e.name());
	}

	//TODO Object
	
	@Override
	public String toString(){
		return list.toString();
	}
	
	public static <T> T valueOf(Class<T> cls, String data) throws InstantiationException, IllegalAccessException{

		T obj = cls.newInstance();
		if (!(obj instanceof ArrayMap)) return null;

		ArrayMap array = (ArrayMap) obj;
		
		Matcher hash, key, value;
		String hashstr;
    	hash = Pattern.compile("\\{[^\\{\\}]+\\}").matcher(data);
		while(hash.find()){
			hashstr = hash.group();
			key = Pattern.compile("(?<=\\{|\\, )[^,{}\\=\\{\\}]+(?=\\=)").matcher(hashstr);
			value = Pattern.compile("(?<=\\=)[^,{}\\=\\{\\}]+(?=\\,|\\})").matcher(hashstr);
			
			while(key.find() && value.find()){
				array.put(key.group(), value.group());
			}
			array.store();
		}
	    return obj;
	}

	public static <T extends ArrayMap> T[] valueOf(Class<T> cls, String[] data) throws InstantiationException, IllegalAccessException{
		T[] array = (T[]) Array.newInstance(cls, data.length);

		for(int i = 0;i<data.length; i++){
			array[i] = valueOf(cls, data[i]);
		}

		return array;
	}

}
