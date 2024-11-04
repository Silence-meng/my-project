package com.silence.enetity;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * @author silence
 * @since 2024/6/27 15:18
 **/
@Data
public class Location {

    /**
     * 经度
     */
    @NotBlank(message = "{longitude.not.blank}")
    private String longitude;

    /**
     * 纬度
     */
    @NotBlank(message = "{latitude.not.blank}")
    private String latitude;
}
