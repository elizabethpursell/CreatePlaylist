/* 
Name:Elizabeth Pursell
Date: 4/22/2022
CSE 007 Spring 2022: Lab 10
Produce MakePlaylist program that will create and change a playlist/array of song objects and create a queue
IDE Used: Visual Studio Code
*/
import java.util.Scanner;
public class MakePlaylist {
    public static void main(String [] args){
        Scanner myScan = new Scanner(System.in);
        Playlist playlist = createPlaylist(myScan);
        displayMenu(myScan, playlist);
    }
    //method to display menu
    public static void displayMenu(Scanner myScan, Playlist playlist){
        boolean quit = false;
        do{
            boolean emptyPlaylist = (Song.getNumSongs() == 0);
            System.out.println("Would you like to add/remove from the playlist (1), create a queue (2), show the playlist (3), or quit (4)?");
            int userChoice = getUserChoice(myScan, emptyPlaylist, true);
            switch(userChoice){
                case 1:
                    changePlaylist(myScan, playlist);       //calls changePlaylist method if user picked 1
                    break;
                case 2:
                    createQueue(myScan, playlist);      //calls createQueue method if user picked 2
                    break;
                case 3:
                    System.out.println(playlist.toString());       //prints playlist name songs in playlist if user picked 3
                    displayPlaylist(playlist.getSongs());
                    break;
                case 4:
                    quit = true;        //breaks loop if user picked 4
                    break;
                default:
                    System.out.println("Invalid Input: Not a 1, 2, or 3.");     //invalid input if other user choice
                    break;
            }
        }while(!quit);
    }
    //method to create playlist object
    public static Playlist createPlaylist(Scanner myScan){
        System.out.println("Enter the name of your playlist: ");
        String playlistName = myScan.nextLine();
        System.out.println("Enter the creator of the playlist: ");
        String playlistCreator = myScan.nextLine();
        Playlist nullPlaylist = new Playlist(null, null);        //create null playlist to use to get numSongs
        boolean validLength = false;
        int playlistLength = 0;
        do{         //ask for number of songs until valid input given
            System.out.println("Enter the number of songs in the playlist: ");
            validLength = myScan.hasNextInt();
            if(!validLength){
                System.out.println("Invalid Input: Not an integer.");
                myScan.nextLine();
            }
            else{
                playlistLength = myScan.nextInt();
                if(playlistLength > nullPlaylist.getCapacity() || playlistLength <= 0){
                    System.out.println("Invalid Input: Out of range.");
                    validLength = false;
                    myScan.nextLine();
                }
            }
        }while(!validLength);
        Playlist playlist = new Playlist(playlistName, playlistCreator);
        return playlist;
    }
    //method to add/remove songs from playlist
    public static void changePlaylist(Scanner myScan, Playlist playlist){
        System.out.println("Would you like to add (1) or remove (2) a song?");
        boolean emptyPlaylist = Song.getNumSongs() == 0;
        int userChoice = getUserChoice(myScan, emptyPlaylist, false);
        Song song;
        switch(userChoice){
            case 1:
                song = createSong(myScan);
                playlist.addSong(song);
                break;
            case 2:
                song = createSong(myScan);
                playlist.removeSong(song);
                break;
            default:
                System.out.println("Invalid Input: Not a 1 or 2.");
                break;
        }
    }
    //method to create a song object
    public static Song createSong(Scanner myScan){
        System.out.println("Enter the name of the song: ");
        String songName = myScan.nextLine();
        System.out.println("Enter the artist of the song: ");
        String songArtist = myScan.nextLine();
        System.out.println("Enter the album of the song: ");
        String songAlbum = myScan.nextLine();
        boolean intCheck = false;
        int songTime = 0;
        do{         //asks for time of song until valid input given
            System.out.println("Enter the time of the song: ");
            intCheck = myScan.hasNextInt();
            if(!intCheck){
                myScan.nextLine();
                System.out.println("Invalid Input: Not an integer.");
            }
            else{
                songTime = myScan.nextInt();
                if(songTime <= 0){
                    System.out.println("Invalid Input: Not a positive integer.");
                    intCheck = false;
                }
            }
        }while(!intCheck);
        Song song = new Song(songName, songArtist, songAlbum, songTime);
        return song;
    }
    //method to get the user's input
    public static int getUserChoice(Scanner myScan, boolean emptyPlaylist, boolean display){
        boolean validChoice = false;
        int userChoice = 0;
        do{     //asks for input until it is valid
            validChoice = myScan.hasNextInt();
            if(!validChoice){
                System.out.println("Invalid Input: Not an integer. Try again.");
                myScan.nextLine();
            }
            else{
                userChoice = myScan.nextInt();
                myScan.nextLine();
                if(userChoice == 4 && display){        //valid input of 4 when menu displayed
                    return userChoice;
                }
                if(emptyPlaylist){          //checks valid conditions for an empty playlist
                    if(display){        //checks valid conditions for when in the display menu with empty playlist
                        if(userChoice == 2){
                            validChoice = false;
                            System.out.println("Invalid Input: Cannot create queue from empty playlist. Try again.");
                        }
                        else if(userChoice != 1 && userChoice != 2 && userChoice != 3){
                            validChoice = false;
                            System.out.println("Invalid Input: Not a 1, 2, or 3. Try again.");
                        }
                        else{
                            validChoice = true;
                        }
                    }
                    else{           //checks valid conditions for when not in display menu with empty playlist
                        if(userChoice == 2){
                            validChoice = false;
                            System.out.println("Invalid Input: Cannot remove songs from empty playlist. Try again.");
                        }
                        else if(userChoice != 1 && userChoice != 2){
                            validChoice = false;
                            System.out.println("Invalid Input: Not a 1 or 2. Try again.");
                        }
                    }
                }
                else{       //checks valid conditions for not empty playlist
                    if(display){        //checks valid conditions for when in display menu with filled playlist
                        if(userChoice != 1 && userChoice != 2 && userChoice != 3){
                            validChoice = false;
                            System.out.println("Invalid Input: Not a 1, 2, or 3. Try again.");
                        }
                    }
                    else{       //checks valid conditions for when not in display menu with filled playlist
                        if(userChoice != 1 && userChoice != 2){
                            validChoice = false;
                            System.out.println("Invalid Input: Not a 1 or 2. Try again.");
                        }
                    }
                }
            }
        }while(!validChoice);
        return userChoice;
    }
    //method to print songs of array
    public static void displayPlaylist(Song [] songs){
        for(int index = 0; index < songs.length; index++){
            if(songs[index] != null){           //does not print if null values
                System.out.println(songs[index].toString());
            }
        }
    }
    //method to create and display queue
    public static void createQueue(Scanner myScan, Playlist playlist){
        boolean quit = false;
        do{         //asks for new number of songs and creates and displays queue until quit
            boolean validNumQueue = false;
            int numQueue = 0;
            do{         //asks for new number of songs for queue until valid input
                System.out.println("Enter the number of songs in queue: ");
                validNumQueue = myScan.hasNextInt();
                if(!validNumQueue){
                    myScan.nextLine();
                    System.out.println("Invalid Input: Not an integer.");
                }
                else{
                    numQueue = myScan.nextInt();
                    myScan.nextLine();
                    if(numQueue == -1){     //breaks from loop if -1 input
                        break;
                    }
                    else if(numQueue <= 0 || numQueue > Song.getNumSongs()){
                        System.out.println("Invalid Input: Integer out of range.");
                        validNumQueue = false;
                    }
                }
            }while(!validNumQueue);
            Song [] queue = playlist.makeQueue(numQueue, playlist.getSongs());
            System.out.println("Current Queue: ");
            displayPlaylist(queue);         //calls method to print songs in queue
            System.out.println("Enter -1 to stop creating queues. Enter anything else to continue.");
            quit = myScan.nextLine().equals("-1");
        }while(!quit);
    }
}