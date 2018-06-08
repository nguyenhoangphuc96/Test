package vn.com.lacviet.lacviethpsmuseummanagementapp.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vn.com.lacviet.lacviethpsmuseummanagementapp.FileDecryption;
import vn.com.lacviet.lacviethpsmuseummanagementapp.KeyString;
import vn.com.lacviet.lacviethpsmuseummanagementapp.R;
import vn.com.lacviet.lacviethpsmuseummanagementapp.Util;
import vn.com.lacviet.lacviethpsmuseummanagementapp.WebAPI.Model.ExhibitMainScreenModel;
import vn.com.lacviet.lacviethpsmuseummanagementapp.WebAPI.Remote.ApiService;
import vn.com.lacviet.lacviethpsmuseummanagementapp.WebAPI.Remote.ApiUtils;

public class ExhibitMainscreenRecyclerViewAdapter extends RecyclerView.Adapter<ExhibitMainscreenRecyclerViewAdapter.ViewHolder> {

    private List<ExhibitMainScreenModel> ExhibitList;
    private Context mContext;
    private PostItemListener mItemListener;
    private int id;
    //web api
    private ApiService mService;


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView tvName,tvDescription;
        public ImageView imvExhibit;
        PostItemListener mItemListener;

        public ViewHolder(View itemView, PostItemListener postItemListener) {
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
    }

    public ExhibitMainscreenRecyclerViewAdapter(Context context, List<ExhibitMainScreenModel> posts, PostItemListener itemListener) {
        ExhibitList = posts;
        mContext = context;
        mItemListener = itemListener;
    }

    @Override
    public ExhibitMainscreenRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View postView = inflater.inflate(R.layout.item_recyclerview_mainscreen, parent, false);

        ViewHolder viewHolder = new ViewHolder(postView, this.mItemListener);
        //web api
        mService = ApiUtils.getSOService();
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ExhibitMainscreenRecyclerViewAdapter.ViewHolder holder, int position) {

        ExhibitMainScreenModel item = ExhibitList.get(position);
        TextView tvName = holder.tvName;
        tvName.setText(item.getEXHIBITNAME());
        TextView tvDesc = holder.tvDescription;
        tvDesc.setText(item.getDESCRIPTION());
        ImageView imv = holder.imvExhibit;

        //get ID and load image by id
        id = item.getEXHID();
        loadImage(id,holder);


    }


    private void loadImage(int id, ViewHolder holder) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                    //get image default
                    mService.getExhibitImageById(id, true).enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {

                            if (response.isSuccessful()) {
                                try{
                                    showImage(response.body(),holder);
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
    private void showImage(String imageString, ViewHolder holder) {
        /*Bitmap bmp = Util.StringToBitMap(imageString);
        ImageView imv = holder.imvExhibit;
        imv.setImageBitmap(bmp);
*/
        ImageView imv = holder.imvExhibit;
        String data =  new FileDecryption().decrypt(imageString);
        Picasso.with(mContext).load(data).into(imv);


    }

    public void showErrorMessage() {
        Toast.makeText(mContext, "Error loading posts", Toast.LENGTH_SHORT).show();
    }

}