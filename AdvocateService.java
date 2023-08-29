package com.advocate.service;
import com.advocate.dao.AdvocateDAO;
import com.advocate.model.Advocate;
import java.util.List;
public class AdvocateService {
    private AdvocateDAO advocateDAO;
    public AdvocateService() {
        advocateDAO = new AdvocateDAO();
    }
    public boolean addAdvocate(Advocate advocate) {
        return advocateDAO.addAdvocate(advocate);
    }
    public Advocate getAdvocateById(int advocateId) {
        return advocateDAO.getAdvocateById(advocateId);
    }
    public List<Advocate> getAllAdvocates() {
        return advocateDAO.getAllAdvocates();
    }
    public boolean updateAdvocate(Advocate advocate) {
        return advocateDAO.updateAdvocate(advocate);
    }
    public boolean deleteAdvocate(int advocateId) {
        return advocateDAO.deleteAdvocate(advocateId);
    }
}

