/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author will
 */
import java.util.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class stringParse {

    public List<List<String>> parseMe(String input){
        List<List<String>> results = new ArrayList<>();

        String myString = "";
       	myString = input;

       	//comment out this portion if not reading from file
		try{
		    myString = readFile(input);
		}catch (IOException e){
		   e.printStackTrace();
		}
		//my jank implementation to get the title

		//get everything below the title class

		String delims = "<a class=\"title\"";
		if (myString.contains(delims)){ //Check if there are any movies listed for that day 

			String[] tokens = myString.split(delims); //each token holds info on one movie
			for (int i = 1; i < tokens.length; i++) {
				//System.out.println(tokens[i]+"\n");
				//System.out.println("----------------------");

				List<String> movie_info = new ArrayList<>(); //title, rating, times

				String title = "";
				String[] time_tokens = tokens[i].split("<a class=\"onsale\"");
		       	for (int x = 0; x < time_tokens.length; x++) {
					String[] myStuff = time_tokens[x].split("[<>]+");
					if (x == 0) {
					   title = myStuff[1];
					   movie_info.add(title);
					}
					if(x > 0) {
					   movie_info.add(myStuff[1]);  
					}
		         	//System.out.println( myStuff[1]);
		       	}
				results.add(movie_info);	
			}
		}

		return results;
    }

    String readFile(String fileName) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        try {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append("\n");
                line = br.readLine();
            }
            return sb.toString();
        } finally {
            br.close();
        }
    }

}


class Test {
	public static void main (String[] args) {
		stringParse will = new stringParse();
        
        List<List<String>> movies_list = 
        will.parseMe("./htmlSource.txt");

        for (int i = 0; i < movies_list.size(); i++) {
        	List<String> movie = movies_list.get(i);
        	for (int j = 0; j < movie.size(); j++)
        		System.out.println(movie.get(j));
        }

	}
}