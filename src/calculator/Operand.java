package calculator;

public class Operand extends ExpressionElement{
	private int value;

	//TODO create a factory
	
	public Operand(){
		
	}
	
	public Operand(String stringValue){
		try{
			this.value = Integer.parseInt(stringValue);
		}catch(NumberFormatException e){
			System.err.println("Unable to create Operand from inavlid number.");
			this.value=0;
		}
	}
	
	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
	
	public String toString(){
		return ""+value;
	}
}
