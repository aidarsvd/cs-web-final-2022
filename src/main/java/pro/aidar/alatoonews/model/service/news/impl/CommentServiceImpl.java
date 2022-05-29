package pro.aidar.alatoonews.model.service.news.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pro.aidar.alatoonews.model.entity.news.Comment;
import pro.aidar.alatoonews.model.repository.news.CommentRepository;
import pro.aidar.alatoonews.model.service.news.CommentService;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    @Override
    public Optional<Comment> getById(Long id) {
        return commentRepository.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        commentRepository.deleteById(id);
    }
}
