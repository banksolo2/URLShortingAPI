package app.web.seun_olo2.URLShortingAPI.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.LocalDateTime;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ShortUrlModel {
    private Long id;
    private String url;
    private String shortCode;
    private Instant createdAt;
    private Instant updatedAt;
}
