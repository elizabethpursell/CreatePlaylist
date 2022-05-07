/* 
Name:Elizabeth Pursell
Date: 4/22/2022
CSE 007 Spring 2022: Lab 10
Produce Playlist program that will create a playlist or array of song objects
IDE Used: Visual Studio Code
*/
public class Playlist {
    //create private variables for Playlist objects
    private String name;
    private String creator;
    private Song [] songs;
    private final int CAPACITY = 20;

    //3 arg constructor to create Playlist objects; creates songs array to hold all songs in playlist
    public Playlist(String name, String creator){
        this.name = name;
        this.creator = creator;
        songs = new Song[CAPACITY];
    }
    //method to get value of private name of playlist
    public String getName(){
        return name;
    }
    //method to add a song to playlist object's songs array
    public void addSong(Song song){
        int numSongs = Song.getNumSongs();
        if((numSongs - 1) < songs.length){
            songs[numSongs - 1] = song;
        }
        else{
            System.out.println("Unable to add. Your playlist is full.");
        }
    }
    //method to remove a song from playlist object's songs array
    public void removeSong(Song song){
        boolean removed = false;
        int i;
        int numSongs = Song.getNumSongs();
        for(i = 0; i < songs.length; i++){
            if(songs[i] != null){
                if(songs[i].getName().equals(song.getName())){      //executes if it finds the song in the songs array
                    songs[i] = null;
                    removed = true;
                    Song.setNumSongs(numSongs - 1);
                    break;
                }
            }
        }
        if(removed){
            for(int j = i; j < (songs.length - 1); j++){        //shifts all songs when song removed
                Song temp = songs[j];
                songs[j] = songs[j + 1];
                songs[j + 1] = temp;
            }
        }
        else{
            System.out.println(song.getName() + " was not found in the playlist.");
        }
    }
    //method to get value of private songs array
    public Song [] getSongs(){
        return songs;
    }
    //method to get value of private static final capacity
    public int getCapacity(){
        return CAPACITY;
    }
    //method that overrides Object toString method; returns string that can be printed
    public String toString(){
        return "Name of Playlist: " + name + "\nCreator of Playlist: " + creator;
    }
    //method to make a queue of random songs
    public Song [] makeQueue(int numQueue, Song [] songs){
        Song [] queue = new Song[numQueue];
        for(int shuffle = 0; shuffle < 60; shuffle++){          //gets random song from songs array and copies it to queue
            int randomIndex = (int) (Math.random() * (numQueue));
            if(songs[randomIndex] != null){
                Song randomSong = songs[randomIndex];
                if(queue[0] != null){
                    queue[randomIndex] = queue[0];
                }
                queue[0] = randomSong;
            }
        }
        return queue;
    }
}
