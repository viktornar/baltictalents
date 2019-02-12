package lt.baltictalents.lessons.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@AllArgsConstructor
@NoArgsConstructor
public class IsAdmin {
    @Getter
    @Setter
    @JsonProperty("isAdmin")
    private boolean admin;
}