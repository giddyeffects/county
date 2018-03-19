/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Project;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 *
 * @author user
 */
@Entity
@Table(name = "projects", catalog = "ooproject", schema = "")
@NamedQueries({
    @NamedQuery(name = "Projects.findAll", query = "SELECT p FROM Projects p"),
    @NamedQuery(name = "Projects.findById", query = "SELECT p FROM Projects p WHERE p.id = :id"),
    @NamedQuery(name = "Projects.findByUserId", query = "SELECT p FROM Projects p WHERE p.userId = :userId"),
    @NamedQuery(name = "Projects.findByCountyId", query = "SELECT p FROM Projects p WHERE p.countyId = :countyId"),
    @NamedQuery(name = "Projects.findByTitle", query = "SELECT p FROM Projects p WHERE p.title = :title"),
    @NamedQuery(name = "Projects.findByManager", query = "SELECT p FROM Projects p WHERE p.manager = :manager"),
    @NamedQuery(name = "Projects.findByActivities", query = "SELECT p FROM Projects p WHERE p.activities = :activities"),
    @NamedQuery(name = "Projects.findByStartDate", query = "SELECT p FROM Projects p WHERE p.startDate = :startDate"),
    @NamedQuery(name = "Projects.findByEndDate", query = "SELECT p FROM Projects p WHERE p.endDate = :endDate"),
    @NamedQuery(name = "Projects.findByBudget", query = "SELECT p FROM Projects p WHERE p.budget = :budget"),
    @NamedQuery(name = "Projects.findByActualCost", query = "SELECT p FROM Projects p WHERE p.actualCost = :actualCost"),
    @NamedQuery(name = "Projects.findByPersonnel", query = "SELECT p FROM Projects p WHERE p.personnel = :personnel"),
    @NamedQuery(name = "Projects.findByProgress", query = "SELECT p FROM Projects p WHERE p.progress = :progress")})
public class Projects implements Serializable {

    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "user_id")
    private int userId;
    @Basic(optional = false)
    @Column(name = "county_id")
    private int countyId;
    @Basic(optional = false)
    @Column(name = "title")
    private String title;
    @Basic(optional = false)
    @Column(name = "manager")
    private String manager;
    @Basic(optional = false)
    @Column(name = "activities")
    private int activities;
    @Basic(optional = false)
    @Column(name = "start_date")
    @Temporal(TemporalType.DATE)
    private Date startDate;
    @Basic(optional = false)
    @Column(name = "end_date")
    @Temporal(TemporalType.DATE)
    private Date endDate;
    @Basic(optional = false)
    @Column(name = "budget")
    private String budget;
    @Basic(optional = false)
    @Column(name = "actual_cost")
    private String actualCost;
    @Basic(optional = false)
    @Column(name = "personnel")
    private int personnel;
    @Basic(optional = false)
    @Column(name = "progress")
    private int progress;

    public Projects() {
    }

    public Projects(Integer id) {
        this.id = id;
    }

    public Projects(Integer id, int userId, int countyId, String title, String manager, int activities, Date startDate, Date endDate, String budget, String actualCost, int personnel, int progress) {
        this.id = id;
        this.userId = userId;
        this.countyId = countyId;
        this.title = title;
        this.manager = manager;
        this.activities = activities;
        this.startDate = startDate;
        this.endDate = endDate;
        this.budget = budget;
        this.actualCost = actualCost;
        this.personnel = personnel;
        this.progress = progress;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        Integer oldId = this.id;
        this.id = id;
        changeSupport.firePropertyChange("id", oldId, id);
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        int oldUserId = this.userId;
        this.userId = userId;
        changeSupport.firePropertyChange("userId", oldUserId, userId);
    }

    public int getCountyId() {
        return countyId;
    }

    public void setCountyId(int countyId) {
        int oldCountyId = this.countyId;
        this.countyId = countyId;
        changeSupport.firePropertyChange("countyId", oldCountyId, countyId);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        String oldTitle = this.title;
        this.title = title;
        changeSupport.firePropertyChange("title", oldTitle, title);
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        String oldManager = this.manager;
        this.manager = manager;
        changeSupport.firePropertyChange("manager", oldManager, manager);
    }

    public int getActivities() {
        return activities;
    }

    public void setActivities(int activities) {
        int oldActivities = this.activities;
        this.activities = activities;
        changeSupport.firePropertyChange("activities", oldActivities, activities);
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        Date oldStartDate = this.startDate;
        this.startDate = startDate;
        changeSupport.firePropertyChange("startDate", oldStartDate, startDate);
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        Date oldEndDate = this.endDate;
        this.endDate = endDate;
        changeSupport.firePropertyChange("endDate", oldEndDate, endDate);
    }

    public String getBudget() {
        return budget;
    }

    public void setBudget(String budget) {
        String oldBudget = this.budget;
        this.budget = budget;
        changeSupport.firePropertyChange("budget", oldBudget, budget);
    }

    public String getActualCost() {
        return actualCost;
    }

    public void setActualCost(String actualCost) {
        String oldActualCost = this.actualCost;
        this.actualCost = actualCost;
        changeSupport.firePropertyChange("actualCost", oldActualCost, actualCost);
    }

    public int getPersonnel() {
        return personnel;
    }

    public void setPersonnel(int personnel) {
        int oldPersonnel = this.personnel;
        this.personnel = personnel;
        changeSupport.firePropertyChange("personnel", oldPersonnel, personnel);
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        int oldProgress = this.progress;
        this.progress = progress;
        changeSupport.firePropertyChange("progress", oldProgress, progress);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Projects)) {
            return false;
        }
        Projects other = (Projects) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Project.Projects[ id=" + id + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}
