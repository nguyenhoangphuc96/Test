package vn.com.lacviet.lacviethpsmuseummanagementapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vn.com.lacviet.lacviethpsmuseummanagementapp.DetailScreen.ExhibitDetailActivityNew;
import vn.com.lacviet.lacviethpsmuseummanagementapp.LoadMore.EndlessRecyclerViewScrollListener;
import vn.com.lacviet.lacviethpsmuseummanagementapp.MenuNavigation.CategogMenuFragment;
import vn.com.lacviet.lacviethpsmuseummanagementapp.MenuNavigation.IntroMenuFragment;
import vn.com.lacviet.lacviethpsmuseummanagementapp.MenuNavigation.LegislationMenuFragment;
import vn.com.lacviet.lacviethpsmuseummanagementapp.MenuNavigation.SearchMenuFragment;
import vn.com.lacviet.lacviethpsmuseummanagementapp.ProgressDialog.CustomProgressDialogTwo;
import vn.com.lacviet.lacviethpsmuseummanagementapp.ProgressDialog.LoadMoreProgressDialog;
import vn.com.lacviet.lacviethpsmuseummanagementapp.WebAPI.Model.AllExhibitJsonResponse;
import vn.com.lacviet.lacviethpsmuseummanagementapp.WebAPI.Model.ExhibitMainScreenModel;
import vn.com.lacviet.lacviethpsmuseummanagementapp.WebAPI.Remote.ApiService;
import vn.com.lacviet.lacviethpsmuseummanagementapp.WebAPI.Remote.ApiUtils;
import vn.com.lacviet.lacviethpsmuseummanagementapp.adapter.Adapter;



public class MainActivityNew extends AppCompatActivity implements View.OnClickListener {
    Toolbar toolbar;
    RecyclerView recyclerView;
    NavigationView navigationView;
    DrawerLayout drawerLayout;
    TextView tvTitleToolbar, txtTitleCategory;


    //menu
    private int arrImageViewId[] = {R.id.imvHomeMenu, R.id.imvIntroMenu, R.id.imvSearchMenu, R.id.imvCateMenu, R.id.imvLegisMenu, R.id.imvContactMenu, R.id.imvExitMenu};
    private ImageView arrImageView[] = new ImageView[arrImageViewId.length];
    //Dialog

    AlertDialog alertDialog;
    ListView lvCategogyName;
    RelativeLayout loToolBar, rlTitleCategory;
    //RecyclerView api
    private Adapter mAdapter;
    private ApiService mService;
    //ProgressDialog
    private ProgressBar viewProgressBar;
    private CustomProgressDialogTwo customProgressDialogTwo;
    private LoadMoreProgressDialog loadMoreProgressDialog;
    //load more
    private int indexPage=1;
    private int size=10;

    private EndlessRecyclerViewScrollListener scrollListener;
    //
    int id;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_new);
        //set transparent stt bar
        //StatusBarUtil.setTransparent(this);
        addControl();
        actionBar();
        showDataToRecyclerView();
        showProgressDialog();
        loadAnswers(indexPage,size);
        showIntroMenu();
        setPositionTextViewTittleCategogy();
        addEvent();




    }



    public void showErrorMessage() {
        Toast.makeText(MainActivityNew.this, "Error loading posts", Toast.LENGTH_SHORT).show();
    }

    private void showProgressDialog() {
        viewProgressBar.setVisibility(View.GONE);
        customProgressDialogTwo.show();
    }
    private void showLoadMoreProgressDialog(){
        viewProgressBar.setVisibility(View.GONE);
        loadMoreProgressDialog.show();
    }
    private void loadAnswers(int indexPage,int size) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                mService.getExhibitByPage(indexPage,size).enqueue(new Callback<AllExhibitJsonResponse>() {
                    @Override
                    public void onResponse(Call<AllExhibitJsonResponse> call, Response<AllExhibitJsonResponse> response) {

                        if(response.isSuccessful()) {
                            customProgressDialogTwo.dismiss();
                            mAdapter.updateAnswers(response.body().getExhibitModels());
                            Log.d("AnswersPresenter", "posts loaded from API");
                        }else {
                            int statusCode  = response.code();
                            Toast.makeText(MainActivityNew.this, "Error"+statusCode+response.message(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<AllExhibitJsonResponse> call, Throwable t) {
                        showErrorMessage();
                        Log.d("AnswersPresenter", "error loading from API");

                    }
                });
            }
        }).start();

    }
    private void showDataToRecyclerView() {
        mAdapter = new Adapter(this, new ArrayList<ExhibitMainScreenModel>(0), new Adapter.PostItemListener() {


            @Override
            public void onPostClick(long id) {
                startDetailActivity((int)id);

            }
        });
        /*RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);*/
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(mAdapter);
        recyclerView.setHasFixedSize(true);
        EndlessRecyclerViewScrollListener scrollListener = new EndlessRecyclerViewScrollListener(layoutManager) {

            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                showLoadMoreProgressDialog();
                loadMoreAnswers(page,size);


            }
        };
        recyclerView.addOnScrollListener(scrollListener);
    }

    private void loadMoreAnswers(int i, int size) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                mService.getExhibitByPage(i+1,size).enqueue(new Callback<AllExhibitJsonResponse>() {
                    @Override
                    public void onResponse(Call<AllExhibitJsonResponse> call, Response<AllExhibitJsonResponse> response) {

                        if(response.isSuccessful()) {
                            loadMoreProgressDialog.dismiss();
                            mAdapter.updateMoreAnswers(response.body().getExhibitModels());

                            Log.d("AnswersPresenter", "posts loaded from API");
                        }else {
                            int statusCode  = response.code();
                            Toast.makeText(MainActivityNew.this, "Error"+statusCode+response.message(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<AllExhibitJsonResponse> call, Throwable t) {
                        showErrorMessage();
                        Log.d("AnswersPresenter", "error loading from API");

                    }
                });
            }
        }).start();



    }


    private void startDetailActivity(int id) {
        Intent intent = new Intent(this, ExhibitDetailActivityNew.class);
        KeyString key = new KeyString();
        intent.putExtra(key.ITEM_KEY,id);
        startActivity(intent);
    }

    private void setPositionTextViewTittleCategogy() {

        loToolBar = findViewById(R.id.loToolBar);
        rlTitleCategory = findViewById(R.id.rlTitleCategory);
        ViewTreeObserver vto = toolbar.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                int width = loToolBar.getMeasuredWidth();
                int height = loToolBar.getMeasuredHeight();
                int tvWidth = txtTitleCategory.getMeasuredWidth();
                int tvHeight = txtTitleCategory.getMeasuredHeight();
                RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
                lp.setMargins((width / 2) - (tvWidth / 2), height - (tvHeight / 2) + 4, 0, 0);
                rlTitleCategory.setLayoutParams(lp);
            }
        });
    }

    private void addEvent() {
        txtTitleCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showViewDialog();
            }
        });
        drawerLayout.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {

            }

            @Override
            public void onDrawerOpened(@NonNull View drawerView) {

            }

            @Override
            public void onDrawerClosed(@NonNull View drawerView) {
                showView(txtTitleCategory);

            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });

    }

    private void showViewDialog() {
        View view = getLayoutInflater().inflate(R.layout.dialog_categogy, null);
        lvCategogyName = (ListView) view.findViewById(R.id.lvCategogyName);
        String[] items = new String[]{"Tất cả", "Giấy", "Kim loại", "Sành sứ", "Đá", "Nhựa", "Khác"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items);
        lvCategogyName.setAdapter(adapter);
        alertDialog = new AlertDialog.Builder(this, R.style.CustomDialog)
                .setView(view)
                .create();
        alertDialog.setCancelable(true);
        alertDialog.show();
    }

    private void showIntroMenu() {
        //show fragment
        IntroMenuFragment introMenuFragment = new IntroMenuFragment();
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction()
                .replace(R.id.layout_show_menu, introMenuFragment, introMenuFragment.getTag())
                .commit();
        //set color
        setDefaultIconMenu();
        arrImageView[1].setImageDrawable(getResources().getDrawable(R.drawable.ic_intro_yellow));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_search:

                return true;
            case R.id.menu_user:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void actionBar() {
        setSupportActionBar(toolbar);
        tvTitleToolbar.setText(toolbar.getTitle());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setNavigationIcon(R.drawable.ic_menu);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
                //hide text view categogy
                hideView(txtTitleCategory);

            }
        });


    }

    public void hideView(View view) {


        view.setVisibility(View.INVISIBLE);


    }

    public void showView(View view) {
        view.setVisibility(View.VISIBLE);
    }

    private void addControl() {
        toolbar = findViewById(R.id.toolbarMainscreen);
        recyclerView = findViewById(R.id.rcvMainScreen);
        navigationView = findViewById(R.id.ngvMainscreen);
        drawerLayout = findViewById(R.id.drawerLayout);
        tvTitleToolbar = findViewById(R.id.tvTitleToolbar);
        //Menu
        int index = 0;
        for (index = 0; index < arrImageView.length; index++) {
            arrImageView[index] = findViewById(arrImageViewId[index]);
            arrImageView[index].setOnClickListener(this);
        }
        txtTitleCategory = findViewById(R.id.txtTitleCategory);
        //api
        mService = ApiUtils.getSOService();
        //
        viewProgressBar = findViewById(R.id.viewProgressBar);
        customProgressDialogTwo = new CustomProgressDialogTwo(this,R.drawable.ic_vn_drum);
        loadMoreProgressDialog = new LoadMoreProgressDialog(this,R.drawable.ic_vn_drum);
        //
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.imvHomeMenu: {
                Intent intent = new Intent(MainActivityNew.this,MainActivityNew.class);
                startActivity(intent);

                break;
            }
            case R.id.imvIntroMenu: {
                //show fragment
                IntroMenuFragment introMenuFragment = new IntroMenuFragment();
                FragmentManager manager = getSupportFragmentManager();
                manager.beginTransaction()
                        .replace(R.id.layout_show_menu, introMenuFragment, introMenuFragment.getTag())
                        .commit();
                //set color
                setDefaultIconMenu();
                arrImageView[1].setImageDrawable(getResources().getDrawable(R.drawable.ic_intro_yellow));
                break;
            }
            case R.id.imvSearchMenu: {
                //show fragment
                SearchMenuFragment searchMenuFragment = new SearchMenuFragment();
                FragmentManager manager = getSupportFragmentManager();
                manager.beginTransaction()
                        .replace(R.id.layout_show_menu, searchMenuFragment, searchMenuFragment.getTag())
                        .commit();
                //set color
                setDefaultIconMenu();
                arrImageView[2].setImageDrawable(getResources().getDrawable(R.drawable.ic_search_yellow));
                break;
            }
            case R.id.imvCateMenu: {
                //show fragment
                CategogMenuFragment cateMenuFragment = new CategogMenuFragment();
                FragmentManager manager = getSupportFragmentManager();
                manager.beginTransaction()
                        .replace(R.id.layout_show_menu, cateMenuFragment, cateMenuFragment.getTag())
                        .commit();
                //set color
                setDefaultIconMenu();
                arrImageView[3].setImageDrawable(getResources().getDrawable(R.drawable.ic_categogy_yellow));
                break;
            }
            case R.id.imvLegisMenu: {
                //show fragment
                LegislationMenuFragment legisMenuFragment = new LegislationMenuFragment();
                FragmentManager manager = getSupportFragmentManager();
                manager.beginTransaction()
                        .replace(R.id.layout_show_menu, legisMenuFragment, legisMenuFragment.getTag())
                        .commit();
                //set color
                setDefaultIconMenu();
                arrImageView[4].setImageDrawable(getResources().getDrawable(R.drawable.ic_legislation_yellow));
                break;
            }
            case R.id.imvContactMenu: {
                break;

            }
            case R.id.imvExitMenu: {
                onBackPressed();

                break;
            }
            default:
                break;
        }

    }
    @Override
    public void onBackPressed() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("Exit Application?");
        alertDialogBuilder
                .setMessage("Click yes to exit!")
                .setCancelable(false)
                .setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                moveTaskToBack(true);
                                android.os.Process.killProcess(android.os.Process.myPid());
                                System.exit(1);
                            }
                        })

                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        dialog.cancel();
                    }
                });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    private void setDefaultIconMenu() {
        int index = 0;
        for (index = 0; index < arrImageView.length; index++) {
            switch (index) {
                case 0: {
                    break;
                }
                case 1: {
                    arrImageView[1].setImageDrawable(getResources().getDrawable(R.drawable.ic_intro_circle));
                }
                case 2: {
                    arrImageView[2].setImageDrawable(getResources().getDrawable(R.drawable.ic_search_circle));
                }
                case 3: {
                    arrImageView[3].setImageDrawable(getResources().getDrawable(R.drawable.ic_categogy_circle));
                }
                case 4: {
                    arrImageView[4].setImageDrawable(getResources().getDrawable(R.drawable.ic_legislation_circle));
                }
                case 5: {
                    arrImageView[5].setImageDrawable(getResources().getDrawable(R.drawable.ic_contact_circle));
                }
                case 6: {

                }
            }
        }
    }

}
