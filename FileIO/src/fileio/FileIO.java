package fileio;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.Scanner;

class FileIO {
    private static final String BYTE_FILE = "ByteFile.txt";
    private static final String CHAR_FILE = "CharFile.txt";
    private static final String BUFF_FILE = "BuffFile.txt";
    private static final String DATA_FILE = "DataFile.txt";
    private static final String OBJ_FILE = "ObjFile.txt";
    private static final String NIO_FILE = "NioFile.txt";
    
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        // write to a file using byte stream
        try (FileOutputStream output = new FileOutputStream(BYTE_FILE)) {
            String text = "a line of text";
            
            output.write(text.getBytes());
        }
        
        // read from a file using byte stream
        try (FileInputStream input = new FileInputStream(BYTE_FILE)) {
            int ch;
            StringBuilder text = new StringBuilder();
            
            while ((ch = input.read()) != -1)
                text.append((char) ch);
            
            System.out.println(text.toString());
        }
        
        // write to a file using character stream
        try (FileWriter writer = new FileWriter(CHAR_FILE)) {
            String text = "some sample text";
            
            writer.write(text);
        }
        
        // read from a file using character stream
        try (FileReader reader = new FileReader(CHAR_FILE)) {
            int ch;
            StringBuilder text = new StringBuilder();
            
            while ((ch = reader.read()) != -1)
                text.append((char) ch);
            
            System.out.println(text.toString());
        }
        
        // write to a file using buffered stream
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(BUFF_FILE))) {
            String text = "this is a new line of text";
            
            writer.write(text);
        }
        
        // read from a file using buffered stream
        try (BufferedReader reader = new BufferedReader(new FileReader(BUFF_FILE))) {
            String text = reader.readLine();
            
            System.out.println(text);
        }
        
        // read from a file using a Scanner
        try (Scanner scanner = new Scanner(new BufferedReader(new FileReader(BUFF_FILE)))) {
            while (scanner.hasNextLine())
                System.out.println(scanner.nextLine() + " (read by a Scanner)");
        }
        
        // write to a file using data stream
        try (DataOutputStream output = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(DATA_FILE)))) {
            String text = "string";
            double d = 3.89;
            
            output.writeUTF(text);
            output.writeDouble(d);
        }
        
        // read from a file using data stream
        try (DataInputStream input = new DataInputStream(new BufferedInputStream(new FileInputStream(DATA_FILE)))) {
            String text = input.readUTF();
            double d = input.readDouble();
            
            System.out.println(text + ", " + d);
        }
        
        // write to a file using object stream
        try (ObjectOutputStream output = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(OBJ_FILE)))) {
            LocalDate date = LocalDate.now();
            
            output.writeObject(date);
        }
        
        // read from a file using object stream
        try (ObjectInputStream input = new ObjectInputStream(new BufferedInputStream(new FileInputStream(OBJ_FILE)))) {
            LocalDate date = (LocalDate) input.readObject();
            
            System.out.println(date);
        }
        
        // write to a file using nio
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(NIO_FILE), Charset.forName("UTF-8"))) {
            String text = "nio package";
            
            writer.write(text);
        }
        
        // read from a file using nio
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(NIO_FILE), Charset.forName("UTF-8"))) {
            String text = null; 
            while ((text = reader.readLine()) != null)
                System.out.println(text);
        }
    }
}