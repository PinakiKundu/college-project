/* *Implementation of Thompson's Construction algorithm.
   *The program will accept a postfix form of any regular expression as input
    and will generate the corresponding NFA in the form of HashMap.
   *The set of accepted operators are "+","|","." and "*".
   *"|" and "+" can be used alternatively.
   *
   *@author  Sujit K. Biswas.(04CSE43)
 */
import java.util.Stack;
import java.*;
import java.util.*;

class CreateNfa
        {
        int i;
        int j;
        Object k;
        Object l;
        Object m;
        int x,cnt;
        HashMap<String, String> trans;
        //String postFix= "ab*.";  //Postfix form of regular expression.
        char charSymbol;
        String stringSymbol;
        String oldVal;
        String input[] =new String[50];
        SetAttributes obj;//=new SetAttributes((j-2),startState,finalStates);


        public void addTransition(String key,String value)
			{
			if(trans.containsKey(key))
				{
				oldVal=trans.get(key);
				trans.put(key,oldVal + "," + value);
				}
			else
				trans.put(key,value);

			}

        static public void main(String a[])
                {
                CreateNfa obj = new CreateNfa("ab*.");

                }

        public CreateNfa(String postFix)
                {
                i=0;
                j=1;
                x=0;
                cnt=0;
				Stack i_stack = new Stack();   //Stack containing the start node.
                Stack j_stack = new Stack();   //Stack containing the end node.
                trans = new HashMap<String, String>();   //Transition table as Sparse Matrix.
				String startState;             //Array containing the start state.
				String[] finalStates=new String[500]; //Array containing final states.

                while(x<postFix.length())      //Checking whether index "x" is within the bounds of "postFix".
                        {
                        charSymbol=postFix.charAt(x);
                        stringSymbol = Character.toString(charSymbol);  // Type Conversion from Char to String

			            // if the symbol found is an operand only.
                        if(! (stringSymbol.equals("+") ||  stringSymbol.equals(".") ||  stringSymbol.equals("*")||  stringSymbol.equals("|")))
                                {

                                //Adding transition rule in the State Table.
                                this.addTransition(Integer.toString(i) +","+ stringSymbol,Integer.toString(j));
                                i_stack.push(i);
                                j_stack.push(j);
                                i=i+2;
                                j=j+2;
								input[cnt++]=stringSymbol; //adding input symbol to the input[] array
			                    }
			            //else the symbol found is an operator.
                        else
                                {
                                if (stringSymbol.equals("+") || stringSymbol.equals("|"))     //if the operator found is "+" or "|".
                                        {
                                        k=i_stack.pop();

                                        this.addTransition(Integer.toString(i)+",^",k.toString());

                                        k=i_stack.pop();

                                        this.addTransition(Integer.toString(i) + ",^",k.toString());

                                        i_stack.push(i);

                                        k=j_stack.pop();


                                        this.addTransition(k.toString() + ",^",Integer.toString(j));
                                        k=j_stack.pop();


                                        this.addTransition(k.toString() + ",^",Integer.toString(j));
                                        j_stack.push(j);
                                        i=i+2;j=j+2;
                                        }
                                 else if (stringSymbol.equals("."))  //if the operator is ".".
                                        {
                                        k=i_stack.pop();

                                        l=j_stack.pop();
                                        m=j_stack.pop();
                                        j_stack.push(l);
                                        this.addTransition(m.toString() + ",^",k.toString());


                                        }
                                 else                		     //if the operator is "*".
                                        {
                                        k=i_stack.pop();
                                        l=j_stack.pop();
                                        this.addTransition(l.toString() + ",^",k.toString());
                                        this.addTransition(Integer.toString(i) + ",^",Integer.toString(j));
                                        this.addTransition(l.toString() + ",^",Integer.toString(j));
                                        this.addTransition(Integer.toString(i) + ",^",k.toString());

                                        i_stack.push(i);
										j_stack.push(j);
                                        i=i+2;j=j+2;
                                        }
                                 } // end of outer else statement

                        x++;

                        } //end of while loop
                input[cnt++]="-1"; //marking the end of "input[]" array with "-1".

                //Storing the start state in the String object "startState".
				k=i_stack.pop();
				startState=k.toString();

				//Storing all the final states in array "finalStates[]".
				x=0;
				while(!j_stack.empty())
					{
					k=j_stack.pop();
					finalStates[x++]=k.toString();
					}
				finalStates[x]="-1";

				for(int y=0;finalStates[y]!="-1";y++)
					{
					System.out.println("NFA Final States ::" + finalStates[y]);

					}

				//obj=new SetAttributes((j-2),startState,finalStates);
				System.out.println("NFA");



				System.out.println("Start State :" + startState);
				System.out.println("J value :"+(j-2));
                //Creating a set view of HashMap for printing HashMap.
                Set<Map.Entry<String, String>> Set = trans.entrySet();
				//Printing the HashMap.
				System.out.println("NFA Production rules ::");
				for(Map.Entry<String, String> display : Set)
					{
					System.out.print(display.getKey()+ " --> ");
					System.out.println(display.getValue());
					}

				Cnvrt obj=new Cnvrt(trans,finalStates,startState,1);
				SubsetConst obj1=new SubsetConst(trans,startState,input,finalStates);
                }

        }

