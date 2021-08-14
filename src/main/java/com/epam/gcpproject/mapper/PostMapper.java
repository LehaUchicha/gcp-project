package com.epam.gcpproject.mapper;

import com.epam.gcpproject.model.domain.Post;
import com.epam.gcpproject.model.dto.PostDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PostMapper {

    Post toEntity(PostDto dto);

    PostDto toDto(Post post);
}
