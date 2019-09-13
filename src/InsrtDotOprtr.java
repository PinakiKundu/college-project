/**
 * @(#)InsrtDotOprtr.java

 *	Program to insert the Concatenation operator(.) in places where it is required.
 *	Allowed operators in the regular expression are "+" , "|" , "." , "*".
 *	Opening and Closing brackets can also be used where required.
 *	The "+" and "|" operators can be used interchangebly.They bear the same meaning.

 * @author Sujit K. Biswas(04CSE43)
 * @version 1.00 2008/5/17
 */


public class InsrtDotOprtr {

	char wFRegx[];
	int j;
	int tempIndx;
	String regx;
    public InsrtDotOprtr(String regExpr) {

    	wFRegx=new char[50];
    	j=0;

    	for(int i=0;i<regExpr.length();i++){
			//If the symbol is an "operand or an closing Bracket".
    		if(regExpr.charAt(i)!='+'&regExpr.charAt(i)!='|'&regExpr.charAt(i)!='.'&regExpr.charAt(i)!='*'&regExpr.charAt(i)!='('){
    			System.out.println("hello operand" + i);
    			//Copy the operand or closing bracket.
    			wFRegx[j++]=regExpr.charAt(i);
    			//Conditional checking to avoid "ArrayIndexOutOfBoundsException".
    			if(i<(regExpr.length()-1)){
    				//Using temporary index for look ahead.
    				tempIndx=i+1;
    				//If the next symbol is not an "operator or a closing bracket" i.e complemented condition.
    				if(regExpr.charAt(tempIndx)!='+'&regExpr.charAt(tempIndx)!='|'&regExpr.charAt(tempIndx)!='.'&regExpr.charAt(tempIndx)!='*'&regExpr.charAt(tempIndx)!=')'){
    					//Then insert a "." operator.
    					wFRegx[j++]='.';

    				}
    			}
    		}
			//If the symbol is "*".
    		else if(regExpr.charAt(i)=='*'){
    			System.out.println("hello star" + i);
    			//Then copy the "*" operator in place.
    			wFRegx[j++]='*';
    			if(i<(regExpr.length()-1)){
    				tempIndx=i+1;
    				//If there is no other operator or closing bracket after the "*".
    				if(regExpr.charAt(tempIndx)!='+'&regExpr.charAt(tempIndx)!='|'&regExpr.charAt(tempIndx)!='.'&regExpr.charAt(tempIndx)!='*'&regExpr.charAt(tempIndx)!=')'){
    					//Then insert ".".
    					wFRegx[j++]='.';
    				}
    			}
    		}
    		//If the symbol is any operator other than "*" or an opening bracket.
    		else if(regExpr.charAt(i)=='.'|regExpr.charAt(i)=='+'|regExpr.charAt(i)=='|'|regExpr.charAt(i)=='('){
    			//Then copy the symbol in place.
    			wFRegx[j++]=regExpr.charAt(i);
    		}
			//If all the above conditions fail.
    		else{
    			//And if the symbol is ")".
    			if(regExpr.charAt(i)!=')'){
    				//Then copy the symbol in place.
    				wFRegx[j++]=')';
    				if(i<(regExpr.length()-1)){
    					tempIndx=i+1;
    					//If the next symbol is not "+ , | , . , * , )".
    					if(regExpr.charAt(tempIndx)!='+'&regExpr.charAt(tempIndx)!='|'&regExpr.charAt(tempIndx)!='.'&regExpr.charAt(tempIndx)!='*'&regExpr.charAt(tempIndx)!=')'){
    						//Then insert ".".
    						wFRegx[j++]='.';
    					}
    				}
    			}
    		}





    	} //End of outer "for" loop.
    //Coping the contents of the Character type array to a String variable.
	regx=Character.toString(wFRegx[0]);
	for(int i=1;i<j;i++){
		regx=regx + Character.toString(wFRegx[i]);

	}


	System.out.println("J value: "+ j);
	System.out.println("input regx: "+regExpr);
	System.out.println("output regx: "+regx);
	System.out.println("Hurray, success at last");
	PostfixConversion exp=new PostfixConversion(regx);

    }
	/*public static void main (String[] args) {
		InsrtDotOprtr obj =new InsrtDotOprtr("a(b+cd)*");
	}*/

}