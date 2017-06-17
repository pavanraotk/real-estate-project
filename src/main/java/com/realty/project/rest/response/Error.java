package com.realty.project.rest.response;

import lombok.*;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class Error {
    String code;
    String message;
}
