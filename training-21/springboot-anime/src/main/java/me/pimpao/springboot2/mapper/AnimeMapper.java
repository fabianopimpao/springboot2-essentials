package me.pimpao.springboot2.mapper;

import me.pimpao.springboot2.model.Anime;
import me.pimpao.springboot2.request.AnimePostRequestBody;
import me.pimpao.springboot2.request.AnimePutRequestBody;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AnimeMapper {

    AnimeMapper INSTANCE = Mappers.getMapper(AnimeMapper.class);

    Anime toAnime(AnimePostRequestBody animePostRequestBody);
    Anime toAnime(AnimePutRequestBody animePutRequestBody);
}
