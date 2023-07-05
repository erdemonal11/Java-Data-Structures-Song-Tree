package comp2112_project_2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;

public class COMP2112_Project_2 {

    public static void main(String[] args) throws Exception {
        SongList ListOfSongs = SongReadFromTxt();
        AvlTree<String> avlWithId = new AvlTree<String>();

        AvlTree<String> avlWithSongName = new AvlTree<String>();

        AvlTree<String> avlWithArtist = new AvlTree<String>();

        int i = 0;
        while (ListOfSongs.songlist[i] != null) {

            avlWithId.insert(ListOfSongs.songlist[i].id, i);

            avlWithSongName.insert(ListOfSongs.songlist[i].songName, i);

            avlWithArtist.insert(ListOfSongs.songlist[i].artist, i);

            i++;
        }

        Scanner scn = new Scanner(System.in);
        int usernumber=1;

        while (usernumber != 5) {
            System.out.println("1- search " + "\n" + "2-delete song from tree " + "\n" + "3-print all songs " + "\n" + "4-print tree " + "\n" + "5-exit" + "\n");
            System.out.println("please enter which task do you want do to" + "\n" );
            usernumber = scn.nextInt();
            switch (usernumber) {
                case 1:
                    System.out.println("which search do you want do do? " + "\n" + "Data or ID  (please write correctly) ");
                    String searchType = scn.next();
                    if (searchType.equals("Data")) {
                        System.out.println("please enter your data to search data type (ID, songName,Artist) ");
                        String datatypeOfSearch = scn.next();
                        if (datatypeOfSearch.equals("ID")) {
                            System.out.println("please enter ID that you want to search");
                            String id = scn.next();
                            int index = avlWithId.firstSearchMethod(id);
                            if (index != -1) {
                                System.out.println("song is found with these values");
                                System.out.println(ListOfSongs.songlist[index].toString());
                            } else {
                                System.out.println("song couldn't found");
                            }

                        } else if (datatypeOfSearch.equals("songName")) {
                            System.out.println("please enter song name that you want to search");
                            String userSongName = scn.next();
                            int index = avlWithSongName.firstSearchMethod(userSongName);
                            if (index != -1) {
                                System.out.println("song is found with these values");
                                System.out.println(ListOfSongs.songlist[index].toString());
                            } else {
                                System.out.println("song couldn't found");
                            }
                        } else if (datatypeOfSearch.equals("Artist")) {
                             System.out.println("please enter artist that you want to search");
                            String userArtist = scn.next();
                            int index = avlWithArtist.firstSearchMethod(userArtist);
                            if (index != -1) {
                                System.out.println("song is found with these values");
                                System.out.println(ListOfSongs.songlist[index].toString());
                            } else {
                                System.out.println("song couldn't found");
                            }
                        } else {
                            System.out.println("you entered invalid input");
                        }
                    } else if (searchType.equals("ID")) {
                        System.out.println("please enter your lower ID bound");
                        String lower = scn.next();
                        System.out.println("please enter your upper ID bound");
                        String upper = scn.next();
                        avlWithId.thirdSearchMethod(lower, upper, ListOfSongs);
                    } else {
                        System.out.println("you entered invalid value " + "\n");
                    }
                    break;
                case 2:
                    System.out.println("please enter dataType that you want to delete (ID,SongName,Artist");
                    String datatype = scn.next();
                    if(datatype.equals("ID")){
                        System.out.println("enter id of song that you want to delete");
                        String id = scn.next();
                        int index = avlWithId.delete(id, datatype);
                        avlWithSongName.delete(ListOfSongs.songlist[index].songName,"SongName");
                        avlWithArtist.delete(ListOfSongs.songlist[index].artist, "Artist");
                        System.out.println(ListOfSongs.songlist[index].toString() + "is deleted from all trees");
                    }
                    
                    else if(datatype.equals("SongName")){
                        System.out.println("enter name of song you want to delete");
                        String songname = scn.next();
                        int index =avlWithSongName.delete(songname, "SongName");
                        avlWithId.delete(ListOfSongs.songlist[index].id, "ID");
                        avlWithArtist.delete(ListOfSongs.songlist[index].artist, "Artist");
                        System.out.println(ListOfSongs.songlist[index].toString() + "is deleted from all trees");
                    }
                    
                    else if(datatype.equals("Artist")){
                        System.out.println("enter artist of song that you want to delete");
                        String artist = scn.next();
                        int index = avlWithArtist.delete(artist, "Artist");
                        avlWithId.delete(ListOfSongs.songlist[index].id, "ID");
                        avlWithSongName.delete(ListOfSongs.songlist[index].songName,"SongName");
                        System.out.println(ListOfSongs.songlist[index].toString() + "is deleted from all trees");
                    }
                    else{
                        System.out.println("you entered an invalid value");
                    }
                    break;
                case 3:
                    for(int k=0;k<ListOfSongs.songlist.length;k++){
                        if(ListOfSongs.songlist[k]!=null){
                            System.out.println(ListOfSongs.songlist[k].toString());
                        }
                    }
                    break;
                    
                case 4:
                    System.out.println("please enter which tree you want to print (ID,SongName,Artist)");
                    String treeType = scn.next();
                    if(treeType.equals("ID")){
                        avlWithId.traverseInOrder();
                    }
                    else if(treeType.equals("SongName")){
                        avlWithSongName.traverseInOrder();
                    }
                    else if(treeType.equals("Artist")){
                        avlWithArtist.traverseInOrder();
                    }
                    else{
                        System.out.println("you entered an invalid value");
                    }
                    break;
                    
                case 5:
                    System.out.println("goodbye");
            }

        }

    }

    public static SongList SongReadFromTxt() throws Exception {
        BufferedReader br = new BufferedReader(new FileReader("songs.txt"));
        String line = null;

        SongList songlist = new SongList(10);
        while ((line = br.readLine()) != null) {
            String[] songstxt = line.split(";");

            for (int i = 0; i < songstxt.length; i += 5) {
                Song song = new Song(songstxt[i], songstxt[i + 1], songstxt[i + 2], songstxt[i + 3], songstxt[i + 4]);
                songlist.insert(song);
            }
        }
        br.close();

        return songlist;
    }

}
