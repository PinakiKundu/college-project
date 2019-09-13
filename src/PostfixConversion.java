/**
 * @(#)PostfixConversion.java
 *
 *
 * @author Sujit K. Biswas
 * @version 1.00 2008/4/12
 */

import java.util.Stack;

public class PostfixConversion {
	String infixExpr,strOpp,strOpp1;
	String oparr[];
	Object opp;
	char charOpp;
	int cnt;
	Stack oppStck;

	public PostfixConversion(String _infixExpr){
		oparr=new String[50];
		oppStck =new Stack();
		infixExpr=_infixExpr;
		cnt=0;

		for(int i=0;i<infixExpr.length();i++){
			charOpp=infixExpr.charAt(i);
			strOpp=Character.toString(charOpp);

			if(!(strOpp.equals(")")||strOpp.equals("(")||strOpp.equals("+")||strOpp.equals("|")||strOpp.equals(".")||strOpp.equals("*"))){
				//System.out.println(i+"operand");
				oparr[cnt++]=strOpp;//
				if(i<(infixExpr.length()-1)){//to avoid "ArrayIndexOutOfBoundsException".
					i++;
					if(infixExpr.charAt(i)=='*')
						oparr[cnt++]="*";

					else
						//Since the operator is not "*", hence undo the change in "i".
						i--;

				}
				//Checking whether there is any operator in the Stack.
				if(!oppStck.isEmpty()){
					opp=oppStck.pop();
					strOpp1=opp.toString();
					//If the operator is "(" ,then discard.
					//Else store it into the "oparr" array.
					if(!strOpp1.equals("(")){
						oparr[cnt++]=strOpp1;

					}

				}

			}

			//If the operator found is "*", then store it in-place into the array.
			else if(strOpp.equals("*")){
				oparr[cnt++]=strOpp;
				//System.out.println(i+"*");
			}

			else{

				if(strOpp.equals("+")||strOpp.equals("|")||strOpp.equals(".")||strOpp.equals("(")){
					//System.out.println(i+"operator");
					oppStck.push(strOpp);
				}

			}


		}
		if(!oppStck.isEmpty()){
			opp=oppStck.pop();
			strOpp=opp.toString();
			if(!strOpp.equals("("))
				oparr[cnt++]=strOpp;
		}
		System.out.println("Infix: "+_infixExpr);
		String postFixExpr=oparr[0];
		for(int i=1;i<cnt;i++)
		{
			postFixExpr=postFixExpr + oparr[i];
			//System.out.print(oparr[i]);
		}
		System.out.println("PostFix: "+postFixExpr);
		CreateNfa nfaObj=new CreateNfa(postFixExpr);
	}

	/*public static void main(String as[])
	{
		PostfixConversion obj=new PostfixConversion("a.b*");
	}*/


}


