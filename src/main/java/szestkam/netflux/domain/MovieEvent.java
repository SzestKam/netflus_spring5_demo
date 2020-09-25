package szestkam.netflux.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class MovieEvent {

    private String movieID;
    private Date date;
}
