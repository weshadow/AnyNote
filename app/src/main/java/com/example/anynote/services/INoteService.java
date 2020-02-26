package com.example.anynote.services;

import com.example.anynote.dto.NoteQueryParameter;
import com.example.anynote.models.NoteModel;

import java.util.List;

public interface INoteService {
    /**
     * 编辑
     *
     * @param model
     * @return
     */
    NoteModel Edit(NoteModel model);

    /**
     * 获取一条文章
     *
     * @param Id
     * @return
     */
    NoteModel GetById(Long Id);

    /**
     * 删除文章
     *
     * @param Id
     * @return
     */
    boolean Del(Long Id);

    /**
     * 获取文章列表
     *
     * @return
     */
    List<NoteModel> QueryList(NoteQueryParameter request);

    /**
     * 获取文章列表总条数
     *
     * @return
     */
    long QueryCount(NoteQueryParameter request);
}
