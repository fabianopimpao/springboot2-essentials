package com.pimpao.springboot2.mapper;

import com.pimpao.springboot2.domain.Anime;
import com.pimpao.springboot2.request.AnimePostRequestBody;
import com.pimpao.springboot2.request.AnimePutRequestBody;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AnimeMapper {

    AnimeMapper INSTANCE = Mappers.getMapper(AnimeMapper.class);

    Anime toAnime(AnimePostRequestBody animePostRequestBody);
    Anime toAnime(AnimePutRequestBody animePutRequestBody);
}
