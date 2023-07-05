
package comp2112_project_2;


public class SongList {
    
    int size;
    Song[] songlist;

    public SongList(int length) {
        songlist = new Song[length];
        this.size=0;
    }
    
    public SongList(){
        
    }

    public void insert(Song song) {
        for (int i = 0; i < songlist.length; i++) {
            if(isFull()){
                resize();
            }
            if (songlist[i] == null) {
                songlist[i] = song;
                songlist[i].index = i;
                size++;
                break;
            }
        }
    }

    public void resize() {
        Song[] songlist2 = new Song[songlist.length * 2];

        for (int i = 0; i < songlist.length; i++) {
            songlist2[i] = songlist[i];
        }

        songlist = songlist2;
    }

    public String deleteSong(String userId) {
        String deletedId = "-1";
        String nameOfSong = "";
        for (int i = 0; i < songlist.length; i++) {
            if (songlist[i].id.equals(userId) ) {
                deletedId = songlist[i].id;
                nameOfSong = songlist[i].songName;
                songlist[i] = null;
                size--;
                break;
            }

        }
        return "song id: " + deletedId + " and name :" + nameOfSong + "  is removed from the list";

    }
    
    public boolean isFull(){
        if(size==songlist.length){
            return true;
        }
        
        return false;
    }
}
