package APITest.naverAPI.domain.movie;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ParsingJson {

    public List<String> parsingActor(String actorList) {

//        actorList = actorList.replace("|", " ");
        String[] temp = actorList.split("\\|");
        List<String> result = new ArrayList<>();

        if (temp.length == 0) {
            result.add("-");
        } else {
            result = new ArrayList<>(Arrays.asList(temp));
        }
        return result;
    }

    public String parsingImageUrl(String imageUrl) {
        imageUrl = imageUrl.replace("\\", "");
        return imageUrl;
    }

    public List<String> parsingDirector(String directorList) {
//        directorList = directorList.replace("|", " ");
        String[] temp = directorList.split("\\|");
        return new ArrayList<>(Arrays.asList(temp));
    }

    public String parsingTitle(String title) {
        title = title.replace("<b>", "");
        title = title.replace("</b>", "");
        return title;
    }

    public String parsingSubTitle(String subtitle) {
        return subtitle;
    }

    public Integer parsingPubDate(String pubdate) {
        return Integer.parseInt(pubdate);
    }

    public Double parsingUserRating(String userRating) {
        return Double.parseDouble(userRating);
    }
}
