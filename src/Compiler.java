import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;

import java_cup.runtime.Scanner;
import java_cup.runtime.Symbol;


public class Compiler {

   
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		String path = "/home/david/Desktop/exemplo.txt";	
		
		Lexer scanner;
		try {
			scanner = new Lexer(new FileReader(path));
			
			//Print the tokens generated by the scanner
			Symbol s;
	        do {
	          s = scanner.debug_next_token();
	          System.out.println("token: "+s);
	        } while (s.sym != sym.EOF);
	        
	        
			parser p = new parser(scanner);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	
}
