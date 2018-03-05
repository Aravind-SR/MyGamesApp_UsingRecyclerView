package com.example.aravind_pt1748.recyclerviewapp2;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.LruCache;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

public class RecyclerAdapterActivity extends RecyclerView.Adapter<RecyclerAdapterActivity.MyViewHolder>
                                     implements ItemTouchHelperAdapter{

    private List<Game> gamesList;
    private LruCache<String,Bitmap> mMemoryCache;

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView titleText, genreText, releaseYear;
        private ImageView gameImage;
        public MyViewHolder(View view){
            super(view);
            titleText = view.findViewById(R.id.titleText);
            genreText = view.findViewById(R.id.gameGenreText);
            releaseYear = view.findViewById(R.id.releaseYear);
            gameImage = view.findViewById(R.id.gameImage);
        }
    }

    public RecyclerAdapterActivity(List<Game> gamesList){
        this.gamesList=gamesList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_details,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Game game = gamesList.get(position);
        holder.titleText.setText(game.getTitle());
        holder.genreText.setText(game.getGenre());
        holder.releaseYear.setText(game.getReleaseYear());

        //Call image setting method here.

        //setImage(holder.gameImage,game.getImageId());

        /*Glide.with(holder.gameImage.getContext())
                .load(game.getImageId())
                .into(holder.gameImage);
                */

//LRUCACHE IMPLEMENTATION
        final int maxMemory = (int) Runtime.getRuntime().maxMemory()/1024;
        int cacheSize = maxMemory/2;
        mMemoryCache = new LruCache<String, Bitmap>(cacheSize){
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getByteCount()/1024;
            }
        };

        if(getBitmapFromMemoryCache(position+"")!=null){
            Bitmap bMap = getBitmapFromMemoryCache(holder.titleText.toString());
        }

//LRUCACHE IMPLEMENTATION ENDS

        else {
            new LoadImageTask(holder.gameImage).execute(game.getImageId());
        }
    }

    @Override
    public int getItemCount() {
        return gamesList.size();
    }

    public int prepareInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight){
        final int imageHeight = options.outHeight;
        final int imageWidth = options.outWidth;
        Log.d("TAG", "prepareInSampleSize: "+imageWidth+" "+imageHeight);
        int halfWidth = imageWidth/2;
        int halfHeight = imageHeight/2;
        Log.d("TAG", "prepareInSampleSize: "+halfWidth+" "+halfHeight);
        Log.d("TAG", "prepareInSampleSize: "+reqWidth+" "+reqHeight);
        int inSampleSize=1;
        while( ( ( halfWidth/inSampleSize) > reqWidth ) && ((halfHeight/inSampleSize) > reqHeight ) ){
            inSampleSize*=2;
        }
        Log.d("TAG", "prepareInSampleSize: "+inSampleSize);
        return inSampleSize;
    }

    class LoadImageTask extends AsyncTask<Integer,Void,Bitmap>{
        private ImageView imageView;
        public LoadImageTask(ImageView imageView){
            this.imageView=imageView;
        }
        @Override
        protected Bitmap doInBackground(Integer... position) {
            return setImage(imageView,position[0]);
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            imageView.setImageBitmap(bitmap);
        }
    }

    public Bitmap setImage(ImageView iv, int position){
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds=true;
        BitmapFactory.decodeResource(iv.getContext().getResources(),position,options);
        options.inSampleSize=prepareInSampleSize(options,384,200);
        options.inJustDecodeBounds=false;
        Bitmap bMap= BitmapFactory.decodeResource(iv.getContext().getResources(),position,options);

//LRUCACHE IMPLEMENTATION
        putBitmapInMemoryCache(position+"",bMap);
//LRUCACHE IMPLEMENTATION ENDS

        return bMap;
    }

//LRUCACHE IMPLEMENTATION

    public Bitmap getBitmapFromMemoryCache(String key){
        Log.d("TAG", "getBitmapFromMemoryCache: "+key);
        return mMemoryCache.get(key);
    }
//LRUCACHE IMPLEMENTATION ENDS

//LRUCACHE IMPLEMENTATION

    public void putBitmapInMemoryCache(String key, Bitmap bitmap){
        Log.d("TAG", "putBitmapInMemoryCache: "+key);
        //if(getBitmapFromMemoryCache(key)==null){
        mMemoryCache.put(key,bitmap);
        //}
    }
//LRUCACHE IMPLEMENTATION ENDS


    @Override
    public void onItemDismiss(int position) {
        gamesList.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        if(fromPosition<toPosition){
            for(int i=fromPosition;i<toPosition;i++){
                Collections.swap(gamesList,i,i+1);
            }
        }
        else{
            for(int i=fromPosition;i<toPosition;i--){
                Collections.swap(gamesList,i,i-1);
            }
        }
        notifyItemMoved(fromPosition,toPosition);
        return true;
    }
}
