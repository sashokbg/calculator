package calculator;

public class Expression extends Operand{
	public Expression(String stringValue) {
		super(stringValue);
		// TODO Auto-generated constructor stub
	}

	public Expression(){
		
	}

	private Operand a;
	private Operand b;
	private Operator op;
	
	public Operator getOp() {
		return op;
	}

	public void setOp(Operator op) {
		this.op = op;
	}

	public Operand getA() {
		return a;
	}

	public void setA(Operand a) {
		this.a = a;
	}

	public Operand getB() {
		return b;
	}

	public void setB(Operand b) {
		this.b = b;
	}

	public Operator getOperator() {
		return op;
	}

	public void setOperator(Operator operator) {
		this.op = operator;
	}

	
	@Override
	public int getValue(){
		return op.apply(a,b);
	}
}
