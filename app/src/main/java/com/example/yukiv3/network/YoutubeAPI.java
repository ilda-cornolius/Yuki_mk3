package com.example.yukiv3.network;

import com.example.yukiv3.models.ModelChannel;
import com.example.yukiv3.models.ModelHome;
import com.example.yukiv3.models.ModelPlaylist;

import java.nio.channels.Channel;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Url;

//this is where the api call takes place
public class YoutubeAPI {

  //  https://www.googleapis.com/youtube/v3/
  //  search?
  //  key=AIzaSyCSDuOx7G9JlQy_JfDMd8GFsz9QO5wmsfM
  //  &channelID=UCvXEtcKSYzjn8Oye7N1fMQg
  //  &maxResults=10
  //  &order=date
  //  &part=snippet

//
//    https://www.googleapis.com/youtube/v3/
//    playlists?
//    part=snippet,contentDetails
//    &channelId=UCvXEtcKSYzjn8Oye7N1fMQg
//    &key=AIzaSyCSDuOx7G9JlQy_JfDMd8GFsz9QO5wmsfM
//


    public static final String BASE_URL = "https://www.googleapis.com/youtube/v3/";
    public static final String CHANNEL_ID = "UCvXEtcKSYzjn8Oye7N1fMQg";
    public static final String KEY = "key=AIzaSyCSDuOx7G9JlQy_JfDMd8GFsz9QO5wmsfM";
    public static final String sch = "search?";
    //chid this is the thing they mentioned to delete when doing the API call
    public static final String chid = "&channelId=" + CHANNEL_ID;
    public static final String mx = "&maxResults=10";
    public static final String ord = "&order=date";
    public static final String part = "&part=snippet";
    public static final String NPT = "&pageToken=";

    public static final String ply ="playlists?";
    public static final String part_ply = "&part=snippet,contentDetails";

    public static final String query = "&q=";
    public static final String type = "&type=video";


    public static final String CH = "channels?";
    public static final String IDC = "&id=" + CHANNEL_ID;
    public static final String CH_PART = "&part=snippet,statistics,brandingSettings";




//in the search thing it just shows the latest items regardless of wheteher its a playlist or a video
//https://www.googleapis.com/youtube/v3/
// channels?
// key=AIzaSyCSDuOx7G9JlQy_JfDMd8GFsz9QO5wmsfM
// &id=UCvXEtcKSYzjn8Oye7N1fMQg
// &part=snippet,statistics,brandingSettings


    public interface Video {
        @GET
        Call<ModelHome> getHomeVideo(@Url String url);

        @GET
        Call<ModelPlaylist> getPlaylist(@Url String url);

        @GET
        Call<ModelChannel> getChannel(@Url String url);
    }

    private static Video video = null;

    public static Video getVideo(){
        if (video == null){
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            video = retrofit.create(Video.class);
        }
        return video;
    }




}
