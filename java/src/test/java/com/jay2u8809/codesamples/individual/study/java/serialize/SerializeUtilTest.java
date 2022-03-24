package com.jay2u8809.codesamples.individual.study.java.serialize;

import org.apache.commons.lang3.SerializationUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

/**
 * https://huisam.tistory.com/entry/javaserialization?category=705896
 */
class SerializeUtilTest {

    @DisplayName("Serialize - Deserialize: SerializeUtil")
    @Test
    void serialized_test() {
        // given
        OrderDto order = OrderDto.builder()
                .id(50000L)
                .name("Shoes")
                .address("Jeju")
                .product("Nike")
                .price(67000)
                .orderedTime(LocalDateTime.now())
                .build();
        OrderDto afterOrder = OrderDto.builder().build();

        // when
        // serailize
        final byte[] byteArray = SerializeUtil.serialize(order);
        // deserialize
        afterOrder = SerializeUtil.deserialize(byteArray);

        // then
        Assertions.assertEquals(afterOrder, order);
    }

    @DisplayName("Serialize - Deserialize: Apache commons-lang3")
    @Test
    void serialized_commons_lang3_test() {

        // given
        OrderDto order = OrderDto.builder()
                .id(50000L)
                .name("Shoes")
                .address("Jeju")
                .product("Nike")
                .price(67000)
                .orderedTime(LocalDateTime.now())
                .build();
        OrderDto afterOrder = OrderDto.builder().build();

        // when
        // serailize
        final byte[] byteArray = SerializationUtils.serialize(order);
        // deserialize
        afterOrder = SerializationUtils.deserialize(byteArray);

        // then
        Assertions.assertEquals(afterOrder, order);
    }
}