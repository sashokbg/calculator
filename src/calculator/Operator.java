package calculator;

public class Operator extends ExpressionElement implements Comparable<Operator>{

	private String type;
	private int order;

	public Operator(String type){
		this.type=type;
		if(this.type.equals("*")||this.type.equals("/")){
			order = 1;
		}
		if(this.type.equals("+")||this.type.equals("-")){
			order = 0;
		}
	}
	
	public int apply(Operand a, Operand b) {
		int value = 0;
		switch(type){
		case "*": value = a.getValue()*b.getValue(); break;
		case "/": value = a.getValue()/b.getValue();  break;
		case "+": value = a.getValue()+b.getValue(); ; break;
		case "-": value = a.getValue()-b.getValue(); ; break;
		}
		
		return value;
	}
	
	@Override
	public String toString(){
		return type;
	}
	
	@Override
	public int compareTo(Operator otherOperator){
		return (this.order < otherOperator.order ) ? -1: (this.order > otherOperator.order) ? 1:0 ;
	}
}
