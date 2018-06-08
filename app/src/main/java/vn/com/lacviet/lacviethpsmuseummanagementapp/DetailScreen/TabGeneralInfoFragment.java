package vn.com.lacviet.lacviethpsmuseummanagementapp.DetailScreen;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.gigamole.infinitecycleviewpager.HorizontalInfiniteCycleViewPager;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vn.com.lacviet.lacviethpsmuseummanagementapp.KeyString;
import vn.com.lacviet.lacviethpsmuseummanagementapp.R;
import vn.com.lacviet.lacviethpsmuseummanagementapp.Util;
import vn.com.lacviet.lacviethpsmuseummanagementapp.WebAPI.Model.ExhibitModel;
import vn.com.lacviet.lacviethpsmuseummanagementapp.WebAPI.Model.ImageByIDResponse;
import vn.com.lacviet.lacviethpsmuseummanagementapp.WebAPI.Remote.ApiService;
import vn.com.lacviet.lacviethpsmuseummanagementapp.WebAPI.Remote.ApiUtils;
import vn.com.lacviet.lacviethpsmuseummanagementapp.adapter.InfinityCycleImageViewAdapter;

public class TabGeneralInfoFragment extends Fragment {

    List<Bitmap> lstImages;
    HorizontalInfiniteCycleViewPager pager;
    ImageView imgExhibitDatail;
    //web api
    private ApiService mService;
    private TextView tvName, tvOtherName, tvStuff, tvNumber, tvFeild, tvCode, tvMaterial, tvElement, tvDesc;
    //
    ProgressBar progressBar;
    //
    int id;


    public TabGeneralInfoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_info_detail_tab_general_info, container, false);

        addControls(view);
        loadAnswers();
        loadImageDefault();
        loadImageList();
        addEvents();
        return view;
    }

    private void loadImageList() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                //Get list image
                mService.getAllExhibitImageById(id, true).enqueue(new Callback<ImageByIDResponse>() {

                    @Override
                    public void onResponse(Call<ImageByIDResponse> call, Response<ImageByIDResponse> response) {
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                if (response.isSuccessful()) {
                                    initDataInfinityCycle(response.body().getExhibitImages());
                                    Log.d("AnswersPresenter", "Detail image list loaded!!!");
                                } else {
                                    int statusCode = response.code();
                                    Toast.makeText(getContext(), "Error" + statusCode + response.message(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        }).run();
                    }

                    @Override
                    public void onFailure(Call<ImageByIDResponse> call, Throwable t) {
                        showErrorMessage();
                        Log.d("AnswersPresenter", "error loading from API");
                    }
                });
            }
        }).start();
    }

    private void loadImageDefault() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                //get image default
                mService.getExhibitImageById(id, true).enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {

                        if (response.isSuccessful()) {
                            showImage(response.body());
                            Log.d("AnswersPresenter", "Detail image default loaded!!!");
                        } else {
                            int statusCode = response.code();
                            Toast.makeText(getContext(), "Error" + statusCode + response.message(), Toast.LENGTH_SHORT).show();
                        }

                    }


                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        showErrorMessage();
                        Log.d("AnswersPresenter", "error loading from API");

                    }
                });
            }
        }).start();

    }


    private void loadAnswers() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                //get id
                Bundle extras = getActivity().getIntent().getExtras();
                KeyString key = new KeyString();
                id = extras.getInt(key.ITEM_KEY);


                mService.getExhibitById(id).enqueue(new Callback<ExhibitModel>() {


                    @Override
                    public void onResponse(Call<ExhibitModel> call, Response<ExhibitModel> response) {

                        if (response.isSuccessful()) {
                            showDataToView(response.body());
                            Log.d("AnswersPresenter", "posts loaded from API");
                        } else {
                            int statusCode = response.code();
                            Toast.makeText(getContext(), "Error" + statusCode + response.message(), Toast.LENGTH_SHORT).show();
                        }


                    }

                    @Override
                    public void onFailure(Call<ExhibitModel> call, Throwable t) {
                        showErrorMessage();
                        Log.d("AnswersPresenter", "error loading from API");

                    }
                });

            }
        }).run();


    }

    private void showImage(String imageString) {
        Bitmap bmp = Util.StringToBitMap(imageString);
        imgExhibitDatail.setImageBitmap(bmp);

    }

    public void showErrorMessage() {
        Toast.makeText(getContext(), "Error loading posts", Toast.LENGTH_SHORT).show();
    }

    private void showDataToView(ExhibitModel body) {

        tvName.setText(body.getEXHIBITNAME());
        tvOtherName.setText(body.getOTHERNAME());
        tvCode.setText(body.getCODEID());
        tvDesc.setText(body.getDESCRIPTION());
        tvStuff.setText(body.getSTUFFNAME());
        tvNumber.setText(body.getNUMBER().toString());
        tvElement.setText(body.getELEMENT());
        tvMaterial.setText(body.getMATERIALNAME());
        tvFeild.setText(body.getFIELDNAME());

    }

    private void addEvents() {
        imgExhibitDatail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startShowImageActivity(id);
            }
        });
        pager.startAutoScroll(true);

    }

    private void startShowImageActivity(int id) {
        Intent intent = new Intent(getContext(), ShowImageExhibitActivity.class);
        KeyString key = new KeyString();
        intent.putExtra(key.ITEM_KEY, id);
        getContext().startActivity(intent);
    }

    private void addControls(View view) {


        pager = view.findViewById(R.id.horizontal_cycle_mini);
        imgExhibitDatail = view.findViewById(R.id.imgExhibitDetail);
        //web api
        mService = ApiUtils.getSOService();
        tvName = view.findViewById(R.id.tvExhibitName);
        tvOtherName = view.findViewById(R.id.tvOtherName);
        tvStuff = view.findViewById(R.id.tvStuff);
        tvNumber = view.findViewById(R.id.tvNumber);
        tvFeild = view.findViewById(R.id.tvField);
        tvCode = view.findViewById(R.id.tvCodeID);
        tvMaterial = view.findViewById(R.id.tvMaterialName);
        tvElement = view.findViewById(R.id.tvElement);
        tvDesc = view.findViewById(R.id.tvDescription);

        //
        progressBar = view.findViewById(R.id.progressBar);
        //
        lstImages = new ArrayList<>();
    }

    private void initDataInfinityCycle(List<String> exhibitImages) {
        if (exhibitImages.size() == 1) {
            Bitmap bmp = Util.StringToBitMap(exhibitImages.get(0));
            lstImages.add(bmp);
            lstImages.add(bmp);
            lstImages.add(bmp);
        } else {
            for (int i = 0; i < exhibitImages.size(); i++) {
                lstImages.add(Util.StringToBitMap(exhibitImages.get(i)));

            }
        }
        showDatatoInfinityCycle(lstImages);


    }

    private void showDatatoInfinityCycle(List<Bitmap> lstImages) {
        InfinityCycleImageViewAdapter adapter = new InfinityCycleImageViewAdapter(lstImages,getContext());
        pager.setAdapter(adapter);

    }
}
