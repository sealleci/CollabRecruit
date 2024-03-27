package com.rai.dao.impl;

import com.rai.dao.DeliveryDao;
import com.rai.domain.po.Delivery;
import com.rai.domain.vo.Page;
import com.rai.utils.PageUtil;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DeliveryDaoImpl extends GenericBaseDao implements DeliveryDao {

    @Override
    public Delivery findById(Integer id) {
        Delivery delivery = null;
        try {
            this.getConnection();
            String sql = "select * from delivery where did = ? ;";
            this.executeQuery(sql, id);
            if (rs != null && rs.next()) {
                delivery = new Delivery(
                        rs.getInt("did"), rs.getInt("reid")
                        , rs.getInt("userid"), rs.getInt("state"));
            }
            this.closeAll();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

        return delivery;
    }

    @Override
    public Delivery findByReIdAndUserId(int reId, int userId) {
        Delivery delivery = null;
        try {
            this.getConnection();
            String sql = "select * from delivery where reid = ? and userid = ?;";
            this.executeQuery(sql, reId, userId);
            if (rs != null && rs.next()) {
                delivery = new Delivery(
                        rs.getInt("did"), rs.getInt("reid")
                        , rs.getInt("userid"), rs.getInt("state"));
            }
            this.closeAll();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

        return delivery;
    }

    @Override
    public List<Delivery> findAll() {
        List<Delivery> deliverys = null;
        try {
            this.getConnection();
            String sql = "select * from delivery ;";
            this.executeQuery(sql);
            if (rs != null) {
                deliverys = new ArrayList<Delivery>();
                while (rs.next()) {
                    Delivery delivery = new Delivery(
                            rs.getInt("did"), rs.getInt("reid")
                            , rs.getInt("userid"), rs.getInt("state"));
                    deliverys.add(delivery);
                }
            }
            this.closeAll();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

        return deliverys;
    }

    @Override
    public List<Delivery> findBySQL(String sql, Object... params) {
        List<Delivery> deliverys = null;
        try {
            this.getConnection();
            this.executeQuery(sql, params);
            if (rs != null) {
                deliverys = new ArrayList<Delivery>();
                while (rs.next()) {
                    Delivery delivery = new Delivery(
                            rs.getInt("did"), rs.getInt("reid")
                            , rs.getInt("userid"), rs.getInt("state"));
                    deliverys.add(delivery);
                }
            }
            this.closeAll();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

        return deliverys;
    }

    @Override
    public int insert(Delivery entity) {
        int res = -1;
        try {
            this.getConnection();
            String sql = "insert into delivery values(null, ?, ?, ?) ;";
            this.executeUpdate(sql, entity.getReid()
                    , entity.getUserid(), entity.getState());
            res = result;
            this.closeAll();
        } catch (SQLException e) {
            e.printStackTrace();
            return res;
        }

        return res;
    }

    @Override
    public int delete(Delivery entity) {
        return this.delete(entity.getDid());
    }

    @Override
    public int delete(Integer id) {
        int res = -1;
        try {
            this.getConnection();
            String sql = "delete from delivery where did = ? ;";
            this.executeUpdate(sql, id);
            res = result;
            this.closeAll();
        } catch (SQLException e) {
            e.printStackTrace();
            return res;
        }

        return res;
    }

    @Override
    public int update(Delivery entity) {
        int res = -1;
        try {
            this.getConnection();
            String sql = "update delivery set reid = ?, userid = ?, state = ? "
                    + " where did = ? ;";
            this.executeUpdate(sql, entity.getReid(), entity.getUserid()
                    , entity.getState(), entity.getDid());
            res = result;
            this.closeAll();
        } catch (SQLException e) {
            e.printStackTrace();
            return res;
        }

        return res;
    }

    @Override
    public int getTotalRows(String sql) {
        try {
            this.getConnection();
            this.executeQuery(sql);
            if (rs != null && rs.next())
                return rs.getInt(1);
            this.closeAll();
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }

        return -1;
    }

    @Override
    public Page findByPage(int currentPage) {
        Page page = new Page();
        String sql = "select count(*) from delivery ;";
        int totalRows = this.getTotalRows(sql);
        page.setTotalRows(totalRows);
        if (totalRows == -1) {
            totalRows = 0;
        }

        page.setPageSize(PageUtil.pageSize);
        int totalPages = (int) Math.ceil(page.getTotalRows() * 1.0 / page.getPageSize());
        page.setTotalPages(totalPages);
        page.setCurrentPage(currentPage);

        int beginPos = (page.getCurrentPage() - 1) * page.getPageSize();
        String sql2 = "select * from delivery limit ?,? ;";
        try {
            page.setData(this.findBySQL(sql2, beginPos, page.getPageSize()));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return page;
    }

    @Override
    public Page findByPageByUserId(int currentPage, Integer id) {
        Page page = new Page();
        String sql = String.format("select count(*) from delivery where userid = %d ;", id);
        int totalRows = this.getTotalRows(sql);
        page.setTotalRows(totalRows);
        if (totalRows == -1) {
            totalRows = 0;
        }

        page.setPageSize(PageUtil.pageSize);
        int totalPages = (int) Math.ceil(page.getTotalRows() * 1.0 / page.getPageSize());
        page.setTotalPages(totalPages);
        page.setCurrentPage(currentPage);

        int beginPos = (page.getCurrentPage() - 1) * page.getPageSize();
        String sql2 = "select * from delivery where userid=? limit ?,? ;";
        try {
            page.setData(this.findBySQL(sql2, id, beginPos, page.getPageSize()));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return page;
    }

    @Override
    public Page findByPageByEnterId(int currentPage, Integer id) {
        Page page = new Page();
        String sql = String.format("select count(*) from delivery d,enterpriseinfo e,reinformation re where e.eid = %d and e.eid=re.eid and d.reid=re.reid ;", id);
        int totalRows = this.getTotalRows(sql);
        page.setTotalRows(totalRows);
        if (totalRows == -1) {
            totalRows = 0;
        }

        page.setPageSize(PageUtil.pageSize);
        int totalPages = (int) Math.ceil(page.getTotalRows() * 1.0 / page.getPageSize());
        page.setTotalPages(totalPages);
        page.setCurrentPage(currentPage);

        int beginPos = (page.getCurrentPage() - 1) * page.getPageSize();
        String sql2 = "select d.* "
                + "from delivery d,enterpriseinfo e,reinformation re "
                + "where e.eid=? and e.eid=re.eid and d.reid=re.reid "
                + "limit ?,?;";
        try {
            page.setData(this.findBySQL(sql2, id, beginPos, page.getPageSize()));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return page;
    }

    @Override
    public Page findByPageByReinfoId(int currentPage, Integer id) {
        Page page = new Page();
        String sql = String.format("select count(*) from delivery where reid = %d ;", id);
        int totalRows = this.getTotalRows(sql);
        page.setTotalRows(totalRows);
        if (totalRows == -1) {
            totalRows = 0;
        }

        page.setPageSize(PageUtil.pageSize);
        int totalPages = (int) Math.ceil(page.getTotalRows() * 1.0 / page.getPageSize());
        page.setTotalPages(totalPages);
        page.setCurrentPage(currentPage);

        int beginPos = (page.getCurrentPage() - 1) * page.getPageSize();
        String sql2 = "select * from delivery where reid=? limit ?,? ;";
        try {
            page.setData(this.findBySQL(sql2, id, beginPos, page.getPageSize()));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return page;
    }
}