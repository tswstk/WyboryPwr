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


/*
    [{
        "$id":"1","Ankieta":
        {
            "$id":"2","Ankieter":
            {
                "$id":"3","IdAnkietera":1,"IdPracownika":8,"Ankietas":
                [{
                "$ref":"2"
                }],
                "Pracownik":null
            },
            "Pytanias":
            [{"$ref":"1"
            }],
            "IdAnkiety":1,"DataZakonczenia":"2017-01-25T00:00:00","IdAnkietera":1
        },
        "Odpowiedzs":
        [{
        "$id":"4","IdOpowiedzi":1,"IdWyborcy":4,"IdPytania":1,"Odpowiedz1":"Bardzo dobrze","Pytania":
            {
                "$ref":"1"
            },"Wyborca":null
        }],
        "IdPytania":1,"IdAnkiety":1,"TrescPytania":"Jak ocenia Pan/Pani aplikacje do przeprowadzania wyborów?"
    }]
*/

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

    public int getQuestionId() {
        return questionId;
    }
}
