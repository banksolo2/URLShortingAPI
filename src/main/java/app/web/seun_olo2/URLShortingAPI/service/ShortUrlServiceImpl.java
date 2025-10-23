package app.web.seun_olo2.URLShortingAPI.service;

import app.web.seun_olo2.URLShortingAPI.entity.ShortUrl;
import app.web.seun_olo2.URLShortingAPI.model.ResponseMessage;
import app.web.seun_olo2.URLShortingAPI.model.ShortUrlModel;
import app.web.seun_olo2.URLShortingAPI.model.UrlModel;
import app.web.seun_olo2.URLShortingAPI.repository.ShortUrlRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
public class ShortUrlServiceImpl implements ShortUrlService {

    private final ShortUrlRepository shortUrlRepository;
    private static final String CHARACTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final int CODE_LENGTH = 7;


    public ShortUrlServiceImpl(ShortUrlRepository shortUrlRepository) {
        this.shortUrlRepository = shortUrlRepository;
    }



    @Override
    public String generateShortCode() {
        SecureRandom random = new SecureRandom();
        StringBuilder code = new StringBuilder();
        for (int i = 0; i < CODE_LENGTH; i++) {
            code.append(CHARACTERS.charAt(random.nextInt(CHARACTERS.length())));
        }
        return code.toString();
    }

    @Override
    public ResponseMessage create(UrlModel urlModel) {
        String shortCode = generateShortCode();
        if(shortUrlRepository.existsShortUrlByShortCode(shortCode))
            return ResponseMessage.builder()
                    .type("error")
                    .message("Short codes must be unique and should be generated randomly.")
                    .httpStatus(HttpStatus.BAD_REQUEST)
                    .build();

        ShortUrl shortUrl = ShortUrl.builder()
                .url(urlModel.getUrl())
                .shortCode(shortCode)
                .accessCount(0)
                .build();
        shortUrl = shortUrlRepository.save(shortUrl);
        return ResponseMessage.builder()
                .type("success")
                .object(shortUrlToModel(shortUrl))
                .httpStatus(HttpStatus.CREATED)
                .build();
    }

    @Override
    public ResponseMessage getByShortCode(String shortCode) {
        ShortUrl shortUrl = shortUrlRepository.findByShortCode(shortCode);
        if(Objects.isNull(shortUrl))
            return ResponseMessage.builder()
                    .type("error")
                    .message("Short URL not found.")
                    .httpStatus(HttpStatus.NOT_FOUND)
                    .build();

        shortUrl.setAccessCount(shortUrl.getAccessCount()+1);
        shortUrl = shortUrlRepository.save(shortUrl);
        return ResponseMessage.builder()
                .type("success")
                .object(shortUrlToModel(shortUrl))
                .httpStatus(HttpStatus.OK)
                .build();
    }

    @Override
    public ResponseMessage update(String shortCode, UrlModel urlModel) {
        if(shortUrlRepository.existsShortUrlByShortCode(shortCode) == false) {
            return ResponseMessage.builder()
                    .type("error")
                    .message("Short URL not found.")
                    .httpStatus(HttpStatus.NOT_FOUND)
                    .build();
        }
        ShortUrl shortUrl = shortUrlRepository.findByShortCode(shortCode);
        shortUrl.setUrl(urlModel.getUrl());
        shortUrl =shortUrlRepository.save(shortUrl);
        return ResponseMessage.builder()
                .type("success")
                .object(shortUrlToModel(shortUrl))
                .httpStatus(HttpStatus.OK)
                .build();


    }

    @Override
    public ResponseMessage delete(String shortCode) {
        if(shortUrlRepository.existsShortUrlByShortCode(shortCode) == false) {
            return ResponseMessage.builder()
                    .type("error")
                    .message("Short URL not found.")
                    .httpStatus(HttpStatus.NOT_FOUND)
                    .build();
        }
        shortUrlRepository.deleteByShortCode(shortCode);
        return ResponseMessage.builder()
                .type("success")
                .message("Short URL deleted successfully.")
                .httpStatus(HttpStatus.NO_CONTENT)
                .build();
    }

    @Override
    public ResponseMessage getStats(String shortCode) {
        if(shortUrlRepository.existsShortUrlByShortCode(shortCode) == false) {
            return ResponseMessage.builder()
                    .type("error")
                    .message("Short URL not found.")
                    .httpStatus(HttpStatus.NOT_FOUND)
                    .build();
        }
        ShortUrl shortUrl = shortUrlRepository.findByShortCode(shortCode);
        return ResponseMessage.builder()
                .type("success")
                .object(shortUrl)
                .httpStatus(HttpStatus.OK)
                .build();
    }

    public ShortUrlModel shortUrlToModel(ShortUrl shortUrl){
        return ShortUrlModel.builder()
                .id(shortUrl.getId())
                .url(shortUrl.getUrl())
                .shortCode(shortUrl.getShortCode())
                .createdAt(shortUrl.getCreatedAt())
                .updatedAt(shortUrl.getUpdatedAt())
                .build();
    }
}
