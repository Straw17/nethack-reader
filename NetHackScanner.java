import java.awt.Dimension;
import java.awt.Font;
import java.io.*;
import java.util.Arrays;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class NetHackScanner {
	public static void main(String args[]) throws FileNotFoundException, IOException {
		final File file = new File("logfile");
		BufferedReader br = new BufferedReader(new FileReader(file));
		String currentStrLine;
		
		int lines = 0;
		while((currentStrLine = br.readLine()) != null) {
			lines++;
		}
		Character[] characters = new Character[lines];
		
		br = new BufferedReader(new FileReader(file));
		for(int currentLine = 0; currentLine < lines; currentLine++) {
			characters[currentLine] = new Character(br.readLine());
		}
		Arrays.sort(characters);
		
		int maxScoreLen = 0, maxNameLen = 0, maxInfoLen = 0; 
		for(Character c : characters) {
			if(Integer.toString(c.score).length() > maxScoreLen) {
				maxScoreLen = Integer.toString(c.score).length();
			}
			
			if(c.name.length() > maxNameLen) {
				maxNameLen = c.name.length();
			}
			
			if(c.getInfoLen() > maxInfoLen) {
				maxInfoLen = c.getInfoLen();
			}
		}
		
		String fullStr = "";
		for(Character c : characters) {
			fullStr += c.score;
			//System.out.print(c.score);
			for(int i = 0; i < (maxScoreLen - Integer.toString(c.score).length() + 1); i++) {
				fullStr += " ";
				//System.out.print(" ");
			}
			fullStr += c.name;
			//System.out.print(c.name);
			for(int i = 0; i < (maxNameLen - c.name.length() + 1); i++) {
				fullStr += " ";
				//System.out.print(" ");
			}
			fullStr += c.returnInfo();
			for(int i = 0; i < (maxInfoLen - c.getInfoLen() + 3); i++) {
				fullStr += " ";
				//System.out.print(" ");
			}
			fullStr += c.deathCause + "\n";
		}
		
		System.out.println(fullStr);
		JOptionPane displayStr = new JOptionPane();
		JTextArea textArea = new JTextArea (fullStr);
		JScrollPane scrollPane = new JScrollPane(textArea); 
		textArea.setFont(new Font("Monospaced", Font.BOLD, 14));
		textArea.setLineWrap(true);  
		textArea.setWrapStyleWord(true); 
		scrollPane.setPreferredSize(new Dimension(1000, 500));
		displayStr.showMessageDialog(null, scrollPane, "Results", JOptionPane.PLAIN_MESSAGE);
	}
}