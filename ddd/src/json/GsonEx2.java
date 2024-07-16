package json;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GsonEx2 {
	public static void main(String[] args) {
		
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		
		//person 객체를 json 문자열로
		Person person1 = new Person("손흥민", 30);
		String person1Str = gson.toJson(person1);
		System.out.println(person1Str);
		
		//실습 1의 Json문자열을 Person 객체로 변환
		Person person2 = gson.fromJson(person1Str, Person.class);
		System.out.println(person2);
		
		//아래 Map객체를 Json 문자열로 변환 후 출력
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("국어", 100);
		map.put("수학", 90);
		map.put("영어", 80);
		
		String mapStr = gson.toJson(map);
		
		//json 문자열을 Map객체로 변환 후 출력
		Map<String, String> map2 = gson.fromJson(mapStr, Map.class);
		System.out.println(map2);
		
		//List객체를 Json문자열로 변환 후 출력
		List<Person> personList = new ArrayList<Person>();
		personList.add(new Person("이대호", 30));
		personList.add(new Person("이승엽", 40));
		personList.add(new Person("선동열", 50));
		
		String listStr = gson.toJson(personList);
		System.out.println(listStr);
		
		//실습 json문자열을 list객체로 변환 후 출력 
		List<Person> personList2 = gson.fromJson(listStr, List.class);
		System.out.println(personList2);
		
	}//main
}//class