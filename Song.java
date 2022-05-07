/* 
Name:Elizabeth Pursell
Date: 4/22/2022
CSE 007 Spring 2022: Lab 10
Produce Song program that will create song objects
IDE Used: Visual Studio Code
*/
public class Song {
    //create private varibles for Song objects
    private String name;
    private String artist;
    private String album;
    private int time;
    private static int numSongs = 0;

    //4 arg constructor to create Song objects; increments numSongs to keep track of number of songs
    public Song(String name, String artist, String album, int time){
        this.name = name;
        this.artist = artist;
        this.album = album;
        this.time = time;
        numSongs++;
    }
    //method to get value of private name of song
    public String getName(){
        return name;
    }
    //method to get value of private artist
    public String getArtist(){
        return artist;
    }
    //method to get value of private album
    public String getAlbum(){
        return album;
    }
    //method to get value of private time
    public int getTime(){
        return time;
    }
    //method to set the private static numSongs variable; used when removing a song from playlist
    public static void setNumSongs(int number){
        numSongs = number;
    }
    //method to get value of private static numSongs
    public static int getNumSongs(){
        return numSongs;
    }
    //method that overrides Object toString method; returns string that can be printed
    public String toString(){
        return "Song: " + name + "\tArtist: " + artist + "\tAlbum: " + album + "\tTime: " + time;
    }
}
