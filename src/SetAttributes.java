/**
 * @(#)SetAttributes.java
 *
 *
 * @author Sujit K. Biswas(04CSE43)
 * @version 1.00 2008/5/9
 */

import java.util.Random;
import java.util.StringTokenizer;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class SetAttributes{
	Random randomValue;
	int xValue,yValue;
	int maxValue;
	String finalStates[];
	String startState;
	String stateList[];
    final String stateAttributes[][];
//	String tateList[] =new String[numberOfStates];
	

    public SetAttributes(final int numberOfStates,String _startState,String _stateList[],String _finalStates[],final String oparr[][],final int cnt){

    	stateAttributes=new String[500][4];
    	randomValue = new Random(50);
    	startState=_startState;
    	finalStates=_finalStates;
    	stateList=_stateList;
    	maxValue=400;
    	for(int k=0;k<500;k++)
			stateAttributes[k][3]="0";
		for(int i=0;i<numberOfStates;i++){

			stateAttributes[i][0]=stateList[i];
			this.setCoordinate(i);
			//stateAttributes[i][1]=Integer.toString(xValue);
			//stateAttributes[i][2]=Integer.toString(yValue);
			this.determineTag(i);

		}
		SwingUtilities.invokeLater(new Runnable() {
         public void run() {
           new  SampleFr1( "Graph",stateAttributes,oparr,numberOfStates,cnt).makeUI();
         }
   	});
}

	public SetAttributes(final int numberOfStates,String _startState,String _finalStates[],final String oparr[][],final int cnt) {

		stateAttributes=new String[500][4];
    	randomValue = new Random(50);
    	stateList =new String[numberOfStates];
    	startState=_startState;
    	finalStates=_finalStates;
    	maxValue=400;
    	System.out.println("sujit");
    	for(int i=0;i<numberOfStates;i++)
    		stateList[i]=Integer.toString(i);

    	for(int k=0;k<500;k++){
			stateAttributes[k][3]="0";
		}

    	for(int i=0;i<numberOfStates;i++){

    		stateAttributes[i][0]=Integer.toString(i);
    		this.setCoordinate(i);
    		this.determineTag(i);

    		//stateAttributes[i][1]=Integer.toString(xValue);
    		//stateAttributes[i][2]=Integer.toString(yValue);
    	}
    	for(int i=0;i<numberOfStates;i++){
    		for(int j=0;j<4;j++)
    			System.out.println(stateAttributes[i][j]);
    	}
    	SwingUtilities.invokeLater(new Runnable() {
         public void run() {
           new  SampleFr1( "Graph",stateAttributes,oparr,numberOfStates,cnt).makeUI();
         }
   	});
}
	//Determining the (x,y) coordinate value of each states.
	public void setCoordinate(int i){
		xValue = randomValue.nextInt(maxValue);
		yValue = randomValue.nextInt(maxValue);
		stateAttributes[i][1]=Integer.toString(xValue);
    	stateAttributes[i][2]=Integer.toString(yValue);

	}

	//Determining the tag value of each state.
	public void determineTag(int i){

		String basicState;
		StringTokenizer token=new StringTokenizer(stateList[i],",");


		while(token.hasMoreTokens()){
			basicState=token.nextToken();

			//If the state is a Start state then assign "1" as tag.
			if(basicState.equals(startState) & !stateAttributes[i][3].equals("2") && !stateAttributes[i][3].equals("3")){
				stateAttributes[i][3]="1";
				System.out.println("hello" + stateAttributes[i][3] + startState);

			}
			System.out.println("no exception 1");

			for(int j=0;!finalStates[j].equals("-1");j++){
				System.out.println("no exception 2"+j);
				//If the state is only a Final state then asign "2" as tag.
				if(basicState.equals(finalStates[j])&& !stateAttributes[i][3].equals("1")){

					stateAttributes[i][3]="2";
					break;
				}

				//If the state is a Start state & Final state then asign "3" as tag.
				if(basicState.equals(finalStates[j])&& stateAttributes[i][3].equals("1") ){

					stateAttributes[i][3]="3";
					break;
				}

			}
		}//End of token.hasMoreTokens().

			//If none then assign "4" as tag.

			if(!stateAttributes[i][3].equals("1") && !stateAttributes[i][3].equals("2") && !stateAttributes[i][3].equals("3"))
				stateAttributes[i][3]="4";


		System.out.println("xhk1 "+ stateAttributes[i][3]);

	}//End of determineTag().

}// End of class SetAttribute.