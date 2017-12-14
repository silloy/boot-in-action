package com.zj.services;

import com.zj.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: SuShaohua
 * Date: 2017/12/11
 * Time: 20:53
 * CopyRight: Zhouji
 */
public interface ReadingListRepository extends JpaRepository<Book, Long> {
    List<Book> findByReader(String reader);
}
