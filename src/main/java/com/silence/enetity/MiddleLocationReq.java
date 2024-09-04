package com.silence.enetity;

import lombok.Data;

import java.util.List;

/**
 * @author silence
 * @since 2024/6/27 15:21
 **/
@Data
public class MiddleLocationReq {

    private Integer locationNumber;

    private List<Location> locationList;
}
