import java.io.*;
import java.lang.*; //was having some issues so did this just incase and? worked? leaving it
import java.util.*;

public class CSVHandler implements InputHandler, OutputHandler{
    public static List<GameContent> parse(File f){
        List<GameContent> dataDump = new ArrayList<>();
        // StringBuilder charlotte = new StringBuilder();
        // boolean inQuotes = false;

        try(BufferedReader buffer = new BufferedReader(new FileReader(f))){
            buffer.readLine();

            String row;

            while((row = buffer.readLine()) != null){
                List<String> columnData = new ArrayList<>();
                StringBuilder charlotte = new StringBuilder();
                //builds string, yk?
                boolean inQuotes = false;
                for(char c: row.toCharArray()){
                    // switch(c){
                    //     case '"':
                    //     case (',' && !inQuotes):
                    //     default:
                    // }
                    if(c == '"') inQuotes = !inQuotes;
                    else if((c == ',') && !inQuotes){
                        columnData.add(charlotte.toString());
                        charlotte.setLength(0);
                    } else charlotte.append(c);
                }

                // columnData += charlotte.toString();
                columnData.add(charlotte.toString());

                GameContent question = new GameContent(columnData.get(0), Integer.parseInt(columnData.get(1)),columnData.get(2),columnData.get(3),columnData.get(4),columnData.get(5),columnData.get(6),columnData.get(7).charAt(0));

                // String[] content = row.split(",");
                // GameContent question = new GameContent(content[0], Integer.parseInt(content[1]), content[2], content[3], content[4], content[5], content[6], content[7].charAt(0));
                dataDump.add(question);
            }
        } catch(IOException e){
            System.out.println("Error reading data file.");
        }

        return dataDump;
    }
}