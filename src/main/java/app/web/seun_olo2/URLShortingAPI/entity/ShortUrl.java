package app.web.seun_olo2.URLShortingAPI.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;

@Entity
@Table( name = "short_urls" )
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ShortUrl {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;
    private String url;
    private String shortCode;
    private Integer accessCount;
    @CreationTimestamp
    private Instant createdAt;
    @UpdateTimestamp
    private Instant updatedAt;


}
