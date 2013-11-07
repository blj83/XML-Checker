import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/*******************************************************
 * This project is designed to take in a XML file as a parameter and
 * check to make sure the tags are in a valid order and match appropriately.
 * It will perform some error checking and print valid if the file
 * is good and not valid if it is bad.
 ******************************************************/
public class XMLChecker {
	/*
	 * Main method: Performs some error checking and calls the methods
	 * necessary to complete the task.
	 */
	public static void main(String[] args){
		
		Queue<String> queue;
		Stack<String> stack = new Stack<String>();

		if (args.length != 1){
			System.out.println("Please enter a filename as the argument");
			System.exit(0);
		}
		
		try {
			FileInputStream fstream = new FileInputStream(args[0]);
			BufferedReader br = new BufferedReader(new InputStreamReader(fstream));		
			queue = parse(br);
			stack = isValid(queue);
			if (stack.empty()){
				System.out.println("Your XML file is valid!");
			}
			else{
				System.out.println("Your XML file is not valid!");
			}
			 
		} catch (FileNotFoundException e) {
			System.out.println("Please enter a valid file");
			System.exit(0);
		} 
		
		
	}
	
	/*
	 * This method will take in a bufferedReader object and go through
	 * the elements character by character and will add the tags to 
	 * the queue by parsing it when it finds a < and > character. The
	 * method will return a queue with the tags in order of how they appear
	 * in the file.
	 */
	
	private static Queue<String> parse(BufferedReader reader){
		Queue<String> q = new LinkedList<String>();
		 try { 
			int ch;
			boolean isTag = false;
			String tag = "";
			while ((ch = reader.read()) != -1){
				 char character = (char) ch;
				 
				 if (character == '<'){
					 isTag = true;
					 continue;
				 }
				 
				 else if (character == '>'){
					 isTag = false;
					 q.add(tag);
					 tag = "";
				 }
				 if (isTag){
					 tag = tag + character;
				 }
			 }
		} catch (IOException e) {
			System.out.println("Plaease enter a valid file");
		}
		
		return q;
	}
	
	/*
	 * This method will take in the queue that was parsed and will
	 * empty the queue into a stack removing the elements when 
	 * they match. It will continue this process until the queue is
	 * empty. It will then return a stack. If the stack is empty then 
	 * the XML file was valid if not it was not valid.
	 */
	
	public static Stack<String> isValid(Queue<String> q){
		Stack<String> s = new Stack<String>();
		
		while (!q.isEmpty()){
			if (q.peek().charAt(0) != '/'){
				String start = q.remove();
				s.push(start);
			}else {
				String end = q.peek().substring(1);
				if (end.equalsIgnoreCase(s.peek())){
					s.pop();
					q.remove();
				}else {
					q.remove();
					s.push(end);
				}
			}
		}
		return s;
	}
}
