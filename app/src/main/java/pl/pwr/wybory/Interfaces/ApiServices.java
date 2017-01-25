package pl.pwr.wybory.Interfaces;

import java.util.ArrayList;

import okhttp3.ResponseBody;
import pl.pwr.wybory.Model.Answers;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by Tomek on 21.01.2017.
 */

public interface ApiServices {

    @GET("WyboryWyswietlanie")
    Call<ResponseBody> getAllElections();

    @GET("ElectionKandidate/{id}")
    Call<ResponseBody> getCandidates(@Path("id") int id);

    @DELETE("Kandydat/{id}")
    Call<ResponseBody> deleteCandidate(@Path("id") int id);

    @GET("KandydatIdW/{id}")
    Call<ResponseBody> getCandidate(@Path("id") int id);

    @GET("Ankieta")
    Call<ResponseBody> getAllQuestionnaires();

    @GET("Pytaniaankiety/{id}")
    Call<ResponseBody> getQuestion(@Path("id") int id);

    @GET("Logowanie/{login}/{pass}")
    Call<ResponseBody>logOn(@Path("login") String login, @Path("pass") String pass);

    @GET("Glosowanie/{idK}/{idW}")
    Call<ResponseBody> vote(@Path("idK") int idK, @Path("idW") int idW);

    @POST("OdbiorAnkiety/{listaOdpowiedzi}/{idWyborcy}/{listaPytan}")
    Call<ResponseBody> sendAnswers(@Path("listaOdpowiedzi") ArrayList<Answers> listaOdpowiedzi, @Path("idWyborcy") int idWyborcy, @Path("listaPytan") ArrayList<Integer> listaPytan);

}
