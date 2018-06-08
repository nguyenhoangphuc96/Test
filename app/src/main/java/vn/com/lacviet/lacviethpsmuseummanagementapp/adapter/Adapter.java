package vn.com.lacviet.lacviethpsmuseummanagementapp.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.ref.WeakReference;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vn.com.lacviet.lacviethpsmuseummanagementapp.R;
import vn.com.lacviet.lacviethpsmuseummanagementapp.Util;
import vn.com.lacviet.lacviethpsmuseummanagementapp.WebAPI.Model.ExhibitMainScreenModel;
import vn.com.lacviet.lacviethpsmuseummanagementapp.WebAPI.Remote.ApiService;
import vn.com.lacviet.lacviethpsmuseummanagementapp.WebAPI.Remote.ApiUtils;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    private List<ExhibitMainScreenModel> ExhibitList;
    private Context mContext;
    private Adapter.PostItemListener mItemListener;
    private Integer id;
    //web api
    private ApiService mService;


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView tvName,tvDescription;
        public ImageView imvExhibit;
        Adapter.PostItemListener mItemListener;

        public ViewHolder(View itemView, Adapter.PostItemListener postItemListener) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvExhibitName);
            tvDescription = itemView.findViewById(R.id.tvExhibitDescription);
            imvExhibit=itemView.findViewById(R.id.imgExhibit);

            this.mItemListener = postItemListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            ExhibitMainScreenModel item = getItem(getAdapterPosition());
            this.mItemListener.onPostClick(item.getEXHID());

            notifyDataSetChanged();
        }
        public ImageView getImageView() {return imvExhibit;}
    }

    public Adapter(Context context, List<ExhibitMainScreenModel> posts, Adapter.PostItemListener itemListener) {
        ExhibitList = posts;
        mContext = context;
        mItemListener = itemListener;
    }

    @Override
    public Adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View postView = inflater.inflate(R.layout.item_recyclerview_mainscreen, parent, false);

        Adapter.ViewHolder viewHolder = new Adapter.ViewHolder(postView, this.mItemListener);
        //web api
        mService = ApiUtils.getSOService();
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(Adapter.ViewHolder holder, int position) {

        ExhibitMainScreenModel item = ExhibitList.get(position);
        TextView tvName = holder.tvName;
        tvName.setText(item.getEXHIBITNAME());
        TextView tvDesc = holder.tvDescription;
        tvDesc.setText(item.getDESCRIPTION());
        ImageView imv = holder.imvExhibit;

        /*//get ID and load image by id
        id = item.getEXHID();
        loadImage(id,holder);*/
        id = item.getEXHID();
        LoadImageTask task = new LoadImageTask(holder.imvExhibit);
        task.execute(id);


    }
private class LoadImageTask extends AsyncTask<Integer,Void,String>
{
    String string;
    WeakReference<ImageView> imageViewWeakReference;

    public LoadImageTask(ImageView imageView) {
        imageViewWeakReference = new WeakReference<ImageView>(imageView);
    }

    @Override
    protected String doInBackground(Integer... params) {

        //get image default
        mService.getExhibitImageById(params[0], true).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {

                if (response.isSuccessful()) {
                    try{
                        string = response.body();
                        Log.d("AnswersPresenter", "Image loaded!!!!");
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }

                } else {
                    int statusCode = response.code();
                    Toast.makeText(mContext, "Error" + statusCode + response.message(), Toast.LENGTH_SHORT).show();
                }

            }


            @Override
            public void onFailure(Call<String> call, Throwable t) {
                showErrorMessage();
                Log.d("AnswersPresenter", "error loading image!!!");

            }
        });
        return string;
    }

    @Override
    protected void onPostExecute(String string) {
        super.onPostExecute(string);
        if (string !=null && imageViewWeakReference !=null)
        {
            ImageView imv =imageViewWeakReference.get();
            if (imv!=null)
            {
                Bitmap bitmap = Util.StringToBitMap(string);
                imv.setImageBitmap(bitmap);
            }
        }

    }
}

    private void loadImage(Integer id) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                //get image default
                mService.getExhibitImageById(id, true).enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {

                        if (response.isSuccessful()) {
                            try{
                                showImage(response.body());
                                Log.d("AnswersPresenter", "Image loaded!!!!");
                            }
                            catch (Exception e)
                            {
                                e.printStackTrace();
                            }

                        } else {
                            int statusCode = response.code();
                            Toast.makeText(mContext, "Error" + statusCode + response.message(), Toast.LENGTH_SHORT).show();
                        }

                    }


                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        showErrorMessage();
                        Log.d("AnswersPresenter", "error loading image!!!");

                    }
                });

            }
        }).run();
    }

    @Override
    public int getItemCount() {
        return ExhibitList.size();
    }

    public void updateAnswers(List<ExhibitMainScreenModel> items) {
        ExhibitList = items;
        notifyDataSetChanged();
    }
    public void updateMoreAnswers(List<ExhibitMainScreenModel> items) {
        ExhibitList.addAll(items);
        notifyDataSetChanged();
    }

    private ExhibitMainScreenModel getItem(int adapterPosition) {
        return ExhibitList.get(adapterPosition);
    }

    public interface PostItemListener {
        void onPostClick(long id);
    }
    private Bitmap showImage(String imageString) {
        Bitmap bmp = Util.StringToBitMap(imageString);

        return bmp;
    }

    public void showErrorMessage() {
        Toast.makeText(mContext, "Error loading posts", Toast.LENGTH_SHORT).show();
    }

}