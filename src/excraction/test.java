package excraction;

import java.util.Scanner;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class test {
	
	
	public static void extract(String terme) throws IOException {
		//Lecture à partire d'une URL construite avec le terme
		String urlString = "http://www.jeuxdemots.org/rezo-dump.php?gotermsubmit=Chercher&gotermrel="+terme;
		URL url = new URL(urlString);
		
		BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
		
		String codeSource = "";
		String s;
		Boolean existeTerme = false;

		
		//Lecture du code ligne par ligne
		while ((s = in.readLine()) != null) {
			codeSource += "\n"+s;
			
			//Recherche de la balise <CODE> qui stipule le début des termes/relations
			if(s.equals("<CODE>")){
				existeTerme = true;
			}
			
			//Si on est à l'interieur des termes/relation
			if(existeTerme) {
				//Si une ligne non vide
				if(!s.equals("")) {
					//Ligne d'une relation
					if(s.charAt(0) == 'r') {
						System.out.println("Une relation  : "+s);
						
					//Ligne d'un terme
					}else if(s.charAt(0) == 'e') {
						System.out.println("Un terme : "+s);
					}
				}
				
				
				
				
			}
			
			
			//System.out.println(s);
		}
			
		System.out.println(existeTerme);
		//System.out.println(codeSource);
		in.close();
	}
	
	
	
	
	
	
	

	public static void main(String[] args)  {
		Scanner sc = new Scanner(System.in);
		
		while(true) {
			System.out.println("Veuillez entrez un terme : ");
			String terme = sc.nextLine();
			try {
				test.extract(terme);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		
		
		
		
	}

}
