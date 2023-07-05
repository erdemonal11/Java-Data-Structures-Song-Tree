package comp2112_project_2;



public class Song {

    String songName;
    String artist;
    String id;
    String genre;
    String year;
    int index;
    

    public Song() {

    }

    public Song(String songName, String artist, String id, String genre, String year) {
        this.songName = songName;
        this.artist = artist;
        this.id = id;
        this.genre = genre;
        this.year = year;
        
    }

    @Override
    public String toString() {
        return "Song{" + "songName=" + songName + ", artist=" + artist + ", id=" + id + ", genre=" + genre + ", year=" + year + ", index=" + index + '}';
    }
    
    
    
    

    

    

}
