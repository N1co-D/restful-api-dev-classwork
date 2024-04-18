package dev.restful.api.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class ProductResponse {
    private String id;
    private String name;
    private String createdAt;
    private Data data;
}
