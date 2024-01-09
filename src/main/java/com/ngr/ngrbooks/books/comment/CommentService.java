package com.ngr.ngrbooks.books.comment;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    public List<Comment> getCommentsByBookId(Long id) {
        return commentRepository.findByBookId(id);
    }

    public void addComment(Comment comment) {
        commentRepository.save(comment);
    }
}
