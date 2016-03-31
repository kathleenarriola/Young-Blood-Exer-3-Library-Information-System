/* 
 * Class Name: Library
 * Class Description: Defines the Library class, where books are stored using a hashmap.
 * 
 */

package main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.BufferedReader;
import java.io.Serializable;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.File;


public class Library {

	private static final String SER_MAP_FILE = "libraryHash.ser";	//tentative, IDK that file path
	private static final String BOOK_INFO_FILE = "books.csv";
	HashMap<String,ArrayList<Book>> books;

	public Library(){
		this.books = new HashMap<String,ArrayList<Book>>();
		this.loadBooks();
	}

	private void loadBooks(){
		if(Library.isFileExist(SER_MAP_FILE)) this.loadMAP();
		else this.loadCSV();
	}

	private void loadCSV(){
		Random rand = new Random();
		String ln = null;
		String[] info;
		ArrayList<Book> list;
		int noOfBooks;

		try{
			File file = new File(BOOK_INFO_FILE);
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);

			
			while((ln = br.readLine()) != null){
				info = ln.split(",", -1);
				noOfBooks = rand.nextInt(6) + 15;
				list = new ArrayList<Book>(noOfBooks);
				for(int i = 0; i < noOfBooks ;i++){
					list.add(new Book(info[0], info[1], Integer.parseInt(info[2]), info[3], this.generateID()));
				}
				this.books.put(info[0], list);
			}


			br.close();
		}catch(Exception e){
			e.printStackTrace();
			System.exit(0);
		}
	}

	@SuppressWarnings("unchecked")												//supresses warning on unsafe operations
	private void loadMAP(){
	
		try{
			File file = new File(SER_MAP_FILE);
			FileInputStream fis = new FileInputStream(file);
			ObjectInputStream ois = new ObjectInputStream(fis);

			this.books = ((HashMap<String,ArrayList<Book>>)ois.readObject());	

			ois.close();
		}catch(Exception e){
			e.printStackTrace();
			System.exit(0);
		}
	}

	public void saveMAP(){
	
		try{
			File file = new File(SER_MAP_FILE);
			FileOutputStream fos = new FileOutputStream(file);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			
			oos.writeObject(this.books);
			oos.close();
		}catch(Exception e){
			e.printStackTrace();
			System.exit(0);
		}
	}
	
	private String generateID(){
		Random rand = new Random();
		String id = null;

		do{
			id = Integer.toHexString(rand.nextInt(1000));
			System.out.println(isIDExist(id));
		}while(this.isIDExist(id));

		return id;
	}
	
	private boolean isIDExist(String id){
		ArrayList<Book> temp;
	
		for (String key : this.books.keySet()){
			temp = this.books.get(key);
			for(int i = 0; i < temp.size(); i++){
				if((temp.get(i)).getID().equalsIgnoreCase(id)) return true;
			}
		}
		return false;
	}

	private static boolean isFileExist(String filename){
		try{
			File file = new File(filename);

			if(file.exists()) return true;
		}catch(Exception e){e.printStackTrace();}
		return false;
	}
	
}
