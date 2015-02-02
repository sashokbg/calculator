package calculator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {
	private String expression;
	
	public Parser(String expression){
		this.expression = expression;
	}
	
	public String getExpression() {
		return expression;
	}

	//(3+(4*5))
	public ArrayList<Expression> findMostInnerExpressions(){
//		for(expression.toCharArray()
		
		return null;
	}
	
	public void setExpression(String expression) {
		this.expression = expression;
	}
	
	public ArrayList<ExpressionElement> parse(){
		ArrayList<ExpressionElement> parsedExpression = new ArrayList<ExpressionElement>();
		
		Pattern pattern = Pattern.compile("[0-9]{1,}");
	    Matcher matcher = pattern.matcher(expression);
		
		while(matcher.find()){
			String s = matcher.group();
			Operand operand = new Operand(s);
			operand.setStartPosition(matcher.start());
			parsedExpression.add(operand);
			System.out.println(s+" at "+matcher.start()+" to "+matcher.end());
		}
		
		pattern = Pattern.compile("[/*+-]{1,}");
		matcher = pattern.matcher(expression);
		
		while(matcher.find()){
			String s = matcher.group();
			Operator operator = new Operator(s);
			operator.setStartPosition(matcher.start());
			parsedExpression.add(operator);
			System.out.println(s+" at "+matcher.start()+" to "+matcher.end());
		}
		
		pattern = Pattern.compile("[(]{1,1}|[)]{1,1}");
		matcher = pattern.matcher(expression);
		
		while(matcher.find()){
			String s = matcher.group();
			if(s.equals("(")){
				OpenBracket open = new OpenBracket();
				open.setStartPosition(matcher.start());
				parsedExpression.add(open);
			}
			else{
				ClosingBracket close = new ClosingBracket();
				close.setStartPosition(matcher.start());
				parsedExpression.add(close);
			}
			System.out.println(s+" at "+matcher.start()+" to "+matcher.end());
		}
		
		Collections.sort(parsedExpression, new Comparator<ExpressionElement>() {
			@Override
			public int compare(ExpressionElement o1, ExpressionElement o2) {
				if(o1.getStartPosition()==o2.getStartPosition())
					return 0;
				return o1.getStartPosition()>o2.getStartPosition()?1:-1;
			}
		});
//		
//		if(expression!=null){
//			for(Character c : expression.toCharArray()){
//				if(c=='(')
//					parsedExpression.add(new OpenBracket());
//				else if(c==')')
//					parsedExpression.add(new ClosingBracket());
//				else if(c=='/' || c=='*' || c=='+' || c=='-')
//					parsedExpression.add(new Operator(c.toString()));
//				else{
//					Integer.parseInt(c.toString());
//					parsedExpression.add(new Operand(c.toString()));
//				}
//			}
//		}
		
		return parsedExpression;
	}
}
