package sda.catalunhab.assignment2_app.types;

import android.os.Parcel;
import android.os.Parcelable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Email implements Parcelable {

    private String to;

    private String subject;

    private String content;

    private Email(Parcel in) {
        to = in.readString();
        subject = in.readString();
        content = in.readString();
    }

    public static final Creator<Email> CREATOR = new Creator<Email>() {
        @Override
        public Email createFromParcel(Parcel source) {
            return new Email(source);
        }

        @Override
        public Email[] newArray(int size) {
            return new Email[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(to);
        dest.writeString(subject);
        dest.writeString(content);
    }

    public static Creator<Email> getCREATOR() {
        return CREATOR;
    }

    @Override
    public String toString() {
        return  "To: " + to + "\n" +
                "Subject: " + subject + "\n" +
                "Content: " + content + "\n";
    }
}
