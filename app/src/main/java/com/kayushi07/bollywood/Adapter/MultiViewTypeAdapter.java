package com.kayushi07.bollywood.Adapter;

import android.content.Context;
import android.content.Intent;

import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kayushi07.bollywood.Activity.Dummy;
import com.kayushi07.bollywood.Activity.GameActivity;
import com.kayushi07.bollywood.Model.Model;
import com.kayushi07.bollywood.R;

import java.util.ArrayList;

import static com.kayushi07.bollywood.R.drawable.h_timestamp;
import static com.kayushi07.bollywood.R.drawable.i_video;
import static com.kayushi07.bollywood.R.drawable.j_travel;
import static com.kayushi07.bollywood.R.drawable.k_gal;


public class MultiViewTypeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<Model> dataSet;
    Context mContext;
    int total_types, listLayoutRes;
    int gametype;
//    SQLiteDatabase Database;

    public MultiViewTypeAdapter(ArrayList<Model> data, Context context, int gametype) {
        this.dataSet = data;
        this.mContext = context;
        total_types = dataSet.size();
        this.gametype = gametype;
//      this.mDatabase = mDatabase;
    }

    public static class ClosedLevelViewHolder extends RecyclerView.ViewHolder {
        TextView txtType, txtMsg;
        LinearLayout ll_closed;
        ImageView c_image;

        public ClosedLevelViewHolder(View itemView) {
            super(itemView);
            this.txtType = (TextView) itemView.findViewById(R.id.type);
            this.txtMsg = (TextView) itemView.findViewById(R.id.levelmsg);
            this.c_image = (ImageView) itemView.findViewById(R.id.level_img_closed);
            this.ll_closed = (LinearLayout) itemView.findViewById(R.id.closed_ll);

        }
    }

    public static class OpenLevelViewHolder extends RecyclerView.ViewHolder {
        TextView txtLevel, txtPoints;//, txtMovies;
        LinearLayout start;
        ImageView o_image;

        public OpenLevelViewHolder(View itemView) {
            super(itemView);
            this.txtLevel = (TextView) itemView.findViewById(R.id.level_id);
            this.txtPoints = (TextView) itemView.findViewById(R.id.points_count);
//            this.txtMovies = (TextView) itemView.findViewById(R.id.movie_count);
            this.start = (LinearLayout) itemView.findViewById(R.id.start);
            this.o_image = (ImageView) itemView.findViewById(R.id.level_img);

        }
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view;
        switch (viewType) {
            case Model.CLOSED_LEVEL:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.closed_level, parent, false);
                return new ClosedLevelViewHolder(view);
            case Model.OPEN_LEVEL:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.open_level, parent, false);
                return new OpenLevelViewHolder(view);
        }
        return null;


    }


    @Override
    public int getItemViewType(int position) {
        switch (dataSet.get(position).type) {
            case 0:
                return Model.CLOSED_LEVEL;
            case 1:
                return Model.OPEN_LEVEL;
            default:
                return -1;
        }


    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int listPosition) {

        Model object = dataSet.get(listPosition);

        if (object != null) {
            switch (object.type) {
                case Model.CLOSED_LEVEL:
                    if (listPosition == 0) {
                        ((ClosedLevelViewHolder) holder).c_image.setImageResource(R.drawable.a_vcam);
                        ((ClosedLevelViewHolder) holder).ll_closed.setBackgroundColor(Color.parseColor("#C62828"));
                    } else if (listPosition == 1) {
                        ((ClosedLevelViewHolder) holder).c_image.setImageResource(R.drawable.b_web);
                        ((ClosedLevelViewHolder) holder).ll_closed.setBackgroundColor(Color.parseColor("#C2185B"));
                    } else if (listPosition == 2) {
                        ((ClosedLevelViewHolder) holder).c_image.setImageResource(R.drawable.c_tv);
                        ((ClosedLevelViewHolder) holder).ll_closed.setBackgroundColor(Color.parseColor("#7B1FA2"));
                    } else if (listPosition == 3) {
                        ((ClosedLevelViewHolder) holder).c_image.setImageResource(R.drawable.d_cam);
                        ((ClosedLevelViewHolder) holder).ll_closed.setBackgroundColor(Color.parseColor("#303F9F"));
                    } else if (listPosition == 4) {
                        ((ClosedLevelViewHolder) holder).c_image.setImageResource(R.drawable.e_social);
                        ((ClosedLevelViewHolder) holder).ll_closed.setBackgroundColor(Color.RED);
                    } else if (listPosition == 5) {
                        ((ClosedLevelViewHolder) holder).c_image.setImageResource(R.drawable.f_ticket);
                        ((ClosedLevelViewHolder) holder).ll_closed.setBackgroundColor(Color.MAGENTA);
                    } else if (listPosition == 6) {
                        ((ClosedLevelViewHolder) holder).c_image.setImageResource(i_video);
                        ((ClosedLevelViewHolder) holder).ll_closed.setBackgroundColor(Color.parseColor("#00701a"));
                    } else if (listPosition == 7) {
                        ((ClosedLevelViewHolder) holder).c_image.setImageResource(k_gal);
                        ((ClosedLevelViewHolder) holder).ll_closed.setBackgroundColor(Color.BLUE);
                    } else if (listPosition == 8) {
                        ((ClosedLevelViewHolder) holder).c_image.setImageResource(j_travel);
                        ((ClosedLevelViewHolder) holder).ll_closed.setBackgroundColor(Color.parseColor("#E65100"));
                    } else if (listPosition == 9) {
                        ((ClosedLevelViewHolder) holder).c_image.setImageResource(h_timestamp);
                        ((ClosedLevelViewHolder) holder).ll_closed.setBackgroundColor(Color.GRAY);
                    }
                    Model object1 = dataSet.get(listPosition - 1);


                    int unlock_score, total_score, diff_score;
                    unlock_score = object1.unlock_score;
                    total_score = object1.score;
                    diff_score = unlock_score - total_score;
                    String msg = "Score " + diff_score + " points to unlock movie set.";

                    ((ClosedLevelViewHolder) holder).txtType.setText(object.text);
                    ((ClosedLevelViewHolder) holder).txtMsg.setText(msg);
//                    ((ClosedLevelViewHolder) holder).image.setImageResource(object.data);
                    break;
                case Model.OPEN_LEVEL:


                    if (listPosition == 0) {
                        ((OpenLevelViewHolder) holder).o_image.setImageResource(R.drawable.a_vcam);
                        ((OpenLevelViewHolder) holder).start.setBackgroundColor(Color.parseColor("#C62828"));
                    } else if (listPosition == 1) {
                        ((OpenLevelViewHolder) holder).o_image.setImageResource(R.drawable.b_web);
                        ((OpenLevelViewHolder) holder).start.setBackgroundColor(Color.parseColor("#C2185B"));
                    } else if (listPosition == 2) {
                        ((OpenLevelViewHolder) holder).o_image.setImageResource(R.drawable.c_tv);
                        ((OpenLevelViewHolder) holder).start.setBackgroundColor(Color.parseColor("#7B1FA2"));
                    } else if (listPosition == 3) {
                        ((OpenLevelViewHolder) holder).o_image.setImageResource(R.drawable.d_cam);
                        ((OpenLevelViewHolder) holder).start.setBackgroundColor(Color.parseColor("#303F9F"));
                    } else if (listPosition == 4) {
                        ((OpenLevelViewHolder) holder).o_image.setImageResource(R.drawable.e_social);
                        ((OpenLevelViewHolder) holder).start.setBackgroundColor(Color.RED);
                    } else if (listPosition == 5) {
                        ((OpenLevelViewHolder) holder).o_image.setImageResource(R.drawable.f_ticket);
                        ((OpenLevelViewHolder) holder).start.setBackgroundColor(Color.MAGENTA);
                    } else if (listPosition == 6) {
                        ((OpenLevelViewHolder) holder).o_image.setImageResource(i_video);
                        ((OpenLevelViewHolder) holder).start.setBackgroundColor(Color.parseColor("#00701a"));
                    } else if (listPosition == 7) {
                        ((OpenLevelViewHolder) holder).o_image.setImageResource(k_gal);
                        ((OpenLevelViewHolder) holder).start.setBackgroundColor(Color.BLUE);
                    } else if (listPosition == 8) {
                        ((OpenLevelViewHolder) holder).o_image.setImageResource(j_travel);
                        ((OpenLevelViewHolder) holder).start.setBackgroundColor(Color.parseColor("#E65100"));
                    } else if (listPosition == 9) {
                        ((OpenLevelViewHolder) holder).o_image.setImageResource(h_timestamp);
                        ((OpenLevelViewHolder) holder).start.setBackgroundColor(Color.GRAY);
                    }



                    ((OpenLevelViewHolder) holder).txtLevel.setText(object.text);
                    ((OpenLevelViewHolder) holder).txtPoints.setText(" " + object.score);
//                    ((OpenLevelViewHolder) holder).txtMovies.setText(" "+object.movies);

                    ((OpenLevelViewHolder) holder).start.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            System.out.println("POSITION :  " + listPosition);

                            Intent i = new Intent(mContext, GameActivity.class);
                            int pos = listPosition;
                            Dummy.setCurrent_level(pos + 1);
//                            pos++;
//                            i.putExtra("Level",pos);
                            i.putExtra("gametype", gametype);
                            mContext.startActivity(i);
//                            ((Activity)mContext).finish();
                        }
                    });


//                    ((OpenLevelViewHolder) holder).fab.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View view) {
//
//                            if (fabStateVolume) {
//                                if (mPlayer.isPlaying()) {
//                                    mPlayer.stop();
//
//                                }
//                                ((OpenLevelViewHolder) holder).fab.setImageResource(R.drawable.volume);
//                                fabStateVolume = false;
//
//                            } else {
//                                mPlayer = MediaPlayer.create(mContext, R.raw.sound);
//                                mPlayer.setLooping(true);
//                                mPlayer.start();
//                                ((OpenLevelViewHolder) holder).fab.setImageResource(R.drawable.mute);
//                                fabStateVolume = true;
//
//                            }
//                        }
//                    });


                    break;
            }
        }

    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }
}
