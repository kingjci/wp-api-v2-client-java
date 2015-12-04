package com.afrozaar.wordpress.wpapi.v2;

import com.afrozaar.wordpress.wpapi.v2.model.Post;
import com.afrozaar.wordpress.wpapi.v2.model.builder.PostBuilder;
import com.afrozaar.wordpress.wpapi.v2.model.builder.TitleBuilder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.commons.lang.RandomStringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

public class WordpressMockGenerator implements IWordpressMockGenerator {
    private ObjectMapper objectMapper = new ObjectMapper();

    Random random = new Random(System.currentTimeMillis());

    private String getRandomTitle() {
        return RandomStringUtils.randomAlphabetic(random.nextInt(5));
    }

    private List<Post> getPosts(int numOfPosts) {
        List<Post> posts = new ArrayList<>();

        IntStream.range(0, numOfPosts).forEach(id -> {
            Post post = PostBuilder.aPost()
                    .withId(id)
                    .withTitle(TitleBuilder.aTitle().withRendered(getRandomTitle()).build())
                    .build();
            posts.add(post);
        });

        return posts;
    }

    @Override
    public String generatePosts(int numOfPosts) throws JsonProcessingException {
        return objectMapper.writeValueAsString(getPosts(numOfPosts));
    }

}
