
import java.io.File;
import java.util.List;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.BufferedWriter;
import java.io.BufferedReader;

public class johnandcows{
  private char [][] mLand = new char[10][10];
  private int mMinutes = 0;

  private char [][] readInput(){
    char [][] theLand = new char [10][10];
    String linesTogether = null;
    String line = null;
    try{
      int row = 0;
      File input = new File("Input.txt");
      FileReader fileReader = new FileReader(input);
      BufferedReader bufferedReader = new BufferedReader(fileReader);
      while((line = bufferedReader.readLine()) != null) { //loops through each line in the file
        //takes takes in line and puts it together as a string into the array
        String [] linesAsStrings = new String [10];
        for(int x = 0; x < 10; x++){
          linesAsStrings[x] = line;
        }
          for(int col = 0; col < 10; col++){
            theLand[row][col] = linesAsStrings[row].charAt(col);
          }
          row++;
      }
      fileReader.close();
      bufferedReader.close();
    }catch(IOException ioe){
      System.out.println("IO Exception was thrown");
    }
    return theLand;
  }

  private int johnAndCowMovements(char [][] land){
    int maxMinutes = 49;
    int minSpent = 0;
    //up for 1, right for 2, down for 3, left for 4
    int johnsPosition = 1;
    char openSpace = '.';
    char obstacle = '*';
    char john = 'F';
    char cows = 'C';
    while(minSpent != maxMinutes){
      for(int row = 0; row < 10; row++){
        for(int col = 0; col < 10; col++){
          if(land[row][col] == cows){
            land[row][col] = '.';
          }else if(land[row][col] == john &&  johnsPosition == 1){
            try{
              switch(land[row - 1][col]){
                case '.':
                  land[row - 1][col] = 'F';
                  land[row][col] = '.';
                  minSpent++;
                  break;
                case '*':
                  johnsPosition = 2;
                  minSpent++;
                  break;
                default:
                  System.exit(0);
              }
          }catch(IndexOutOfBoundsException e){
            johnsPosition = 2;
            minSpent++;
          }
          }else if(land[row][col] == john &&  johnsPosition == 2){
            try{
              switch(land[row][col + 1]){
                case '.':
                  land[row][col + 1] = 'F';
                  land[row][col] = '.';
                  minSpent++;
                  break;
                case '*':
                  johnsPosition = 3;
                  minSpent++;
                  break;
                default:
                  System.exit(0);
              }
            }catch(IndexOutOfBoundsException e){
              johnsPosition = 4;
              minSpent++;
            }
          }else if(land[row][col] == john &&  johnsPosition == 3){
            try{
              switch(land[row + 1][col]){
                case '.':
                  land[row + 1][col] = 'F';
                  land[row][col] = '.';
                  minSpent++;
                  break;
                case '*':
                  johnsPosition = 4;
                  minSpent++;
                  break;
                default:
                  System.exit(0);
              }
            }catch(IndexOutOfBoundsException e){
              johnsPosition = 4;
              minSpent++;
            }
          }else if(land[row][col] == john &&  johnsPosition == 4){
            try{
              switch(land[row][col - 1]){
                case '.':
                  land[row][col - 1] = 'F';
                  land[row][col] = '.';
                  minSpent++;
                  break;
                case '*':
                  johnsPosition = 1;
                  minSpent++;
                  break;
                default:
                  System.exit(0);
              }
            }catch(IndexOutOfBoundsException e){
              johnsPosition = 1;
              minSpent++;
            }
          }
        }
      }
    }
    return minSpent;
  }

  private void setMinutes(){
    this.mMinutes = johnAndCowMovements(readInput());
  }

  public int getMinutes(){
    setMinutes();
    return this.mMinutes;
  }

  public void writeFile(){
    int minutes = getMinutes();
    try {
			String content = Integer.toString(minutes);
			File file = new File("Output.txt");
			if (!file.exists()) {
				file.createNewFile();
			}
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(content);
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
  }
}
