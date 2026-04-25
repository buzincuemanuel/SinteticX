package com.buzincuemanuel.sinteticx.model;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class User {

    private Long id;
    private String username;
    private String email;
    private String password;

}
