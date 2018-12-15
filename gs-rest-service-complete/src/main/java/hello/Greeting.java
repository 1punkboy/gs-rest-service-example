package hello;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Setter
@Getter
public class Greeting {
    private long id;
    private String content;
}
