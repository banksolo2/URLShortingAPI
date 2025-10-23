package app.web.seun_olo2.URLShortingAPI.repository;

import app.web.seun_olo2.URLShortingAPI.entity.ShortUrl;
import app.web.seun_olo2.URLShortingAPI.model.ShortUrlModel;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ShortUrlRepository extends JpaRepository<ShortUrl, Long> {

    @Query("select su from ShortUrl su where su.shortCode = :shortCode")
    public ShortUrl findByShortCode(@Param("shortCode") String shortCode);

    @Query("select max(su.id) from ShortUrl su")
    public Long lastHighestId();

    @Query("select case when count(su) > 0 then true else false end from ShortUrl su where su.shortCode = :shortCode")
    public Boolean existsShortUrlByShortCode(@Param("shortCode") String shortCode);

    @Query("select new app.web.seun_olo2.URLShortingAPI.model.ShortUrlModel(su.id, su.url, su.shortCode, su.createdAt, su.updatedAt) from ShortUrl su where su.shortCode = :shortCode")
    public ShortUrlModel getShortUrlsWithModel(@Param("shortCode")String shortCode);


    @Modifying
    @Transactional
    @Query("delete from ShortUrl su where su.shortCode = :shortCode")
    public void deleteByShortCode(@Param("shortCode") String shortCode);
}
