package pl.pwr.wybory.Model;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Yebuo-admin on 2017-01-22.
 */

public class Questionnaire implements Parcelable {

    Date finishDate;
    String dateString;
    int questionnaireId;
    QuestionnaireWorker questionnaireWorker;


    public Questionnaire(JSONObject jsonString) {
        try {
            this.questionnaireId = jsonString.getInt("IdAnkiety");
            this.dateString = jsonString.getString("DataZakonczenia");
            dateString = dateString.substring(0, 10);

            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date d = formatter.parse(dateString);
            this.finishDate = d;

        } catch (JSONException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    protected Questionnaire(Parcel in) {
        questionnaireId = in.readInt();
        dateString = in.readString();
        questionnaireWorker = in.readParcelable(QuestionnaireWorker.class.getClassLoader());
    }

    public static final Creator<Questionnaire> CREATOR = new Creator<Questionnaire>() {
        @Override
        public Questionnaire createFromParcel(Parcel in) {
            return new Questionnaire(in);
        }

        @Override
        public Questionnaire[] newArray(int size) {
            return new Questionnaire[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    public QuestionnaireWorker getQuestionnaireWorker() {
        return questionnaireWorker;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(dateString);
        parcel.writeParcelable(questionnaireWorker, i);
    }

    public String getDate() {
        return dateString;
    }

    public int getQuestionnaireId() {
        return questionnaireId;
    }

    public int getId()
    {
        return questionnaireWorker.elector_id;
    }
}

