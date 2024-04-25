package javabasic;

public class Operator {
	public static void main(String[] args) {
		int i = 100;
		int j = -i;
		
		int i2 = i + j;
		
		int i3 = i2 == 100 ? 100 : 0;
		
		System.out.println(i);
		System.out.println(j);
		System.out.println(12);
		System.out.println(13);
		System.out.println("----------");
		
		int i4 = 10;
		int i5 = 20;
		System.out.println(i4 + i5);
		System.out.println(i4% i5);
		
		int i6 = 15;
		if(i6==15){
			System.out.println("i6은 15입니다.");
		}else{
			System.out.println("i6은 5가 아닙니다.");
		}
		System.out.println("----------");
		boolean b = false;
		
		System.out.println(b&true);
		System.out.println(b&&false);
		System.out.println(b|true);
		System.out.println(b||false);
		System.out.println("----------");
		//and(&&)좌항이 false라면 우항을 평가하지 않음. 전체가 false가 되니까
		//or(||)은 좌항이 true면 우랑을 평가하지 않음. 전체가 true가 되니까
		System.out.println(b&true);
		System.out.println(b&&false);
		System.out.println(b|true);
		System.out.println(b||false);
		
		
	}//main
}//class
