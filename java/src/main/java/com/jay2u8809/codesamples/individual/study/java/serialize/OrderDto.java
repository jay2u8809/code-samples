package com.jay2u8809.codesamples.individual.study.java.serialize;

import lombok.*;

import java.io.*;
import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@Builder
@EqualsAndHashCode
public class OrderDto implements Serializable {

    public static final long serialVersionUID = 42L;

    private Long id;

    private String name;

    private String product;

    private Integer price;

    private String address;

    private LocalDateTime orderedTime;
}
