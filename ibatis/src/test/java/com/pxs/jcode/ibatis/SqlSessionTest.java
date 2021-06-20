package com.pxs.jcode.ibatis;

import java.io.Reader;

import com.pxs.jcode.ibatis.domain.blog.Author;
import com.pxs.jcode.ibatis.domain.blog.Section;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.beans.factory.annotation.Autowired;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;


class SqlSessionTest extends BaseDataTest {

   @Autowired
    private static SqlSessionFactory sqlMapper;

    @BeforeAll
    static void setup() throws Exception {
      createBlogDataSource();
      final String resource = "com/pxs/jcode/ibatis/builder/MapperConfig.xml";
      final Reader reader = Resources.getResourceAsReader(resource);
      sqlMapper = new SqlSessionFactoryBuilder().build(reader);
    }

    @Test
    void shouldSelectOneAuthor() {
      try (SqlSession session = sqlMapper.openSession()) {
        Author author = session.selectOne(
            "com.pxs.jcode.ibatis.domain.blog.mappers.AuthorMapper.selectAuthor", new Author(101));
        assertEquals(101, author.getId());
        assertEquals(Section.NEWS, author.getFavouriteSection());
      }
    }
}