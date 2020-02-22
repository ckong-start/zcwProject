package exer1.pojo;

import exer.pojo.Calculate;

public class Calculator implements Calculate {

	@Override
	public int add(int num1, int num2) {
		System.out.println("目标方法：public int add(int num1, int num2)");
		int result = num1 + num2;
		return result;
	}
	
	@Override
	public int add(int num1, int num2, int num3) {
		System.out.println("目标方法：public int add(int num1, int num2, int num3)");
		int result = num1 + num2 + num3;
		return result;
	}
	
	@Override
	public int div(int num1, int num2) {
		System.out.println("目标方法：public int div(int num1, int num2)");
		int result = num1 / num2;
		return result;
	}

}
