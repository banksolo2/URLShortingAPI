package app.web.seun_olo2.URLShortingAPI.service;

import app.web.seun_olo2.URLShortingAPI.model.ResponseMessage;
import app.web.seun_olo2.URLShortingAPI.model.ShortUrlModel;
import app.web.seun_olo2.URLShortingAPI.model.UrlModel;
import org.springframework.http.HttpStatus;

public interface ShortUrlService {

    public String generateShortCode();

    public ResponseMessage create(UrlModel urlModel);

    public ResponseMessage getByShortCode(String shortCode);

    public ResponseMessage  update(String shortCode, UrlModel urlModel);

    public ResponseMessage delete(String shortCode);

    public ResponseMessage getStats(String shortCode);
}
