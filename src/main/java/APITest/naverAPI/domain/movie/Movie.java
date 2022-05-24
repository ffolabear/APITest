package APITest.naverAPI.domain.movie;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Movie {

    private Long id;
    private String actor;
    private String image;
    private String director;
    private String subtitle;
    private String title;
    private Integer pubDate;
    private Double userRating;

}
