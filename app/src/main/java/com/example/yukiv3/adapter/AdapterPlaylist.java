package com.example.yukiv3.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yukiv3.R;
import com.example.yukiv3.models.PlaylistItems;
import com.example.yukiv3.models.VideoYT;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

import static android.content.ContentValues.TAG;

//implementing the methods in recyclerview.adapter
public class AdapterPlaylist extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    //this is the adapter to be used on the playlist fragment screen


    private Context context;
    private List<PlaylistItems> videoList;

    //creating a constructor with these default values
    public AdapterPlaylist(Context context, List<PlaylistItems> videoList) {
        this.context = context;
        this.videoList = videoList;
    }

  class YoutubeHolder extends RecyclerView.ViewHolder{

//holding the thumbnail image view
      //putting the textView together
//judul thumbnail and tanggal are matching with the row item home

        ImageView thumbnail;
        TextView judul, vid_count1, vid_count2;


        //this function connects the element data found in row item home
      // and links it with the youtube Holder
      public YoutubeHolder(@NonNull View itemView) {
          super(itemView);
          thumbnail = itemView.findViewById(R.id.iv_playlist_thumb);
          judul = itemView.findViewById(R.id.tv_playlist_title);
          vid_count1 = itemView.findViewById(R.id.tv_video_count1);
          vid_count2 = itemView.findViewById(R.id.tv_video_count2);

      }

      //setting the data
      //why are these people talking outside my fucking door?
      //why wont these stupid fucks shut up?????????????????????????
      //this function sets the data received from VideoYT
      // and puts it into containers
      //didnt used to be nonnull
      public void setData(PlaylistItems data) {
          //didnt used to be final
          final String getJudul = data.getSnippet().getTitle();
          int getCount = data.getContentDetails().getItemCount();
          String getThumb = data.getSnippet().getThumbnails().getMedium().getUrl();

          itemView.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  Toast.makeText(context, getJudul, Toast.LENGTH_SHORT).show();
              }
          });


          judul.setText(getJudul);
          vid_count1.setText(String.valueOf(getCount) + " video");
          vid_count2.setText(String.valueOf(getCount));


          Picasso.get()
                  .load(getThumb)
                  .placeholder(R.mipmap.ic_launcher)
                  .fit()
                  .centerCrop()
                  .into(thumbnail, new Callback() {
                      @Override
                      public void onSuccess() {

                          Log.d(TAG, "Thumbnail berhasil ditampilkan");

                      }

                      @Override
                      public void onError(Exception e) {
                          Log.e(TAG, "Thumbnail error: ", e);
                      }
                  });




      }
  }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.row_item_playlist, parent, false);
        return new YoutubeHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        PlaylistItems videoYT = videoList.get(position);
        YoutubeHolder yth = (YoutubeHolder) holder;
        yth.setData(videoYT);

    }

    @Override
    public int getItemCount() {
        return videoList.size();
    }
}
