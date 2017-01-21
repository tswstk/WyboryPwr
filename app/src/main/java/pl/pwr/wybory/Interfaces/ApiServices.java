package pl.pwr.wybory.Interfaces;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Tomek on 21.01.2017.
 */

public interface ApiServices {

    @GET("Wybory")
    Call<ResponseBody> getAllElections();
}
