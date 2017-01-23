package pl.pwr.wybory.Model;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Yebuo-admin on 2017-01-22.
 */

public class Questions implements Parcelable {

    int questionId;
    String questionBody;
    Questionnaire questionnaire;


    public Questions(JSONObject jsonString) {
        try {
            this.questionBody = jsonString.getString("TrescPytania");
            this.questionId = jsonString.getInt("IdPytania");
            JSONObject questionnaireObject = jsonString.getJSONObject("Ankieta");

            this.questionnaire = new Questionnaire(questionnaireObject);


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    protected Questions(Parcel in) {
        questionId = in.readInt();
        questionBody = in.readString();
        questionnaire = in.readParcelable(Questionnaire.class.getClassLoader());
    }

    public static final Creator<Questions> CREATOR = new Creator<Questions>() {
        @Override
        public Questions createFromParcel(Parcel in) {
            return new Questions(in);
        }

        @Override
        public Questions[] newArray(int size) {
            return new Questions[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(questionId);
        parcel.writeString(questionBody);
        parcel.writeParcelable(questionnaire, i);
    }

    public String getQuestionBody() {
        return questionBody;
    }
}
