package packages;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
	
	// Abstract ============================================================
	// In this class a random value is generated by the player.
	// based on the current pon position, the random value decides which cells 
	// on the board becomes active for the player to move his/her pon to.
	// the output of the mehtod "getMultipathMethod" is a list om cell ID active values 
	// =====================================================================


public class setMultiPath {
	
	// Calculte direction method ===========================================
	// A numbers of methods calculating the number of step in the positive or negative direction
	// input = the current x or y location on the board
	// =====================================================================

			  public static int add(int input) { // add(pos);
				  
				  int x = input +1;
				  
				  if ( x > 7 ) { // 7 => 2 temporary value
					  x = 99;
				  } 
		
				  return x;
			  }
			  
			  
			  public static int sub(int input) { // sub(pos);
				  
				  int x = input -1;
				  
				  if ( x < 0 ) {
					  x = 99;
				  } 
		
				  return x;
			  }
			  
			  public static int add2(int input) { // add2(pos);
				  
				  int x = input +2;
				  
				  if ( x > 7 ) { // 7 => 2 temporary value
					  x = 99;
				  } 
		
				  return x;
			  }
			  
			  
			  public static int sub2(int input) { // sub2(pos);
				  
				  int x = input -2;
				  
				  if ( x < 0 ) {
					  x = 99;
				  } 
		
				  return x;
			  }

	  

		// get player cell options ============================================
		// 0. construct method:	find out current player next move
			  
		// 1. get player data	Get current player data form XML-file
			  			  
		// 3. initialize list	set up a list with starter values
		//						later we will fill this list with cell ID, which the player can move his/her pon to		

		// 4. convert datatype:	convert current X & Y  String position to Integer values
			  
		// 5. calculte Option:	based on the gamble value and current pon position a number of posible path get calculated
		// 5.1 case 1:			move pon 	1 step 			up/down
		// 5.2 case 2:			move pon 	1 step 			left/right
		// 5.3 case 3:			move pon 	1 steps 		crossway
		// 5.4 case 4:			move pon 	1 or 2 step 	up/down
		// 5.5 case 5:			move pon 	1 or 2 step 	left/right
		// 5.6 case 6:			move pon 	1 0r 2 step 	crossway
			  
		// 6 Calculate directional step
		// 6.1 bind X/Y 		bind new posistion X & Y to XY, this value will represent the cell ID
		// 6.2 set Return value:position XY set into a slot, ready to export out of this method
		// 6.3 empty slot:		some port ready to export out of this method will stay empty
			  
		// 7 export data:		Export generated data out of this method
		//						This data resambles cell ID this will be active for the player to move to
		// =====================================================================  

		  // 0. Construct Method
		  public static int[] getMultipathMethod(int randomNum) {

			  
				System.out.println("\n--------------------------------- START: getMultipath");
		

				// 1.  get player data
				String[] readLocation = readXml.readPlayerDataMethod();
				String playerName = 	readLocation[0];	
				String historyX = 		readLocation[3];
				String historyY = 		readLocation[4];
				String latitudeX = 		readLocation[5];
				String longitudeY = 	readLocation[6];
				  
				System.out.println("READ " +playerName +" DATA:");
				System.out.println("---------------------------------");
				System.out.println("current position X: " +historyX +"    latitudeX: " +latitudeX);
				System.out.println("current position Y: " +historyY +"   longitudeY: " +longitudeY);

			    
		     	// 3. initialize list
		     	int[] cellSwitch = { 0,0,0,0,0,0,0,0};
		     
		     	
				  System.out.println("PLAYER CELL OPTIONS");
				  System.out.println("---------------------------------");
		     	
				// 4. String => Integer
			    int posX = Integer.valueOf(historyX);
		 	    int posY = Integer.valueOf(historyY);


			  // 5. get move Choices
			  switch (randomNum) {
			  	
			  	// 5.1
			    case 1:
			    	
			    	
				      // 6. Calculate directional step
				      int newPosYA = add(posY);
				      int newPosXA = posX;
				      
				      // 6.1 bind X & Y
				      String temA = Integer.toString(newPosYA) +Integer.toString(newPosXA);
				      int PosA = Integer.parseInt(temA);
					  
				      // 6.2 set Return value
				      cellSwitch[0] = PosA;
				      
				      System.out.println("case 1, option A: " +PosA);
					  
				      
			
				      
				      // get new Position, option B (Down)
				      int newPosYB = sub(posY);
				      int newPosXB = posX;
				      String tempB = Integer.toString(newPosYB) +Integer.toString(newPosXB);
				      int posB = Integer.parseInt(tempB);
					  System.out.println("case 1, option B: " +posB);
					  cellSwitch[1] = posB;	      

				  // keep empty
				  cellSwitch[2] = 999;
				  cellSwitch[3] = 999;
				  cellSwitch[4] = 999;
				  cellSwitch[5] = 999;
				  cellSwitch[6] = 999;
				  cellSwitch[7] = 999;
			     
			      break;
			    
			    // 5.2  
			    case 2:
			    		      
				   	  // get new Position, option C (Right)
				      int newPosYC = posY;
				      int newPosXC = add(posX);
				      String tempC = Integer.toString(newPosYC) +Integer.toString(newPosXC);
				      int posC = Integer.parseInt(tempC);
					  System.out.println("case 2, option C: " +posC);
					  cellSwitch[0] = posC;
			
				      
				      // get new Position, option D (Left)
				      int newPosYD = posY;
				      int newPosXD = sub(posX);
				      String tempD = Integer.toString(newPosYD) +Integer.toString(newPosXD);
				      int posD = Integer.parseInt(tempD);
					  System.out.println("case 2, option D: " +posD);
					  cellSwitch[1] = posD;	      
				  
				  // keep empty
				  cellSwitch[2] = 999;
				  cellSwitch[3] = 999;
				  cellSwitch[4] = 999;
				  cellSwitch[5] = 999;
				  cellSwitch[6] = 999;
				  cellSwitch[7] = 999;
			      
			      break;
			    
			    // 5.3 
			    case 3:
			    		      
			      	  // get new Position, option E (Up/Left
				      int newPosYE = add(posY);
				      int newPosXE = sub(posX);
				      String tempE = Integer.toString(newPosYE) +Integer.toString(newPosXE);
				      int posE = Integer.parseInt(tempE);
					  System.out.println("case 3, option E: " +posE);
					  cellSwitch[0] = posE;
			
				      
				      // get new Position, option F (Up/Right)
				      int newPosYF = add(posY);
				      int newPosXF = add(posX);
				      String tempF = Integer.toString(newPosYF) +Integer.toString(newPosXF);
				      int posF = Integer.parseInt(tempF);
					  System.out.println("case 3, option F: " +posF);
					  cellSwitch[1] = posF;	      
			
					  
				      // get new Position, option G (Down/Left)
				      int newPosYG = sub(posY);
				      int newPosXG = sub(posX);
				      String tempG = Integer.toString(newPosYG) +Integer.toString(newPosXG);
				      int posG = Integer.parseInt(tempG);
					  System.out.println("case 3, option G: " +posG);
					  cellSwitch[2] = posG;	
			
					  
				      // get new Position, option H (Down/Right)
				      int newPosYH = sub(posY);
				      int newPosXH = add(posX);
				      String tempH = Integer.toString(newPosYH) +Integer.toString(newPosXH);
				      int posH = Integer.parseInt(tempH);
					  System.out.println("case 3, option H: " +posH);
					  cellSwitch[3] = posH;	
				  
				  // keep empty
				  cellSwitch[4] = 999;
				  cellSwitch[5] = 999;
				  cellSwitch[6] = 999;
				  cellSwitch[7] = 999;
		
			      break;
			    
			    // 5.4 
			    case 4:
			      	      
				      // get new Position, option I (Up)
				      int newPosYI = add(posY);
				      int newPosXI = posX;
				      String tempI = Integer.toString(newPosYI) +Integer.toString(newPosXI);
				      int posI = Integer.parseInt(tempI);
					  System.out.println("case 4, option I: " +posI);
					  cellSwitch[0] = posI;
			
				      
				      // get new Position, option J (Down)
				      int newPosYJ = sub(posY);
				      int newPosXJ = posX;
				      String tempJ = Integer.toString(newPosYJ) +Integer.toString(newPosXJ);
				      int posJ = Integer.parseInt(tempJ);
					  System.out.println("case 4, option J: " +posJ);
					  cellSwitch[1] = posJ;	  
				      
			
				      // get new Position, option K (Up x2)
				      int newPosYK = add2(posY);
				      int newPosXK = posX;
				      String tempK = Integer.toString(newPosYK) +Integer.toString(newPosXK);
				      int posK = Integer.parseInt(tempK);
					  System.out.println("case 4, option K: " +posK);
					  cellSwitch[2] = posK;
					  
					  
				      // get new Position, option L (Down x2)
				      int newPosYL = sub(posY);
				      int newPosXL = posX;
				      String tempL = Integer.toString(newPosYL) +Integer.toString(newPosXL);
				      int posL = Integer.parseInt(tempL);
					  System.out.println("case 4, option L: " +posL);
					  cellSwitch[3] = posL;	
				  
				  // keep empty
				  cellSwitch[4] = 999;
				  cellSwitch[5] = 999;
				  cellSwitch[6] = 999;
				  cellSwitch[7] = 999;  
				  
			      break;
			      
			    // 5.5  
			    case 5:
				      		  
				      // get new Position, option M (Right)
				      int newPosYM = posY;
				      int newPosXM = add(posX);
				      String tempM = Integer.toString(newPosYM) +Integer.toString(newPosXM);
				      int posM = Integer.parseInt(tempM);
					  System.out.println("case 5, option M: " +posM);
					  cellSwitch[0] = posM;
			
				      
				      // get new Position, option N (Left)
				      int newPosYN = posY;
				      int newPosXN = sub(posX);
				      String tempN = Integer.toString(newPosYN) +Integer.toString(newPosXN);
				      int posN = Integer.parseInt(tempN);
					  System.out.println("case 5, option N: " +posN);
					  cellSwitch[1] = posN;	  
				      
			
				      // get new Position, option O (Right x2)
				      int newPosYO = posY;
				      int newPosXO = add2(posX);
				      String tempO = Integer.toString(newPosYO) +Integer.toString(newPosXO);
				      int posO = Integer.parseInt(tempO);
					  System.out.println("case 5, option O: " +tempO);
					  cellSwitch[2] = posO;
					  
					  
				      // get new Position, option P (Left x2)
				      int newPosYP = posY;
				      int newPosXP = sub2(posX);
				      String tempP = Integer.toString(newPosYP) +Integer.toString(newPosXP);
				      int posP = Integer.parseInt(tempP);
					  System.out.println("case 5, option P: " +tempP);
					  cellSwitch[3] = posP;	
				  
				  // keep empty
				  cellSwitch[4] = 999;
				  cellSwitch[5] = 999;
				  cellSwitch[6] = 999;
				  cellSwitch[7] = 999;
				      
				  break;
				    
				// 5.6  
			    case 6:
				      		  
					  // get new Position, option Q (Up/Left
				      int newPosYQ = add(posY);
				      int newPosXQ = sub(posX);
				      String tempQ = Integer.toString(newPosYQ) +Integer.toString(newPosXQ);
				      int posQ = Integer.parseInt(tempQ);
					  System.out.println("case 6, option Q: " +posQ);
					  cellSwitch[0] = posQ;
			
				      
				      // get new Position, option R (Up/Right)
				      int newPosYR = add(posY);
				      int newPosXR = add(posX);
				      String tempR = Integer.toString(newPosYR) +Integer.toString(newPosXR);
				      int posR = Integer.parseInt(tempR);
					  System.out.println("case 6, option R: " +posR);
					  cellSwitch[1] = posR;	      
			
					  
				      // get new Position, option S (Down/Left)
				      int newPosYS = sub(posY);
				      int newPosXS = sub(posX);
				      String tempS = Integer.toString(newPosYS) +Integer.toString(newPosXS);
				      int posS = Integer.parseInt(tempS);
					  System.out.println("case 6, option S: " +posS);
					  cellSwitch[2] = posS;	
			
					  
				      // get new Position, option T (Down/Right)
				      int newPosYT = sub(posY);
				      int newPosXT = add(posX);
				      String tempT = Integer.toString(newPosYT) +Integer.toString(newPosXT);
				      int posT = Integer.parseInt(tempT);
					  System.out.println("case 6, option T: " +posT);
					  cellSwitch[3] = posT;
					  
					  
					  // get new Position, option U (Up/Left x2)
				      int newPosYU = add2(posY);
				      int newPosXU = sub2(posX);
				      String tempU = Integer.toString(newPosYU) +Integer.toString(newPosXU);
				      int posU = Integer.parseInt(tempU);
					  System.out.println("case 6, option U: " +posU);
					  cellSwitch[4] = posU;
			
				      
				      // get new Position, option V (Up/Right x2)
				      int newPosYV = add2(posY);
				      int newPosXV = add2(posX);
				      String tempV = Integer.toString(newPosYV) +Integer.toString(newPosXV);
				      int posV = Integer.parseInt(tempV);
					  System.out.println("case 6, option V: " +posV);
					  cellSwitch[5] = posV;	      
			
					  
				      // get new Position, option W (Down/Left x2)
				      int newPosYW = sub(posY);
				      int newPosXW = sub(posX);
				      String tempW = Integer.toString(newPosYW) +Integer.toString(newPosXW);
				      int posW = Integer.parseInt(tempW);
					  System.out.println("case 6, option W: " +posW);
					  cellSwitch[6] = posW;	
			
					  
				      // get new Position, option X (Down/Right x2)
				      int newPosYX = sub(posY);
				      int newPosXX = add(posX);
				      String tempX = Integer.toString(newPosYX) +Integer.toString(newPosXX);
				      int posXZ = Integer.parseInt(tempX);
					  System.out.println("case 6, option X: " +posXZ);
					  cellSwitch[7] = posXZ;	
				  break;			  
			  }


		System.out.println("--------------------------------- END: getMultipath");


	    // 7. Export out of Method
	    return new int[] { cellSwitch[0], cellSwitch[1], cellSwitch[2], cellSwitch[3], cellSwitch[4], cellSwitch[5], cellSwitch[6], cellSwitch[7] };	    
	  }



		// set Cell to active =================================================
		// based on the "getMultipathMethod" the system now has al list of cell to be active on the board
		// The "setMultipathMethod" will set these cells to active mode,
		// this will give the player to opertionity to move his pon on the board
		  
		// 0. construct method
		  
		// 1. Cell Id value:		a list of values resambeling the cell ID on the board, for comparisons purpose
		// 1.1 Cell boolean valeu:	initialise list of boolean values for each Cell ID
		  
		// 2 loop list:				the output of the "getMultipathMethod" are compared with de cellID list
		// 2.1 if condition:			if the values are the same the Boolean in de "cellBool list" will become true
		// 2.2 else condition:			value in the "cellBool list" stay false"

		// 4. modify XML-file:			the "cellBool list" is set to update cell data in de XML-file
		// 4.1 get file:				get XML-file from the locl drive
		// 4.2 get element				get element "Globlas" from the XML-file
		// 4.3 get items				get all the cell items (by number) from the element "Globals"
		// 4.4 update items				update all item with thie booleans from the "cellBool list"
		// 4.5 write content			write all content to XML-file
		
		// 5. get name					get the name from the current player from the XML-file
		// 5.1 print content			print all the Boolean items
		// =====================================================================  
	  
		// 0. construct value
		public static void setMultipathMethod(int[] cellCoor) {


			System.out.println("\n--------------------------------- START: setMultipath");


		    // 1. Cell ID value
			int[] cellID = {
					00, 01, 02, 03, 04, 05, 06, 07,
					10, 11, 12, 13, 14, 15, 16, 17,
					20, 21, 22, 23, 24, 25, 26, 27,
					30, 31, 32, 33, 34, 35, 36, 37,
					40, 41, 42, 43, 44, 45, 46, 47,
					50, 51, 52, 53, 54, 55, 56, 57,
					60, 61, 62, 63, 64, 65, 66, 67,
					70, 71, 72, 73, 74, 75, 76, 77	};
			
			
			
			// 1.1 Cell Boolean value
			String[] cellBool = {			
					"false", "false", "false", "false", "false", "false", "false", "false", 
					"false", "false", "false", "false", "false", "false", "false", "false",
					"false", "false", "false", "false", "false", "false", "false", "false",
					"false", "false", "false", "false", "false", "false", "false", "false",
					"false", "false", "false", "false", "false", "false", "false", "false",
					"false", "false", "false", "false", "false", "false", "false", "false",
					"false", "false", "false", "false", "false", "false", "false", "false",
					"false", "false", "false", "false", "false", "false", "false", "false"	};

	
		// 2. loop list
	    for (int i = 0; i < cellID.length; i++) { 
	
	        // Nest Loop
	        for (int j = 0; j < cellCoor.length; j++) {        	  
	            
	            if ( cellCoor[j] == cellID[i]) {
	          	  cellBool[i] = "true";	 
	            } 
	        } 
	    } 


	    // 4. modify XML-file   
		   try {
			   
			// 4.1 get file
			String filepath = "src/data/gamesheet.xml";
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			Document doc = docBuilder.parse(filepath);

			
			// 4.2 get element
			Node globals = doc.getElementsByTagName("globals").item(0);

			// 4.3 get item
			NodeList list = globals.getChildNodes();			
			Node cell00 = list.item(7);
			Node cell01 = list.item(9);
			Node cell02 = list.item(11);
			Node cell03 = list.item(13);
			Node cell04 = list.item(15);
			Node cell05 = list.item(17);
			Node cell06 = list.item(19);
			Node cell07 = list.item(21);
			
			Node cell10 = list.item(23);
			Node cell11 = list.item(25);
			Node cell12 = list.item(27);
			Node cell13 = list.item(29);
			Node cell14 = list.item(31);
			Node cell15 = list.item(33);
			Node cell16 = list.item(35);
			Node cell17 = list.item(37);
			
			Node cell20 = list.item(39);
			Node cell21 = list.item(41);
			Node cell22 = list.item(43);
			Node cell23 = list.item(45);
			Node cell24 = list.item(47);
			Node cell25 = list.item(49);
			Node cell26 = list.item(51);
			Node cell27 = list.item(53);
			
			Node cell30 = list.item(55);
			Node cell31 = list.item(57);
			Node cell32 = list.item(59);
			Node cell33 = list.item(61);
			Node cell34 = list.item(63);
			Node cell35 = list.item(65);
			Node cell36 = list.item(67);
			Node cell37 = list.item(69);
			
			Node cell40 = list.item(71);
			Node cell41 = list.item(73);
			Node cell42 = list.item(75);
			Node cell43 = list.item(77);
			Node cell44 = list.item(79);
			Node cell45 = list.item(81);
			Node cell46 = list.item(83);
			Node cell47 = list.item(85);
			
			Node cell50 = list.item(87);
			Node cell51 = list.item(89);
			Node cell52 = list.item(91);
			Node cell53 = list.item(93);
			Node cell54 = list.item(95);
			Node cell55 = list.item(97);
			Node cell56 = list.item(99);
			Node cell57 = list.item(101);
			
			
			Node cell60 = list.item(103);
			Node cell61 = list.item(105);
			Node cell62 = list.item(107);
			Node cell63 = list.item(109);
			Node cell64 = list.item(111);
			Node cell65 = list.item(113);
			Node cell66 = list.item(115);
			Node cell67 = list.item(117);
			
			
			Node cell70 = list.item(119);
			Node cell71 = list.item(121);
			Node cell72 = list.item(123);
			Node cell73 = list.item(125);
			Node cell74 = list.item(127);
			Node cell75 = list.item(129);
			Node cell76 = list.item(131);
			Node cell77 = list.item(133);
			
			// 4.4 update items
			cell00.setTextContent(cellBool[0]);
			cell01.setTextContent(cellBool[1]);
			cell02.setTextContent(cellBool[2]);
			cell03.setTextContent(cellBool[3]);
			cell04.setTextContent(cellBool[4]);
			cell05.setTextContent(cellBool[5]);
			cell06.setTextContent(cellBool[6]);
			cell07.setTextContent(cellBool[7]);
			
			cell10.setTextContent(cellBool[8]);
			cell11.setTextContent(cellBool[9]);
			cell12.setTextContent(cellBool[10]);
			cell13.setTextContent(cellBool[11]);
			cell14.setTextContent(cellBool[12]);
			cell15.setTextContent(cellBool[13]);
			cell16.setTextContent(cellBool[14]);
			cell17.setTextContent(cellBool[15]);
			
			cell20.setTextContent(cellBool[16]);
			cell21.setTextContent(cellBool[17]);
			cell22.setTextContent(cellBool[18]);
			cell23.setTextContent(cellBool[19]);
			cell24.setTextContent(cellBool[20]);
			cell25.setTextContent(cellBool[21]);
			cell26.setTextContent(cellBool[22]);
			cell27.setTextContent(cellBool[23]);
			
			cell30.setTextContent(cellBool[24]);
			cell31.setTextContent(cellBool[25]);
			cell32.setTextContent(cellBool[26]);
			cell33.setTextContent(cellBool[27]);
			cell34.setTextContent(cellBool[28]);
			cell35.setTextContent(cellBool[29]);
			cell36.setTextContent(cellBool[30]);
			cell37.setTextContent(cellBool[31]);
			
			cell40.setTextContent(cellBool[32]);
			cell41.setTextContent(cellBool[33]);
			cell42.setTextContent(cellBool[34]);
			cell43.setTextContent(cellBool[35]);
			cell44.setTextContent(cellBool[36]);
			cell45.setTextContent(cellBool[37]);
			cell46.setTextContent(cellBool[38]);
			cell47.setTextContent(cellBool[39]);
			
			cell50.setTextContent(cellBool[40]);
			cell51.setTextContent(cellBool[41]);
			cell52.setTextContent(cellBool[42]);
			cell53.setTextContent(cellBool[43]);
			cell54.setTextContent(cellBool[44]);
			cell55.setTextContent(cellBool[45]);
			cell56.setTextContent(cellBool[46]);
			cell57.setTextContent(cellBool[47]);
			
			cell60.setTextContent(cellBool[48]);
			cell61.setTextContent(cellBool[49]);
			cell62.setTextContent(cellBool[50]);
			cell63.setTextContent(cellBool[51]);
			cell64.setTextContent(cellBool[52]);
			cell65.setTextContent(cellBool[53]);
			cell66.setTextContent(cellBool[54]);
			cell67.setTextContent(cellBool[55]);
			
			cell70.setTextContent(cellBool[56]);
			cell71.setTextContent(cellBool[57]);
			cell72.setTextContent(cellBool[58]);
			cell73.setTextContent(cellBool[59]);
			cell74.setTextContent(cellBool[60]);
			cell75.setTextContent(cellBool[61]);
			cell76.setTextContent(cellBool[62]);
			cell77.setTextContent(cellBool[63]);

			
			// 4.5 write content
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File(filepath));
			transformer.transform(source, result);


		   } catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		   } catch (TransformerException tfe) {
			tfe.printStackTrace();
		   } catch (IOException ioe) {
			ioe.printStackTrace();
		   } catch (SAXException sae) {
			sae.printStackTrace();
		   }
		   
			  
		  // 5. get name
		  String[] getName = readXml.readPlayerDataMethod();
		  String playerName = getName[0];


		    // 5.1 print content
		  System.out.println("UPDATED " +playerName +" DATA:");
		  System.out.println("---------------------------------");
		  if (Boolean.parseBoolean(cellBool[0]) == true) { System.out.println("cell00 = " +cellBool[0]); }
		  if (Boolean.parseBoolean(cellBool[1]) == true) { System.out.println("cell01 = " +cellBool[1]); }
		  if (Boolean.parseBoolean(cellBool[2]) == true) { System.out.println("cell02 = " +cellBool[2]); }
		  if (Boolean.parseBoolean(cellBool[3]) == true) { System.out.println("cell03 = " +cellBool[3]); }
		  if (Boolean.parseBoolean(cellBool[4]) == true) { System.out.println("cell04 = " +cellBool[4]); }
		  if (Boolean.parseBoolean(cellBool[5]) == true) { System.out.println("cell05 = " +cellBool[5]); }
		  if (Boolean.parseBoolean(cellBool[6]) == true) { System.out.println("cell06 = " +cellBool[6]); }
		  if (Boolean.parseBoolean(cellBool[7]) == true) { System.out.println("cell07 = " +cellBool[7]); }
	      
		  if (Boolean.parseBoolean(cellBool[8]) == true) { System.out.println("cell10 = " +cellBool[8]); }
		  if (Boolean.parseBoolean(cellBool[9]) == true) { System.out.println("cell11 = " +cellBool[9]); }
		  if (Boolean.parseBoolean(cellBool[10]) == true) { System.out.println("cell12 = " +cellBool[10]); }
		  if (Boolean.parseBoolean(cellBool[11]) == true) { System.out.println("cell13 = " +cellBool[11]); }
		  if (Boolean.parseBoolean(cellBool[12]) == true) { System.out.println("cell14 = " +cellBool[12]); }
		  if (Boolean.parseBoolean(cellBool[13]) == true) { System.out.println("cell15 = " +cellBool[13]); }
		  if (Boolean.parseBoolean(cellBool[14]) == true) { System.out.println("cell16 = " +cellBool[14]); }
		  if (Boolean.parseBoolean(cellBool[15]) == true) { System.out.println("cell17 = " +cellBool[15]); }
	      
		  if (Boolean.parseBoolean(cellBool[16]) == true) { System.out.println("cell20 = " +cellBool[16]); }
		  if (Boolean.parseBoolean(cellBool[17]) == true) { System.out.println("cell21 = " +cellBool[17]); }
		  if (Boolean.parseBoolean(cellBool[18]) == true) { System.out.println("cell22 = " +cellBool[18]); }
		  if (Boolean.parseBoolean(cellBool[19]) == true) { System.out.println("cell23 = " +cellBool[19]); }
		  if (Boolean.parseBoolean(cellBool[20]) == true) { System.out.println("cell24 = " +cellBool[20]); }
		  if (Boolean.parseBoolean(cellBool[21]) == true) { System.out.println("cell25 = " +cellBool[21]); }
		  if (Boolean.parseBoolean(cellBool[22]) == true) { System.out.println("cell26 = " +cellBool[22]); }
		  if (Boolean.parseBoolean(cellBool[23]) == true) { System.out.println("cell27 = " +cellBool[23]); }
	      
		  if (Boolean.parseBoolean(cellBool[24]) == true) { System.out.println("cell30 = " +cellBool[24]); }
		  if (Boolean.parseBoolean(cellBool[25]) == true) { System.out.println("cell31 = " +cellBool[25]); }
		  if (Boolean.parseBoolean(cellBool[26]) == true) { System.out.println("cell32 = " +cellBool[26]); }
		  if (Boolean.parseBoolean(cellBool[27]) == true) { System.out.println("cell33 = " +cellBool[27]); }
		  if (Boolean.parseBoolean(cellBool[28]) == true) { System.out.println("cell34 = " +cellBool[28]); }
		  if (Boolean.parseBoolean(cellBool[29]) == true) { System.out.println("cell35 = " +cellBool[29]); }
		  if (Boolean.parseBoolean(cellBool[30]) == true) { System.out.println("cell36 = " +cellBool[30]); }
		  if (Boolean.parseBoolean(cellBool[31]) == true) { System.out.println("cell37 = " +cellBool[31]); }
	      
		  if (Boolean.parseBoolean(cellBool[32]) == true) { System.out.println("cell40 = " +cellBool[32]); } 
		  if (Boolean.parseBoolean(cellBool[33]) == true) { System.out.println("cell41 = " +cellBool[33]); }
		  if (Boolean.parseBoolean(cellBool[34]) == true) { System.out.println("cell42 = " +cellBool[34]); }
		  if (Boolean.parseBoolean(cellBool[35]) == true) { System.out.println("cell43 = " +cellBool[35]); }
		  if (Boolean.parseBoolean(cellBool[36]) == true) { System.out.println("cell44 = " +cellBool[36]); }
		  if (Boolean.parseBoolean(cellBool[37]) == true) { System.out.println("cell45 = " +cellBool[37]); }
		  if (Boolean.parseBoolean(cellBool[38]) == true) { System.out.println("cell46 = " +cellBool[38]); }
		  if (Boolean.parseBoolean(cellBool[39]) == true) { System.out.println("cell47 = " +cellBool[39]); }
	      
		  if (Boolean.parseBoolean(cellBool[40]) == true) { System.out.println("cell50 = " +cellBool[40]); }
		  if (Boolean.parseBoolean(cellBool[41]) == true) { System.out.println("cell51 = " +cellBool[41]); }
		  if (Boolean.parseBoolean(cellBool[42]) == true) { System.out.println("cell52 = " +cellBool[42]); }
		  if (Boolean.parseBoolean(cellBool[43]) == true) { System.out.println("cell53 = " +cellBool[43]); }
		  if (Boolean.parseBoolean(cellBool[44]) == true) { System.out.println("cell54 = " +cellBool[44]); }
		  if (Boolean.parseBoolean(cellBool[45]) == true) { System.out.println("cell55 = " +cellBool[45]); }
		  if (Boolean.parseBoolean(cellBool[46]) == true) { System.out.println("cell56 = " +cellBool[46]); }
		  if (Boolean.parseBoolean(cellBool[47]) == true) { System.out.println("cell57 = " +cellBool[47]); }
	      
		  if (Boolean.parseBoolean(cellBool[48]) == true) { System.out.println("cell60 = " +cellBool[48]); }
		  if (Boolean.parseBoolean(cellBool[49]) == true) { System.out.println("cell61 = " +cellBool[49]); }
		  if (Boolean.parseBoolean(cellBool[50]) == true) { System.out.println("cell62 = " +cellBool[50]); }
		  if (Boolean.parseBoolean(cellBool[51]) == true) { System.out.println("cell63 = " +cellBool[51]); }
		  if (Boolean.parseBoolean(cellBool[52]) == true) { System.out.println("cell64 = " +cellBool[52]); }
		  if (Boolean.parseBoolean(cellBool[53]) == true) { System.out.println("cell65 = " +cellBool[53]); }
		  if (Boolean.parseBoolean(cellBool[54]) == true) { System.out.println("cell66 = " +cellBool[54]); }
		  if (Boolean.parseBoolean(cellBool[55]) == true) { System.out.println("cell67 = " +cellBool[55]); }
	      
		  if (Boolean.parseBoolean(cellBool[56]) == true) { System.out.println("cell70 = " +cellBool[56]); }
		  if (Boolean.parseBoolean(cellBool[57]) == true) { System.out.println("cell71 = " +cellBool[57]); }
		  if (Boolean.parseBoolean(cellBool[58]) == true) { System.out.println("cell72 = " +cellBool[58]); }
		  if (Boolean.parseBoolean(cellBool[59]) == true) { System.out.println("cell73 = " +cellBool[59]); }
		  if (Boolean.parseBoolean(cellBool[60]) == true) { System.out.println("cell74 = " +cellBool[60]); }
		  if (Boolean.parseBoolean(cellBool[61]) == true) { System.out.println("cell75 = " +cellBool[61]); }
		  if (Boolean.parseBoolean(cellBool[62]) == true) { System.out.println("cell76 = " +cellBool[62]); }
		  if (Boolean.parseBoolean(cellBool[63]) == true) { System.out.println("cell77 = " +cellBool[63]); }
	      
	      System.out.println("--------------------------------- END: setMultipath\n");

	   	  }
}