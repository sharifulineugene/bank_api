package com.github.sharifulineugene.excception_handling.root;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class BadRequest {
    private String info;
}
