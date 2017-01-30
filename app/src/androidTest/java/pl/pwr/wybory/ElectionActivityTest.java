package pl.pwr.wybory;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.ResponseBody;
import pl.pwr.wybory.Interfaces.ApiServices;
import pl.pwr.wybory.Interfaces.Const;
import pl.pwr.wybory.Model.Election;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Created by Tomek on 30.01.2017.
 */
@RunWith(AndroidJUnit4.class)
public class ElectionActivityTest {


    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("pl.pwr.wybory", appContext.getPackageName());
    }

    @Rule
    public ActivityTestRule<ElectionsActivity> rule = new ActivityTestRule<>(ElectionsActivity.class);

    @Test
    public void downloadingElectionsTest() {
        try {
            final ArrayList<Election> mValuse = new ArrayList<>();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(Const.BASE_URL)
                    .build();

            ApiServices services = retrofit.create(ApiServices.class);

            assertNotNull(services);

            services.getAllElections().enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    try {
                        assertNotNull(response);
                        assertNotNull(response.body());
                        assertNotNull(response.body().string());

                        JSONArray jsonArray = new JSONArray(response.body().string());

                        assertNotNull(jsonArray);
                        assertTrue(jsonArray.length() > 0);

                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            Election election = new Election(jsonObject);

                            assertNotNull(election);

                            assertTrue(election.getPositionId() != 0);
                            assertTrue(election.getElectionId() != 0);
                            assertNotNull(election.getFaculty());
                            assertNotNull(election.getDate());
                            assertNotNull(election.getPosition());

                            mValuse.add(election);
                            assertEquals(mValuse.size(), jsonArray.length());
                        }

                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {

                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
