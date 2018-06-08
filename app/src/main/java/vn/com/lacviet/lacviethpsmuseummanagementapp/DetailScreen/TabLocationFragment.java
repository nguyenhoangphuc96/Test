package vn.com.lacviet.lacviethpsmuseummanagementapp.DetailScreen;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vn.com.lacviet.lacviethpsmuseummanagementapp.ProgressDialog.CustomProgressDialogTwo;
import vn.com.lacviet.lacviethpsmuseummanagementapp.KeyString;
import vn.com.lacviet.lacviethpsmuseummanagementapp.R;
import vn.com.lacviet.lacviethpsmuseummanagementapp.WebAPI.Model.ExhibitModel;
import vn.com.lacviet.lacviethpsmuseummanagementapp.WebAPI.Remote.ApiService;
import vn.com.lacviet.lacviethpsmuseummanagementapp.WebAPI.Remote.ApiUtils;

public class TabLocationFragment extends Fragment {
    //web api
    private ApiService mService;
    private TextView tvLocation,tvLocationNote,tvOwner,tvOwnerAddress,tvSecretLv,tvColector;
    //ProgressDialog
    private ProgressBar viewProgressBar;
    private CustomProgressDialogTwo customProgressDialogTwo;

    public TabLocationFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_info_detail_tab_location,container,false);
        addControl(view);
        loadAnswers();
        return view;
    }
    private void showProgressDialog() {
        viewProgressBar.setVisibility(View.GONE);
        customProgressDialogTwo.show();
    }

    private void loadAnswers() {
        //get id
        Bundle extras = getActivity().getIntent().getExtras();
        KeyString key = new KeyString();
        int id = extras.getInt(key.ITEM_KEY);

        mService.getExhibitById(id).enqueue(new Callback<ExhibitModel>() {
            @Override
            public void onResponse(Call<ExhibitModel> call, Response<ExhibitModel> response) {

                if(response.isSuccessful()) {
                    showDataToView(response.body());
                    Log.d("AnswersPresenter", "posts loaded from API");
                }else {
                    int statusCode  = response.code();
                    Toast.makeText(getContext(), "Error"+statusCode+response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ExhibitModel> call, Throwable t) {
                showErrorMessage();
                Log.d("AnswersPresenter", "error loading from API");

            }
        });
    }


    public void showErrorMessage() {
        Toast.makeText(getContext(), "Error loading posts", Toast.LENGTH_SHORT).show();
    }
    private void showDataToView(ExhibitModel exhibitModel) {
        tvLocation.setText(exhibitModel.getLOCATIONNAME());
        tvLocationNote.setText(exhibitModel.getLOCATIONNOTE());
        tvOwner.setText(exhibitModel.getOWNERNAME());
        tvOwnerAddress.setText(exhibitModel.getOWNERADDRESS());
        tvSecretLv.setText(exhibitModel.getSECRETLEVELNAME());
        tvColector.setText(exhibitModel.getCOLLECTORNAME());





    }

    private void addControl(View view) {

        mService = ApiUtils.getSOService();
        tvLocation=view.findViewById(R.id.tvLocation);
        tvLocationNote=view.findViewById(R.id.tvLocationNote);
        tvOwner=view.findViewById(R.id.tvOwner);
        tvOwnerAddress=view.findViewById(R.id.tvOwnerAddress);
        tvSecretLv=view.findViewById(R.id.tvSecretLv);
        tvColector=view.findViewById(R.id.tvColector);
        //
        viewProgressBar = view.findViewById(R.id.viewProgressBar);
        customProgressDialogTwo = new CustomProgressDialogTwo(getContext(),R.drawable.ic_vn_drum);


    }
}
