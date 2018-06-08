package vn.com.lacviet.lacviethpsmuseummanagementapp.WebAPI.Remote;



public class ApiUtils {


    private ApiUtils() {
    }

    public static final String BASE_URL = "http://demo.museum.vebrary.vn/api/";

    public static ApiService getSOService() {

        return RetrofitClient.getClient(BASE_URL).create(ApiService.class);
    }
}
