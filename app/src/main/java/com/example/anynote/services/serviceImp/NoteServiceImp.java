package com.example.anynote.services.serviceImp;

import com.example.anynote.dto.NoteQueryParameter;
import com.example.anynote.models.NoteModel;
import com.example.anynote.services.INoteService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class NoteServiceImp implements INoteService {
    @Override
    public NoteModel Edit(NoteModel model) {
        model.setTime(new Date().getTime());

        if (model.getId() != null && model.getId() > 0) {
            long result_id = model.save();
            if (result_id > 0)
                return model;
        } else {
            long id = model.save();
            if (id > 0) {
                model.setId(id);
                return model;
            }
        }
        return null;
    }

    @Override
    public NoteModel GetById(Long Id) {
        NoteModel result = NoteModel.findById(NoteModel.class, Id);
        if (result == null)
            return null;
        return result;
    }

    @Override
    public boolean Del(Long Id) {
        NoteModel model = GetById(Id);
        if (model == null)
            return false;
        if (model.delete())
            return true;
        return false;
    }

    @Override
    public List<NoteModel> QueryList(NoteQueryParameter request) {
        StringBuffer queryString = new StringBuffer();
        StringBuffer OrString = new StringBuffer();
        queryString.append("1=1 ");
        ArrayList<String> args = new ArrayList<>();

        if (request.getContextSearch() != null) {
            OrString.append("Context LIKE ? OR ");
            args.add("%" + request.getContextSearch() + "%");
        }
        if (request.getContextSearch() != null) {
            OrString.append("Key LIKE ? OR ");
            args.add("%" + request.getKeySearch() + "%");
        }
        if (request.getTypeSearch() != null) {
            OrString.append("Type LIKE ? OR ");
            args.add("%" + request.getTypeSearch() + "%");
        }
        if (OrString.length() > 0) {
            queryString.append("AND (");
            queryString.append(OrString.substring(0, OrString.length() - 3));
            queryString.append(") ");
        }

        if (request.getStartTime() != null) {
            queryString.append("AND CreateTime >= ? ");
            args.add(request.getStartTime().toString());
        }
        if (request.getEndTime() != null) {
            queryString.append("AND CreateTime < ? ");
            args.add(request.getEndTime().toString());
        }

        String orderby = "time DESC ";
        if (request.getOrderBy() != null)
            orderby = request.getOrderBy();
        int pageIndex = 1;
        int pageSize = 10;
        if (request.getPageIndex() != null && request.getPageIndex() > 0)
            pageIndex = request.getPageIndex();
        if (request.getPageSize() != null && request.getPageSize() > 0)
            pageSize = request.getPageSize();
        String limit = "" + (pageIndex - 1) * pageSize + "," + pageSize;
        List<NoteModel> result_list = NoteModel.find(NoteModel.class, queryString.toString(), args.toArray(new String[args.size()]), null, orderby, limit);
        return result_list;
    }

    @Override
    public long QueryCount(NoteQueryParameter request) {
        StringBuffer queryString = new StringBuffer();
        StringBuffer OrString = new StringBuffer();
        queryString.append("1=1 ");
        ArrayList<String> args = new ArrayList<>();

        if (request.getContextSearch() != null) {
            OrString.append("Context LIKE ? OR ");
            args.add("%" + request.getContextSearch() + "%");
        }
        if (request.getContextSearch() != null) {
            OrString.append("Key LIKE ? OR ");
            args.add("%" + request.getKeySearch() + "%");
        }
        if (request.getTypeSearch() != null) {
            OrString.append("Type LIKE ? OR ");
            args.add("%" + request.getTypeSearch() + "%");
        }
        if (OrString.length() > 0) {
            queryString.append("(");
            queryString.append(OrString.substring(0, OrString.length() - 3));
            queryString.append(") ");
        }

        if (request.getStartTime() != null) {
            queryString.append("AND CreateTime >= ? ");
            args.add(request.getStartTime().toString());
        }
        if (request.getEndTime() != null) {
            queryString.append("AND CreateTime < ? ");
            args.add(request.getEndTime().toString());
        }

        long count = NoteModel.count(NoteModel.class, queryString.toString(), args.toArray(new String[args.size()]));
        return count;
    }

}
