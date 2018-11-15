package COM_301;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FinalProject {
	
	public static int file;
	public static String fileName;

	public static void main(String[] args) {
		
		System.out.println("Files: ");
		System.out.println("1. HelloWorld.java");
		System.out.println("2. HelloWorld_wrong.java");
		System.out.println("3. SampleFile.java");
		System.out.println("4. SampleFile_wrong.java");
		System.out.println("\nPlease enter the number of the file to be checked: ");
		
		//gathers file number from console
		Scanner scan = new Scanner( System.in );
		file = scan.nextInt();
		
		//calls file method depending on choice in the console
		if (file == 1) {
			fileName = "C:\\Users\\brianna.weaver\\OneDrive - Saint Leo University\\Personal\\Eclipse\\School\\src\\COM_301\\HelloWorld.java";
			readFile();
		}
		else if (file == 2) {
			fileName = "C:\\Users\\brianna.weaver\\OneDrive - Saint Leo University\\Personal\\Eclipse\\School\\src\\COM_301\\HelloWorld_wrong.java";
			readFile();
		}
		else if (file == 3) {
			fileName = "C:\\Users\\brianna.weaver\\OneDrive - Saint Leo University\\Personal\\Eclipse\\School\\src\\COM_301\\SampleFile.java";
			readFile();
		}
		else if (file == 4) {
			fileName = "C:\\Users\\brianna.weaver\\OneDrive - Saint Leo University\\Personal\\Eclipse\\School\\src\\COM_301\\SampleFile_wrong.java";
			readFile();
		}

	}
	
	//reads the proper file
	public static void readFile() {
		try {
			List<Character> symbolInput = new ArrayList<Character>();
			List<Character> letters = new ArrayList<Character>();
			String line;
			
			//opens file
			FileReader read = new FileReader(fileName);
			BufferedReader reader = new BufferedReader(read);
			
			//while the file has lines
			while ((line = reader.readLine()) != null) {
				
				//adds the line of text to a character array for comparison
				char[] chars = line.toCharArray();
				
				//loop through each character in the line
				for (int i = 0; i < chars.length; i++) {
					
					//if the character is not a symbol, add it to the array of non-symbol characters
					if (Character.isLetterOrDigit(chars[i])) {
						letters.add(chars[i]);
					}
					//if the character is a symbol, add it to the array of symbol inputs
					else if (!Character.isLetterOrDigit(chars[i])) {
						symbolInput.add(chars[i]);
					}
				}				
			}
			
			//call the stack function with the symbol array
			//it returns a boolean value
			//if true, no syntax errors, if false, syntax errors
			if (stack(symbolInput)) 
				System.out.println("\n\nNo syntax error");
			else 
				System.out.println("\n\nSyntax errors occurred");
			
			//close the reader
			reader.close();
			
		} catch (Exception e) {
			System.out.println("File Not Found");
		}
	}
	
	//stack algorithm for balancing symbols
	public static boolean stack(List<Character> chars) {
		
		List<Character> stack = new ArrayList<Character>();
		
		//loops through each character in the arrayList param
		for (char symbol : chars) {
			
			//if it is an opening symbol, add it to the ArrayList stack
			if (symbol == '(' || symbol == '{' || symbol == '[') {
				stack.add(symbol);
			}
			
			//if it is a closing parenthesis
			if (symbol == ')') {
				
				//add the symbol to the stack and get the index of the symbol
				stack.add(symbol);
				int index = stack.indexOf(symbol);
				
				//make sure the index is not the first character in the stack
				if (index > 0) {
					
					//if the preceding symbol is an opening parenthesis, remove both from the stack
					if (stack.get(index - 1) == '(') {
						stack.remove(index);
						stack.remove(index - 1);
					}
				}
			}
			
			//if it is a closing bracket
			else if (symbol == ']') {
				
				//add the symbol to the stack and get the index of the symbol
				stack.add(symbol);
				int index = stack.indexOf(symbol);
				
				//make sure the index is not the first character in the stack				
				if (index > 0) {
					
					//if the preceding symbol is an opening bracket, remove both from the stack
					if (stack.get(index - 1) == '[') {
						stack.remove(index);
						stack.remove(index - 1);
					}
				}
			}
			
			//if it is a closing curly brace
			else if (symbol == '}') {
				
				//add the symbol to the stack and get the index of the symbol
				stack.add(symbol);
				int index = stack.indexOf(symbol);
				
				//make sure the index is not the first character in the stack
				if (index > 0) {
					
					//if the preceding symbol is an opening curly braces, remove both from the stack
					if (stack.get(index - 1) == '{') {
						stack.remove(index);
						stack.remove(index - 1);
					}
				}
			}
		}
		
		//if the stack is empty (no errors) return true, else return false
		return stack.isEmpty();
	}

}
