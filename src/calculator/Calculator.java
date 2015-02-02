package calculator;

import java.util.ArrayList;

public class Calculator {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
//		String expression = "(((2+3)-1)+1)";
		String expression = "2*(3+4)";
		Parser parser = new Parser(expression);
		Expression parsedExpression = createExpression(parser.parse());
//		getSubExpression(parser.parse(), 0);
		//will call recursively all inner expressions and get final result
//		parsedExpression.getValue();
	
	}
	
	private static ArrayList<ExpressionElement> getSubExpression(ArrayList<ExpressionElement> parsedExpression, int startingPosition){
		ArrayList<ExpressionElement> result = new ArrayList<ExpressionElement>();
		
		//we start at one opening bracket and while counter is bigger than zero, we continue searching for closing brackets
		int brackets = 0;
		int counter = startingPosition;
		
		do{
			ExpressionElement element = parsedExpression.get(counter);
			if(element instanceof ClosingBracket){
				brackets--;
			}
			else if(element instanceof OpenBracket){
				brackets++;
			}
			
			if(brackets>=0)
				result.add(element);
			
			counter++;
		}while(brackets>=0 && counter < parsedExpression.size());
	
		System.out.println("Created subexpression :");
		for(ExpressionElement element: result){
			System.out.println(element);
		}
		
		//remove start and end bracket if any
		if(result.get(0) instanceof OpenBracket){
			ArrayList<ExpressionElement> noBracketsResult = new ArrayList<ExpressionElement>();
			noBracketsResult.addAll(result.subList(1, result.size()-1));
			
			result = noBracketsResult;
		}
		
		return result;
	}
	
	private static Expression createExpression(ArrayList<ExpressionElement> parsedExpression) {
		Expression e = new Expression();
		
		for(int i = 0; i< parsedExpression.size(); i++){
			ExpressionElement element = parsedExpression.get(i);
			if(element instanceof OpenBracket){
				ArrayList<ExpressionElement> subExpressionElements = getSubExpression(parsedExpression, i);
				Expression subExpression = createExpression(subExpressionElements);
				
				//cut the subexpression from i (incl) to subExpression.length
				ArrayList<ExpressionElement> replacement = replace(parsedExpression, subExpression, i, subExpressionElements.size());
				parsedExpression = replacement;
				if(e.getA()==null)
					e.setA((Operand) element);
				else{
					e.setB((Operand) element);
				}
			}
			else if(element instanceof Operand){
				if(e.getA()==null)
					e.setA((Operand) element);
				else{
					e.setB((Operand) element);
				}
			}
			else if(element instanceof Operator){
				e.setOp((Operator) element);
			}
		}
		
		return e;
	}

	private static ArrayList<ExpressionElement> replace(ArrayList<ExpressionElement> parsedExpression,
			Expression subExpression, int from, int to) {
		ArrayList<ExpressionElement> before = new ArrayList<ExpressionElement>(parsedExpression.subList(0, from));
		ArrayList<ExpressionElement> after = new ArrayList<ExpressionElement>(parsedExpression.subList(to, parsedExpression.size()));
		
		ArrayList<ExpressionElement> result = new ArrayList<ExpressionElement>();
		result.addAll(before);
		result.add(subExpression);
		result.addAll(after);
		
		return result;
	}
}



