package net.vedp.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Named;

import net.vedp.beans.util.JsfUtil;
import net.vedp.dao.JournalFacade;
import net.vedp.entities.Journal;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;

/**
 * 
 * @author MOHAMMED BOUNAGA
 * 
 * github.com/medbounaga
 */

@Named(value = "journalController")
@ViewScoped
public class JournalController implements Serializable {

    @Inject
    private JournalFacade journalFacade;
    private List<Journal> paymentJournals;
    private List<Journal> journals;
    private List<Journal> filteredJournals;
    private Journal journal;
    private String journalId;
    private List<String> journalTypes;
    private String currentPage = "/pages/journal/List.xhtml";
    
    @PostConstruct
    public void init(){
        journalTypes = new ArrayList<>();
        journalTypes.add("Sale"); 
        journalTypes.add("Purchase"); 
        journalTypes.add("Cash"); 
        journalTypes.add("Bank");  
    }

    public void viewJournal() {
        if (journalId != null && JsfUtil.isNumeric(journalId)) {
            Integer id = Integer.valueOf(journalId);
            journal = journalFacade.find(id);
            if (journal != null) {
                journals = journalFacade.findAll();
                currentPage = "/pages/journal/View.xhtml";
                return;
            }
        }

        journals = journalFacade.findAll();
        currentPage = "/pages/journal/List.xhtml";
    }
    
    
    
    
    
    
    
    
    public void prepareCreateJournal() {
        journal = new Journal();
        journal.setActive(Boolean.TRUE);
        currentPage = "/pages/journal/Create.xhtml";
    }
    
    public void deleteJournal(Integer id) {
        if (journalExist(id)) {
            try {
                journalFacade.remove(journal);
            } catch (Exception e) {
                System.out.println("Error Delete: "+e.getMessage());
                JsfUtil.addWarningMessageDialog("InvalidAction", "ErrorDelete3");
                return;
            }
         
            JsfUtil.addSuccessMessage("ItemDeleted");
            currentPage = "/pages/journal/List.xhtml";
            journals.remove(journal);
            journal = null;
            
        } else {
            JsfUtil.addWarningMessageDialog("InvalidAction", "ErrorDelete");
        }
    }
    
    public void cancelEditJournal(Integer id) {
        if (journalExist(id)) {
            currentPage = "/pages/journal/View.xhtml";
        }
    }
    
     public void updateJournal(Integer id) {
        if (journalExistTwo(id)) {
            journal = journalFacade.update(journal);
            journals = journalFacade.findAll();
            currentPage = "/pages/journal/View.xhtml";
        }
    }
     
    private boolean journalExistTwo(Integer id) {
        if (id != null) {
            Journal journal = journalFacade.find(id);
            if (journal == null) {
                JsfUtil.addWarningMessage("ItemDoesNotExist");
                journals = null;
                currentPage = "/pages/journal/List.xhtml";
                return false;
            } else {
                return true;
            }

        } else {
            return false;
        }
    }
    
    
    
    
    
    
    
    

    public void createJournal() {
        if (journal != null) {
            journal = journalFacade.create(journal);
            journals = journalFacade.findAll();
            currentPage = "/pages/journal/View.xhtml";
        }
    }

    public void prepareEditJournal(Integer id) {
        if (journalExist(id)) {
            currentPage = "/pages/journal/Edit.xhtml";
        }
    }


    private boolean journalExist(Integer id) {
        if (id != null) {
            journal = journalFacade.find(id);
            if (journal == null) {
                JsfUtil.addWarningMessage("ItemDoesNotExist");
                journals = null;
                currentPage = "/pages/journal/List.xhtml";
                return false;
            } else {
                return true;
            }

        } else {
            return false;
        }
    }

    public void prepareView() {

        if (journal != null) {
            if (journalExist(journal.getId())) {
                currentPage = "/pages/journal/View.xhtml";
            }
        }
    }

    public void showJournalList() {
        journal = null;
        currentPage = "/pages/journal/List.xhtml";
    }

    public void showJournalForm() {
        if (journals.size() > 0) {
            journal = journals.get(0);
            currentPage = "/pages/journal/View.xhtml";
        }
    }

    public int getJournalIndex() {
        if (journals != null && journal != null) {
            return journals.indexOf(journal) + 1;
        }
        return 0;
    }

    public void nextJournal() {
        if (journals.indexOf(journal) == (journals.size() - 1)) {
            journal = journals.get(0);
        } else {
            journal = journals.get(journals.indexOf(journal) + 1);
        }
    }

    public void previousJournal() {
        if (journals.indexOf(journal) == 0) {
            journal = journals.get(journals.size() - 1);
        } else {
            journal = journals.get(journals.indexOf(journal) - 1);
        }
    }

    public List<Journal> getPaymentJournals() {

        paymentJournals = journalFacade.findJournalByType("Cash");
        paymentJournals.addAll(journalFacade.findJournalByType("Bank"));
        return paymentJournals;

    }

    public List<Journal> getInvoiceJournals() {

        paymentJournals = journalFacade.findByName("Customer Invoices");
        return paymentJournals;

    }

    public List<Journal> getBillJournals() {

        paymentJournals = journalFacade.findByName("Vendor Bills");
        return paymentJournals;

    }

    public List<Journal> getJournals() {
        if (journals == null) {
            journals = journalFacade.findAll();
        }
        return journals;
    }

    public void setJournals(List<Journal> journals) {
        this.journals = journals;
    }

    public List<Journal> getFilteredJournals() {
        return filteredJournals;
    }

    public void setFilteredJournals(List<Journal> filteredJournals) {
        this.filteredJournals = filteredJournals;
    }

    public Journal getJournal() {
        return journal;
    }

    public void setJournal(Journal journal) {
        this.journal = journal;
    }

    public String getJournalId() {
        return journalId;
    }

    public void setJournalId(String journalId) {
        this.journalId = journalId;
    }

    public String getPage() {
        return currentPage;
    }

    public void setPage(String currentPage) {
        this.currentPage = currentPage;
    }

    public List<String> getJournalTypes() {
        return journalTypes;
    }

    public void setJournalTypes(List<String> journalTypes) {
        this.journalTypes = journalTypes;
    }

}
